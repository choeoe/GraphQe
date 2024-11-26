package cypher.ast.expression;

import cypher.ast.clause.match.pattern.NamedPatternPart;
import cypher.ast.clause.match.pattern.PatternElement;
import cypher.ast.clause.match.pattern.PatternPart;

public class ShortestPathFunc extends NamedPatternPart {
    public ShortestPathFunc(PatternElement element) {
        super(element, null);
    }

    public ShortestPathFunc(PatternElement element, Variable var) {
        super(element, var);
    }
}
