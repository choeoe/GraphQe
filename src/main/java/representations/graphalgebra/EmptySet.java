package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.List;

public class EmptySet implements GraphAlgebra {
    @Override
    public List<GraphAlgebra> subAlgebras() {
        return null;
    }

    @Override
    public Kind kind() {
        return Kind.EMPTY_SET;
    }
}
