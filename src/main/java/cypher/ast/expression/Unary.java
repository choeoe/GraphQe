package cypher.ast.expression;


/**
 */
public abstract class Unary extends Expression {

    public Expression lhs;

    Unary(final Expression unary) {
        this.lhs = unary;
    }

    public static class Not extends Unary {
        public Not(Expression unary) {
            super(unary);
        }

        @Override
        public String toString() {
            return "NOT " + lhs;
        }

        @Override
        public Not copy() {
            return new Not(lhs.copy());
        }
    }
    public static class Add extends Unary {
        public Add(Expression unary) {
            super(unary);
        }

        @Override
        public String toString() {
            return "+" + lhs;
        }

        @Override
        public Add copy() {
            return new Add(lhs.copy());
        }
    }
    public static class Subtract extends Unary {
        public Subtract(Expression unary) {
            super(unary);
        }

        @Override
        public String toString() {
            return "-" + lhs;
        }

        @Override
        public Subtract copy() {
            return new Subtract(lhs.copy());
        }
    }
    public static class IsNull extends Unary {
        public IsNull(Expression unary) {
            super(unary);
        }

        @Override
        public String toString() {
            return lhs + " IS NULL";
        }

        @Override
        public IsNull copy() {
            return new IsNull(lhs.copy());
        }
    }
    public static class IsNotNull extends Unary {
        public IsNotNull(Expression unary) {
            super(unary);
        }

        @Override
        public String toString() {
            return lhs + " IS NOT NULL";
        }

        @Override
        public IsNotNull copy() {
            return new IsNotNull(lhs.copy());
        }
    }
}
