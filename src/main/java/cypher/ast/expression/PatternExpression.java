package cypher.ast.expression;

import representations.graphalgebra.GraphPatternPart;

import java.util.ArrayList;
import java.util.List;

public class  PatternExpression extends Expression {

    public List<GraphPatternPart> element = new ArrayList<>();

    public PatternExpression(final List<GraphPatternPart> element) {
        this.element.addAll(element);
    }

    @Override
    public PatternExpression copy() {
        return new PatternExpression(element.stream().map(GraphPatternPart::copy).toList());
    }
}
