package cypher.ast.expression;

import cypher.ast.clause.match.pattern.LabelName;

import java.util.List;

public class LabelExpression extends Expression{
    public Variable var;
    public List<LabelName> labelNames;

    public LabelExpression(Variable var, List<LabelName> labelNames) {
        this.var = var;
        this.labelNames = labelNames;
    }

    @Override
    public LabelExpression copy() {
        return new LabelExpression(var.copy(),labelNames);
    }
}
