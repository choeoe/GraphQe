package cypher.ast.expression;

import cypher.Pair;
import cypher.ast.ASTNode;
import cypher.ast.clause.match.pattern.PatternElement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Expression extends ASTNode {
    public abstract Expression copy();

    public boolean isAggFuntion() {
        return this instanceof FunctionInvocation && ((FunctionInvocation) this).isAGG();
    }

    public boolean isAggExpression() {
        boolean aggArithmetic = false;
        if (this instanceof Binary.Subtract || this instanceof Binary.Add || this instanceof Binary.Divide
                || this instanceof Binary.Multiply) {
            Binary binary = ((Binary) this);
            aggArithmetic = binary.lhs.isAggExpression() || binary.rhs.isAggExpression();
        }
        return isAggFuntion() || aggArithmetic;
    }

    public boolean isPattern() {
        return this instanceof PatternElement;
    }

    public boolean isProp() {
        return this instanceof Property;
    }

    public boolean isVar() {
        return this instanceof Variable;
    }

    public boolean isList() {
        return this instanceof ListExpression || (this instanceof FunctionInvocation functionInvocation
                && functionInvocation.isCOLLECT());
    }

    public boolean isConstList() {
        return this instanceof ListExpression
                && ((ListExpression) this).exprs.stream().allMatch(t -> t instanceof MapExpression);
    }

    public boolean hasLinked() {
        return this instanceof Variable variable && variable.linked != null;
    }
    public List<String> getConstCols() {
        if (!this.isConstList()) {
            return null;
        }
        List<String> cols = new ArrayList<>();
        ListExpression list = ((ListExpression) this);
        List<Expression> exprs = list.exprs;
        MapExpression map = ((MapExpression) exprs.get(0));
        for (Pair<PropertyKeyName, Expression> col : map.props) {
            cols.add(col.getLeft().name);
        }
        return cols;
    }
}
