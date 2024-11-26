package cypher.ast.expression;

import cypher.ast.ASTNode;

/**
 */
public class PropertyKeyName extends ASTNode {

    public String name;

    public PropertyKeyName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
