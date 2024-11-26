package cypher.ast.expression;


import cypher.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 */
public class CaseExpression extends Expression {

    public Optional<Expression> expression;
    public Optional<Expression> default_;
    public List<Pair<Expression, Expression>> alternatives;

    public CaseExpression(Expression expression, Expression default_,
                          List<Pair<Expression, Expression>> alternatives) {
        this.expression = (expression == null) ? Optional.empty(): Optional.of(expression);
        this.default_ = (default_ == null) ? Optional.empty(): Optional.of(default_);
        this.alternatives = alternatives;
    }

    @Override
    public CaseExpression copy() {
        List<Pair<Expression, Expression>> clone = alternatives.stream().map(x -> new Pair<>(x.getLeft().copy(), x.getRight().copy())).toList();
        List<Pair<Expression, Expression>> alternatives = new ArrayList<>(clone);
        return new CaseExpression(expression.map(Expression::copy).orElse(null), default_.map(Expression::copy).orElse(null),alternatives);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CASE ");
        expression.ifPresent(sb::append);
        for (Pair<Expression, Expression> pair : alternatives) {
            sb.append(" WHEN ").append(pair.getLeft()).append(" THEN ").append(pair.getRight());
        }
        default_.ifPresent(value -> sb.append(" ELSE ").append(value));
        sb.append(" END ");
        return sb.toString();
    }
}
