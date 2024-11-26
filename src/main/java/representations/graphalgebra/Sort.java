package representations.graphalgebra;

import cypher.ast.clause.projection.SortItem;
import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort implements GraphAlgebra {
    public GraphAlgebra algebra;
    private boolean outer = false;
    public List<SortItem> sortItems;

    private static final String tau = "\u03C4";

    public Sort(GraphAlgebra algebra) {
        this.algebra = algebra;
    }

    public boolean isOuter() {
        return outer;
    }

    public void outer() {
        this.outer = true;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(Collections.singleton(algebra));
    }

    @Override
    public Kind kind() {
        return Kind.SORT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sortItems.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(sortItems.get(i).algebra());
        }
        return "%s{%s}(%s)".formatted(tau, sb, algebra);
    }
}
