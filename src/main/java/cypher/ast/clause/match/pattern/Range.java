package cypher.ast.clause.match.pattern;

import cypher.ast.ASTNode;
import cypher.ast.expression.Literal;

import java.util.Optional;

/**
 */
public class Range extends ASTNode {
    public Optional<Literal.Integer> lower;
    public Optional<Literal.Integer> upper;

    public Range(Literal.Integer lower, Literal.Integer upper) {
        this.lower = (lower == null) ? Optional.empty(): Optional.of(lower);
        this.upper = (upper == null) ? Optional.empty(): Optional.of(upper);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        lower.ifPresent(sb::append);
        if (upper.isPresent()) {
            sb.append("..");
        }
        upper.ifPresent(sb::append);
        return sb.toString();
    }
}
