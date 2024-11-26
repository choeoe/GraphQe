package cypher.ast.expression;

/**
 *
 */
public abstract class Binary extends Unary {

    public Expression rhs;

    Binary(Expression lhs, Expression rhs) {
        super(lhs);
        this.rhs = rhs;
    }

    public static class GreaterThan extends Binary {

        public GreaterThan(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + ">" + rhs;
        }

        @Override
        public GreaterThan copy() {
            return new GreaterThan(lhs.copy(),rhs.copy());
        }
    }

    public static class LessThan extends Binary {

        public LessThan(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + "<" + rhs;
        }

        @Override
        public LessThan copy() {
            return new  LessThan(lhs.copy(),rhs.copy());
        }
    }

    public static class Equals extends Binary {

        public Equals(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + "=" + rhs;
        }

        @Override
        public Equals copy() {
            return new Equals(lhs.copy(),rhs.copy());
        }
    }

    public static class NotEquals extends Binary {

        public NotEquals(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + "<>" + rhs;
        }

        @Override
        public NotEquals copy() {
            return new NotEquals(lhs.copy(),rhs.copy());
        }
    }

    public static class GreaterThanOrEqual extends Binary {

        public GreaterThanOrEqual(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + ">=" + rhs;
        }

        @Override
        public GreaterThanOrEqual copy() {
            return new GreaterThanOrEqual(lhs.copy(),rhs.copy());
        }
    }

    public static class LessThanOrEqual extends Binary {

        public LessThanOrEqual(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + "<=" + rhs;
        }

        @Override
        public LessThanOrEqual copy() {
            return new LessThanOrEqual(lhs.copy(),rhs.copy());
        }
    }

    public static class InvalidNotEquals extends Binary {

        public InvalidNotEquals(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + "!=" + rhs;
        }

        @Override
        public InvalidNotEquals copy() {
            return new InvalidNotEquals(lhs.copy(),rhs.copy());
        }
    }

    public static class RegexMatch extends Binary {

        public RegexMatch(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + " =~ " + rhs;
        }

        @Override
        public RegexMatch copy() {
            return new RegexMatch(lhs.copy(),rhs.copy());
        }
    }

    public static class And extends Binary {

        public And(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " " + lhs + " AND " + rhs;
        }

        @Override
        public And copy() {
            return new And(lhs.copy(),rhs.copy());
        }
    }

    public static class Or extends Binary {

        public Or(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + " OR " + rhs;
        }

        @Override
        public Or copy() {
            return new Or(lhs.copy(),rhs.copy());
        }
    }

    public static class Xor extends Binary {

        public Xor(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + " XOR " + rhs;
        }

        @Override
        public Xor copy() {
            return new Xor(lhs.copy(),rhs.copy());
        }
    }

    public static class StartsWith extends Binary {

        public StartsWith(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + " STARTS WITH " + rhs;
        }

        @Override
        public StartsWith copy() {
            return new StartsWith(lhs.copy(),rhs.copy());
        }
    }

    public static class EndsWith extends Binary {

        public EndsWith(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + " ENDS WITH " + rhs;
        }

        @Override
        public EndsWith copy() {
            return new EndsWith(lhs.copy(),rhs.copy());
        }
    }

    public static class In extends Binary {

        public In(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + " IN " + rhs;
        }

        @Override
        public In copy() {
            return new In(lhs.copy(),rhs.copy());
        }
    }

    public static class Subtract extends Binary {

        public Subtract(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + "-" + rhs;
        }

        @Override
        public Binary.Subtract copy() {
            return new Binary.Subtract(lhs.copy(),rhs.copy());
        }
    }

    public static class Add extends Binary {

        public Add(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + "+" + rhs;
        }

        @Override
        public Binary.Add copy() {
            return new Binary.Add(lhs.copy(),rhs.copy());
        }
    }

    public static class Divide extends Binary {

        public Divide(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + "/" + rhs;
        }

        @Override
        public Divide copy() {
            return new Divide(lhs.copy(),rhs.copy());
        }
    }

    public static class Multiply extends Binary {

        public Multiply(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + "*" + rhs;
        }

        @Override
        public Multiply copy() {
            return new Multiply(lhs.copy(),rhs.copy());
        }
    }

    public static class Modulo extends Binary {

        public Modulo(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + "%" + rhs;
        }

        @Override
        public Modulo copy() {
            return new Modulo(lhs.copy(),rhs.copy());
        }
    }

    public static class Pow extends Binary {

        public Pow(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + "^" + rhs;
        }

        @Override
        public Pow copy() {
            return new Pow(lhs.copy(),rhs.copy());
        }
    }

    public static class Contains extends Binary {

        public Contains(Expression lhs, Expression rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return lhs + " CONTAINS " + rhs;
        }

        @Override
        public Contains copy() {
            return new Contains(lhs.copy(),rhs.copy());
        }
    }
}
