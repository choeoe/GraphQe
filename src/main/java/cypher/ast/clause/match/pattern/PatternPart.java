package cypher.ast.clause.match.pattern;

import cypher.ast.ASTNode;
import cypher.ast.expression.Variable;

/**
 */
public class PatternPart extends ASTNode {

    public PatternElement element;

    public PatternPart clone;

    public PatternPart(final PatternElement element) {
        this.element = element;
    }

    public PatternPart copy(){
        clone =  new PatternPart(element.copy());
        return clone;
    }
}
