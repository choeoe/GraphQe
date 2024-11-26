package cypher.ast.expression;

import java.util.List;

/**
 *
 */
public class ListExpression extends Expression {

    public List<Expression> exprs;

    public ListExpression(final List<Expression> exprs) {
        this.exprs = exprs;
    }

    @Override
    public ListExpression copy() {
        return new ListExpression(exprs.stream().map(Expression::copy).toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exprs.size(); i++) {
            sb.append(exprs.get(i).toString());
            if (i != exprs.size() - 1) {
                sb.append(",");
            }
        }
        return "[ " + sb.toString() + " ]";
    }
}
