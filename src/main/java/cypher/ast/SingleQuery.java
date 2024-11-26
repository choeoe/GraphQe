package cypher.ast;

import cypher.ast.clause.Clause;
import cypher.parser.CypherStringVisitor;

import java.util.List;

/**
 */
public class SingleQuery extends QueryPart {

    public List<Clause> clauses;

    public SingleQuery(final List<Clause> clauses) {
        this.clauses = clauses;
    }

    public SingleQuery copy() {
        return new SingleQuery(clauses.stream().map(Clause::copy).toList());
    }

    @Override
    public String toString() {
        CypherStringVisitor visitor = new CypherStringVisitor();
        for (Clause clause : clauses) {
            visitor.visit(clause);
        }
        return visitor.get();
    }
}
