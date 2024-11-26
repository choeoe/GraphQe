package cypher.translate;

import cypher.Pair;
import cypher.ast.expression.Expression;
import cypher.ast.expression.Literal;
import cypher.ast.expression.PropertyKeyName;
import cypher.ast.expression.Variable;

import java.util.ArrayList;
import java.util.List;

public class CypherList extends Expression {
    public Variable var;
    public List<String> colNames = new ArrayList<>();
    public List<List<Literal>> rows = new ArrayList<>();

    @Override
    public Expression copy() {
        CypherList cypherList = new CypherList();
        cypherList.rows = rows;
        cypherList.var = var.copy();
        return cypherList;
    }
}
