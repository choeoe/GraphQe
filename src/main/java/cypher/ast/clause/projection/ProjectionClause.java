package cypher.ast.clause.projection;

import cypher.ast.clause.Clause;

import java.util.List;
import java.util.Optional;

/**
 */
public abstract class ProjectionClause extends Clause {

    public boolean distinct;
    public List<ReturnItem> returnItems;
    public Optional<OrderBy> orderBy;
    public Optional<Skip> skip;
    public Optional<Limit> limit;

    public ProjectionClause(boolean distinct, List<ReturnItem> returnItems,
                            OrderBy orderBy, Skip skip, Limit limit) {
        this.distinct = distinct;
        this.returnItems = returnItems;
        this.orderBy = (orderBy == null) ? Optional.empty(): Optional.of(orderBy);
        this.skip = (skip == null) ? Optional.empty(): Optional.of(skip);
        this.limit = (limit == null) ? Optional.empty(): Optional.of(limit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (distinct) {
            sb.append("DISTINCT ");
        }
        if (returnItems != null) {
            for (int i = 0; i < returnItems.size(); i++) {
                sb.append(returnItems.get(i));
                if (i != returnItems.size() - 1) {
                    sb.append(",");
                }
            }
        }

        orderBy.ifPresent(sb::append);
        skip.ifPresent(sb::append);
        limit.ifPresent(sb::append);
        return sb.toString();
    }
}
