package uexpression;

import java.util.List;

public class UUnary implements UExpr{
    public UExpr c;

    public UUnary(UExpr c) {
        this.c = c;
    }

    public static class Not extends UUnary{

        public Not(UExpr c) {
            super(c);
        }

        @Override
        public String toString() {
            return "Not" + c;
        }
    }

    public static class Sum extends UUnary {
        List<UTuple> bound;

        public Sum(UExpr c, List<UTuple> tuples) {
            super(c);
            bound = tuples;
        }

        public List<UTuple> getBound() {
            return bound;
        }

        @Override
        public String toString() {
            return "Σ" + bound.toString().strip() + "( " + c + " )";
        }
    }

    public static class Squash extends UUnary {
        public Squash(UExpr c) {
            super(c);
        }

        @Override
        public String toString() {
            return "|| " + c + " ||";
        }
    }


    public static class Label extends UUnary {
        public String label;

        final int type;

        public Label(UExpr c, String label, int type) {
            super(c);
            this.label = label;
            this.type = type;
        }
    }

    public static class Vertex extends UUnary {

        public Vertex(UExpr c) {
            super(c);
        }

        @Override
        public String toString() {
            return "V("+c+")";
        }
    }

    public static class Relation extends UUnary {
        public Relation(UExpr c) {
            super(c);
        }
        @Override
        public String toString() {
            return "R("+c+")";
        }
    }

    public static class From extends UUnary {
        public From(UExpr c) {
            super(c);
        }

        @Override
        public String toString() {
            return "From("+c+")";
        }
    }

    public static class To extends UUnary {
        public To(UExpr c) {
            super(c);
        }

        @Override
        public String toString() {
            return "To("+c+")";
        }
    }


}
