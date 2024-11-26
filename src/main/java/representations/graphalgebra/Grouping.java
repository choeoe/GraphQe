package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.List;

public class Grouping extends Projection {
    public List<GroupKey> keys = new ArrayList<>();
    private final String gamma = "\u03B3";

    public Grouping(GraphAlgebra alge, List<GroupKey> groupByFields, List<ProjOp> returnFields) {
        super(alge, returnFields);
        keys = groupByFields;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return relation.subAlgebras();
    }

    @Override
    public Kind kind() {
        return Kind.PROJ;
    }

    @Override
    public String toString() {
        StringBuilder keys = new StringBuilder();
        for (Object item : this.keys) {
            keys.append(item).append(", ");
        }
        StringBuilder projs = new StringBuilder();
        for (Object item : this.projs) {
            projs.append(item).append(", ");
        }
        if (keys.length() > 0) {
            keys.setLength(keys.length() - 2);
        }
        if (projs.length() > 0) {
            projs.setLength(projs.length() - 2);
        }
        return "{%s}%s{%s}(%s)".formatted(keys.toString(),gamma,projs.toString(),relation.toString());
    }
}
