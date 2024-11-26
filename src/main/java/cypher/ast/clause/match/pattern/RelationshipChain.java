package cypher.ast.clause.match.pattern;

/**
 */
public class RelationshipChain extends PatternElement {

    public PatternElement element;
    public RelationshipPattern relationship;
    public NodePattern rightNode;

    public RelationshipChain clone;
    public RelationshipChain(final PatternElement element, final RelationshipPattern relationship,
                             final NodePattern rightNode) {
        this.element = element;
        this.relationship = relationship;
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return element.toString()+relationship.toString()+rightNode.toString();
    }

    @Override
    public RelationshipChain copy() {
        clone = new RelationshipChain(element.copy(), relationship.copy(), rightNode.copy());
        return clone;
    }
}
