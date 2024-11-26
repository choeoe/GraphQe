package cypher.ast.clause.projection;

import cypher.ast.ASTNode;

import java.util.List;

/**
 */
public class OrderBy extends ASTNode {

    public List<SortItem> sortItems;

    public OrderBy(List<SortItem> sortItems) {
        this.sortItems = sortItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sortItems.size(); i++) {
            sb.append(sortItems.get(i));
            if (i != sortItems.size() - 1) {
                sb.append(",");
            }
        }
        return " ORDER BY " + sb;
    }

    public OrderBy copy() {
        return new OrderBy(sortItems.stream().map(SortItem::copy).toList());
    }
}
