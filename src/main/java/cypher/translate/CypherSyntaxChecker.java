package cypher.translate;

import cypher.ast.QueryPart;
import cypher.ast.SingleQuery;
import cypher.ast.Statement;
import cypher.ast.Union;
import cypher.ast.clause.Clause;
import cypher.ast.clause.Where;
import cypher.ast.clause.match.Match;
import cypher.ast.clause.match.pattern.*;
import cypher.ast.expression.*;
import cypher.err.CypherSemanticError;

import java.util.ArrayList;
import java.util.List;

import static cypher.translate.CypherNormalizer.findInList;

public class CypherSyntaxChecker {
    public static void referenceCheck(Statement stmt) {
        referenceCheck(stmt.query.part);
    }

    private static void referenceCheck(QueryPart part) {
        if (part instanceof Union union) {
            referenceCheck(union.lhs);
            referenceCheck(union.rhs);
        } else if (part instanceof SingleQuery singleQuery) {
            referenceCheck(singleQuery);
        }
    }

    private static void referenceCheck(SingleQuery query) {
        for (Clause clause : query.clauses) {
            if (clause instanceof Match match) {
                referenceCheck(match);
            }
        }
    }

    private static void referenceCheck(Match match) {
        List<Variable> vars = new ArrayList<>();
        Where where = match.where.orElse(null);
        for (PatternPart part : match.pattern.patternParts) {
            PatternElement element = part.element;
            if (element instanceof NodePattern node) {
                if (node.variable.isPresent()) {
                    if (findInList(vars, node.variable.get()) == null) {
                        vars.add(node.variable.get());
                    }
                }
            } else if (element instanceof RelationshipChain chain) {
                while (true) {
                    NodePattern node = chain.rightNode;
                    if (node.variable.isPresent()) {
                        if (findInList(vars, node.variable.get()) == null) {
                            vars.add(node.variable.get());
                        }
                    }
                    RelationshipPattern rel = chain.relationship;
                    if (rel.variable.isPresent()) {
                        if (findInList(vars, rel.variable.get()) == null) {
                            vars.add(rel.variable.get());
                        }
                    }
                    if (chain.element instanceof RelationshipChain chain1) {
                        chain = chain1;
                    } else {
                        NodePattern n = ((NodePattern) chain.element);
                        if (n.variable.isPresent()) {
                            if (findInList(vars, n.variable.get()) == null) {
                                vars.add(n.variable.get());
                            }
                        }
                        break;
                    }
                }
            }
        }
        if (where != null) {
            checkWhereExpr(vars, where.expression);
        }
    }

    private static void checkWhereExpr(List<Variable> vars, Expression expr) {
        if (expr instanceof Variable var) {
            if (!vars.contains(var)) {
                throw new CypherSemanticError("Reference error by WHERE in clause");
            }
        } else if (expr instanceof Binary binary) {
            checkWhereExpr(vars, binary.lhs);
            checkWhereExpr(vars, binary.rhs);
        } else if (expr instanceof Unary unary) {
            checkWhereExpr(vars, unary.lhs);
        } else if (expr instanceof Property prop) {
            checkWhereExpr(vars, prop.map);
        } else if (expr instanceof FunctionInvocation functionInvocation) {
            for (Expression expression : functionInvocation.args) {
                checkWhereExpr(vars, expression);
            }
        } else if (expr instanceof ListExpression listExpression) {
            for (Expression expression : listExpression.exprs) {
                checkWhereExpr(vars, expression);
            }
        } else if (expr instanceof MapExpression mapExpression) {
            for (int i = 0; i < mapExpression.props.size(); i++) {
                checkWhereExpr(vars, mapExpression.props.get(i).getRight());
            }
        } else if (expr instanceof RelTypeExpression relTypeExpression) {
            checkWhereExpr(vars, relTypeExpression.var);
        } else if (expr instanceof LabelExpression labelExpression) {
            checkWhereExpr(vars, labelExpression.var);
        } else if (expr instanceof PatternElement element) {
            if (element instanceof NodePattern node) {
                node.variable.ifPresent(variable -> checkWhereExpr(vars, variable));
            } else if (element instanceof RelationshipChain chain) {
                while (true) {
                    NodePattern node = chain.rightNode;
                    node.variable.ifPresent(variable -> checkWhereExpr(vars, variable));
                    RelationshipPattern rel = chain.relationship;
                    rel.variable.ifPresent(variable -> checkWhereExpr(vars, variable));
                    if (chain.element instanceof RelationshipChain chain1) {
                        chain = chain1;
                    } else {
                        NodePattern n = ((NodePattern) chain.element);
                        n.variable.ifPresent(variable -> checkWhereExpr(vars, variable));
                        break;
                    }
                }
            }
        }
    }
}
