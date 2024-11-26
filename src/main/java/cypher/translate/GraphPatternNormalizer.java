package cypher.translate;

import cypher.ast.expression.Binary;
import representations.graphalgebra.Expand;
import representations.graphalgebra.GraphPattern;
import representations.graphalgebra.GraphPatternPart;

import java.util.ArrayList;
import java.util.List;

public class GraphPatternNormalizer {
    public static void semanticCompletion(GraphPattern pattern) {
        List<Expand> relations = new ArrayList<>();
        for (GraphPatternPart part : pattern.getAllGraphPatternParts()) {
            if (part instanceof Expand relation)  {
                relations.add(relation);
            }
        }
        for (int i = 0; i < relations.size(); i++) {
            for (int j = i+1; j < relations.size(); j++) {
                pattern.addExpr(new Binary.Equals(relations.get(i).var,relations.get(j).var));
            }
        }
    }

    public static void eliminatingUndirectedRelationships(GraphPattern pattern) {

    }


}
