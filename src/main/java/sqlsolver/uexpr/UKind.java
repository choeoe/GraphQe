package sqlsolver.uexpr;

public enum UKind {
  CONST,
  STRING,
  TABLE,
  PRED,
  FUNC,
  VAR,
  MULTIPLY,
  ADD,
  SUMMATION,
  NEGATION,
  SQUASH;

  public boolean isTermAtomic() {
    return this == TABLE || this == PRED || this == VAR || this == CONST || this == STRING;
  }

  public boolean isVarAtomic() {
    return this == TABLE || this == VAR;
  }

  public boolean isBinary() {
    return this == MULTIPLY || this == ADD;
  }

  public boolean isUnary() {
    return this == NEGATION || this == SQUASH;
  }

  public boolean isVarTerm() {return this == VAR;}
}
