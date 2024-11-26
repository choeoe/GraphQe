package cypher.ast.clause.match.pattern;

import cypher.ast.ASTNode;
import cypher.ast.expression.Expression;

/**
 */
public abstract class PatternElement extends Expression {
    public abstract PatternElement copy();
}
