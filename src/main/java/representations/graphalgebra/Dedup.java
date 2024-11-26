package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dedup implements GraphAlgebra {
    private static final String delta = "\u03B4";

    public GraphAlgebra algebra;

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(Collections.singleton(algebra));
    }

    @Override
    public Kind kind() {
        return Kind.DEDUP;
    }

    public Dedup(GraphAlgebra algebra) {
        this.algebra = algebra;
    }

    @Override
    public String toString() {
        return "%s(%s)".formatted(delta, algebra);
    }
}
