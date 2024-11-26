package cypher.translate;

import sqlsolver.uexpr.*;

import java.util.HashMap;
import java.util.Map;

public class Cypher2UExprHelper {
    private static Map<String, UConst> stringValues = new HashMap<>();

    private static int stringToken = 1;

    public static UVar relIn(UVar rel) {
        return UVar.mkProj(UName.mk("in"), rel);
    }

    public static UTerm mkRelIn(UVar rel, UVar in) {
        return UPred.mkBinary(UPred.PredKind.EQ, relIn(rel), in);
    }

    public static UTerm mkRelIOut(UVar rel, UVar out) {
        return UPred.mkBinary(UPred.PredKind.EQ, relOut(rel), out);
    }

    public static UVar relOut(UVar rel) {
        return UVar.mkProj(UName.mk("out"), rel);
    }
    public static UVar id(UVar record) {
        return UVar.mkProj(UName.mk("id"), record);
    }

    public static UTerm node(UVar var) {
//        return UTable.mk(UName.mk("Node"), var);
        return UPred.mkBinary(UPred.PredKind.EQ,UVarTerm.mk(UVar.mkProj(UName.mk("Node"), var)) , UConst.one());
    }

    public static UTerm rel(UVar var) {
//        return UTable.mk(UName.mk("Rel"), var);
        return UPred.mkBinary(UPred.PredKind.EQ,UVarTerm.mk(UVar.mkProj(UName.mk("Rel"), var)) , UConst.one());
    }

    public static UTerm label(UVar var, String labelName) {
//        return UTable.mk(UName.mk(labelName), var);
        return UPred.mkBinary(UPred.PredKind.EQ,UVarTerm.mk(UVar.mkProj(UName.mk(labelName), var)) , UConst.one());
    }

    public static UConst addOrGetStringValue(String v) {
        if (stringValues.containsKey(v)) {
            return stringValues.get(v);
        } else {
            UConst value = UConst.mk(stringToken++);
            stringValues.put(v, value);
            return value;
        }
    }
}
