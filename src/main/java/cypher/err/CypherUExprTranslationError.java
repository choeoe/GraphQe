package cypher.err;

public class CypherUExprTranslationError extends RuntimeException{
    public static final String EMPTY_PATTERNS = "Patterns are empty!";
    public static final String NOT_SUPPORT = "Not support!";
    public CypherUExprTranslationError(String message) {
        super(message);
    }
}
