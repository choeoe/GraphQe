package cypher.ast.clause.projection;

import cypher.ast.ASTNode;
import cypher.ast.expression.Expression;
import cypher.ast.expression.Variable;

/**
 */
public abstract class ReturnItem extends ASTNode {

    public Expression expression;

    public abstract ReturnItem copy();
    public static class Aliased extends ReturnItem {
        public Variable alias;

        public String name() {
            return alias.name;
        }

        public Aliased(Expression expression, Variable alias) {
            this.expression = expression;
            this.alias = alias;
        }

        @Override
        public String toString() {
            return expression + " AS " + name();
        }

        @Override
        public ReturnItem copy() {
            return new Aliased(expression.copy(), alias.copy());
        }
    }

    public static class Unaliased extends ReturnItem {

        // The input text trimmed.
        public String name;

        public Unaliased(Expression expression, String inputText) {
            this.expression = expression;
            this.name = inputText.trim();
        }

        @Override
        public String toString() {
            if (expression == null) {
                return name;
            }
            return expression.toString();
        }

        @Override
        public ReturnItem copy() {
            return new Unaliased(expression.copy(), name);
        }
    }
}
