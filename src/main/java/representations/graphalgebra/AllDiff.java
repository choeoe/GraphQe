package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.List;

public class AllDiff implements GraphAlgebra {
    public List<GraphAlgebra> bodies;

    public GraphAlgebra sub;

    public AllDiff(List<GraphAlgebra> bodies, GraphAlgebra sub) {
        this.bodies = bodies;
        this.sub = sub;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(sub.subAlgebras());
    }

    @Override
    public Kind kind() {
        return Kind.ALLDIFF;
    }
}
