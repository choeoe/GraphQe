package cypher.ast.clause.match.pattern;

import cypher.ast.expression.Variable;

/**
 */
public class NamedPatternPart extends PatternPart {

    public Variable variable;

    public NamedPatternPart(final PatternElement element, final Variable var) {
        super(element);
        this.variable = var;
    }

    @Override
    public NamedPatternPart copy(){
        NamedPatternPart copy = new NamedPatternPart(element.copy(), variable);
        clone = copy;
        return copy;
    }
}
