package cypher.ast.clause.projection;

import cypher.ast.clause.Clause;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Return extends ProjectionClause {

    public Return clone;
    public Return(boolean distinct, List<ReturnItem> returnItems, OrderBy orderBy, Skip skip, Limit limit) {
        super(distinct, returnItems, orderBy, skip, limit);
    }

    @Override
    public String toString() {
        return " RETURN "+super.toString();
    }

    @Override
    public Clause copy() {
        clone = new Return(distinct,new ArrayList<>(returnItems.stream().map(ReturnItem::copy).toList()),
                orderBy.<OrderBy>map(OrderBy::copy).orElse(null),skip.<Skip>map(Skip::copy).orElse(null),
                limit.<Limit>map(Limit::copy).orElse(null));
        return clone;
    }
}
