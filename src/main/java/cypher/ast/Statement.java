package cypher.ast;

/**
 */
public class Statement extends ASTNode {

    public Query query;

    public Statement(final Query query) {
        this.query = query;
    }
}
