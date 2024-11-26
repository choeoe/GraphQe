package cypher.algebra.translator;

import cypher.Pair;
import cypher.algebra.HyperTuple;
import cypher.ast.clause.projection.Limit;
import cypher.ast.clause.projection.SortItem;
import cypher.ast.expression.*;
import cypher.err.CypherUExprTranslationError;
import cypher.translate.Cypher2UExpr;
import cypher.translate.Cypher2UExprHelper;
import representations.GraphAlgebra;
import representations.graphalgebra.*;
import sqlsolver.liastar.Liastar;
import sqlsolver.uexpr.*;
import sqlsolver.util.AbstractPrettyPrinter;
import sqlsolver.util.SetMatching;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

import static sqlsolver.uexpr.UExprSupport.*;
import static sqlsolver.uexpr.UKind.ADD;
import static sqlsolver.uexpr.UPred.PredKind.*;

public class GraphAlgebraTranslator {
    GraphAlgebra algebra;
    Cypher2UExpr exprTranslator = new Cypher2UExpr(this);

    List<HyperTuple> hyperTuples = new ArrayList<>();

    private List<Pair<UVar, UVar>> eqVars = new ArrayList<>();
    public static boolean peel = false;
    public static UVar outVar;

    UTerm body;
    public Set<UVar> sumBounds = new HashSet<>();

    Set<UVar> solidVars = new HashSet<>();

    Set<UVar> optionalVars = new HashSet<>();

    public Map<UTerm, UTerm> renameMap = new HashMap<>();

    boolean innerDedupFlag = false;

    boolean solidPattern = true;

    private static Queue<List<Pair<Integer, Integer>>> maps = new LinkedList<>();

    public boolean isKey = false;

    public Map<UTerm, UTerm> colMaps = new HashMap<>();

    public List<UVar> tmpVars = new ArrayList<>();

    public GraphAlgebraTranslator(GraphAlgebra algebra) {
        this.algebra = algebra;
    }

    public static void setReturnMaps(List<List<Pair<Integer, Integer>>> maps) {
        GraphAlgebraTranslator.maps.addAll(maps);
    }

    public static Queue<List<Pair<Integer, Integer>>> getMaps() {
        return maps;
    }

    public static void clearMaps() {
        maps.clear();
    }

    public UTerm translate() {
        exprTranslator.renameMap = this.renameMap;
        if (outVar == null) {
            outVar = UVar.mkBase(UName.mk("t"));
        }
        UTerm result = translateAlgebra(algebra);
        for (Pair<UVar, UVar> eqVar : eqVars) {
            result=result.replaceVar(eqVar.getLeft(), eqVar.getRight(),false);
        }
        return result;
    }

    public UTerm translateWithOutProjec() {
        UTerm term = translateAlgebra(algebra);
        return USum.mk(sumBounds, term);
    }

    private UTerm translateAlgebra(GraphAlgebra algebra) {
        if (algebra == null) {
            return null;
        }
        switch (algebra.kind()) {
            case UNION -> {
                return translateUnion(((Union) algebra));
            }
            case BAG_UNION -> {
                return translateBagUnion(((BUnion) algebra));
            }
            case NATURE_JOIN -> {
                return translateJoin(((Join) algebra));
            }
            case LEFT_OUTER_JOIN -> {
                return translateLOJ(((LOJoin) algebra));
            }
            case PROJ -> {
                return translateProj(((Projection) algebra));
            }
            case GRAPH_PATTERN_PART -> {
                return translateGraph(((GraphPatternPart) algebra));
            }
            case SELECTION -> {
                return translateSelection(((Selection) algebra));
            }
            case UNWIND -> {
                return translateUnwind(((UnwindAlge) algebra));
            }
            case EMPTY_SET -> {
                return null;
            }
            case SORT -> {
                return translateSort(((Sort) algebra));
            }
            case TOP -> {
                return translateTop(((Top) algebra));
            }
            case DEDUP -> {
                return translateDedup(((Dedup) algebra));
            }
        }
        return null;
    }

    private UTerm translateDedup(Dedup algebra) {
        if (algebra.algebra instanceof Projection && !(algebra.algebra instanceof Grouping))
            innerDedupFlag = true;
        return USquash.mk(translateAlgebra(algebra.algebra));
    }

    private UTerm translateSort(Sort sort) {
        UTerm body = translateAlgebra(sort.algebra);
        List<UTerm> orders = new ArrayList<>();
        for (int i = 0; i < sort.sortItems.size(); i++) {
            SortItem item = sort.sortItems.get(i);
            String asc = item instanceof SortItem.Asc ? "ASC" : "DSC";
            UVar orderProj = UVar.mkProj(UName.mk(asc), outVar);
            UTerm order = UPred.mkBinary(EQ, UVarTerm.mk(orderProj), exprTranslator.translateExpr(item.expression));
            orders.add(order);
        }
        return UMul.mk(UAdd.mk(orders), body);
    }

    private UTerm translateTop(Top top) {
        Limit limit = top.limit;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            String result = String.valueOf(engine.eval(limit.expression.toString()));
            UVar orderProj = UVar.mkProj(UName.mk("limit"), outVar);
            UTerm eq = UPred.mkBinary(EQ, UVarTerm.mk(orderProj), UConst.mk(Integer.parseInt(result)));
            return UMul.mk(eq, translateAlgebra(top.algebra));
        } catch (ScriptException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<UVar, UTerm> constMap = new HashMap<>();

    private UTerm translateUnwind(UnwindAlge unwind) {
        UTerm body = translateAlgebra(unwind.sub);
        Variable alias = unwind.alias;
        UTerm varTerm = exprTranslator.translateExpr(alias);
        Expression set = unwind.set;
        UTerm listTerm = exprTranslator.translateExpr(set);

        if (listTerm instanceof ListUTerm listUTerm) {
            List<UTerm> orTerms = new ArrayList<>();
            for (List<Pair<String, UTerm>> element : listUTerm.elements) {
                List<UTerm> andTerms = new ArrayList<>();
                for (Pair<String, UTerm> stringUTermPair : element) {
                    UTerm re;
                    if (stringUTermPair.getLeft() == null) {
                        re = UPred.mkBinary(
                                EQ,
                                varTerm,
                                stringUTermPair.getRight()
                        );
                    } else {
                        UTerm proVar = UVarTerm.mk(UVar.mkProj(UName.mk(stringUTermPair.getLeft()), ((UVarTerm) varTerm).var()));
                        re = UPred.mkBinary(
                                EQ,
                                proVar,
                                stringUTermPair.getRight()
                        );
                        renameMap.put(proVar, stringUTermPair.getRight());
                    }
                    andTerms.add(re);
                }
                orTerms.add(UMul.mk(andTerms));
            }
            body = UMul.mk(body, UAdd.mk(orTerms));
        } else {
            renameMap.put(varTerm, listTerm);
            body = UMul.mk(UPred.mkBinary(
                    EQ,
                    varTerm, listTerm
            ), body);
        }
//        if (alias.linked.isConstList()) {
//            List<UTerm> listTerms = new ArrayList<>();
//            List<Expression> exprs = ((ListExpression) alias.linked).exprs;
//            if (exprs.isEmpty()) {
//                UTerm term = UConst.zero();
//                listTerms.add(term);
//            }
//            for (Expression expr : exprs) {
//                MapExpression map = ((MapExpression) expr);
//                int colIndex = 1;
//                List<UTerm> rowTerm = new ArrayList<>();
//                for (Pair<PropertyKeyName, Expression> col : map.props) {
//                    String colName = "c" + colIndex;
//                    Expression value = col.getRight();
//                    assert value instanceof Literal;
//                    UVar colVar = UVar.mkProj(UName.mk(colName), UVar.mkBase(UName.mk(alias.name)));
//                    UTerm term = UPred.mkBinary(EQ, UVarTerm.mk(colVar), exprTranslator.translateExpr(value));
//                    constMap.put(colVar, term);
//                    rowTerm.add(term);
//                    colIndex++;
//                }
//                listTerms.add(UMul.mk(rowTerm));
//            }
//            return UMul.mk(body, UAdd.mk(listTerms));
//        }
//        alias.kind = Variable.VarKind.BASE;
        return body;
    }

    private UTerm translateGraph(GraphPatternPart patternPart) {
        if (patternPart.patternKind() == GraphPatternPart.PatternKind.RELATIONSHIP) {
            return translateExpand(((Expand) patternPart));
        }
        if (patternPart.patternKind() == GraphPatternPart.PatternKind.NODE) {
            return translateNode(((Node) patternPart));
        }
        throw new CypherUExprTranslationError("GraphPatternPart type error");
    }

    private Variable getLinked(Variable variable) {
        while (variable.linked != null) {
            if (variable.linked instanceof Variable) {
                variable = ((Variable) variable.linked);
            } else
                break;
        }
        return variable;
    }

    private UTerm translateNode(Node node) {
//        node.var = getLinked(node.var);
        UVar nVar = UVar.mkBase(UName.mk(node.var.name));
        sumBounds.add(nVar);
        if (solidPattern) {
            solidVars.add(nVar);
        } else {
            if (!solidVars.contains(nVar)) {
                optionalVars.add(nVar);
            }
        }
        UTerm n = Cypher2UExprHelper.node(nVar);
        List<UTerm> labels = new ArrayList<>();
        for (String type : node.labels) {
            labels.add(Cypher2UExprHelper.label(nVar, type));
        }
        if (!labels.isEmpty())
            n = UMul.mk(n, UAdd.mk(labels));
        if (node.constraint != null) {
            UTerm cond = exprTranslator.translateExpr(node.constraint);
            n = UMul.mk(n, cond);
        }
        return n;
    }

    private UTerm translateExpand(Expand expand) {
        expand.var = getLinked(expand.var);
        UVar var = UVar.mkBase(UName.mk(expand.var.name));
        UTerm rel = Cypher2UExprHelper.rel(var);
        UTerm right = translateAlgebra(expand.end);
        UTerm left = translateAlgebra(expand.start);
        UVar leftEnd = UVar.mkBase(UName.mk(expand.start.var.name));
        UVar rightEnd = UVar.mkBase(UName.mk(expand.end.var.name));
        sumBounds.add(var);
        if (solidPattern) {
            solidVars.add(var);
        } else {
            if (!solidVars.contains(var)) {
                optionalVars.add(var);
            }
        }
//        sumBounds.remove(leftEnd);
//        sumBounds.remove(rightEnd);
        UTerm out;
        UTerm in;
        if (expand.getDirection() == Expand.Direction.OUT) {
            out = UPred.mkBinary(UPred.PredKind.EQ, leftEnd, Cypher2UExprHelper.relIn(var));
            in = UPred.mkBinary(UPred.PredKind.EQ, rightEnd, Cypher2UExprHelper.relOut(var));
        } else {
            out = UPred.mkBinary(UPred.PredKind.EQ, rightEnd, Cypher2UExprHelper.relIn(var));
            in = UPred.mkBinary(UPred.PredKind.EQ, leftEnd, Cypher2UExprHelper.relOut(var));
        }
        rel = UMul.mk(rel, out, in);
        List<UTerm> types = new ArrayList<>();
        for (String type : expand.labels) {
            types.add(Cypher2UExprHelper.label(var, type));
        }
        if (!types.isEmpty())
            rel = UMul.mk(rel, UAdd.mk(types));
        if (expand.constraint != null) {
            UTerm cond = exprTranslator.translateExpr(expand.constraint);
            rel = UMul.mk(rel, cond);
        }
        UTerm inner = translateAlgebra(expand.algebra);
        return UMul.mk(left, rel, right, inner);
    }

    // (Node(x8) * Rel(x9) * [x1 = in(x9)] * [x8 = out(x9)] * Node(x1) * Rel(x2) * [x1 = in(x2)] * [x3 = out(x2)] * Node(x3) * Rel(x4) * [x5 = in(x4)] * [x3 = out(x4)] * Node(x5) * Node(x6) * Rel(x7) * [x6 = in(x7)] * [x8 = out(x7)] * [Person(x5) = 1] * [name(x5) = '"Tom Hanks"'] * [ACTED_IN(x4) = 1] * [ACTED_IN(x2) = 1] * [ACTED_IN(x9) = 1] * [Person(x6) = 1] * [name(x6) = '"Tom Cruise"'] * [ACTED_IN(x7) = 1] * [queryTuple = name(x1)])||
    private UTerm translateBagUnion(BUnion bagUnion) {
        UTerm left = translateAlgebra(bagUnion.left);
        sumBounds = new HashSet<>();
        UTerm right = translateAlgebra(bagUnion.right);
        return UAdd.mk(left, right);
    }

    private UTerm translateUnion(Union union) {
        UTerm left = translateAlgebra(union.left);
        sumBounds = new HashSet<>();
        UTerm right = translateAlgebra(union.right);
        return USquash.mk(UAdd.mk(left, right));
    }

    private UTerm translateLOJ(LOJoin loJoin) {
        GraphAlgebra left = loJoin.left;
        GraphAlgebra right = loJoin.right;
        UTerm lTerm = translateAlgebra(left);
        solidPattern = false;
        UTerm rTerm = translateAlgebra(right);
        solidPattern = true;
        if (!optionalVars.isEmpty())
            rTerm = USum.mk(optionalVars, rTerm);
        UTerm absenceCond = UNeg.mk(rTerm.copy());
        for (UVar var : optionalVars) {
            absenceCond.replaceVar(var, UVar.mkBase(UName.mk(Liastar.newVarName())), true);
            if (!(getRootTerm(UVarTerm.mk(var)) instanceof UConst)) {
                absenceCond = UMul.mk(absenceCond, UExprSupport.mkIsNullPred(var));
            }
        }
        rTerm = UAdd.mk(rTerm, absenceCond);
        if (lTerm != null) {
            return UMul.mk(lTerm, rTerm);
        } else {
            return rTerm;
        }
    }

    private UTerm translateJoin(Join join) {
        UTerm left = translateAlgebra(join.left);
        UTerm right = translateAlgebra(join.right);
        return UMul.mk(left, right);
    }

    private UTerm parseCase(UTerm term1) {
        if (term1 instanceof CaseUTerm caseUTerm) {
            List<UTerm> terms = new ArrayList<>();
            for (Pair<UTerm, UTerm> termPair : caseUTerm.caseMap) {
                terms.add(UMul.mk(termPair.getLeft(), termPair.getRight()));
            }
            term1 = UAdd.mk(terms);
        } else {
            if (term1 instanceof UPred pred) {
                if (UExprSupport.varIsNullPred(pred)) {
                    List<UTerm> subTerms = new ArrayList<>();
                    UTerm sub = pred.subTerms().get(0);
                    if (sub instanceof CaseUTerm caseUTerm) {
                        List<UTerm> terms = new ArrayList<>();
                        for (Pair<UTerm, UTerm> termPair : caseUTerm.caseMap) {
                            terms.add(UMul.mk(termPair.getLeft(), mkIsNullPred(termPair.getRight())));
                        }
                        subTerms.add(UAdd.mk(terms));
                    }
                    term1 = UAdd.mk(subTerms);
                } else {
                    List<UTerm> subTerms = new ArrayList<>();
                    for (UTerm subTerm : pred.subTerms()) {
                        if (subTerm instanceof CaseUTerm caseUTerm) {
                            List<UTerm> terms = new ArrayList<>();
                            for (Pair<UTerm, UTerm> termPair : caseUTerm.caseMap) {
                                terms.add(UMul.mk(termPair.getLeft(), termPair.getRight()));
                            }
//                        for (UTerm keyTerm : caseUTerm.caseMap.keySet()) {
//                            terms.add(UMul.mk(keyTerm, caseUTerm.caseMap.get(keyTerm)));
//                        }
                            subTerms.add(UAdd.mk(terms));
                        } else {
                            subTerms.add(subTerm);
                        }
                    }
                    term1 = remakeTerm(term1, subTerms);
                }
            }
            if (term1 instanceof USquash squash) {
                List<UTerm> subTerms = new ArrayList<>();
                for (UTerm term : squash.subTerms()) {
                    subTerms.add(parseCase(term));
                }
                term1 = remakeTerm(term1, subTerms);
            }
            if (term1 instanceof UAdd add) {
                List<UTerm> subTerms = new ArrayList<>();
                for (UTerm term : add.subTerms()) {
                    subTerms.add(parseCase(term));
                }
                term1 = remakeTerm(term1, subTerms);
            }
        }
        return term1;
    }

    private Pair<UVar, UVar> parseEQ(UTerm term1, UTerm term){
        if (term1 instanceof UPred pred) {
            if (((UPred) term1).isPredKind(EQ)) {
                if (pred.subTerms().get(0).kind().isVarTerm() && pred.subTerms().get(1).kind().isVarTerm()) {
                    UVar var1 = ((UVarTerm) pred.subTerms().get(0)).var();
                    UVar var2 = ((UVarTerm) pred.subTerms().get(1)).var();
                    if (sumBounds.contains(var1)) {
                        renameMap.put(pred.subTerms().get(1), pred.subTerms().get(0));
                        sumBounds.remove(var2);
                        return new Pair<>(var2, var1);
                    } else if (sumBounds.contains(var2)) {
                        renameMap.put(pred.subTerms().get(1), pred.subTerms().get(1));
                        sumBounds.remove(var1);
                        return new Pair<>(var1, var2);
                    }
                }
            }
        } else {
            for (UTerm subTerm : term1.subTerms()) {
                Pair<UVar, UVar> pair = parseEQ(subTerm, term);
                if (pair != null) {
                    return pair;
                }
            }
        }
        return null;
    }
    private UTerm translateSelection(Selection selection) {
        UTerm term = translateAlgebra(selection.relation);
        UTerm term1 = exprTranslator.translateExpr(selection.pred);
        term1 = parseCase(term1);
        Pair<UVar, UVar> eqVar = parseEQ(term1, term);
        if (eqVar != null) {
//            term=term.replaceVar(eqVar.getLeft(), eqVar.getRight(), false);
//            term1=term1.replaceVar(eqVar.getLeft(), eqVar.getRight(), false);
            eqVars.add(eqVar);
        }
        return UMul.mk(term, term1);
    }

    private UTerm translateProj(Projection projection) {

        if (projection instanceof Grouping grouping) {
            return translateGrouping(grouping);
        } else {
            return translateCommonProj(projection);
        }
    }

    public static class ListUTerm implements UTerm {
        List<List<Pair<String, UTerm>>> elements = new ArrayList<>();

        public void addElement(List<Pair<String, UTerm>> ele) {
            elements.add(ele);
        }

        public List<List<Pair<String, UTerm>>> getElements() {
            return elements;
        }

        @Override
        public UTerm copy() {
            return this;
        }

        @Override
        public UKind kind() {
            return UKind.CONST;
        }

        @Override
        public List<UTerm> subTerms() {
            return Collections.emptyList();
        }

        @Override
        public boolean isUsing(UVar var) {
            return false;
        }

        @Override
        public boolean isUsingProjVar(UVar var) {
            return false;
        }

        @Override
        public UTerm replaceVar(UVar baseVar, UVar repVar, boolean freshVar) {
            return null;
        }

        @Override
        public boolean replaceVarInplace(UVar baseVar, UVar repVar, boolean freshVar) {
            return false;
        }

        @Override
        public boolean replaceVarInplaceWOPredicate(UVar baseVar, UVar repVar) {
            return false;
        }

        @Override
        public UTerm replaceAtomicTermExcept(UTerm baseTerm, UTerm repTerm, UTerm exceptTerm) {
            return null;
        }

        @Override
        public UTerm replaceAtomicTerm(UTerm baseTerm, UTerm repTerm) {
            return null;
        }

        @Override
        public void prettyPrint(AbstractPrettyPrinter printer) {
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < elements.size(); i++) {
                List<Pair<String, UTerm>> innerList = elements.get(i);
                sb.append("{");
                for (int j = 0; j < innerList.size(); j++) {
                    Pair<String, UTerm> pair = innerList.get(j);
                    sb.append(pair.getLeft())
                            .append(": ")
                            .append(pair.getRight());
                    if (j < innerList.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("}");
                if (i < elements.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        @Override
        public boolean isPrettyPrintMultiLine() {
            return false;
        }

        @Override
        public int hashForSort(Map<String, Integer> varHash) {
            return 0;
        }

        @Override
        public void sortCommAssocItems() {

        }

        @Override
        public Set<String> getFVs() {
            return null;
        }

        @Override
        public boolean groupSimilarVariables(UTerm that, SetMatching<String> matching) {
            return false;
        }
    }

    public static class CaseUTerm implements UTerm {
        List<Pair<UTerm, UTerm>> caseMap = new ArrayList<>();

        public CaseUTerm() {
        }


        public void addThenforWhen(Pair<UTerm, UTerm> thenWhen) {
            caseMap.add(thenWhen);
        }

        @Override
        public UTerm copy() {
            return this;
        }

        @Override
        public UKind kind() {
            return ADD;
        }

        @Override
        public List<UTerm> subTerms() {
            return Collections.emptyList();
        }

        @Override
        public boolean isUsing(UVar var) {
            return false;
        }

        @Override
        public boolean isUsingProjVar(UVar var) {
            return false;
        }

        @Override
        public UTerm replaceVar(UVar baseVar, UVar repVar, boolean freshVar) {
            return null;
        }

        @Override
        public boolean replaceVarInplace(UVar baseVar, UVar repVar, boolean freshVar) {
            return false;
        }

        @Override
        public boolean replaceVarInplaceWOPredicate(UVar baseVar, UVar repVar) {
            return false;
        }

        @Override
        public UTerm replaceAtomicTermExcept(UTerm baseTerm, UTerm repTerm, UTerm exceptTerm) {
            return null;
        }

        @Override
        public UTerm replaceAtomicTerm(UTerm baseTerm, UTerm repTerm) {
            return null;
        }

        @Override
        public void prettyPrint(AbstractPrettyPrinter printer) {

        }

        @Override
        public boolean isPrettyPrintMultiLine() {
            return false;
        }

        @Override
        public int hashForSort(Map<String, Integer> varHash) {
            return 0;
        }

        @Override
        public void sortCommAssocItems() {

        }

        @Override
        public Set<String> getFVs() {
            return null;
        }

        @Override
        public boolean groupSimilarVariables(UTerm that, SetMatching<String> matching) {
            return false;
        }
    }

    private UTerm translateProjOp(ProjOp op, UVar var, int index) {
        UTerm colTerm = exprTranslator.translateExpr(op.expr);
        if (op instanceof Rename rename) {
            UTerm alias = UVarTerm.mk(UVar.mkBase(UName.mk(rename.alias.name)));
//                conds.add(UPred.mkBinary(EQ, alias, colTerm));
            renameMap.put(alias, colTerm);
            colTerm = alias;
        }
        UTerm col = getRootTerm(colTerm);
        if (index == -1) {
            if (col instanceof CaseUTerm caseUTerm) {
                List<UTerm> terms = new ArrayList<>();
                for (Pair<UTerm, UTerm> termPair : caseUTerm.caseMap) {
                    UTerm re = UPred.mkBinary(
                            EQ,
                            UVarTerm.mk(var),
                            termPair.getLeft()
                    );
                    UTerm cond = termPair.getRight();
                    terms.add(UMul.mk(re, cond));
                }
//                for (UTerm term : caseUTerm.caseMap.keySet()) {
//                    UTerm re = UPred.mkBinary(
//                            EQ,
//                            UVarTerm.mk(var),
//                            term
//                    );
//                    UTerm cond = caseUTerm.caseMap.get(term);
//                    terms.add(UMul.mk(re, cond));
//                }
                return UAdd.mk(terms);
            } else if (col instanceof ListUTerm term) {
//                List<UTerm> orTerms = new ArrayList<>();
//                for (Pair<String, UTerm> element : term.elements) {
//                    UTerm re = UPred.mkBinary(
//                            EQ,
//                            UVarTerm.mk(UVar.mkProj(UName.mk(element.getLeft()), var)),
//                            element.getRight()
//                    );
//                    orTerms.add(re);
//                }
                UTerm varTerm = UVarTerm.mk(var);
                UTerm re = UPred.mkBinary(
                        EQ,
                        varTerm,
                        term
                );
                renameMap.put(varTerm, term);
                return re;
            } else {
                return UPred.mkBinary(
                        EQ,
                        UVarTerm.mk(var),
                        col
                );
            }
        } else {
            UTerm outTerm = UVarTerm.mk(UVar.mkProj(UName.mk("#" + index), var));
            if (col instanceof CaseUTerm caseUTerm) {
                List<UTerm> terms = new ArrayList<>();
                for (Pair<UTerm, UTerm> termPair : caseUTerm.caseMap) {
                    UTerm re = UPred.mkBinary(
                            EQ,
                            outTerm,
                            termPair.getLeft()
                    );
                    UTerm cond = termPair.getRight();
                    terms.add(UMul.mk(re, cond));
                }
//                for (UTerm term : caseUTerm.caseMap.keySet()) {
//                    UTerm re = UPred.mkBinary(
//                            EQ,
//                            outTerm,
//                            term
//                    );
//                    UTerm cond = caseUTerm.caseMap.get(term);
//                    terms.add(UMul.mk(re, cond));
//                }
                return UAdd.mk(terms);
            } else if (col instanceof ListUTerm term) {
                List<UTerm> orTerms = new ArrayList<>();
//                for (Pair<String, UTerm> element : term.elements) {
//                    UTerm re = UPred.mkBinary(
//                            EQ,
//                            UVarTerm.mk(UVar.mkProj(UName.mk(element.getLeft()), var)),
//                            element.getRight()
//                    );
//                    orTerms.add(re);
//                }
                UTerm re = UPred.mkBinary(
                        EQ,
                        outTerm,
                        term
                );
                renameMap.put(outTerm, re);
                return re;
            } else {
                return UPred.mkBinary(
                        EQ,
                        outTerm,
                        col
                );
            }
        }
    }

    private UTerm translateGrouping(Grouping grouping) {
        UVar currentVar;
        List<Pair<Integer, Integer>> map = maps.peek();
        if (grouping.isOuter()) {
            currentVar = outVar;
        } else {
            currentVar = UVar.mkBase(UName.mk(Variable.getValidName()));
        }
        List<GroupKey> keys = grouping.keys;
        List<ProjOp> projOps = grouping.projs;
//        List<UTerm> keyTerms = new ArrayList<>();
//        List<UTerm> projTerms = new ArrayList<>();
        UTerm body = translateAlgebra(grouping.relation).copy();
        Set<UVar> currentBounds = new HashSet<>(sumBounds);
//        for (ProjOp key : keys) {
//            if (key instanceof ProjExpr expr) {
//                keyTerms.add(exprTranslator.translateExpr(expr.expr));
//            } else if (key instanceof Rename rename) {
//                UTerm left = exprTranslator.translateExpr(rename.alias);
//                UTerm right = exprTranslator.translateExpr(rename.expr);
//                keyTerms.add(right);
//                renameMap.put(left, right);
//            }
//        }
        UTerm groupingTerm = null;
        for (int i = 0; i < keys.size(); i++) {
            GroupKey op = keys.get(i);

//            if (op instanceof Rename rename) {
//                UTerm left = exprTranslator.translateExpr(rename.alias);
//                UTerm right = exprTranslator.translateExpr(rename.expr);
//                renameMap.put(left, right);
//                keyTerm = right;
//            } else {
//                keyTerm = exprTranslator.translateExpr(op.expr);
//            }
            int index = op.index;
            if (grouping.isOuter() && (map != null))
                index = isKey ? map.get(op.index).getLeft() : map.get(op.index).getRight();
            UTerm keyTerm = translateProjOp(op.op, currentVar, index);
//            UTerm left = UVarTerm.mk(UVar.mkProj(UName.mk("#%d".formatted(index)), currentVar));

            if (groupingTerm == null) {
                groupingTerm = keyTerm;
            } else groupingTerm = UMul.mk(keyTerm, groupingTerm);
        }
        List<UTerm> conds = new ArrayList<>();

        for (int i = 0; i < projOps.size(); i++) {
            int index = i;
            if (grouping.isOuter()) {
                if ((map != null))
                    index = isKey ? map.get(i).getLeft() : map.get(i).getRight();
            }
            ProjOp projOp = projOps.get(i);
            UTerm colTerm = null;
            if (projOp.expr.isAggExpression()) {
                FunctionInvocation func = ((FunctionInvocation) projOp.expr);
                Expression arg = func.args.get(0);
                UTerm aggPara = null;
                if (!(arg instanceof Literal.Star)) {
                    aggPara = exprTranslator.translateExpr(arg);
//                    for (UVar key : varMap.keySet()) {
//                        aggPara = aggPara.replaceVar(key, varMap.get(key), true);
//                    }
                }
                if (func.functionName.name.equals("SUM")) {
                    if (groupingTerm == null) {
                        body = UMul.mk(body, aggPara);
                    } else
                        body = UMul.mk(body, aggPara, groupingTerm);
                    if (func.distinct) {
                        body = USquash.mk(body);
                    }
                    colTerm = USum.mk(currentBounds, body);
                }
                if (func.functionName.name.equals("COUNT")) {
                    if (groupingTerm == null) {
                        if (aggPara != null) {
                            aggPara = mkNotNullPred(aggPara);
                            body = UMul.mk(body, aggPara);
                        }
                    } else {
                        if (aggPara != null) {
                            aggPara = mkNotNullPred(aggPara);
                            body = UMul.mk(body, aggPara, groupingTerm);
                        } else
                            body = UMul.mk(body, groupingTerm);
                    }
                    colTerm = USum.mk(currentBounds, body);
                    if (func.distinct) {
                        colTerm = USquash.mk(colTerm);
                    }
//                    conds.add(USquash.mk(colTerm));
                }
                if (func.functionName.name.equals("MAX")) {
                    UTerm var = UVarTerm.mk(UVar.mkProj(UName.mk("#%d".formatted(index)), currentVar));
                    UTerm cond = USum.mk(currentBounds, UMul.mk(body, UPred.mkBinary(GT, aggPara, var)));
                    colTerm = var;
                    conds.add(UNeg.mk(cond));
                }
                if (func.functionName.name.equals("MIN")) {
                    UTerm var = UVarTerm.mk(UVar.mkBase(UName.mk(Variable.getValidName())));
                    UTerm cond = USum.mk(currentBounds, UMul.mk(body, UPred.mkBinary(LT, aggPara, var)));
                    colTerm = var;
                    conds.add(UNeg.mk(cond));
                }
                if (func.functionName.name.equals("COLLECT")) {
                    UVar var = UVar.mkBase(UName.mk(Variable.getValidName()));
                    if (groupingTerm == null) {
                        if (aggPara != null) {
                            aggPara = mkNotNullPred(aggPara);
                            body = UMul.mk(body, aggPara);
                        }
                    }
                    conds.add(UPred.mkFunc(UName.mk("COLLECT"), var));
                }
                sumBounds.clear();
            } else {
                colTerm = exprTranslator.translateExpr(projOp.expr);
            }
//            if (projOp instanceof Rename rename) {
//                UTerm alias = UVarTerm.mk(UVar.mkBase(UName.mk(rename.alias.name)));
//                conds.add(UPred.mkBinary(EQ, alias, colTerm));
//                projTerms.add(alias);
//            }else
//            if (projOp instanceof Rename rename) {
//                UTerm alias = UVarTerm.mk(UVar.mkBase(UName.mk(rename.alias.name)));
//                renameMap.put(alias, colTerm);
//                projTerms.add(alias);
//            } else {
//                projTerms.add(colTerm);
//            }


            if (grouping.isOuter()) {
//                UTerm retTerm = translateProjOp(projOp, currentVar, index);
                UTerm retTerm;
                if (projOps.size() == 1) {
                    UTerm outTerm = UVarTerm.mk(currentVar);
                    if (colTerm instanceof CaseUTerm caseUTerm) {
                        List<UTerm> terms = new ArrayList<>();
                        for (Pair<UTerm, UTerm> termPair : caseUTerm.caseMap) {
                            UTerm re = UPred.mkBinary(
                                    EQ,
                                    outTerm,
                                    termPair.getLeft()
                            );
                            UTerm cond = termPair.getRight();
                            terms.add(UMul.mk(re, cond));
                        }
//                        for (UTerm term : caseUTerm.caseMap.keySet()) {
//                            UTerm re = UPred.mkBinary(
//                                    EQ,
//                                    outTerm,
//                                    term
//                            );
//                            UTerm cond = caseUTerm.caseMap.get(term);
//                            terms.add(UMul.mk(re, cond));
//                        }
                        retTerm = UPred.mkBinary(
                                EQ,
                                UVarTerm.mk(currentVar),
                                UAdd.mk(terms));
                    } else {
                        retTerm = UPred.mkBinary(
                                EQ,
                                UVarTerm.mk(currentVar),
                                colTerm
                        );
                    }
                } else {
                    UTerm outTerm = UVarTerm.mk(UVar.mkProj(UName.mk("#" + index), outVar));
                    UTerm col = getRootTerm(colTerm);
                    if (colTerm instanceof CaseUTerm caseUTerm) {
                        List<UTerm> terms = new ArrayList<>();
                        for (Pair<UTerm, UTerm> termPair : caseUTerm.caseMap) {
                            UTerm re = UPred.mkBinary(
                                    EQ,
                                    outTerm,
                                    termPair.getLeft()
                            );
                            UTerm cond = termPair.getRight();
                            terms.add(UMul.mk(re, cond));
                        }
//                        for (UTerm term : caseUTerm.caseMap.keySet()) {
//                            UTerm re = UPred.mkBinary(
//                                    EQ,
//                                    outTerm,
//                                    term
//                            );
//                            UTerm cond = caseUTerm.caseMap.get(term);
//                            terms.add(UMul.mk(re, cond));
//                        }
                        retTerm = UAdd.mk(terms);
                    } else {
                        retTerm = UPred.mkBinary(
                                EQ,
                                outTerm,
                                col
                        );
                        renameMap.put(outTerm, col);
                    }
                }
                conds.add(retTerm);
            } else {
                tmpVars.add(currentVar);
                UTerm retTerm;
                if (projOps.size() == 1) {
                    retTerm = translateProjOp(projOp, currentVar, -1);
                } else {
                    retTerm = translateProjOp(projOp, currentVar, i);
                }
                conds.add(retTerm);
                sumBounds.add(currentVar);
            }
        }
        if (sumBounds.size() > 0 && grouping.isOuter()) {
            return USquash.mk(USum.mk(sumBounds, UMul.mk(conds)));
        }
//        if (grouping.isOuter()) {
//            for (int i = 0; i < projTerms.size(); i++) {
//                int index;
//                if ((map == null)) {
//                    index = i;
//                } else
//                    index = isKey ? map.get(i).getLeft() : map.get(i).getRight();
//                UTerm outTerm = UVarTerm.mk(UVar.mkProj(UName.mk("#" + index), outVar));
//                UTerm col = getRootTerm(projTerms.get(i));
////                if (col.isUsing(currentVar)) {
////                    continue;
////                }
//                UTerm retTerm = UPred.mkBinary(
//                        EQ,
//                        outTerm,
//                        col
//                );
//                conds.add(retTerm);
//                renameMap.put(outTerm, col);
//            }
//            if (sumBounds.size() > 0) {
//                return USquash.mk(USum.mk(sumBounds, UMul.mk(conds)));
//            }
//        } else {
//            UVar var = currentVar;
//            tmpVars.add(currentVar);
//            if (projTerms.size() == 1) {
//                UTerm outTerm = UVarTerm.mk(var);
//                UTerm retTerm = UPred.mkBinary(
//                        EQ,
//                        outTerm,
//                        projTerms.get(0)
//                );
//                renameMap.put(projTerms.get(0), outTerm);
//                conds.add(retTerm);
//                colMaps.put(getRootTerm(projTerms.get(0)), outTerm);
//            } else
//                for (int i = 0; i < projTerms.size(); i++) {
//                    UTerm outTerm = UVarTerm.mk(UVar.mkProj(UName.mk("#" + i), var));
//                    UTerm retTerm = UPred.mkBinary(
//                            EQ,
//                            outTerm,
//                            getRootTerm(projTerms.get(i))
//                    );
//                    renameMap.put(outTerm, projTerms.get(i));
//                    conds.add(retTerm);
//                    colMaps.put(projTerms.get(i), outTerm);
//                }
//            sumBounds.add(var);
//        }
        return UMul.mk(conds);
    }

    private UTerm translateCommonProj(Projection projection) {
        UTerm body = translateAlgebra(projection.relation);
//        List<UTerm> projTerms = new ArrayList<>();
        List<UTerm> conds = new ArrayList<>();
        if (body != null)
            conds.add(body);
//        for (ProjOp projOp : projOps) {
//            UTerm colTerm = exprTranslator.translateExpr(projOp.expr);
//            if (projOp instanceof Rename rename) {
//                UTerm alias = UVarTerm.mk(UVar.mkBase(UName.mk(rename.alias.name)));
////                conds.add(UPred.mkBinary(EQ, alias, colTerm));
//                renameMap.put(alias, colTerm);
//                projTerms.add(alias);
//            } else
//                projTerms.add(colTerm);
//        }
        if (projection.isOuter()) {
            if (projection.projs.size() == 1) {
                ProjOp projOp = projection.projs.get(0);
                UTerm retTerm = translateProjOp(projOp, outVar, -1);
//                UTerm col = getRootTerm(projTerm);
//                UTerm retTerm = UPred.mkBinary(
//                        EQ,
//                        UVarTerm.mk(outVar),
//                        col
//                );
                conds.add(retTerm);
            } else {
                for (int i = 0; i < projection.projs.size(); i++) {
                    List<Pair<Integer, Integer>> map = maps.peek();
                    int index;
                    if ((map == null)) {
                        index = i;
                    } else
                        index = isKey ? map.get(i).getLeft() : map.get(i).getRight();
                    ProjOp projOp = projection.projs.get(i);
                    UTerm retTerm = translateProjOp(projOp, outVar, index);
//                    UTerm projTerm = translateProjOp(projOp);
//                    UTerm col = getRootTerm(projTerm);
//                    UTerm outTerm = UVarTerm.mk(UVar.mkProj(UName.mk("#" + index), outVar));
//                    UTerm retTerm = UPred.mkBinary(
//                            EQ,
//                            outTerm,
//                            col
//                    );
//                renameMap.put(col, outTerm);
                    conds.add(retTerm);
                }
            }
            if (sumBounds.size() > 0) {
                UTerm result = USum.mk(sumBounds, UMul.mk(conds));
//                if (innerDedupFlag) {
//                    return USquash.mk(result);
//                }
                return result;
            } else {
//                if (innerDedupFlag) {
//                    return USquash.mk(UMul.mk(conds));
//                }
                return UMul.mk(conds);
            }

        } else {
            UVar curVar = UVar.mkBase(UName.mk(Variable.getValidName()));
            tmpVars.add(curVar);
            if (projection.projs.size() == 1) {
                UTerm outTerm = UVarTerm.mk(curVar);
                ProjOp projOp = projection.projs.get(0);
                UTerm retTerm = translateProjOp(projOp, curVar, -1);
//                UTerm projTerm = translateProjOp(projOp);
//                UTerm col = getRootTerm(projTerm);
//                UTerm retTerm = UPred.mkBinary(
//                        EQ,
//                        outTerm,
//                        col
//                );
//                renameMap.put(outTerm, projTerm);
                conds.add(retTerm);
            } else {
                for (int i = 0; i < projection.projs.size(); i++) {
                    UTerm outTerm = UVarTerm.mk(UVar.mkProj(UName.mk("#" + i), curVar));
                    ProjOp projOp = projection.projs.get(i);
                    UTerm retTerm = translateProjOp(projOp, curVar, i);
//                    UTerm projTerm = translateProjOp(projOp);
//                    UTerm col = getRootTerm(projTerm);
//                    UTerm retTerm = UPred.mkBinary(
//                            EQ,
//                            outTerm,
//                            col
//                    );
//                    renameMap.put(outTerm, projTerm);
                    conds.add(retTerm);
                }
            }
            Set<UVar> currentBounds = new HashSet<>(sumBounds);
            sumBounds.clear();
            sumBounds.add(curVar);
            if (currentBounds.isEmpty())
                return UMul.mk(conds);
            USum sum = USum.mk(currentBounds, UMul.mk(conds));
            return sum;
        }
    }

    private UTerm getRootTerm(UTerm term) {
        UTerm r = term;
        while (renameMap.containsKey(r)) {
            r = renameMap.get(r);
        }
        return r;
    }

    private UTerm translateCommonProjOld(Projection projection) {
        UTerm body = translateAlgebra(projection.relation);
        this.body = body;
        List<UTerm> retTerms = new ArrayList<>();
        for (int i = 0; i < projection.projs.size(); i++) {
            ProjOp op = projection.projs.get(i);
            UTerm retExpr = null;
            if (projection.isOuter()) {
                List<Pair<Integer, Integer>> map = maps.peek();
                int index;
                if ((map == null)) {
                    index = i;
                } else
                    index = isKey ? map.get(i).getLeft() : map.get(i).getRight();
                UTerm outTerm = UVarTerm.mk(UVar.mkProj(UName.mk("#" + index), outVar));
                Cypher2UExpr.outTerm = outTerm;
                if (op.projKind() == ProjOp.ProjKind.RENAME) {
                    Expression ret = ((Rename) op).expr;
                    Variable var = ((Rename) op).alias;
                    try {
                        UTerm src = exprTranslator.translateExpr(ret);
                        UTerm tgt = UVarTerm.mk(UVar.mkBase(UName.mk(var.name)));
//                    retTerms.add(alias);
                        if (ret.isAggExpression()) {
                            retExpr = translateAgg(outTerm, ret);
                            retTerms.add(retExpr);
                            continue;
                        } else
                            retExpr = exprTranslator.translateExpr(ret);
                    } catch (RuntimeException runtimeException) {

                    }
                }
                if (op.projKind() == ProjOp.ProjKind.EXPR) {
                    Expression ret = ((ProjExpr) op).expr;
                    if (ret instanceof Literal.Null) {
                        ret = new Unary.IsNull(new Property(new Variable(outVar.name().toString()), new PropertyKeyName("#" + (i + 1))));
                        retExpr = exprTranslator.translateExpr(ret);
                        Cypher2UExpr.completed = true;
                    } else if (ret.isAggExpression()) {
                        retExpr = translateAgg(outTerm, ret);
                        retTerms.add(retExpr);
                        continue;
                    } else
                        retExpr = exprTranslator.translateExpr(ret);
                }
                if (!Cypher2UExpr.completed)
                    retTerms.add(UPred.mkBinary(
                            EQ,
                            outTerm,
                            retExpr
                    ));
                else {
                    Cypher2UExpr.completed = false;
                    retTerms.add(retExpr);
                }
            }
        }
        UTerm proj;
        if (body == null) {
            proj = UMul.mk(retTerms);
        } else {
            proj = UMul.mk(body, retTerms);
        }
        if (projection.isOuter() && (sumBounds.size() > 1 || peel)) {
            UTerm sum = USum.mk(new HashSet<>(sumBounds), proj);
            sumBounds.clear();
            return sum;
        } else {
            return proj;
        }
    }

    public UTerm translateAgg(UTerm tr, Expression aggExpr) {
        if (aggExpr instanceof Binary.Multiply multiply) {
            return UMul.mk(translateAgg(tr, multiply.lhs), translateAgg(tr, multiply.rhs));
        } else if (aggExpr instanceof Binary.Divide div) {
            return UFunc.mk(
                    UFunc.FuncKind.INTEGER, UName.mk("divide"),
                    Arrays.asList(translateAgg(tr, div.lhs), translateAgg(tr, div.rhs)));
        } else if (aggExpr instanceof Binary.Add add) {
            return UAdd.mk(translateAgg(tr, add.lhs), translateAgg(tr, add.rhs));
        } else if (aggExpr instanceof Binary.Subtract sub) {
            return UFunc.mk(
                    UFunc.FuncKind.INTEGER, UName.mk("minus"),
                    Arrays.asList(translateAgg(tr, sub.lhs), translateAgg(tr, sub.rhs)));
        } else {
            FunctionInvocation func = ((FunctionInvocation) aggExpr);
            Expression arg = func.args.get(0);
            if (arg instanceof Variable variable && variable.linked != null) {
                arg = variable.linked;
            }
            if (arg instanceof FunctionInvocation functionInvocation) {
                functionInvocation.distinct = true;
                return translateAgg(tr, functionInvocation);
            }
            if (func.functionName.name.equals("COUNT")) {
                UTerm cond = body.copy();
                if (!arg.toString().equals("*")) {
                    UVar agg;
                    if (arg instanceof Variable) {
                        agg = UVar.mkBase(UName.mk(((Variable) arg).name));
                    } else {
                        if (arg instanceof ListExpression listExpression) {
                            return UConst.mk(listExpression.exprs.size());
                        }
                        assert arg instanceof Property || (arg instanceof Variable && ((Variable) arg).linked instanceof Property);
                        if (!(arg instanceof Property)) {
                            arg = ((Property) ((Variable) arg).linked);
                        }
                        UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                        agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                    }
                    if (func.distinct) {

                    }
                    if (cond instanceof USum sum) {
                        sum.addMulSubTerm(mkNotNullPred(agg));
                    } else {
                        cond = UMul.mk(UPred.mkBinary(EQ, UVarTerm.mk(agg), tr), mkNotNullPred(agg));
                        cond = USum.mk(sumBounds, cond).copy();
                    }
                } else {
                    System.out.println();
                }
                cond = UMul.mk(UPred.mkBinary(EQ, cond, tr));
                cond = exprTranslator.replaceAllBoundedVars(cond);
                return cond;
            } else if (func.functionName.name.equals("SUM")) {
                UTerm cond = body.copy();
                UVar agg;
                if (arg instanceof Variable variable) {
                    agg = UVar.mkBase(UName.mk(variable.name));
                } else {
                    assert arg instanceof Property;
                    assert ((Property) arg).map instanceof Variable;
                    UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                    agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                }
                if (func.distinct) {

                }
                UTerm sumTerm = UMul.mk(mkNotNullPred(agg), UVarTerm.mk(agg), UPred.mkBinary(EQ, UVarTerm.mk(agg), tr));
                if (cond instanceof USum sum) {
                    sum.addMulSubTerm(sumTerm);
                } else {
                    cond = UMul.mk(cond, sumTerm);
                }
                cond = UMul.mk(UPred.mkBinary(EQ, cond, tr));
                cond = exprTranslator.replaceAllBoundedVars(cond);
                return cond;
            } else if (func.functionName.name.equals("AVG")) {
                FunctionInvocation SUM = func.copy();
                FunctionInvocation COUNT = new FunctionInvocation(new FunctionName("COUNT"), false,
                        List.of(new Literal.Star()));
                SUM.functionName.name = "SUM";
                COUNT.functionName.name = "COUNT";
                UTerm sumTerm = translateAgg(tr, SUM);
                UTerm cntTerm = translateAgg(tr, COUNT);
                return UFunc.mk(UFunc.FuncKind.INTEGER, UName.mk("divide"), Arrays.asList(sumTerm, cntTerm));
//                return UPred.mkBinary(EQ,
//                        UMul.mk(tr, cntTerm),
//                        sumTerm
//                );
            } else if (func.functionName.name.equals("MAX")) {
                if (func.isLiteralFunc()) {
                    return UPred.mkBinary(EQ, exprTranslator.parseExpr2UTerm(func.args.get(0)), tr);
                }
                UTerm cond1 = body.copy();
                UTerm cond2 = body.copy();
                if (arg instanceof Literal.Integer integer) {
                    return UConst.mk((int) integer.value);
                }
                assert ((Property) arg).map instanceof Variable;
                UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                UVar agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                UTerm subCond1 = UPred.mkBinary(UPred.PredKind.GT, UVarTerm.mk(agg), tr);
                UTerm subCond2 = UPred.mkBinary(EQ, UVarTerm.mk(agg), tr);
                if (cond1 instanceof USum sum) {
                    sum.addMulSubTerm(subCond1);
                } else {
                    cond1 = UMul.mk(cond1, subCond1);
                }
                if (cond2 instanceof USum sum) {
                    sum.addMulSubTerm(subCond2);
                } else {
                    cond2 = UMul.mk(cond2, subCond2);
                }
                System.err.println(cond1);
                System.err.println(cond2);
                cond1 = exprTranslator.replaceAllBoundedVars(cond1);
                cond2 = exprTranslator.replaceAllBoundedVars(cond2);
                UTerm cond = UMul.mk(UNeg.mk(cond1), USquash.mk(cond2));
                cond = UMul.mk(UPred.mkBinary(EQ, cond, tr));
                return cond;
            } else if (func.functionName.name.equals("MIN")) {
                UTerm cond1 = body.copy();
                UTerm cond2 = body.copy();
                assert arg instanceof Property;
                assert ((Property) arg).map instanceof Variable;
                UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                UVar agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                UTerm subCond1 = UPred.mkBinary(LT, UVarTerm.mk(agg), tr);
                UTerm subCond2 = UPred.mkBinary(EQ, UVarTerm.mk(agg), tr);
                if (cond1 instanceof USum sum) {
                    sum.addMulSubTerm(subCond1);
                } else {
                    cond1 = UMul.mk(cond1, subCond1);
                }
                if (cond2 instanceof USum sum) {
                    sum.addMulSubTerm(subCond2);
                } else {
                    cond2 = UMul.mk(cond2, subCond2);
                }
                System.err.println(cond1);
                System.err.println(cond2);
                cond1 = exprTranslator.replaceAllBoundedVars(cond1);
                cond2 = exprTranslator.replaceAllBoundedVars(cond2);
                UTerm cond = UMul.mk(UNeg.mk(cond1), USquash.mk(cond2));
                cond = UMul.mk(UPred.mkBinary(EQ, cond, tr));
                return cond;
            }
        }
        return null;
    }
}
