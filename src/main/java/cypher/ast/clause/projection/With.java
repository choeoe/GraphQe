package cypher.ast.clause.projection;

import cypher.ast.clause.Clause;
import cypher.ast.clause.Where;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class With extends ProjectionClause {

    public Optional<Where> where;

    public With(boolean distinct, List<ReturnItem> returnItems, OrderBy orderBy, Skip skip, Limit limit, Where where) {
        super(distinct, returnItems, orderBy, skip, limit);
        this.where = (where == null) ? Optional.empty() : Optional.of(where);
    }

    @Override
    public String toString() {
        return where.map(value -> " WITH " + super.toString() + " " + "WHERE " + value.expression).orElseGet(() -> " WITH " + super.toString() + " ");
    }

    @Override
    public Clause copy() {
        return new With(distinct, returnItems.stream().map(ReturnItem::copy).toList(),
                orderBy.map(OrderBy::copy).orElse(null), skip.map(Skip::copy).orElse(null),
                limit.map(Limit::copy).orElse(null),
                where.map(Where::copy).orElse(null));
    }
}
