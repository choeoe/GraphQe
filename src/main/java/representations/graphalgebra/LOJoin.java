package representations.graphalgebra;

import representations.GraphAlgebra;

public class LOJoin extends Join{

    public LOJoin(GraphAlgebra left, GraphAlgebra right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return left + " >◁ " + right;
    }

    @Override
    public Kind kind() {
        return Kind.LEFT_OUTER_JOIN;
    }
}
