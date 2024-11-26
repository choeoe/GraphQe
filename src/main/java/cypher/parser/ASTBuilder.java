package cypher.parser;

public abstract class ASTBuilder<T>  {
    public abstract T parse(String input);
}
