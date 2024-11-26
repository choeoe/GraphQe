package representations.graphalgebra;

import cypher.ast.expression.Expression;
import representations.GraphAlgebra;

import java.util.List;

public class ProjExpr extends ProjOp{


    public ProjExpr(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        if (expr == null) {
            return "";
        }
        return expr.toString();
    }

    @Override
    public ProjKind projKind() {
        return ProjKind.EXPR;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return null;
    }

    @Override
    public Kind kind() {
        return Kind.PROJ_OP;
    }


}
