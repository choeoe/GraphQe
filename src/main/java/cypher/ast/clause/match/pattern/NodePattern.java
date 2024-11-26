package cypher.ast.clause.match.pattern;

import cypher.ast.expression.MapExpression;
import cypher.ast.expression.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class NodePattern extends PatternElement {

    public Optional<Variable> variable;
    public List<LabelName> labels;
    public Optional<MapExpression> properties;

    public NodePattern(final Variable variable, final List<LabelName> labels,
                       final MapExpression properties) {
        this.variable = (variable == null) ? Optional.empty() : Optional.of(variable);
        this.labels = (labels == null) ? new ArrayList<>() : labels;
        this.properties = (properties == null) ? Optional.empty() : Optional.of(properties);
    }

    @Override
    public String toString() {
        String name = "";
        String prop = "";
        StringBuilder labels = new StringBuilder();
        if (variable.isPresent()) {
            name = variable.get().name;
        }
        if (this.labels != null) {
            for (int i = 0; i < this.labels.size(); i++) {
                labels.append(":");
                labels.append(this.labels.get(i).name);
            }
        }
        if (properties.isPresent()) {
            prop = properties.get().toString();
        }
        return "(" + name + labels + prop + ")";
    }

    @Override
    public NodePattern copy() {
        return new NodePattern(
                variable.map(Variable::copy).orElse(null), labels, properties.orElse(null));
    }
}
