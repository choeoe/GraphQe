package cypher.ast.clause.match.pattern;

import cypher.ast.ASTNode;

import java.util.List;

/**
 */
public class Pattern extends ASTNode {

    public List<PatternPart> patternParts;

    public Pattern(List<PatternPart> patternParts) {
        this.patternParts = patternParts;
    }
}
