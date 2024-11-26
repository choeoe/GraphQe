package representations.graphalgebra;

import cypher.ast.expression.Expression;
import cypher.ast.expression.Variable;
import representations.GraphAlgebra;

import java.util.ArrayList;
import java.util.List;

public class UnwindAlge implements GraphAlgebra {
    private static final String omega = "\u03C9";
    public GraphAlgebra sub;
    public Expression set;
    public Variable alias;

    public UnwindAlge(GraphAlgebra sub, Expression set, Variable alias) {
        this.sub = sub;
        this.set = set;
        this.alias = alias;
        alias.mkLink(set);
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return new ArrayList<>(sub.subAlgebras());
    }

    @Override
    public Kind kind() {
        return Kind.UNWIND;
    }

    @Override
    public String toString() {
        return "%s{%s->%s}(%s)".formatted(omega,set,alias,sub);
    }
}
