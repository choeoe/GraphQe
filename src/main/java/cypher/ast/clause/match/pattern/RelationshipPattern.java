package cypher.ast.clause.match.pattern;

import cypher.ast.ASTNode;
import cypher.ast.expression.MapExpression;
import cypher.ast.expression.Variable;

import java.util.List;
import java.util.Optional;

/**
 */
public class RelationshipPattern extends ASTNode {

    public enum SemanticDirection {
        OUTGOING,
        INCOMING,
        BOTH
    }

    public Optional<Variable> variable;
    public Optional<Optional<Range>> length;
    public Optional<MapExpression> properties;
    public List<RelTypeName> types;
    public SemanticDirection direction;

    public RelationshipPattern clone;
    public RelationshipPattern(final Variable variable, final Optional<Range> range,
                               final MapExpression properties, final List<RelTypeName> types) {
        this.variable = (variable == null) ? Optional.empty(): Optional.of(variable);
        this.length = (range == null) ? Optional.empty(): Optional.of(range);
        this.properties = (properties == null) ? Optional.empty(): Optional.of(properties);
        this.types = types;
    }

    @Override
    public String toString() {
        String var = "";
        StringBuilder types = new StringBuilder();
        String props = "";
        if (variable.isPresent()) {
            var = variable.get().name;
        }
        for (int i = 0; i < this.types.size(); i++) {
            if(i ==0)
                types.append(":");
            else
                types.append("|");
            types.append(this.types.get(i).name);
        }
        if (properties.isPresent()) {
            props = properties.get().toString();
        }
        String relContent = var+types+props;
        if (length.isPresent()) {
            String l = "";
            if(length.get().isPresent())
                l = length.get().get().toString();
            relContent += ("*" + l);
        }
        if (relContent.length()>0) {
            relContent = "[" + relContent + "]";
        }
        if (direction == SemanticDirection.INCOMING) {
            return "<-" + relContent + "-";
        } else if(direction == SemanticDirection.OUTGOING){
            return "-" + relContent + "->";
        } else {
            return "-" + relContent + "-";
        }
    }

    public RelationshipPattern copy() {
        clone = new RelationshipPattern(variable.map(Variable::copy).orElse(null), length.orElse(null),
                properties.map(MapExpression::copy).orElse(null),types);
        clone.direction = direction;
        return clone;
    }
}
