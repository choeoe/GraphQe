package cypher.ast.expression;


import cypher.ast.clause.match.Match;

public class ExistsFunction extends Expression {

    public Expression arg;

    public Match match;

    public ExistsFunction(Expression arg) {
        this.arg = arg;
    }

    public ExistsFunction(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        if (arg != null) {
            return "EXISTS ( " + arg.toString() + " ) ";
        } else {
            return "EXISTS { " + match.toString() + " } ";
        }
    }

    @Override
    public Expression copy() {
        return new ExistsFunction(arg.copy());
    }
}
