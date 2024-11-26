package cypher.ast.clause.projection;

import cypher.ast.ASTNode;
import cypher.ast.expression.Expression;

/**
 */
public abstract class SortItem extends ASTNode {

    public Expression expression;

    public abstract String name();
    public abstract SortItem copy();
    SortItem(final Expression expression) {
        this.expression = expression;
    }

    public abstract String algebra();

    public static class Asc extends SortItem {

        @Override
        public String name() {
            return "ASC";
        }

        @Override
        public Asc copy() {
            return new Asc(expression.copy());
        }
        public Asc(final Expression expression) {
            super(expression);
        }

        @Override
        public String algebra() {
            return "\u2191%s".formatted(expression.toString());
        }

        @Override
        public String toString() {
            return expression.toString() + " ASC";
        }

    }
    public static class Desc extends SortItem {

        @Override
        public String name() {
            return "DESC";
        }

        @Override
        public Desc copy() {
            return new Desc(expression.copy());
        }

        public Desc(final Expression expression) {
            super(expression);
        }

        @Override
        public String algebra() {
            return "\u2193%s".formatted(expression.toString());
        }

        @Override
        public String toString() {
            return  expression.toString() + " DESC";
        }
    }
}
