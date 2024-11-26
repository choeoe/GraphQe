package cypher.ast.expression;

import cypher.ast.clause.match.pattern.LabelName;
import cypher.ast.clause.match.pattern.RelTypeName;

import java.util.List;

public class RelTypeExpression  extends Expression {
    public Variable var;
    public List<RelTypeName> typeNames;

    public RelTypeExpression(Variable var, List<RelTypeName> labelNames) {
        this.var = var;
        this.typeNames = labelNames;
    }

    @Override
    public RelTypeExpression copy() {
        return new RelTypeExpression(var.copy(),typeNames);
    }
}