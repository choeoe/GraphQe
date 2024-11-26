package cypher.algebra.normalizer;

import cypher.ast.clause.projection.Limit;
import representations.GraphAlgebra;
import representations.graphalgebra.Grouping;
import representations.graphalgebra.ProjOp;
import representations.graphalgebra.Projection;

import java.util.*;
import java.util.stream.Collectors;

public class AlgebraNormalizer {
    GraphAlgebra algebra;

    public static GraphAlgebra normalizeProj(GraphAlgebra algebra) {
        // Check if the input is a Projection or Grouping
        if (algebra instanceof Projection outerProjection) {
            GraphAlgebra innerAlgebra = outerProjection.relation;
            if (innerAlgebra instanceof Projection innerProjection) {
                if (areProjectionsEquivalent(outerProjection, innerProjection)) {
                    innerProjection.relation = normalizeProj(innerProjection.relation);
//                    innerProjection.projs = reorderFields(innerProjection.projs, outerProjection.projs);
                    if (innerAlgebra.kind() == GraphAlgebra.Kind.PROJ) {
                        ((Projection) innerAlgebra).outer();
                    }
                    return innerProjection;
                }
            }
        }
        // If no optimization is applied, return the original algebra
        return algebra;
    }
    private static List<ProjOp> reorderFields(List<ProjOp> innerFields, List<ProjOp> outerFields) {
        Map<ProjOp, Integer> innerFieldCount = new HashMap<>();
        for (ProjOp field : innerFields) {
            innerFieldCount.put(field, innerFieldCount.getOrDefault(field, 0) + 1);
        }

        List<ProjOp> reorderedFields = new ArrayList<>();
        for (ProjOp outerField : outerFields) {
            if (innerFieldCount.containsKey(outerField) && innerFieldCount.get(outerField) > 0) {
                reorderedFields.add(outerField);
                innerFieldCount.put(outerField, innerFieldCount.get(outerField) - 1);
            }
        }

        return reorderedFields;
    }
    private static boolean areProjectionsEquivalent(Projection outer, Projection inner) {
        if (outer.projs.size() != inner.projs.size()) {
            return false;
        }

        // Create a list to track unmatched fields in the inner projection
        List<ProjOp> unmatchedFields = new ArrayList<>(inner.projs);

        // For each field in the outer projection, find a match in the inner projection
        for (ProjOp outerField : outer.projs) {
            boolean matched = false;
            for (int i = 0; i < unmatchedFields.size(); i++) {
                if (outerField.equals(unmatchedFields.get(i))) {
                    // Mark the field as matched and remove it from unmatched fields
                    unmatchedFields.remove(i);
                    matched = true;
                    break;
                }
            }

            // If no match is found for the current outer field, projections are not equivalent
            if (!matched) {
                return false;
            }
        }

        // If all fields are matched, the projections are equivalent
        return true;
    }

    private static boolean areProjectionAndGroupingEquivalent(Projection projection, Grouping grouping) {
        // Check if the projection fields match the grouping's return fields
        return projection.projs.equals(grouping.projs);
    }
}
