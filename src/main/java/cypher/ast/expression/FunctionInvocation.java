package cypher.ast.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
public class FunctionInvocation extends Expression {

    public FunctionName functionName;
    public boolean distinct;
    public List<Expression> args;

    public FunctionInvocation(final FunctionName functionName,
                              boolean distinct, List<Expression> args) {
        this.distinct = distinct;
        this.args = args;
        this.functionName = functionName;
    }

    public boolean isAGG() {
        return functionName.name.equals("COUNT") || functionName.name.equals("MAX") || functionName.name.equals("SUM")
                || functionName.name.equals("AVG") || functionName.name.equals("MIN") || functionName.name.equals("COLLECT");
    }

    public boolean isCOLLECT() {
        return functionName.name.equals("COLLECT");
    }

    public boolean isLiteralFunc() {
        if (args.size() == 1) {
            return args.get(0) instanceof Literal;
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Expression arg : args) {
            sb.append(arg);
            if (args.indexOf(arg) != args.size() - 1) {
                sb.append(",");
            }
        }
        return functionName + "(" + (distinct ? "DISTINCT " : "") + sb + ")";
    }

    @Override
    public FunctionInvocation copy() {
        return new FunctionInvocation(functionName,distinct,args.stream().map(Expression::copy).collect(Collectors.toList()));
    }
}
