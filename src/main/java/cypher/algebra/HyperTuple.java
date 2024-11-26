package cypher.algebra;

import cypher.ast.expression.Expression;
import cypher.ast.expression.Variable;

public class HyperTuple {
    public Variable incoming;
    public Variable outgoing;
    public Variable var;

    public HyperTuple() {
    }

    public Variable getLeft() {
        Expression expr = incoming.getLinked();
        assert expr instanceof Variable;
        return ((Variable) expr);
    }

    public Variable getRight() {
        Expression expr = outgoing.getLinked();
        assert expr instanceof Variable;
        return ((Variable) expr);
    }
}
