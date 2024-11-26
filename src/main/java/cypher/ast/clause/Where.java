package cypher.ast.clause;

import cypher.ast.ASTNode;
import cypher.ast.expression.Expression;

/**
 */
public class Where extends ASTNode {

    public Expression expression;

    public Where clone;

    public Where(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    public Where copy() {
        clone = new Where(expression.copy());
        return clone;
    }
}
