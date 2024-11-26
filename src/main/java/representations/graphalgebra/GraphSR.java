package representations.graphalgebra;

import cypher.ast.expression.Variable;
import representations.GraphAlgebra;

import java.util.HashSet;

public abstract class GraphSR implements GraphAlgebra {
    public Variable var;
    public HashSet<String> labels;
    public NodeType type;

    public enum NodeType {
        POSITIVE("0"),
        NEGATIVE("1"),
        NULLABLE("2");

        private final String value;

        public String getValue() {
            return value;
        }

        private NodeType(String value) {
            this.value = value;
        }
    }
}
