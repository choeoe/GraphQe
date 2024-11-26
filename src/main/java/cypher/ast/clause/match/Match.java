package cypher.ast.clause.match;

import cypher.ast.clause.Clause;
import cypher.ast.clause.Where;
import cypher.ast.clause.match.pattern.Pattern;
import cypher.ast.clause.match.pattern.PatternPart;

import java.util.List;
import java.util.Optional;

/**
 */
public class Match extends Clause {

    public boolean optional;
    public Pattern pattern;
    public Optional<Where> where;
    public Match clone;
    public Match(boolean optional, Pattern pattern, Where where) {
        this.optional = optional;
        this.pattern = pattern;
        this.where = (where == null) ? Optional.empty(): Optional.of(where);
    }

    public Match copy() {
        List<PatternPart> parts = pattern.patternParts.stream().map(PatternPart::copy).toList();
        Pattern cp = new Pattern(parts);
        clone = new Match(optional, cp, where.map(Where::copy).orElse(null));
        return clone;
    }
}
