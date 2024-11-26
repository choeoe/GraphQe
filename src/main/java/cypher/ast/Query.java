package cypher.ast;

/**
 */
public class Query extends ASTNode {

    public QueryPart part;

    public Query(final QueryPart part) {
        this.part = part;
    }
}
