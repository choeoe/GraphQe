package cypher.ast.expression;


/**
 */
public class PathExpression extends Expression {

    public PathExpression() {
        throw new RuntimeException("Unimplemented");
    }

    @Override
    public PathExpression copy() {
        return new PathExpression();
    }
}
