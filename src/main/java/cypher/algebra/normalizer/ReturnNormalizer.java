package cypher.algebra.normalizer;

import cypher.Pair;
import cypher.ast.expression.Expression;
import cypher.ast.expression.FunctionInvocation;
import cypher.ast.expression.Property;
import cypher.ast.expression.Variable;
import representations.GraphAlgebra;
import representations.graphalgebra.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class ReturnNormalizer {


    GraphAlgebra algebra1;
    GraphAlgebra algebra2;


    List<List<Pair<Integer, Integer>>> maps = new ArrayList<>();

    List<List<Integer>> l1 = new ArrayList<>();

    List<List<Integer>> l2 = new ArrayList<>();


    public ReturnNormalizer(GraphAlgebra algebra1, GraphAlgebra algebra2) {
        this.algebra1 = algebra1;
        this.algebra2 = algebra2;
    }

    public List<List<Pair<Integer, Integer>>> getMaps() {
        return maps;
    }

    public void normalize() {
        Projection projection1 = getOuterProjection(algebra1);
//        projection1.outer();
        Projection projection2 = getOuterProjection(algebra2);
//        projection2.outer();
        List<ProjOp> projOps1 = new ArrayList<>(projection1.projs);
        List<ProjOp> projOps2 = new ArrayList<>(projection2.projs);
//        if (algebra1 instanceof Dedup dedup)
//            algebra1 = dedup.algebra;
//        if (algebra2 instanceof Dedup dedup)
//            algebra2 = dedup.algebra;
//        if (algebra1 instanceof Projection projection1 && algebra2 instanceof Projection projection2) {
//            projOps1.addAll(projection1.projs);
//            projOps2.addAll(projection2.projs);
//        } else if (algebra1 instanceof Union union) {
//            getOuterProjection(union.left);
//        }
        if (projOps1.size() == 0 || projOps2.size() == 0 || projOps1.size() != projOps2.size()) {
            return;
        }
        generateCombinations(projOps1, projOps2, new boolean[projOps2.size()], new ArrayList<>(), maps,ReturnNormalizer::isValidMapping);
        if (maps.isEmpty()) {
            generateCombinations(projOps1, projOps2, new boolean[projOps2.size()], new ArrayList<>(), maps, null);
        }
    }

    public Projection getOuterProjection(GraphAlgebra algebra) {
        // Base case: If the current algebra is a Projection, return it
        if (algebra instanceof Projection) {
            return (Projection) algebra;
        }

        // Recursive case: Traverse the sub-algebras
        for (GraphAlgebra subAlgebra : algebra.subAlgebras()) {
            Projection result = getOuterProjection(subAlgebra);
            if (result != null) { // Found a Projection in the sub-algebra
                return result;
            }
        }

        // If no Projection found, return null
        return null;
    }

    private static void generateCombinations(List<ProjOp> list1, List<ProjOp> list2,
                                             boolean[] used, List<Pair<Integer, Integer>> current,
                                             List<List<Pair<Integer, Integer>>> allCombinations,
                                             BiPredicate<ProjOp, ProjOp> isValidMapping) {
        if (current.size() == list1.size()) {
            allCombinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < list2.size(); i++) {
            if (!used[i]) {
                ProjOp proj1 = list1.get(current.size());
                ProjOp proj2 = list2.get(i);

                // Use the passed isValidMapping function to check validity
                if(isValidMapping != null && !isValidMapping.test(proj1, proj2))
                    continue;
                used[i] = true;
                current.add(new Pair<>(current.size(), i));
                generateCombinations(list1, list2, used, current, allCombinations, isValidMapping);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    private static boolean isValidMapping(ProjOp proj1, ProjOp proj2) {
        Expression expr1 = proj1.expr;
        Expression expr2 = proj2.expr;
        return isSameType(expr1, expr2);
    }

    private static boolean isSameType(Expression expr1, Expression expr2) {
        if (expr1 instanceof Variable var1) {
            expr1 = var1.getLinked();
        }
        if (expr2 instanceof Variable var2) {
            expr2 = var2.getLinked();
        }
        if (expr1 instanceof Variable var1 && expr2 instanceof Variable var2) {
            return var1.kind() == var2.kind();
        }
        if (expr1 instanceof FunctionInvocation func1 && expr2 instanceof FunctionInvocation func2) {
            return func1.functionName.name.equals(func2.functionName.name);
        }
        if (expr1 instanceof Property prop1 && expr2 instanceof Property prop2) {
            return isSameType(prop1.map, prop2.map) && prop1.propertyKey.name.equals(prop2.propertyKey.name);
        }
        return true;
    }


    private static boolean hasValidMapping(List<ProjOp> list1, List<ProjOp> list2, boolean[] used) {
        for (ProjOp proj1 : list1) {
            boolean valid = false;
            for (int i = 0; i < list2.size(); i++) {
                if (!used[i] && isValidPair(proj1, list2.get(i))) {
                    valid = true;
                    break;
                }
            }
            if (!valid) return false;
        }
        return true;
    }

    private static boolean isValidPair(ProjOp projOp1, ProjOp projOp2) {
        Expression expr1 = projOp1.expr;
        Expression expr2 = projOp2.expr;
        if (expr1.getClass() != expr2.getClass()) {
            return false;
        } else {
            if (expr1 instanceof Property prop1 && expr2 instanceof Property prop2) {
                return prop1.propertyKey.name.equals(prop2.propertyKey.name);
            }
            return true;
        }
    }
}
