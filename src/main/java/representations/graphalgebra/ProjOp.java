package representations.graphalgebra;

import cypher.ast.expression.Expression;
import representations.GraphAlgebra;

import java.util.Objects;

public abstract class ProjOp implements GraphAlgebra {
    public Expression expr;
    public enum ProjKind {
        EXPR,
        ATTR,
        RENAME,
        GROUPING_KEY
    }

    public abstract ProjKind projKind();

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that instanceof Rename rename) {
            return this.expr.equals(rename.alias);
        } else if (that instanceof ProjExpr projExpr) {
            return this.expr.equals(projExpr.expr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this instanceof Rename rename) {
            return Objects.hash(rename.alias);
        } else {
            return Objects.hash(expr);
        }
    }
}
