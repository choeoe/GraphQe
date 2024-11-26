package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.Collections;
import java.util.List;

public class GroupKey extends ProjOp {
    public ProjOp op;


    public int index;

    public GroupKey(ProjOp op, int index) {
        this.op = op;
        this.index = index;
    }

    @Override
    public String toString() {
        return op.toString();
    }

    @Override
    public ProjKind projKind() {
        return ProjKind.RENAME;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return Collections.singletonList(op);
    }

    @Override
    public Kind kind() {
        return null;
    }

    @Override
    public GraphAlgebra copy() {
        return new GroupKey(op, index);
    }

}
