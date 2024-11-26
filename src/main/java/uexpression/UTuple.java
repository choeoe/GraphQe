package uexpression;

import cypher.ast.expression.Variable;

public class UTuple implements UExpr{
    public Variable variable;

    public TType type;
    public UTuple() {
        variable = null;
        type = TType.SUM;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return variable.name;
    }

    public enum TType{
        SUM,
        PROJ,
        RETURNED
    }
}
