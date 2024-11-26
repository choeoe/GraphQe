package representations.graphalgebra;

import cypher.ast.expression.Expression;
import cypher.ast.expression.Variable;
import representations.GraphAlgebra;

import java.util.Collections;
import java.util.List;

public class Rename extends ProjOp{
    public Variable alias;

    public Rename(Expression expr, Variable alias) {
        this.expr = expr;
        this.alias = alias;
        alias.mkLink(expr);
        if (expr.isPattern()) {
            alias.kind = Variable.VarKind.PATTERN;
        } else if (expr.isProp()) {
            alias.kind = Variable.VarKind.PROJ;
        } else if (expr.isVar()) {
            alias.kind = Variable.VarKind.BASE;
        } else if (expr.isList()) {
            alias.kind = Variable.VarKind.LIST;
        } else {
            alias.kind = Variable.VarKind.EXPR;
        }
    }

    @Override
    public String toString() {
        return expr + "->" + alias;
    }

    @Override
    public ProjKind projKind() {
        return ProjKind.RENAME;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return Collections.singletonList(alias);
    }

    @Override
    public Kind kind() {
        return null;
    }

    @Override
    public GraphAlgebra copy() {
        return new Rename(expr.copy(), alias.copy());
    }

}
