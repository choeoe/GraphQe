package cypher.ast.clause;

import cypher.ast.ASTNode;

/**
 */
public abstract class Clause extends ASTNode {
    public abstract Clause copy();
}
