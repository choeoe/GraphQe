package cypher.ast.expression;


import representations.GraphAlgebra;
import sqlsolver.uexpr.UTerm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Variable extends Expression implements GraphAlgebra{

    public String name;

    List<Variable> subVars;

    public Expression linked;

    public UTerm point;

    public VarKind kind;

    private static final List<Variable> variables = new ArrayList<>();

    private static final List<Variable> vars4copy = new ArrayList<>();


    public Variable(final String name) {
        this.name = name;
        this.subVars = new ArrayList<>();
    }

    public Variable() {
        this.subVars = new ArrayList<>();
    }

    public static Variable getVariable(String name){
        if (name == null) {
            return new Variable();
        } else {
            for (Variable var : variables) {
                if (var.name.equals(name)) {
                    return var;
                }
            }
            variables.add(new Variable(name));
            return variables.get(variables.size() - 1);
        }
    }

    public Expression getLinked() {
        if (linked != null) {
            Expression expr = linked;
            Expression expr1 = this;
            while (expr != null) {
                expr1 = expr;
                if (expr instanceof Variable) {
                    expr = ((Variable) expr).linked;
                } else expr = null;
            }
            return expr1;
        } else return this;
    }

    @Override
    public List<GraphAlgebra> subAlgebras() {
        return null;
    }

    @Override
    public Kind kind() {
        return Kind.VAR;
    }

    public VarKind varKind() {
        if (kind != null) {
            return kind;
        } else {
            return VarKind.BASE;
        }
    }

    public void mkLink(Expression expr) {
        if (!expr.isVar()) {
            linked = expr;
        } else {
            while (((Variable) expr).linked != null) {
                if (!((Variable) expr).linked.isVar()) {
                    linked = ((Variable) expr).linked;
                    break;
                }
                expr = ((Variable) expr).linked;
            }
            if (linked == null) {
                linked = expr;
            }
        }
    }

    public List<Variable> getSubVars() {
        return subVars;
    }

    public void addSubVar(Variable var) {
        subVars.add(var);
    }

    public static List<Variable> getAllVars() {
        return variables;
    }

    public static String getValidName() {
        int index = 0;
        String name = "n" + index;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).name.equals(name)) {
                index++;
                name = "n" + index;
                i = -1;
            }
        }
        variables.add(new Variable(name));
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Variable var = (Variable) obj;
        return Objects.equals(name, var.name);
    }

    @Override
    public Variable copy() {

        return new Variable(name);
    }

    public enum VarKind {
        PROJ,
        BASE,
        PATTERN,
        LIST,
        EXPR,
        CONST,
        NODE,
        REL
    }
}
