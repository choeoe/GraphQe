package representations.graphalgebra;

import cypher.ast.clause.projection.Return;
import cypher.ast.expression.Expression;
import cypher.ast.expression.Variable;
import cypher.translate.CypherList;
import sqlsolver.uexpr.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphPattern {

    private final Set<UVar> sumBounds;

    public final Set<Variable> tmpVars;

    private Set<GraphPatternPart> parts;

    private final List<Expression> exprs;

    private UTerm uTerms;

    private Return RETURN;

    private List<GraphPattern> subPatterns;

    private List<UTerm> retItems;

    public boolean DISTINCT = false;

    public List<CypherList> lists = new ArrayList<>();

    public List<Expression> AGGs = new ArrayList<>();

    public GraphPattern() {
        sumBounds = new HashSet<>();
        parts = new HashSet<>();
        exprs = new ArrayList<>();
        subPatterns = new ArrayList<>();
        retItems = new ArrayList<>();
        tmpVars = new HashSet<>();
    }

    public GraphPattern createPattern() {
        GraphPattern pattern = new GraphPattern();
        subPatterns.add(pattern);
        return pattern;
    }

    public void addRetItem(UTerm term) {
        retItems.add(term);
    }

    public void print() {
        System.out.println(sumBounds);
    }

    public List<GraphPatternPart> getAllGraphPatternParts() {
        return new ArrayList<>(parts);
    }

    public void addPatternPart(GraphPatternPart part) {
        parts.add(part);
    }

    public void addUterm(UTerm term) {
        if (uTerms == null)
            uTerms = term;
        else
            uTerms = UMul.mk(uTerms, term);
    }

    public UVar addSumBound(String name) {
        UName n = UName.mk(name);
        UVar var = UVar.mkBase(n);
        sumBounds.add(var);
        return var;
    }

    public List<Expression> getExprs() {
        return exprs;
    }

    public void addSumBound(UVar var) {
        sumBounds.add(var);
    }

    public void addExpr(Expression expr) {
        exprs.add(expr);
    }

    public void setUTerms(UTerm uTerms) {
        this.uTerms = uTerms;
    }

    public Set<UVar> getSumBounds() {
        return sumBounds;
    }

    public UTerm getUTerms() {
        if (DISTINCT) {
            uTerms = USquash.mk(uTerms);
        }
        return uTerms;
    }

    public Return getRETURN() {
        return RETURN;
    }

    public void setRETURN(Return RETURN) {

        this.RETURN = RETURN;
    }
}
