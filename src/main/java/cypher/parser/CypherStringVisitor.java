package cypher.parser;

import cypher.ast.*;
import cypher.ast.clause.Clause;
import cypher.ast.clause.Unwind;
import cypher.ast.clause.match.Match;
import cypher.ast.clause.match.pattern.*;
import cypher.ast.clause.projection.Return;
import cypher.ast.clause.projection.With;
import cypher.ast.expression.*;

import java.util.Optional;

public class CypherStringVisitor {
    private final StringBuilder sb = new StringBuilder();

    public void visit(Statement statement) {
        visit(statement.query.part);
    }

    public void visit(QueryPart queryPart) {
        if (queryPart instanceof SingleQuery) {
            for (Clause clause : ((SingleQuery) queryPart).clauses) {
                visit(clause);
            }
        }
        if (queryPart instanceof Union union) {
            visit(union.lhs);
            sb.append(union.toString());
            visit(union.rhs);
        }
    }
    public void visit(Expression expr) {
        if (expr instanceof Variable) {
            visit(((Variable) expr));
        } else if (expr instanceof Property) {
            visit(((Property) expr));
        } else if (expr instanceof Binary) {
            visit(((Binary) expr));
        } else if (expr instanceof Literal) {
            visit(((Literal) expr));
        } else if (expr instanceof PatternElement element) {
            visit(element);
        } else if (expr instanceof LabelExpression l) {
            visit(l);
        } else if (expr instanceof RelTypeExpression r) {
            visit(r);
        } else if (expr instanceof ExistsFunction e) {
            sb.append(" EXISTS ");
            if (e.arg != null) {
                sb.append("( ");
                visit(e.arg);
                sb.append(" )");
            } else {
                sb.append("{ ");
                visit(e.match);
                sb.append(" }");
            }
        } else if (expr instanceof CaseExpression c) {
            sb.append(c.toString());
        } else {
            sb.append(expr.toString());
        }
    }


    public void visit(Literal literal) {
        if (literal instanceof Literal.Number) {
            visit(((Literal.Number) literal));
        } else if (literal instanceof Literal.Star star) {
            sb.append("*");
        } else
            sb.append(literal.stringValue);
    }

    public void visit(Literal.Number number) {
        if (number instanceof Literal.Integer) {
            visit(((Literal.Integer) number));
        } else
            sb.append(number.stringValue);
    }

    public void visit(Literal.Integer integer) {
        sb.append(integer.value);
    }

    public void visit(PatternElement element) {
        if (element instanceof NodePattern) {
            visit(((NodePattern) element));
        } else if (element instanceof RelationshipChain)
            visit(((RelationshipChain) element));
    }


    public void visit(Clause clause) {
        if (clause instanceof Match) {
            visit(((Match) clause));
        } else if (clause instanceof Return) {
            visit(((Return) clause));
        } else if (clause instanceof With) {
            visit(((With) clause));
        } else if (clause instanceof Unwind unwind) {
            visit(unwind);
        }
    }

    public void visit(Match match) {
        if (match.optional)
            sb.append("OPTIONAL ");
        sb.append("MATCH ");
        visit(match.pattern);
        if (match.where.isPresent() && match.where.get().expression != null) {
            sb.append(" WHERE ");
            visit(match.where.get().expression);
//            sb.append(match.where.get());
        }
        sb.append(" ");
    }

    public void visit(Unwind unwind) {
        sb.append("UNWIND ");
        visit(unwind.expr);
        sb.append(" AS ");
        visit(unwind.alias);
    }

    public void visit(With with) {
        sb.append(with);
    }
    public void visit(Pattern pattern) {
        for (int i = 0; i < pattern.patternParts.size(); i++) {
            visit(pattern.patternParts.get(i));
            if (i != pattern.patternParts.size() - 1) {
                sb.append(",");
            }
        }
    }

    public void visit(PatternPart part) {
        if (part instanceof NamedPatternPart) {
            visit(((NamedPatternPart) part));
        } else {
            visit(part.element);
        }
    }

    public void visit(NamedPatternPart part) {
        visit(part.variable);
        sb.append("=(");
        visit(part.element);
        sb.append(")");
    }

    public void visit(LabelExpression labelExpression) {
        sb.append(labelExpression.var);
        for (LabelName labelName : labelExpression.labelNames) {
            sb.append(":").append(labelName.name);
        }
    }

    public void visit(RelTypeExpression relTypeExpression) {
        sb.append(relTypeExpression.var);
        for (RelTypeName labelName : relTypeExpression.typeNames) {
            sb.append(":").append(labelName.name);
        }
    }

    public void visit(Return ret) {
        sb.append(ret);
    }

    public void visit(NodePattern node) {
        sb.append(node.toString());
    }

    public void visit(RelationshipChain chain) {
        visit(chain.element);
        visit(chain.relationship);
        visit(chain.rightNode);
    }

    public void visit(RelationshipPattern relation) {
        sb.append(relation.toString());
    }

    public void visit(Binary binary) {
        if (binary instanceof Binary.Equals) {
            visit(((Binary.Equals) binary));
        } else if (binary instanceof Binary.NotEquals) {
            visit(((Binary.NotEquals) binary));
        } else if (binary instanceof Binary.LessThan lessThan) {
            visit(lessThan);
        } else if (binary instanceof Binary.GreaterThan greaterThan) {
            visit(greaterThan);
        } else if (binary instanceof Binary.GreaterThanOrEqual greaterThanOrEqual) {
            visit(greaterThanOrEqual);
        } else if (binary instanceof Binary.LessThanOrEqual lessThanOrEqual) {
            visit(lessThanOrEqual);
        } else if (binary instanceof Binary.Or or) {
            visit(or);
        } else if (binary instanceof Binary.Add add) {
            visit(add);
        } else if (binary instanceof Binary.Divide divide) {
            visit(divide);
        } else if (binary instanceof Binary.Multiply mul) {
            visit(mul);
        } else if (binary instanceof Binary.And and) {
            visit(and);
        } else if (binary instanceof Binary.InvalidNotEquals invalidNotEquals) {
            visit(invalidNotEquals);
        } else {
            sb.append(binary.toString());
        }
    }

    public void visit(Binary.Equals equals) {
        visit(equals.lhs);
        sb.append("=");
        visit(equals.rhs);
        sb.append("\n");
    }

    public void visit(Binary.NotEquals notEquals) {
        visit(notEquals.lhs);
        sb.append("!=");
        visit(notEquals.rhs);
    }

    public void visit(Binary.InvalidNotEquals notEquals) {
        visit(notEquals.lhs);
        sb.append("!=");
        visit(notEquals.rhs);
    }

    public void visit(Binary.LessThan lessThan) {
        visit(lessThan.lhs);
        sb.append("<");
        visit(lessThan.rhs);
    }

    public void visit(Binary.LessThanOrEqual lessThan) {
        visit(lessThan.lhs);
        sb.append("<=");
        visit(lessThan.rhs);
    }

    public void visit(Binary.GreaterThan greaterThan) {
        visit(greaterThan.lhs);
        sb.append(">");
        visit(greaterThan.rhs);
    }

    public void visit(Binary.GreaterThanOrEqual greaterThan) {
        visit(greaterThan.lhs);
        sb.append(">=");
        visit(greaterThan.rhs);
    }

    public void visit(Binary.Or or) {
        visit(or.lhs);
        sb.append(" OR ");
        visit(or.rhs);
    }

    public void visit(Binary.Add add) {
        visit(add.lhs);
        sb.append("+");
        visit(add.rhs);
    }

    public void visit(Binary.Divide divide) {
        visit(divide.lhs);
        sb.append("/");
        visit(divide.rhs);
    }

    public void visit(Binary.Multiply mul) {
        visit(mul.lhs);
        sb.append("*");
        visit(mul.rhs);
    }

    public void visit(Binary.And and) {
        visit(and.lhs);
        sb.append(" AND ");
        visit(and.rhs);
    }

    public void visit(Property prop) {
        visit(prop.map);
        sb.append(".").append(prop.propertyKey.name);
    }

    public void visit(Variable var) {
        sb.append(var.name);
    }

    public String print() {
        System.out.println(sb);
        return sb.toString();
    }
    public String get() {
        return sb.toString();
    }
}
