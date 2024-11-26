package cypher.ast.clause.projection;


import cypher.ast.ASTNode;
import cypher.ast.expression.Expression;

/**
 */
public class Skip extends ASTNode {

    public Expression expression;

    public Skip(final Expression expression) {
        this.expression = expression;
    }

    public Skip copy() {
        return new Skip(expression.copy());
    }
}
