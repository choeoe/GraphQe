package cypher.err;

public class CypherSemanticError extends RuntimeException{
    public static final String WHERE_CLAUSE_ERROR = "Semantic error occurs in WHERE clause at ";
    public CypherSemanticError(String message) {
        super(message);
    }
}
