package cypher.ast.expression;

import cypher.ast.ASTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class FunctionName extends ASTNode {

    public static List<String> aggFuncs = List.of("COUNT","AVG","SUM");

    public String name;

    public FunctionName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
