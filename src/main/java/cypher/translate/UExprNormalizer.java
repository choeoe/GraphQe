package cypher.translate;

import cypher.Pair;
import cypher.algebra.translator.GraphAlgebraTranslator;
import org.apache.xmlbeans.impl.xpath.saxon.SaxonXQuery;
import org.neo4j.cypher.internal.expressions.functions.E;
import sqlsolver.common.NaturalCongruence;
import sqlsolver.liastar.Liastar;
import sqlsolver.uexpr.*;

import java.util.*;
import java.util.function.Function;

import static sqlsolver.common.IterableSupport.any;
import static sqlsolver.common.IterableSupport.linearFind;
import static sqlsolver.common.ListSupport.filter;
import static sqlsolver.uexpr.UExprSupport.*;
import static sqlsolver.uexpr.UKind.*;
import static sqlsolver.uexpr.UPred.PredKind.EQ;
import static sqlsolver.uexpr.UPred.PredKind.LT;

public class UExprNormalizer {
    public boolean isModified;
    public static UTerm globalExpr;

    public List<UVar> tmpVars = new ArrayList<>();
    public Map<UTerm, UTerm> renameMap = new HashMap<>();

    public UTerm normalizeTerm(UTerm expr) {
        System.out.println("Origin expr: %s".formatted(expr));
        do {
            globalExpr = expr;
            isModified = false;
            expr = removeSumEqPred(expr);
//            expr = removeNodes(expr);
            expr = removeDuplicatePatternPartVar(expr);
            expr = eliminateSquash(expr);
            expr = promoteSummation(expr);
            expr = removeUselessTmpBoundedVar(expr);
            expr = removeUselessMulti(expr);
            expr = mergeSummation(expr);
            expr = combineSquash(expr);
            expr = removeUselessSquashSumFromSumBody(expr);
            expr = removeUnusedSummationBoundedVar(expr);
            expr = removeIsNullPredOnValue(expr);
            expr = simplifyInequalityByCongruence(expr);
            expr = moveExternalDependenciesToInnerSummation(expr);
            expr = removeUselessPreds(expr);
            expr = removeTrueOr(expr);
            expr = removeBoundEQPreds(expr);
            expr = removeEmptySum(expr);
//            expr = removeUselessSquash(expr);
        } while (isModified);
//        System.out.println("Normalized");
        System.out.println("Normalized expr: %s".formatted(expr));
        return expr;
    }

    private UTerm removeEmptySum(UTerm expr) {
        if (expr instanceof USum sum) {
            if (sum.boundedVars().isEmpty()) {
                return sum.body();
            }
        }
        return expr;
    }

    private UTerm removeBoundEQPreds(UTerm expr) {
        expr = transformSubTerms(expr, this::promoteSummation);
        if (expr instanceof USum sum) {
            Set<UVar> bounds = sum.boundedVars();
            List<UTerm> subTerms = new ArrayList<>();
            for (UTerm term : sum.subTerms()) {
                if (term instanceof UPred pred) {
                    if (pred.isPredKind(EQ)) {
                        UTerm left = pred.subTerms().get(0);
                        UTerm right = pred.subTerms().get(1);
                        if (left instanceof UVarTerm var1 && right instanceof UVarTerm var2) {
                            if (bounds.contains(var1.var())) {
                                expr.replaceVar(var2.var(), var1.var(), false);
                            }
                            if (bounds.contains(var2.var())) {
                                expr.replaceVar(var1.var(), var2.var(), false);
                            }
                        }
                    }
                }
            }
        }
        return expr;
    }

    private UTerm removeUselessPreds(UTerm term) {
        // Check if the current term is UMul
        if (term instanceof UMul mulTerm) {
            // Collect unique subTerms using a LinkedHashSet to preserve order
            Set<UTerm> uniqueSubTerms = new LinkedHashSet<>(mulTerm.subTerms());
            // If duplicates were removed, mark term as modified
            if (uniqueSubTerms.size() < mulTerm.subTerms().size()) {
                isModified = true; // Assuming you track modification status
            }
            // Recreate the UMul with the unique subTerms
            return UMul.mk(new ArrayList<>(uniqueSubTerms));
        }
        if (term instanceof GraphAlgebraTranslator.CaseUTerm) {
            return term;
        }

        // Recursively process subTerms if the term is not a leaf node
        List<UTerm> newSubTerms = new ArrayList<>();
        for (UTerm subTerm : term.subTerms()) {
            UTerm normalizedSubTerm = removeUselessPreds(subTerm); // Recursive normalization
            newSubTerms.add(normalizedSubTerm);
        }
        // If there are changes in subTerms, remake the term with updated subTerms
        if (isModified) {
//            isModified = true; // Mark term as modified if subTerms changed
            return remakeTerm(term, newSubTerms); // Rebuild term with normalized subTerms
        }

        return term; // Return unchanged term if no modifications
    }

    private UTerm moveExternalDependenciesToInnerSummation(UTerm term) {
        List<USum> innerSums = collectInnerSummations(term);
        List<UTerm> subTerms = term.subTerms();

        for (int i = 0; i < subTerms.size(); i++) {
            UTerm subTerm = subTerms.get(i);

            if (subTerm.kind() == PRED) {
                if (movePredToInnerSums((UPred) subTerm, innerSums)) {
                    subTerms.remove(i);
                    i--;
                }
            } else {
                moveExternalDependenciesToInnerSummation(subTerm);
            }
        }
        return term;
    }

    private List<USum> collectInnerSummations(UTerm term) {
        List<USum> sums = new ArrayList<>();
        for (UTerm subTerm : term.subTerms()) {
            if (subTerm.kind() == SUMMATION) {
                sums.add((USum) subTerm);
            }
            sums.addAll(collectInnerSummations(subTerm));
        }
        return sums;
    }

    private boolean movePredToInnerSums(UPred pred, List<USum> innerSums) {
        boolean moved = false;
        for (int i = 0; i < innerSums.size(); i++) {
            USum innerSum = innerSums.get(i);
            Set<UVar> innerBoundaries = innerSum.boundedVars();

            if (dependsOnBoundedVars(pred, innerBoundaries)) {
                innerSum.addMulSubTerm(pred);
                moved = true;
            }
        }
        return moved;
    }

    private boolean dependsOnBoundedVars(UTerm term, Set<UVar> boundedVars) {
        if (term.kind() == SUMMATION) {
            return false;
        }
        if (term instanceof UVarTerm) {
            UVar var = ((UVarTerm) term).var();

            if (boundedVars.contains(var)) {
                return true;
            }

            if (var.is(UVar.VarKind.PROJ) || var.is(UVar.VarKind.CONCAT)) {
                for (UVar arg : var.args()) {
                    if (boundedVars.contains(arg)) {
                        return true;
                    }
                    if (arg.isUsing(arg)) {
                        for (UVar boundedVar : boundedVars) {
                            if (arg.isUsing(boundedVar)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        for (UTerm subTerm : term.subTerms()) {
            if (dependsOnBoundedVars(subTerm, boundedVars)) {
                return true;
            }
        }

        return false;
    }

    private UTerm removeUselessTmpBoundedVar(UTerm expr) {
        if (expr instanceof USquash squash) {
            return USquash.mk(removeUselessTmpBoundedVar(squash.body()));
        }
        if (expr instanceof UMul mul) {
            List<UTerm> newTerms = new ArrayList<>();
            for (UTerm term : mul.subTerms()) {
                newTerms.add(removeUselessTmpBoundedVar(term));
            }
            if (newTerms.size() == 1) {
                return newTerms.get(0);
            }
            return UMul.mk(newTerms);
        }
        if (expr instanceof UPred pred) {
            if (pred.isBinaryPred()) {
                UTerm left = removeUselessTmpBoundedVar(pred.args().get(0));
                UTerm right = removeUselessTmpBoundedVar(pred.args().get(1));
                return UPred.mkBinary(pred.predKind(), left, right);
            }
        }
        if (expr instanceof UNeg neg) {
            return UNeg.mk(removeUselessTmpBoundedVar(neg.body()));
        }
        if (expr instanceof USum sum) {
            UTerm body = sum.body();
            List<UVar> dupVars = new ArrayList<>();
            Set<UVar> newBoundedVars = new HashSet<>();
            for (UVar var : sum.boundedVars()) {
                if (tmpVars.contains(var)) {
                    dupVars.add(var);
                } else newBoundedVars.add(var);
            }
            if (dupVars.isEmpty()) {
                return sum;
            }
            List<UTerm> newTerms = new ArrayList<>();
            for (UTerm term : body.subTerms()) {
                for (UVar dupVar : dupVars) {
                    UTerm t = removeSpecificPredicates(term, dupVar);
                    if (t != null) {
                        newTerms.add(t);
                    }
                }

            }
            if (newBoundedVars.isEmpty()) {
                return UMul.mk(newTerms);
            }
            return USum.mk(newBoundedVars, UMul.mk(newTerms));
        }

        return expr;
    }

    private UTerm getRootTerm(UTerm term) {
        UTerm r = term;
        while (renameMap.containsKey(r)) {
            r = renameMap.get(r);
        }
        return r;
    }

    private UTerm removeSpecificPredicates(UTerm term, UVar dupVar) {
        if (term instanceof UPred pred) {
            if (pred.isPredKind(EQ)) {
                UTerm left = pred.args().get(0);
                UTerm right = pred.args().get(1);
//                if (left.isUsing(dupVar) || right.isUsing(dupVar)) {
//                    if ((getRootTerm(left).isUsing(dupVar)) &&
//                            (getRootTerm(right).isUsing(dupVar))) {
//                        return null;
//                    }
//                }
                if (renameMap.containsKey(left) || renameMap.containsKey(right)) {
                    isModified = true;
                }
                if (left.isUsing(dupVar)) {
                    return null;
                }
                if (!left.isUsing(GraphAlgebraTranslator.outVar))
                    left = getRootTerm(left);
                if (right.isUsing(dupVar)) {
                    return null;
                }
                if (right.isUsing(GraphAlgebraTranslator.outVar))
                    right = getRootTerm(right);
                return UPred.mkBinary(pred.predKind(), left, right);
            } else if (pred.isBinaryPred()) {
                UTerm left = pred.args().get(0);
                UTerm right = pred.args().get(1);
                if (renameMap.containsKey(left) || renameMap.containsKey(right)) {
                    isModified = true;
                }
                if (left.isUsing(dupVar)) {
                    left = getRootTerm(left);
                }
                if (right.isUsing(dupVar)) {
                    right = getRootTerm(right);
                }
                return UPred.mkBinary(pred.predKind(), left, right);
            }
            return pred;
        }

        List<UTerm> newSubTerms = new ArrayList<>();
        for (UTerm subTerm : term.subTerms()) {
            UTerm processedSubTerm = removeSpecificPredicates(subTerm, dupVar);
            if (processedSubTerm != null) {
                newSubTerms.add(processedSubTerm);
            }
        }

        if (!newSubTerms.equals(term.subTerms())) {
            return remakeTerm(term, newSubTerms);
        }
        return term;
    }


    //    private UTerm removeUselessSquash(UTerm term) {
//
//        term = transformSubTerms(term, this::removeUselessSquash);
//        if (term.kind() != SQUASH)
//            return term;
//        USquash expr = (USquash) term;
//        UTerm body = expr.body();
//        if (!(body instanceof UMul))
//            return expr;
//        ArrayList<UTerm> subTerms = new ArrayList<>();
//        subTerms.addAll(body.subTerms());
//        for (int i = 0; i < body.subTerms().size(); i++) {
//            UTerm term1 = body.subTerms().get(i);
//            for (int j = 0; j < body.subTerms().size(); j++) {
//                UTerm term2 = body.subTerms().get(j);
//                if (term1.equals(term2) && i != j) {
//                    subTerms.remove(term2);
//                }
//            }
//        }
//        return USquash.mk(UMul.mk());
//    }
    UTerm eliminateSquash(UTerm expr) {
        return eliminateSquash0(expr, false);
    }

    private UTerm eliminateSquash0(UTerm expr, boolean isActivated) {
        final UKind kind = expr.kind();
        if (isActivated && kind == SQUASH) {
            isModified = true;
            return eliminateSquash0(((USquash) expr).body(), true);
        } else {
            final boolean activated;
            if (kind == PRED) activated = false;
            else activated = isActivated || kind == SQUASH || kind == NEGATION;
            return transformSubTerms(expr, t -> eliminateSquash0(t, activated));
        }
    }

    UTerm combineSquash(UTerm expr) {
        expr = transformSubTerms(expr, this::combineSquash);

        final UKind kind = expr.kind();
        if (kind != MULTIPLY) return expr;

        final List<UTerm> subTerms = expr.subTerms();
        final List<UTerm> squashedTerms = new ArrayList<>();
        for (int i = 0, bound = subTerms.size(); i < bound; ++i) {
            final UTerm subTerm = subTerms.get(i);
            if (subTerm.kind() != SQUASH) continue;
            squashedTerms.add(((USquash) subTerm).body());
        }
        if (squashedTerms.size() > 1) {
            final USquash combinedSquash = USquash.mk(UMul.mk(squashedTerms));
            subTerms.removeIf(t -> t.kind() == SQUASH);
            subTerms.add(combinedSquash);
            isModified = true;
        }

        return expr;
    }

    UTerm promoteSummation(UTerm expr) {
        expr = transformSubTerms(expr, this::promoteSummation);
        if (expr.kind() != MULTIPLY) return expr;

        Set<UVar> freeVars = null;
        final ListIterator<UTerm> iter = expr.subTerms().listIterator();
        while (iter.hasNext()) {
            final UTerm factor = iter.next();
            if (factor.kind() == SUMMATION) {
                final USum sum = (USum) factor;
                if (freeVars == null) freeVars = new HashSet<>(sum.boundedVars().size());
                freeVars.addAll(sum.boundedVars());
                iter.set(sum.body());
            }
        }
        if (freeVars != null) {
            isModified = true;
            return USum.mk(freeVars, expr);
        } else return expr;
    }

    /**
     * \sum{x}(f(x) * \sum{y}(g(y))) -> Sum[x,y](f(x)*g(y)) *
     */
    UTerm mergeSummation(UTerm expr) {
        expr = transformSubTerms(expr, this::mergeSummation);
        if (expr.kind() != SUMMATION) return expr;

        final USum summation = (USum) expr;
        if (summation.body().kind() != MULTIPLY) return expr;

        final Set<UVar> boundedVars = summation.boundedVars();

        // Sum[x](Prod(..,Sum[y](..),..) -> Sum[x,y](..,..,..)
        final List<UTerm> subTerms = summation.body().subTerms();
        for (int i = 0, bound = subTerms.size(); i < bound; i++) {
            final UTerm subTerm = subTerms.get(i);
            if (subTerm.kind() != SUMMATION) continue;

            final USum subSummation = (USum) subTerm;
            boundedVars.addAll(subSummation.boundedVars());
            subTerms.addAll(subSummation.body().subTerms());
            isModified = true;
        }
        subTerms.removeIf(it -> it.kind() == SUMMATION);

        return expr;
    }

    private UTerm removeNodes(UTerm term) {
        if (term instanceof USum sum) {
            UTerm body = sum.body();
            Set<UVar> bounds = sum.boundedVars();
            List<UTerm> terms = body.subTerms();
            Map<UVarTerm, UVarTerm> uVarMap = new HashMap<>();
            for (UTerm uTerm : terms) {
                removeNodesInSubTerms(uTerm, uVarMap);
            }
            for (UVarTerm uVarTerm : uVarMap.keySet()) {
                body = body.replaceVar(uVarMap.get(uVarTerm).var(), uVarTerm.var(), false);
                bounds.remove(uVarMap.get(uVarTerm).var());
                isModified = true;
            }
            return USum.mk(bounds, body);
        } else {
            return term;
        }
    }

    private void removeNodesInSubTerms(UTerm term, Map<UVarTerm, UVarTerm> uVarMap) {
        if (!term.subTerms().isEmpty() && term.kind() != PRED) {
            for (UTerm subTerm : term.subTerms()) {
                removeNodesInSubTerms(subTerm, uVarMap);
            }
        } else {
            Pair<UVarTerm, UVarTerm> uMap = checkRelEnd(term);
            if (!uMap.isEmpty()) {
                uVarMap.put(uMap.getLeft(), uMap.getRight());
            }
        }
    }

    private Pair<UVarTerm, UVarTerm> checkRelEnd(UTerm term) {
        boolean result = false;
        Pair<UVarTerm, UVarTerm> uMap = new Pair<>();
        UVarTerm k = null;
        UVarTerm v = null;
        if (term instanceof UPred pred) {
            if (pred.isPredKind(EQ)) {
                for (UTerm subTerm : pred.subTerms()) {
                    if (subTerm instanceof UVarTerm uVarTerm) {
                        if (uVarTerm.var().is(UVar.VarKind.PROJ) &&
                                (uVarTerm.var().name().toString().equals("in") ||
                                        uVarTerm.var().name().toString().equals("out"))) {
                            k = uVarTerm;
                            result = true;
                        }
                        if (uVarTerm.var().is(UVar.VarKind.BASE)) {
                            v = uVarTerm;
                        }
                    }
                }
            }
        }
        if (result) {
            uMap.setLeft(k);
            uMap.setRight(v);
        }
        return uMap;
    }

    private boolean checkRelTerm(UTerm term) {
        int result = 0;
        if (term instanceof UPred pred) {
            if (pred.isPredKind(EQ)) {
                for (UTerm subTerm : pred.subTerms()) {
                    if (subTerm.equals(UConst.one())) {
                        result++;
                    }
                    if (subTerm instanceof UVarTerm uVarTerm) {
                        if (uVarTerm.var().is(UVar.VarKind.PROJ) &&
                                uVarTerm.var().name().toString().equals("Rel")) {
                            result++;
                        }
                    }
                }
            }
        }
        return result == 2;
    }

    private UTerm simplifyInequalityByCongruence(UTerm expr) {
        expr = transformSubTerms(expr, this::simplifyInequalityByCongruence);
        if (expr.kind() != UKind.MULTIPLY) return expr;

        final UMul multiply = (UMul) expr;

        final NaturalCongruence<UTerm> congruence = NaturalCongruence.mk();
        getEqCongruenceRecursive(multiply, pred -> true, congruence, false);
        expr = replaceInequalityWithZero(multiply, congruence);

        return expr;
    }

    public static void getEqCongruenceRecursive(UTerm context,
                                                Function<UPred, Boolean> filter,
                                                NaturalCongruence<UTerm> eqClass,
                                                boolean considerNull) {
        switch (context.kind()) {
            case CONST, STRING, TABLE, FUNC, VAR, ADD, NEGATION -> {
            }
            case PRED -> {
                final UPred pred = (UPred) context;
                if (pred.isPredKind(UPred.PredKind.EQ) && filter.apply(pred)) {
                    final List<UTerm> eqPredTerms = pred.args();
                    assert eqPredTerms.size() == 2;
                    final UTerm termArg0 = eqPredTerms.get(0), termArg1 = eqPredTerms.get(1);
                    eqClass.putCongruent(termArg0, termArg1);
                }
                if (considerNull && isNullPred(pred) && filter.apply(pred)) {
                    final List<UTerm> isNullPredTerms = pred.args();
                    assert isNullPredTerms.size() == 1;
                    final UTerm termArg0 = isNullPredTerms.get(0);
                    eqClass.putCongruent(UConst.nullVal(), termArg0);
                }
            }
            case MULTIPLY, SUMMATION, SQUASH -> {
                for (final UTerm subTerm : context.subTerms()) {
                    getEqCongruenceRecursive(subTerm, filter, eqClass, considerNull);
                }
            }
            default ->
                    throw new IllegalArgumentException("[Exception] Unsupported U-expression kind: " + context.kind());
        }
    }

    private UTerm replaceInequalityWithZero(UTerm context, NaturalCongruence<UTerm> congruence) {
        if (context instanceof UPred pred
                && (pred.isPredKind(UPred.PredKind.GT)
                || pred.isPredKind(UPred.PredKind.LT))) {
            assert pred.args().size() == 2;
            if (congruence.eqClassOf(pred.args().get(0)).contains(pred.args().get(1))) {
                isModified = true;
                return UConst.zero();
            }
        }

        final List<UTerm> newSubTerms = new ArrayList<>();

        for (final UTerm subTerm : context.subTerms()) {
            newSubTerms.add(replaceInequalityWithZero(subTerm, congruence));
        }

        return remakeTerm(context, newSubTerms);
    }

    ArrayList<UTerm> notNullTerms = new ArrayList<>();

    private UTerm removeIsNullPredOnValue(UTerm expr) {
        expr = transformSubTerms(expr, this::removeIsNullPredOnValue);
        if (expr.kind() != PRED || !isNullPred(expr)) return expr;

        assert ((UPred) expr).args().size() == 1;
        final UTerm predArg = ((UPred) expr).args().get(0);
        if (predArg.kind() != VAR) {
            isModified = true;
            return predArg.equals(UConst.NULL) ? UConst.one() : UConst.zero();
        } else if (notNullTerms.contains(predArg)) {
            isModified = true;
            return UConst.zero();
        }
        return expr;
    }

    private UTerm removeSumEqPred(UTerm expr) {
        expr = transformSubTerms(expr, this::removeSumEqPred);
        if (expr.kind() != PRED) return expr;

        UPred pred = (UPred) expr;
        if (pred.isPredKind(EQ)) {
            UTerm op1 = pred.args().get(0);
            UTerm op2 = pred.args().get(1);
            if (op1.equals(op2))
                return UConst.one();
        }
        return expr;
    }

    public UTerm removeDuplicatePatternPartVar(UTerm expr) {
        expr = transformSubTerms(expr, this::removeDuplicatePatternPartVar);
        if (expr.kind() != MULTIPLY) return expr;
        final NaturalCongruence<UVar> varEqClass = UExprSupport.getEqVarCongruenceInTermsOfMul(expr);
        final UMul mul = (UMul) expr;
        final List<UTerm> subTerms = mul.subTerms();
        final List<UTerm> tables = filter(subTerms, t -> t.kind() == TABLE);
        final List<UTerm> labels = filter(subTerms, t -> t.kind() == TABLE && !((UTable) t).tableName().equals(UName.mk("Node"))
                && !((UTable) t).tableName().equals(UName.mk("Rel")));
        final List<UVar> eqVars = varEqClass.keys().stream().toList();
        for (int i = 0, bound = eqVars.size(); i < bound; ++i) {
            final UVar var0 = eqVars.get(i);
            for (int j = i + 1, bound0 = eqVars.size(); j < bound0; ++j) {
                final UVar var1 = eqVars.get(j);
                if (var0.equals(var1) || !varEqClass.isCongruent(var0, var1)) continue;
                final UVar baseVar0 = var0.args()[0], baseVar1 = var1.args()[0];
                assert baseVar0.is(UVar.VarKind.BASE) && baseVar1.is(UVar.VarKind.BASE);
                final UTerm table0 = linearFind(tables, t -> ((UTable) t).var().equals(baseVar0));
                final UTerm table1 = linearFind(tables, t -> ((UTable) t).var().equals(baseVar1));
                if (table0 == null || table1 == null) continue;
                // Do rewrite here
                mul.subTerms().remove(table1);
                mul.replaceVarInplace(baseVar1, baseVar0, false);
                isModified = true;
            }
        }
        return expr;
    }

    private UTerm removeUnusedSummationBoundedVar(UTerm expr) {
        expr = transformSubTerms(expr, this::removeUnusedSummationBoundedVar);
        if (expr.kind() != SUMMATION) return expr;

        ((USum) expr).removeUnusedBoundedVar();
        return expr;
    }

    public UTerm removeUselessSquashSumFromSumBody(UTerm term) {
        term = transformSubTerms(term, this::removeUselessSquashSumFromSumBody);
        if (term.kind() != SUMMATION) return term;
        USum expr = (USum) term;
        UTerm body = expr.body();
        if (!(body instanceof UMul))
            return expr;
        for (UTerm subTerm : body.subTerms()) {
            if (subTerm instanceof USquash) {
                UTerm squashBody = ((USquash) subTerm).body();
                if (squashBody instanceof USum squashSum) {
                    if (squashSum.boundedVars().size() > expr.boundedVars().size())
                        continue;
                    ArrayList<UTerm> subTerms = new ArrayList<>();
                    subTerms.addAll(body.subTerms());
                    subTerms.remove(subTerm);
                    USum tmp = USum.mk(expr.boundedVars(), UMul.mk(subTerms));
                    if (containSubTerms(tmp, squashSum)) {
                        return tmp;
                    }
                }
            }
        }
        return expr;
    }

    private boolean containSubTerms(USum bigSum, USum smallSum) {
        UTerm bigBody = bigSum.body().copy();
        UTerm smallBody = smallSum.body().copy();
        HashSet<UVar> bigBoundVars = new HashSet<>(bigSum.boundedVars());
        ArrayList<UVar> smallBoundVars = new ArrayList<>(smallSum.boundedVars());

        if (bigBoundVars.size() < smallBoundVars.size()) return false;
        if (bigSum.equals(smallSum)) return true;

        return checkSubSum(0, bigBoundVars, bigBody, smallBoundVars, smallBody);
    }

    boolean checkSubSum(
            int cur, HashSet<UVar> bigBoundVars, UTerm bigBody, ArrayList<UVar> smallBoundVars, UTerm smallBody) {

        if (cur == smallBoundVars.size()) {
            for (UTerm smallTerm : smallBody.subTerms()) {
                if (bigBody.subTerms().contains(smallTerm))
                    continue;
                if (smallTerm.kind() == PRED) {
                    UPred pred = (UPred) smallTerm;
                    if (pred.isTruePred(globalExpr) == 1) continue;
                    if (pred.isPredKind(EQ)) {
                        UTerm op0 = pred.args().get(0);
                        UTerm op1 = pred.args().get(1);
                        NaturalCongruence<UTerm> cong = buildNaturalCongruence(bigBody.subTerms());
                        if (cong.isCongruent(op0, op1)) continue;
                    }
                }
                if (smallTerm.kind() == NEGATION) {
                    if (smallTerm.subTerms().get(0).kind() == PRED) {
                        UPred pred = (UPred) smallTerm.subTerms().get(0);
                        if (isNullPred(pred)) {
                            if (pred.isTruePred(globalExpr) == 0) continue;
                            NaturalCongruence<UTerm> cong = buildNaturalCongruence(bigBody.subTerms());
                            Set<UTerm> eqTerms = cong.eqClassOf(pred.args().get(0));
                            if (any(eqTerms,
                                    t -> bigBody.subTerms().contains(UNeg.mk(mkIsNullPred(t)))))
                                continue;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        final UVar curVar = smallBoundVars.get(cur);
        final UVar newVar = UVar.mkBase(UName.mk(Liastar.newVarName()));
        smallBody = smallBody.replaceVar(curVar, newVar, true);

        for (UVar v : bigBoundVars) {
            final HashSet<UVar> tmpVars = new HashSet<>(bigBoundVars);
            tmpVars.remove(v);
            final UTerm tmpBigBody = bigBody.replaceVar(v, newVar, true);
            final UTerm tmpSmallBody = smallBody.replaceVar(v, newVar, false);
            final boolean result = checkSubSum(cur + 1, tmpVars, tmpBigBody, smallBoundVars, tmpSmallBody);
            if (result) return true;
        }
        return false;
    }

    public static NaturalCongruence<UTerm> buildNaturalCongruence(List<UTerm> terms) {
        NaturalCongruence<UTerm> result = NaturalCongruence.mk();
        buildNaturalCongruenceRecursively(terms, result);
        return result;
    }

    public static void buildNaturalCongruenceRecursively(List<UTerm> terms, NaturalCongruence<UTerm> result) {
        for (UTerm term : terms) {
            switch (term.kind()) {
                case PRED: {
                    UPred pred = (UPred) term;
                    if (!pred.isPredKind(EQ)) continue;
                    result.putCongruent(pred.args().get(0), pred.args().get(1));
                    break;
                }
                case SQUASH: {
                    buildNaturalCongruenceRecursively(((USquash) term).body().subTerms(), result);
                    break;
                }
                case SUMMATION: {
                    buildNaturalCongruenceRecursively(((USum) term).body().subTerms(), result);
                    break;
                }
                case MULTIPLY, ADD: {
                    buildNaturalCongruenceRecursively(term.subTerms(), result);
                    break;
                }
                default:
                    continue;
            }
        }
    }

    public static void getEqCongruenceRecursive(UTerm context,
                                                Function<UPred, Boolean> filter,
                                                NaturalCongruence<UTerm> eqClass,
                                                UTerm fullContext,
                                                boolean considerNull) {
        switch (context.kind()) {
            case CONST, STRING, TABLE, FUNC, VAR -> {
            }
            case PRED -> {
                if (!isCriticalValue(context, fullContext)) return;
                final UPred pred = (UPred) context;
                if (pred.isPredKind(UPred.PredKind.EQ) && filter.apply(pred)) {
                    final List<UTerm> eqPredTerms = pred.args();
                    assert eqPredTerms.size() == 2;
                    final UTerm termArg0 = eqPredTerms.get(0), termArg1 = eqPredTerms.get(1);
                    eqClass.putCongruent(termArg0, termArg1);
                }
                if (considerNull && isNullPred(pred) && filter.apply(pred)) {
                    final List<UTerm> isNullPredTerms = pred.args();
                    assert isNullPredTerms.size() == 1;
                    final UTerm termArg0 = isNullPredTerms.get(0);
                    eqClass.putCongruent(UConst.nullVal(), termArg0);
                }
            }
            case MULTIPLY, SUMMATION, SQUASH, ADD, NEGATION -> {
                for (final UTerm subTerm : context.subTerms()) {
                    getEqCongruenceRecursive(subTerm, filter, eqClass, fullContext, considerNull);
                }
            }
            default ->
                    throw new IllegalArgumentException("[Exception] Unsupported U-expression kind: " + context.kind());
        }
    }

    public static void getTargetUExprRecursive(UTerm context, Function<UTerm, Boolean> filter, List<UTerm> result, UTerm fullContext) {
        if (filter.apply(context) && isCriticalValue(context, fullContext)) result.add(context);
        switch (context.kind()) {
            case CONST, STRING, TABLE, FUNC, VAR, PRED -> {
            }
            case MULTIPLY, SUMMATION, SQUASH, ADD, NEGATION -> {
                for (final UTerm subTerm : context.subTerms()) {
                    getTargetUExprRecursive(subTerm, filter, result, fullContext);
                }
            }
            default ->
                    throw new IllegalArgumentException("[Exception] Unsupported U-expression kind: " + context.kind());
        }
    }

    public static NaturalCongruence<UTerm> getEqIsNullCongruenceInTermsOfMul(UTerm mulContext, Function<UPred, Boolean> filter) {
        assert mulContext.kind() == UKind.MULTIPLY;
        final NaturalCongruence<UTerm> varEqClass = NaturalCongruence.mk();
        for (UTerm subTerm : mulContext.subTermsOfKind(UKind.PRED)) {
            final UPred pred = (UPred) subTerm;
            if (pred.isPredKind(UPred.PredKind.EQ) && filter.apply(pred)) {
                final List<UTerm> eqPredTerms = pred.args();
                assert eqPredTerms.size() == 2;
                final UTerm termArg0 = eqPredTerms.get(0), termArg1 = eqPredTerms.get(1);
                varEqClass.putCongruent(termArg0, termArg1);
            }
            if (isNullPred(pred) && filter.apply(pred)) {
                final List<UTerm> isNullPredTerms = pred.args();
                assert isNullPredTerms.size() == 1;
                final UTerm termArg0 = isNullPredTerms.get(0);
                varEqClass.putCongruent(UConst.nullVal(), termArg0);
            }
        }
        return varEqClass;
    }

    public static UTerm removeTrueOr(UTerm term) {
        if (term instanceof UAdd) {
            UAdd add = (UAdd) term;
            List<UTerm> subTerms = add.subTerms();

            // 检查是否包含恒真表达式
            if (containsTrueOr(subTerms)) {
                return UConst.ONE; // 恒真，返回常数 1
            }

            // 如果不是恒真，递归检查子项
            List<UTerm> optimizedSubTerms = new ArrayList<>();
            for (UTerm subTerm : subTerms) {
                optimizedSubTerms.add(removeTrueOr(subTerm)); // 递归处理每个子项
            }
            return remakeTerm(add, optimizedSubTerms);
        }

        // 对其他类型的 UTerm 递归处理
        if (!(term instanceof UConst)) { // UConst 没有子项，不需要处理
            List<UTerm> subTerms = term.subTerms();
            List<UTerm> optimizedSubTerms = new ArrayList<>();
            for (UTerm subTerm : subTerms) {
                optimizedSubTerms.add(removeTrueOr(subTerm)); // 递归处理
            }
            return remakeTerm(term, optimizedSubTerms);
        }

        return term;
    }

    /**
     * 检查一个 UTerm 列表是否包含恒真的 OR 形式
     * 如 p1 + not(p1), p2 + not(p2) 等
     *
     * @param subTerms UTerm 子项列表
     * @return 是否包含恒真的 OR 形式
     */
    private static boolean containsTrueOr(List<UTerm> subTerms) {
        Set<UTerm> positiveTerms = new HashSet<>();
        Set<UTerm> negatedTerms = new HashSet<>();

        // 遍历每个子项，检查是否有 p 和 not(p) 配对
        for (UTerm subTerm : subTerms) {
            if (subTerm instanceof UNeg) {
                UTerm innerTerm = ((UNeg) subTerm).body();
                if (positiveTerms.contains(innerTerm)) {
                    // 找到 p 和 not(p)，返回 true，表示恒真
                    return true;
                }
                negatedTerms.add(innerTerm);
            } else {
                positiveTerms.add(subTerm);
            }
        }

        return false;
    }

    public UTerm removeUselessMulti(UTerm term) {
        if (term instanceof UMul) {
            UMul mul = (UMul) term;
            List<UTerm> subTerms = mul.subTerms();

            // 如果 subTerms 只有一个，直接返回其唯一子项
            if (subTerms.size() == 1) {
                return removeUselessMulti(subTerms.get(0)); // 递归检查唯一子项
            }

            // 如果有多个子项，递归处理每个子项
            List<UTerm> optimizedSubTerms = new ArrayList<>();
            for (UTerm subTerm : subTerms) {
                optimizedSubTerms.add(removeUselessMulti(subTerm));
            }
            return UMul.mk(optimizedSubTerms);
        }

        // 如果是其他类型的 UTerm，递归处理其子项
        if (!(term instanceof UConst)) { // UConst 无子项无需处理
            List<UTerm> subTerms = term.subTerms();
            List<UTerm> optimizedSubTerms = new ArrayList<>();
            for (UTerm subTerm : subTerms) {
                optimizedSubTerms.add(removeUselessMulti(subTerm));
            }
            return remakeTerm(term, optimizedSubTerms);
        }

        return term;
    }
}
