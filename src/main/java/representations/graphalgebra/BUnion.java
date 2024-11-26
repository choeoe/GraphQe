package representations.graphalgebra;

import representations.GraphAlgebra;

public class BUnion extends Union{

    public BUnion(GraphAlgebra left, GraphAlgebra right) {
        super(left, right);
    }

    @Override
    public Kind kind() {
        return Kind.BAG_UNION;
    }

    @Override
    public String toString() {
        return "%s \u222A+ %s".formatted(left,right);
    }
}
