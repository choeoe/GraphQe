package representations.graphalgebra;

import cypher.ast.expression.Binary;
import cypher.ast.expression.Expression;
import cypher.ast.expression.Variable;
import representations.GraphAlgebra;

import java.util.*;

public abstract class GraphPatternPart implements GraphAlgebra {
    public Variable var;

    public final List<Binary.Equals> properties;

    public Set<String> labels;

    public Expression constraint = null;

    public Expression labelConstraints;

    protected final String circle = "\u25EF";
    
    public GraphPatternPart(Variable var) {
        this.var = var;
        properties = new ArrayList<>();
        labels = new HashSet<>();
    }

    @Override
    public Kind kind() {
        return Kind.GRAPH_PATTERN_PART;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphPatternPart part = (GraphPatternPart) o;
        return var.equals(part.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    public enum PatternKind {
        NODE, RELATIONSHIP
    }

    public abstract String algebra();

    public abstract PatternKind patternKind();
    public abstract GraphPatternPart copy();

    public void addLabel(String labelName) {
        labels.add(labelName);
    }
}
