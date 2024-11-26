package representations.graphalgebra;

import cypher.ast.expression.Expression;
import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.List;

public class Selection implements GraphAlgebra {
    private static final String sigma = "\u03C3";

    public GraphAlgebra relation;

    public Expression pred;

    public Selection(GraphAlgebra relation, Expression pred) {
        this.relation = relation;
        this.pred = pred;
    }

    public Selection(GraphAlgebra relation) {
        this.relation = relation;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(relation.subAlgebras());
    }

    @Override
    public Kind kind() {
        return Kind.SELECTION;
    }

    @Override
    public String toString() {
        return "%s{%s}(%s)".formatted(sigma, pred, relation);
    }
}
