package cypher.ast.expression;

/**
 */
public abstract class Literal extends Expression {

    public String stringValue;

    Literal(final String stringValue) {
        this.stringValue = stringValue;
    }

    public static class Number extends Literal {

        public Number(final String stringValue) {
            super(stringValue);
        }

        @Override
        public Number copy() {
            return this;
        }
    }

    public static class Integer extends Number {

        public long value;

        public Integer(final String stringValue) {
            super(stringValue);
        }

        public Integer(int value) {
            super(String.valueOf(value));
            this.value = value;
        }
    }

    public static class DecimalDouble extends Number {
        public double value;

        public DecimalDouble(final String stringValue) {
            super(stringValue);
            this.value = Double.parseDouble(stringValue);
        }
    }

    public static class HexInteger extends Integer {

        public HexInteger(final String stringValue) {
            super(stringValue);
            this.value = Long.parseLong(stringValue, 16);
        }
    }

    public static class UnsignedInteger extends Integer {

        public UnsignedInteger(final String stringValue) {
            super(stringValue);
            this.value = Long.parseLong(stringValue);
        }

        public UnsignedInteger(int integer) {
            super(String.valueOf(integer));
            this.value = integer;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

    public static class OctalInteger extends Integer {

        public OctalInteger(final String stringValue) {
            super(stringValue);
            this.value = Long.parseLong(stringValue, 8);
        }
    }

    public static class StringLiteral extends Literal {
        public String value;

        public StringLiteral(final String stringValue) {
            super(stringValue);
            this.value = stringValue.substring(1, stringValue.length() - 1);
        }

        @Override
        public String toString() {
            return '"'+value+'"';
        }

        @Override
        public StringLiteral copy() {
            return this;
        }
    }

    public static class Star extends Literal {

        public Star() {
            super("*");
        }

        @Override
        public String toString() {
            return "*";
        }

        @Override
        public Star copy() {
            return this;
        }
    }

    public static class Null extends Literal {

        public Null() {
            super("NULL");
        }

        public String toString() {
            return "NULL";
        }

        @Override
        public Null copy() {
            return this;
        }
    }

    public static class True extends Literal {

        public True() {
            super("TRUE");
        }

        public String toString() {
            return "TRUE";
        }
        @Override
        public True copy() {
            return this;
        }
    }

    public static class False extends Literal {

        public False() {
            super("FALSE");
        }

        @Override
        public String toString() {
            return "FALSE";
        }

        @Override
        public False copy() {
            return this;
        }
    }
}