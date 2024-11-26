package sqlsolver.uexpr;

import java.util.ArrayList;
import java.util.List;

public interface UPred extends UTerm {
  enum PredKind {
    EQ("="),
    NEQ("<>"),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    FUNC("func"); // Uninterpreted function, e.g. `[p(a(t))]`

    private final String text;

    PredKind(String text) {
      this.text = text;
    }

    UName predName() {
      return UName.mk(text);
    }
  }
  boolean nullSafe();

  default boolean isPredKind(PredKind predKind) {
    return predKind() == predKind;
  }

  default boolean isBinaryPred() {
    return predKind() != PredKind.FUNC;
  }

  default boolean isUnaryPred() {
    return predKind() == PredKind.FUNC;
  }

  @Override
  default UKind kind() {
    return UKind.PRED;
  }

  PredKind predKind();

  UName predName();

  List<UTerm> args();
  
  static UPred mkFunc(UName predName, UVar varArg0) {
    return mkFunc(predName, UVarTerm.mk(varArg0),false);
  }

  static UPred mkBinary(PredKind predKind, UVar varArg0, UVar varArg1) {
    return mkBinary(predKind, UVarTerm.mk(varArg0), UVarTerm.mk(varArg1));
  }

  static UPred mkFunc(UName predName, UTerm arg0, boolean nullSafe) {
    return new UPredImpl(PredKind.FUNC, predName, new ArrayList<>(List.of(arg0)), nullSafe);
  }

  static UPred mkBinary(PredKind predKind, UTerm arg0, UTerm arg1, boolean nullSafe) {
    assert predKind != PredKind.FUNC;
    return UPred.mk(predKind, predKind.predName(), new ArrayList<>(List.of(arg0, arg1)), nullSafe);
  }

  static UPred mkBinary(PredKind predKind, UTerm arg0, UTerm arg1) {
    assert predKind != PredKind.FUNC;
    // default nullSafe is false
    return mkBinary(predKind, arg0, arg1, false);
  }

  static UPred mk(PredKind predKind, UName predName, List<UTerm> arguments, boolean nullSafe) {
    return new UPredImpl(predKind, predName, arguments, nullSafe);
  }

  public int isTruePred(UTerm expr);
}
