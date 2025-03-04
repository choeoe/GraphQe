package sqlsolver.liastar;

import com.microsoft.z3.*;
import sqlsolver.util.PrettyBuilder;

import java.util.*;
import java.util.function.Function;


public class LiaandImpl extends Liastar {

  Liastar operand1;
  Liastar operand2;

  LiaandImpl(Liastar op1, Liastar op2) {
    operand1 = op1;
    operand2 = op2;
    innerStar = false;
  }

  private void flattenFormula(List<Liastar> operands, Liastar f) {
    if (f instanceof LiaandImpl and) {
      and.flatten(operands);
    } else {
      operands.add(f);
    }
  }

  /** Flatten an "AND" tree into a list of its leaves. */
  public void flatten(List<Liastar> operands) {
    flattenFormula(operands, operand1);
    flattenFormula(operands, operand2);
  }

  @Override
  public Set<String> collectVarSet() {
    Set<String> varSet = operand1.collectVarSet();
    varSet.addAll(operand2.collectVarSet());
    return varSet;
  }

  @Override
  protected void prettyPrint(PrettyBuilder builder) {
    boolean needsParen1 = (operand1 instanceof LiaorImpl);
    boolean needsParen2 = (operand2 instanceof LiaorImpl);
    prettyPrintBinaryOp(builder, operand1, operand2,
            needsParen1, needsParen2, true, " /\\ ");
  }

  @Override
  protected boolean isPrettyPrintMultiLine() {
    return operand1.isPrettyPrintMultiLine()
            || operand2.isPrettyPrintMultiLine();
  }

  @Override
  public LiaOpType getType() {
    return LiaOpType.LAND;
  }

  @Override
  public boolean isLia() {
    return operand1.isLia() && operand2.isLia();
  }

  @Override
  public Set<String> getVars() {
    Set<String> result = operand1.getVars();
    result.addAll(operand2.getVars());
    return result;
  }

  @Override
  public Liastar deepcopy() {
    Liastar tmp = mkAnd(innerStar, operand1.deepcopy(), operand2.deepcopy());
    return tmp;
  }

  @Override
  public Liastar mergeMult(HashMap<LiamultImpl, String> multToVar) {
    operand1 = operand1.mergeMult(multToVar);
    operand2 = operand2.mergeMult(multToVar);
    return this;
  }

  @Override
  public Liastar multToBin(int n) {
    operand1 = operand1.multToBin(n);
    operand2 = operand2.multToBin(n);
    return this;
  }

  @Override
  public Liastar simplifyMult(HashMap<Liastar, String> multToVar) {
    operand1.innerStar = innerStar;
    operand2.innerStar = innerStar;
    Liastar[] tmp = new Liastar[2];
    tmp[0] = operand1.simplifyMult(multToVar);
    tmp[1] = operand2.simplifyMult(multToVar);
    return liaAndConcat(tmp);
  }

  @Override
  public Liastar expandStar() throws Exception {
    operand1 = operand1.expandStar();
    operand2 = operand2.expandStar();
    return this;
  }

  @Override
  public Expr transToSMT(Context ctx, HashMap<String, IntExpr> varsName) {
    Expr c1 = operand1.transToSMT(ctx, varsName);
    Expr c2 = operand2.transToSMT(ctx, varsName);
    return ctx.mkAnd((BoolExpr) c1, (BoolExpr) c2);
  }

  @Override
  public EstimateResult estimate() {
    EstimateResult r1 = operand1.estimate();
    EstimateResult r2 = operand2.estimate();
    r1.vars.addAll(r2.vars);
    return new EstimateResult(
            r1.vars,
            r1.n_extra + r2.n_extra,
            r1.m + r2.m,
            Math.max(r1.a,r2.a)
    );
  }

  @Override
  public Expr expandStarWithK(Context ctx, Solver sol, String suffix) {
    return ctx.mkAnd(
            (BoolExpr)operand1.expandStarWithK(ctx, sol, suffix),
            (BoolExpr)operand2.expandStarWithK(ctx, sol, suffix)
    );
  }

  @Override
  public boolean equals(Object that) {
    if(that == this)
      return true;
    if(that == null)
      return false;
    if(!(that instanceof LiaandImpl))
      return false;
    LiaandImpl tmp = (LiaandImpl) that;
    return operand1.equals(tmp.operand1) && operand2.equals(tmp.operand2);
  }

  @Override
  public int hashCode() {
    return operand1.hashCode() + operand2.hashCode();
  }

  @Override
  public void prepareInnervector(HashSet<String> vars) {
    operand1.prepareInnervector(vars);
    operand2.prepareInnervector(vars);
  }

  @Override
  public Liastar removeParameter() {
    operand1 = operand1.removeParameter();
    operand2 = operand2.removeParameter();
    return this;
  }

  @Override
  public Liastar removeParameterEager() {
    operand1 = operand1.removeParameterEager();
    operand2 = operand2.removeParameterEager();
    return this;
  }

  @Override
  public Liastar pushUpParameter(HashSet<String> newVars) {
    operand1 = operand1.pushUpParameter(newVars);
    operand2 = operand2.pushUpParameter(newVars);
    return this;
  }

  @Override
  public HashSet<String> collectParams() {
    HashSet<String> result = operand1.collectParams();
    result.addAll(operand2.collectParams());
    return result;
  }

  @Override
  public Liastar simplifyIte() {
    operand1 = operand1.simplifyIte();
    operand2 = operand2.simplifyIte();
    if(isTrue(operand1)) {
      return operand2;
    } else if(isTrue(operand2)) {
      return operand1;
    } else if(operand1.equals(operand2)) {
      return operand1;
    } else {
      return this;
    }
  }



  @Override
  public Liastar mergeSameVars() {
    ArrayList<Liastar> literals = new ArrayList<>();
    decomposeConjunction(this, literals);
    HashMap<String, String> renameMapping = new HashMap<>();
    for (int i = 0; i < literals.size(); ++ i) {
      Liastar l = literals.get(i);
      if (l == null) continue;
      if (l instanceof LiasumImpl) {
        LiasumImpl sum = (LiasumImpl) l;
        sum.constraints = sum.constraints.mergeSameVars();
        if (sum.OuterVarsEquals()) {
          String var1 = sum.outerVector.get(0);
          String var2 = sum.outerVector.get(1);
          renameMapping.put(var1, var2);
//          literals.set(i, null);
        }
      }
    }

    Liastar result = constructConjunctions(innerStar, literals);
    return replaceVars(result, renameMapping);
  }

  @Override
  public Liastar subformulaWithoutStar() {
    operand1 = operand1.subformulaWithoutStar();
    operand2 = operand2.subformulaWithoutStar();
    return this;
  }

  @Override
  public int embeddingLayers() {
    int leftLayer = operand1.embeddingLayers();
    int rightLayer = operand2.embeddingLayers();
    return (leftLayer > rightLayer) ? leftLayer : rightLayer;
  }

  @Override
  public Liastar transformPostOrder(Function<Liastar, Liastar> transformer) {
    Liastar operand10 = operand1.transformPostOrder(transformer);
    Liastar operand20 = operand2.transformPostOrder(transformer);
    return transformer.apply(mkAnd(innerStar, operand10, operand20));
  }

}
