package cypher.ast.clause;

import cypher.ast.expression.Expression;
import cypher.ast.expression.Variable;

public class Unwind extends Clause{
    public Expression expr;
    public Variable alias;

    public Unwind(Expression expr, Variable alias) {
        this.expr = expr;
        this.alias = alias;
    }

    @Override
    public Clause copy() {
        return new Unwind(expr.copy(), alias.copy());
    }
}
