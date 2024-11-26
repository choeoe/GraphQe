package cypher.translate;

import cypher.Pair;
import cypher.ast.clause.Where;
import cypher.ast.clause.match.Match;
import cypher.ast.clause.match.pattern.*;
import cypher.ast.clause.projection.*;
import cypher.ast.expression.*;
import cypher.err.CypherNormalizationError;
import representations.graphalgebra.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GraphPatternBuilder {
    private static List<GraphPatternPart> patternPartsInWHERE = new ArrayList<>();

    public static GraphPatternPart parseRelChain(RelationshipChain chain, GraphPattern pattern) {
        GraphPatternPart left = null;
        GraphPatternPart right;
        if (chain.element instanceof NodePattern node) {
            left = parseNodePattern(node, pattern);
        } else if (chain.element instanceof RelationshipChain rel) {
            left = parseRelChain(rel, pattern);
        }
        NodePattern node = chain.rightNode;
        right = parseNodePattern(node, pattern);
        RelationshipPattern relationship = chain.relationship;
        switch (relationship.direction) {
            case OUTGOING -> parseRelPattern(relationship, left, right, pattern);
            case INCOMING -> parseRelPattern(relationship, right, left, pattern);
            case BOTH -> parseRelPattern(relationship, null, null, pattern);
            default -> throw new IllegalStateException("Unexpected value: " + relationship.direction);
        }
        return right;
    }

    public static GraphPatternPart parseNodePattern(NodePattern node, GraphPattern pattern) {
        GraphPatternPart part;
        Variable nodeVar;
        if (node.variable.isPresent()) {
            nodeVar = node.variable.get();
            Node gn = new Node(Variable.getVariable(nodeVar.name));
            pattern.tmpVars.add(nodeVar);
            part = gn;
        } else {
            throw new CypherNormalizationError(CypherNormalizationError.STANDARDIZE_ERROR);
        }
        if (node.labels != null && node.labels.size() > 0) {
            pattern.addExpr(new LabelExpression(nodeVar, node.labels));
        }
        if (node.properties.isPresent()) {
            MapExpression mapExpression = node.properties.get();
            List<Pair<PropertyKeyName, Expression>> props = mapExpression.props;
            for (Pair<PropertyKeyName, Expression> prop : props) {
                pattern.addExpr(new Binary.Equals(new Property(nodeVar, prop.getLeft()), prop.getRight()));
            }
        }
        pattern.addPatternPart(part);
        return part;
    }

    private static void parseRelPattern(RelationshipPattern rel, GraphPatternPart left, GraphPatternPart right
            , GraphPattern pattern) {
        Variable relVar;
        if (rel.variable.isPresent()) {
            relVar = rel.variable.get();
            pattern.tmpVars.add(relVar);
            Expand gr = new Expand(relVar);
            gr.start = left;
            gr.end = right;
            pattern.addPatternPart(gr);
        } else {
            throw new CypherNormalizationError(CypherNormalizationError.STANDARDIZE_ERROR);
        }
        if (rel.types != null && rel.types.size() > 0) {
//            part.labelConstraints = new RelTypeExpression(relVar, rel.types);
            pattern.addExpr(new RelTypeExpression(relVar, rel.types));
        }
        if (rel.properties.isPresent()) {
            MapExpression mapExpression = rel.properties.get();
            List<Pair<PropertyKeyName, Expression>> props = mapExpression.props;
            for (Pair<PropertyKeyName, Expression> prop : props) {
                pattern.addExpr(new Binary.Equals(new Property(relVar, prop.getLeft()), prop.getRight()));
            }
        }

    }

    public static void assembleMATCH(Match match, GraphPattern graphPattern) {
        Pattern pattern = match.pattern;
        for (PatternPart part : pattern.patternParts) {
            if (part instanceof NamedPatternPart namedPatternPart) {
                Variable patternVar = namedPatternPart.variable;
                graphPattern.addSumBound(patternVar.name);
                Property start = new Property(patternVar, new PropertyKeyName("start"));
                Property end = new Property(patternVar, new PropertyKeyName("end"));
                Binary.Equals equalsStart = new Binary.Equals(
                        start,
                        patternVar.getSubVars().get(patternVar.getSubVars().size() - 1)
                );
                Binary.Equals equalsEnd = new Binary.Equals(
                        end,
                        patternVar.getSubVars().get(0)
                );
                graphPattern.addExpr(equalsStart);
                graphPattern.addExpr(equalsEnd);
            }
            if (part.element instanceof RelationshipChain relationshipChain) {
                parseRelChain(relationshipChain, graphPattern);
            } else if (part.element instanceof NodePattern node) {
                parseNodePattern(node, graphPattern);
            }
        }
        if (match.where.isPresent()) {
            Where where = match.where.get();
            Expression expr = where.expression;
            graphPattern.addExpr(expr);
        }
    }

    private static void generateLimit(Limit limit, GraphPattern pattern) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            String result = String.valueOf(engine.eval(limit.expression.toString()));
            for (Variable var : pattern.tmpVars) {
                FunctionName limitName = new FunctionName("#limit" + result);
                pattern.addExpr(new FunctionInvocation(limitName, false, Collections.singletonList(var)));
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void assembleRETURN(Return ret, GraphPattern graphPattern) {
        if (ret.limit.isPresent()) {
            Limit limit = ret.limit.get();
            generateLimit(limit, graphPattern);
        }
        if (ret.orderBy.isPresent()) {
            OrderBy orderBy = ret.orderBy.get();
        }
    }

    public static void assembleWITH(With with, GraphPattern graphPattern) {
        if (with.limit.isPresent()) {
            Limit limit = with.limit.get();
            generateLimit(limit, graphPattern);
        } else {
            List<ReturnItem> items = with.returnItems;
            for (ReturnItem item : items) {
                if (item.expression instanceof ListExpression lexpr) {
                    assert item instanceof ReturnItem.Aliased;
                    List<Expression> exprs = lexpr.exprs;
                    CypherList cypherList = new CypherList();
                    cypherList.var = ((ReturnItem.Aliased) item).alias;
                    ((ReturnItem.Aliased) item).alias.linked = cypherList;
                    for (Expression expr : exprs) {
                        List<Literal> row = new ArrayList<>();
                        cypherList.rows.add(row);
                        if (expr instanceof MapExpression map) {
                            for (Pair<PropertyKeyName, Expression> col : map.props) {
                                assert col.getRight() instanceof Literal;
                                if (!cypherList.colNames.contains(col.getLeft().name)) {
                                    cypherList.colNames.add(col.getLeft().name);
                                }
                                row.add(((Literal) col.getRight()));
                            }
                        }
                    }
                    graphPattern.lists.add(cypherList);
                }else if (item instanceof ReturnItem.Aliased aliased) {
                    if (aliased.expression instanceof Variable variable) {
                        aliased.alias.name = variable.name;
                    }else
                        aliased.alias.linked = aliased.expression;
                }
            }
        }
        if (with.where.isPresent()) {
            Where where = with.where.get();
            Expression expr = where.expression;
            graphPattern.addExpr(expr);
        }
    }

    private static PatternExpression assemblePattern(PatternElement element, GraphPattern pattern) {
        if (element instanceof NodePattern node) {
            parseNodePattern(node, pattern);
            PatternExpression expr = new PatternExpression(patternPartsInWHERE);
            patternPartsInWHERE.clear();
            return expr;

        } else if (element instanceof RelationshipChain chain) {
            parseRelChain(chain, pattern);
            PatternExpression expr = new PatternExpression(patternPartsInWHERE);
            patternPartsInWHERE.clear();
            return expr;
        }
        throw new RuntimeException("Assemble pattern error: " + element.getClass() + ": " + element);
    }
}
