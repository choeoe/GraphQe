package representations.graphalgebra;

import cypher.ast.expression.Expression;
import representations.GraphAlgebra;

import java.util.Collections;
import java.util.List;

public class Bag implements GraphAlgebra {
    public List<Expression> body;

    public Bag(List<Expression> body) {
        this.body = body;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return Collections.singletonList(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < body.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(body.get(i));
        }
        return sb.toString();
    }

    @Override
    public Kind kind() {
        return Kind.BAG;
    }
}
