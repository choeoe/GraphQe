package cypher.ast.expression;


/**
 */
public class Property extends Expression {

    public Expression map;
    public PropertyKeyName propertyKey;

    public Property(final Expression map, final PropertyKeyName propertyKey) {
        this.map = map;
        this.propertyKey = propertyKey;
    }

    @Override
    public String toString() {
        return map + "." + propertyKey;
    }

    @Override
    public Property copy() {
        return new Property(map.copy(),propertyKey);
    }
}
