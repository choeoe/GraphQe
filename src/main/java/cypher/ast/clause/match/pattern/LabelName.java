package cypher.ast.clause.match.pattern;

import cypher.ast.ASTNode;

/**
 */
public class LabelName extends ASTNode {

    public String name;

    public LabelName(final String name) {
        this.name = name;
    }
}
