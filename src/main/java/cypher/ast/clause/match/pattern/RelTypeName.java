package cypher.ast.clause.match.pattern;

import cypher.ast.ASTNode;

/**
 */
public class RelTypeName extends ASTNode {

    public String name;

    public RelTypeName(final String name) {
        this.name = name;
    }
}
