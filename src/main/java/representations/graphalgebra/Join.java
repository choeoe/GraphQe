package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.List;

public class Join implements GraphAlgebra {

    public GraphAlgebra left;

    public GraphAlgebra right;

    public Join(GraphAlgebra left, GraphAlgebra right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + " ▷◁ " + right;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(List.of(new GraphAlgebra[]{left, right}));
    }

    @Override
    public Kind kind() {
        return Kind.NATURE_JOIN;
    }
}
