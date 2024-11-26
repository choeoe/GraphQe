package representations.graphalgebra;


import cypher.ast.expression.Variable;
import representations.GraphAlgebra;

import java.util.Collections;
import java.util.List;

public class Node extends GraphPatternPart {

    private final String and = "\u2227";
    public Node(Variable var) {
        super(var);
    }

    @Override
    public String algebra() {
        StringBuilder prop = new StringBuilder();
        if (var != null) {
            prop.append(var.toString());
        }
        if (!labels.isEmpty()) {
            for (int i = 0; i < labels.size(); i++) {
                if (i != 0) {
                    prop.append(" ").append(and).append(" ");
                }
                prop.append(":").append(labels.toArray()[i]);
            }
        }
        String p = prop.isEmpty() ? "" : prop.toString();
        return p;
    }

    @Override
    public PatternKind patternKind() {
        return PatternKind.NODE;
    }

    @Override
    public Node copy() {
        return new Node(var.copy());
    }

    @Override
    public String toString() {
        return "%s(%s)".formatted(circle,algebra());
    }

    @Override
    public int hashCode() {
        if (var != null) {
            return var.hashCode();
        }
        return System.identityHashCode(this);
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return Collections.singletonList(this);
    }
}
