package representations.graphalgebra;

import cypher.ast.expression.Variable;
import representations.GraphAlgebra;

import java.util.List;

public class Expand extends GraphPatternPart {

    public GraphPatternPart start;

    public GraphPatternPart end;

    public GraphAlgebra algebra;

    private Direction direction;

    private final String or = "\u2228";

    public Expand(Variable var) {
        super(var);
    }

    @Override
    public PatternKind patternKind() {
        return PatternKind.RELATIONSHIP;
    }

    public Expand(Variable variable, GraphPatternPart l, GraphPatternPart r, GraphAlgebra a) {
        super(variable);
        start = l;
        end = r;
        algebra = a;
    }

    @Override
    public Expand copy() {
        Expand clone = new Expand(var.copy(), start.copy(), end.copy(), algebra.copy());
        clone.direction = this.direction;
        clone.labels = this.labels;
        clone.properties.addAll(this.properties);
        return clone;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        String dir = direction == Direction.OUT ? "\u2191" : "\u2193";
        StringBuilder prop = new StringBuilder();
        if (var != null) {
            prop.append(var.toString());
        }
        if (!labels.isEmpty()) {
            for (int i = 0; i < labels.size(); i++) {
                if (i != 0) {
                    prop.append(" ").append(or).append(" ");
                }
                prop.append(":").append(labels.toArray()[i]);
            }
        }
        String p = prop.isEmpty() ? "" : "[%s]".formatted(prop.toString());
        return "{%s}%s{%s}%s(%s)".formatted(start.algebra(), dir, end.algebra(), p, algebra);
    }

    @Override
    public String algebra() {
        return end.algebra();
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return algebra.subAlgebras();
    }

    public enum Direction {
        IN, OUT, BOTH;
    }

    @Override
    public int hashCode() {
        if (var != null) {
            return var.hashCode();
        }
        return System.identityHashCode(this);
    }
}
