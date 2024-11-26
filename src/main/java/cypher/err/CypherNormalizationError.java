package cypher.err;

public class CypherNormalizationError extends RuntimeException{
    public static final String STANDARDIZE_ERROR = "Variable Normalization Error!";
    public static final String WHERE_STANDARDIZING_ERROR = "Expr In WHERE Normalization Error!";
    public CypherNormalizationError(String message) {
        super(message);
    }
}
