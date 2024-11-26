package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.List;

public class Union implements GraphAlgebra {
    public GraphAlgebra left;
    public GraphAlgebra right;

    public Union(GraphAlgebra left, GraphAlgebra right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(List.of(new GraphAlgebra[]{left, right}));
    }

    @Override
    public Kind kind() {
        return Kind.UNION;
    }

    @Override
    public String toString() {
        return "%s \u222A %s".formatted(left,right);
    }
}
