package cypher.algebra;

import cypher.Pair;
import cypher.algebra.normalizer.AlgebraNormalizer;
import cypher.ast.QueryPart;
import cypher.ast.SingleQuery;
import cypher.ast.Statement;
import cypher.ast.Union;
import cypher.ast.clause.Clause;
import cypher.ast.clause.Unwind;
import cypher.ast.clause.Where;
import cypher.ast.clause.match.Match;
import cypher.ast.clause.match.pattern.*;
import cypher.ast.clause.projection.*;
import cypher.ast.expression.*;
import cypher.parser.CypherStringVisitor;
import cypher.translate.CypherNormalizer;
import representations.GraphAlgebra;
import representations.graphalgebra.*;

import java.util.ArrayList;
import java.util.List;

public class GraphAlgebraBuilder {
    public Statement stmt;

    public GraphAlgebra graphAlgebra;
    public List<GraphAlgebra> subQueries = new ArrayList<>();

    public GraphAlgebraBuilder(Statement stmt) {
        this.stmt = stmt;
    }

    public GraphAlgebraBuilder() {
    }

    private GraphAlgebra lastOrderWithLIMIT = null;

    public GraphAlgebra build() {
        CypherStringVisitor visitor = new CypherStringVisitor();
        visitor.visit(stmt);
        CypherNormalizer.normalize(stmt, "x");
        graphAlgebra = buildQueryPart(stmt.query.part);
        System.out.println(graphAlgebra);
        graphAlgebra = AlgebraNormalizer.normalizeProj(graphAlgebra);
        System.out.println(graphAlgebra);
        return graphAlgebra;
    }

    public GraphAlgebra buildMatch(Match match) {
        GraphAlgebra pattern = buildPatternParts(match.pattern.patternParts);
        GraphAlgebra algebra = pattern;
        if (match.optional)
            algebra = new LOJoin(new EmptySet(), algebra);
        if (match.where.isPresent()) {
            algebra = wrapSelection(algebra, match.where.get());
        }
        return algebra;
    }

    public GraphAlgebra buildQueryPart(QueryPart part) {
        if (part instanceof SingleQuery singleQuery) {
            return buildSingle(singleQuery);
        } else if (part instanceof Union.Distinct union) {
            return new representations.graphalgebra.Union(buildQueryPart(union.lhs),
                    buildQueryPart(union.rhs));
        } else {
            Union.All all = ((Union.All) part);
            return new BUnion(buildQueryPart(all.lhs), buildQueryPart(all.rhs));
        }
    }

    private GraphAlgebra buildSingle(SingleQuery singleQuery) {
        GraphAlgebra algebra = null;
        for (Clause clause : singleQuery.clauses) {
            if (clause instanceof Match match) {
                GraphAlgebra pattern = buildPatternParts(match.pattern.patternParts);
                if (algebra == null) {
                    algebra = pattern;
                    if (match.optional)
                        algebra = new LOJoin(new EmptySet(), algebra);
                    if (match.where.isPresent()) {
                        algebra = wrapSelection(algebra, match.where.get());
                    }
                } else {
                    if (match.optional)
                        algebra = new LOJoin(algebra, pattern);
                    if (match.where.isPresent()) {
                        algebra = wrapSelection(algebra, match.where.get());
                    }
                    algebra = new Join(algebra, pattern);
                }
            }
            if (clause instanceof Unwind unwind) {
                algebra = buildUnwind(algebra, unwind);
            }
            if (clause instanceof ProjectionClause proj) {
                algebra = buildProj(algebra, proj);
            }
        }
        return algebra;
    }

    private GraphAlgebra buildUnwind(GraphAlgebra algebra, Unwind unwind) {
        Expression expr = unwind.expr;
        Variable alias = unwind.alias;
        return new UnwindAlge(algebra, expr, alias);
    }

    private boolean containsAgg(ProjectionClause proj) {
        List<ReturnItem> returnItems = proj.returnItems;
        for (ReturnItem item : returnItems) {
            item.expression.isAggExpression();
            return true;
        }
        return false;
    }

    private GraphAlgebra buildProj(GraphAlgebra alge, ProjectionClause proj) {
//        if (containsAgg(proj)) {
//            return buildGrouping(alge, proj);
//        }
        GraphAlgebra algebra;

        List<GroupKey> groupKey = new ArrayList<>();
        List<ProjOp> projOps = new ArrayList<>();
        boolean hasAGG = false;

        for (int i = 0; i < proj.returnItems.size(); i++) {
            ReturnItem returnItem = proj.returnItems.get(i);
            Expression expr = returnItem.expression;
            if (returnItem instanceof ReturnItem.Unaliased) {
                ProjExpr unaliased = new ProjExpr(expr);
                projOps.add(unaliased);
                if (expr.isAggExpression()) {
                    hasAGG = true;
                } else groupKey.add(new GroupKey(unaliased, i));
            } else {
                assert returnItem instanceof ReturnItem.Aliased;
                Variable var = ((ReturnItem.Aliased) returnItem).alias;
                Rename rename = new Rename(expr, var);
                projOps.add(rename);
                if (expr.isAggExpression()) {
                    hasAGG = true;
                } else  groupKey.add(new GroupKey(rename, i));
            }

        }
        if (hasAGG) {
            Grouping grouping = new Grouping(alge, groupKey, projOps);
            if (proj instanceof Return) {
                grouping.outer();
            }
            alge = grouping;
        } else {
            Projection projection = new Projection(alge, projOps);
            if (proj instanceof Return) {
                projection.outer();
            }
            alge = projection;
        }
        if (proj.distinct) {
            alge = new Dedup(alge);
        }
        if (proj.orderBy.isPresent()) {
            OrderBy orderBy = proj.orderBy.get();
            if (proj instanceof Return) {
                Sort sort = new Sort(alge);
                sort.sortItems = orderBy.sortItems;
                sort.outer();
                alge = sort;
            }
        }
        if (proj.limit.isPresent()) {
            if (proj instanceof Return) {
                Limit limit = proj.limit.get();
                alge = new Top(limit, alge);
            } else {
                Limit limit = proj.limit.get();
                GraphAlgebra clone = alge.copy();
                ((Projection) clone).outer();
                subQueries.add(new Top(limit, clone));
            }
        }
        if (proj instanceof With with) {
            if (with.where.isPresent()) {
                alge = wrapSelection(alge, with.where.get());
            }
        }
        return alge;
    }

    public GraphAlgebra wrapSelection(GraphAlgebra algebra, Where where) {
        return new Selection(algebra, where.expression);
    }

    private GraphAlgebra buildPatternParts(List<PatternPart> patternParts) {
        List<GraphAlgebra> algebras = new ArrayList<>();
        for (PatternPart patternPart : patternParts) {
            GraphAlgebra ele = buildPatternElement(patternPart.element);
            algebras.add(ele);
        }
        if (algebras.size() > 1) {
            return algebras.stream().reduce(Join::new).get();
        } else
            return algebras.get(0);
    }

    private GraphAlgebra buildPatternElement(PatternElement element) {
        if (element instanceof NodePattern node) {
            return buildNode(node);
        } else {
            RelationshipChain chain = ((RelationshipChain) element);
            return buildRelChain(chain);
        }
    }

    private Node buildNode(NodePattern node) {
        assert node.variable.isPresent();
        Variable var = node.variable.get();
        Node nodeAlgebra = new Node(var);
        var.kind = Variable.VarKind.NODE;
        if (node.properties.isPresent()) {
            MapExpression map = node.properties.get();
            nodeAlgebra.constraint = buildExprFromMapExpr(var, map);
        }
        if (!node.labels.isEmpty()) {
            for (LabelName label : node.labels) {
                nodeAlgebra.labels.add(label.name);
            }
        }
        return nodeAlgebra;
    }

    private Expression buildExprFromMapExpr(Variable var, MapExpression mapExpression) {
        List<Pair<PropertyKeyName, Expression>> props = mapExpression.props;
        List<Expression> exprs = new ArrayList<>();
        for (Pair<PropertyKeyName, Expression> prop : props) {
            Property property = new Property(var, prop.getLeft());
            exprs.add(new Binary.Equals(property, prop.getRight()));
        }
        if (exprs.size() > 1)
            return exprs.stream().reduce(Binary.And::new).get();
        else return exprs.get(0);
    }

    private GraphAlgebra buildRelChain(RelationshipChain chain) {
        assert chain.relationship.variable.isPresent();
        GraphAlgebra algebra = buildNode(chain.rightNode);
        while (true) {
            Variable var = chain.relationship.variable.get();
            var.kind = Variable.VarKind.NODE;
            PatternElement element = chain.element;
            NodePattern node;
            if (element instanceof RelationshipChain)
                node = ((RelationshipChain) element).rightNode;
            else
                node = ((NodePattern) element);
            Expand expand = new Expand(var, buildNode(node), buildNode(chain.rightNode), algebra);
            if (chain.relationship.direction == RelationshipPattern.SemanticDirection.OUTGOING) {
                expand.setDirection(Expand.Direction.OUT);
            } else if (chain.relationship.direction == RelationshipPattern.SemanticDirection.INCOMING)
                expand.setDirection(Expand.Direction.IN);
            else
                expand.setDirection(Expand.Direction.BOTH);
            if (chain.relationship.properties.isPresent()) {
                MapExpression map = chain.relationship.properties.get();
                expand.constraint = buildExprFromMapExpr(var, map);
            }
            if (!chain.relationship.types.isEmpty()) {
                for (RelTypeName type : chain.relationship.types) {
                    expand.labels.add(type.name);
                }
            }
            algebra = expand;
            if (element instanceof RelationshipChain chain1)
                chain = chain1;
            else
                break;
        }
        return algebra;
    }
}
