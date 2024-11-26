package cypher.ast.clause.projection;

import cypher.ast.ASTNode;
import cypher.ast.expression.Expression;

/**
 */
public class Limit extends ASTNode {

    public Expression expression;

    public Limit(final Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return " LIMIT "+ expression.toString();
    }

    public Limit copy() {
        return new Limit(expression.copy());
    }
}
