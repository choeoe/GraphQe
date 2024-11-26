package cypher.ast;

/**
 *
 */
public abstract class Union extends QueryPart {
    public QueryPart lhs;
    public QueryPart rhs;

    public Union(QueryPart lhs, QueryPart rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public static class All extends Union {

        public All(QueryPart lhs, QueryPart rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " UNION All ";
        }

        public void print() {
            System.out.println(lhs + this.toString() + rhs);
        }
    }

    public static class Distinct extends Union {

        public Distinct(QueryPart lhs, QueryPart rhs) {
            super(lhs, rhs);
        }

        @Override
        public String toString() {
            return " UNION ";
        }
    }
}
