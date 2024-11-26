package representations.graphalgebra;

import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.List;

public class Projection implements GraphAlgebra {
    protected final String pi = "\u03C0";

    public GraphAlgebra relation;

    public List<ProjOp> projs = new ArrayList<>();

    private boolean outer = false;

    public Projection(GraphAlgebra relation) {
        this.relation = relation;
    }
    public Projection(GraphAlgebra relation, List<ProjOp> projs) {
        this.relation = relation;
        this.projs = projs;
    }
    public boolean isOuter() {
        return outer;
    }

    public void outer() {
        this.outer = true;
    }

    @Override
    public Projection copy() {
        Projection clone = new Projection(relation.copy());
        clone.projs = projs.stream().map(t -> (ProjOp)t.copy()).toList();
        clone.outer = outer;
        return clone;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(relation.subAlgebras());
    }

    @Override
    public Kind kind() {
        return Kind.PROJ;
    }

    @Override
    public String toString() {
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < projs.size(); i++) {
            if (i != 0) {
                items.append(",");
            }
            items.append(projs.get(i));
        }
        if (relation == null) {
            return "%s{%s}".formatted(pi,items.toString());
        }
        return "%s{%s}(%s)".formatted(pi,items.toString(),relation);
    }
}
