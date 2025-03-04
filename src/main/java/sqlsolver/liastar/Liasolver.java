package sqlsolver.liastar;

import com.microsoft.z3.*;
import sqlsolver.logic.SqlSolver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static sqlsolver.liastar.Liastar.calculateUnderApprox;

public class Liasolver {

  Liastar liaFormula;

  public Liasolver(Liastar f) {
    liaFormula = f;
  }

  Liasolver(Liastar f, long m) {
    liaFormula = f;
  }

  public LiaSolverStatus solve() {
    try {
      String result = checkUnderapp();
      if (result.equals("SAT")) return LiaSolverStatus.SAT;
    } catch (Exception e) {
    }

//    try {
//      String result = checkOverOverapp();
//      if (result.equals("UNSAT")) return LiaSolverStatus.UNSAT;
//    } catch (Exception e) {
//    }

    try {
      String result = checkOverapp();
      if (result.equals("UNSAT")) return LiaSolverStatus.UNSAT;
      if (result.equals("SAT")) return LiaSolverStatus.SAT;
      return LiaSolverStatus.UNKNOWN;
    } catch (Exception e) {
//      if (LogicSupport.dumpLiaFormulas) {
        e.printStackTrace();
//      }
      return LiaSolverStatus.UNKNOWN;
    }

  }

  String checkUnderapp() {
    try {
      Liastar curexp = liaFormula.deepcopy();
      curexp = calculateUnderApprox(curexp, curexp.embeddingLayers() > 4 ? 1 : 2);
      return solveLia(curexp);
    } catch (Exception e) {
      return "UNKNOWN";
    }
  }

  String checkOverOverapp() throws Exception {
    Liastar tmpFormula = liaFormula.deepcopy();
    tmpFormula = tmpFormula.subformulaWithoutStar();
    String result = solveLia(tmpFormula);
    return result;
  }

  String checkOverapp() throws Exception {
//    if (LogicSupport.dumpLiaFormulas)
      System.out.println("init: " + liaFormula);

    Liastar tmpFormula = liaFormula.deepcopy();
    tmpFormula = tmpFormula.pushUpParameter(new HashSet<>());
    tmpFormula = tmpFormula.removeParameter();
//    if (LogicSupport.dumpLiaFormulas)
      System.out.println("remove param: " + tmpFormula);

    tmpFormula.simplifyMult(new HashMap<>());
    tmpFormula.mergeMult(new HashMap<>());
//    if (LogicSupport.dumpLiaFormulas)
      System.out.println("remove multiplication: " + tmpFormula);

    String result = solveNestedLiastar(tmpFormula);

    if (!result.equals("UNSAT")) {
      tmpFormula = liaFormula.deepcopy();
      tmpFormula = tmpFormula.pushUpParameter(new HashSet<>());
      tmpFormula = tmpFormula.mergeSameVars();
      tmpFormula = tmpFormula.removeParameterEager();
//      if (LogicSupport.dumpLiaFormulas)
        System.out.println("eager remove param: " + tmpFormula);

      tmpFormula.simplifyMult(new HashMap<>());
      tmpFormula.mergeMult(new HashMap<>());
//      if (LogicSupport.dumpLiaFormulas)
        System.out.println("remove multiplication: " + tmpFormula);

      return solveNestedLiastar(tmpFormula);
    } else {
      return "UNSAT";
    }
  }

  /** forall t1 t2. ((isnull(t1)<>0) /\ (isnull(t2)<>0)) -> t1 = t2 */
  private BoolExpr ruleNullEquals(Context ctx) {
    String strVar1 = "t1", strVar2 = "t2", strIsNull = "IsNull";
    Expr[] vars = new Expr[2];
    vars[0] = ctx.mkIntConst(strVar1);
    vars[1] = ctx.mkIntConst(strVar2);

    final Sort I = ctx.getIntSort();
    final FuncDecl func = ctx.mkFuncDecl(strIsNull, I, I);
    Expr isNull1 = ctx.mkApp(func, vars[0]);
    Expr isNull2 = ctx.mkApp(func, vars[1]);
    BoolExpr notNull1 = ctx.mkNot(ctx.mkEq(isNull1, ctx.mkInt(0)));
    BoolExpr notNull2 = ctx.mkNot(ctx.mkEq(isNull2, ctx.mkInt(0)));
    BoolExpr eq = ctx.mkEq(vars[0], vars[1]);

    Expr body = ctx.mkImplies(ctx.mkAnd(notNull1, notNull2), eq);
    return ctx.mkForall(vars, body, 1, null, null, null, null);
  }

  private BoolExpr appendRules(Context ctx, BoolExpr target) {
    target = ctx.mkAnd(ruleNullEquals(ctx), target);
    return target;
  }

  private Set<BoolExpr> getMultipleConditions(Context ctx, Expr expr) {
    Set<BoolExpr> conditions = new HashSet<>();
    if (!expr.isApp()) return conditions;

    Expr[] args = expr.getArgs();
    if (expr.isIDiv()
            && args[0] instanceof IntExpr i0
            && args[1] instanceof IntExpr i1) {
      conditions.add(ctx.mkEq(ctx.mkMod(i0, i1), ctx.mkInt(0)));
    }
    // recursion
    for (Expr sub : args) {
      conditions.addAll(getMultipleConditions(ctx, sub));
    }
    return conditions;
  }

  // upon occurrence of "div u1 u2": append "= (mod u1 u2) 0"
  // this restricts valid division between integers
  private BoolExpr appendMultipleConditions(Context ctx, BoolExpr target) {
    Set<BoolExpr> conditions = getMultipleConditions(ctx, target);
    for (BoolExpr condition : conditions) {
      target = ctx.mkAnd(condition, target);
    }
    return target;
  }

  String solveLia(Liastar f) {
    try (final Context ctx = new Context()) {
      BoolExpr target = ctx.mkTrue();

      Set<String> varsName = f.collectVarSet();
      HashMap<String, IntExpr> varDef = new HashMap<>();
      for (String varName : varsName) {
        IntExpr varExp = ctx.mkIntConst(varName);
        varDef.put(varName, varExp);
        target = ctx.mkAnd(target, ctx.mkLe(ctx.mkInt(0), varExp));
      }

      BoolExpr coreExpr = (BoolExpr) f.transToSMT(ctx, varDef);
      coreExpr = appendMultipleConditions(ctx, coreExpr);
      target = ctx.mkAnd(target, coreExpr);

      // when the formula does not contain stars
      if (f.isLia()) {
        // append rules
        target = appendRules(ctx, target);
      }

//      if (LogicSupport.dumpLiaFormulas) {
        System.out.println("FOL: " + target.toString());
//      }

      Solver s = (f.toString().contains("sqrt")) ?
          ctx.mkSolver() :
          ctx.mkSolver(ctx.tryFor(ctx.mkTactic("qflia"), SqlSolver.timeout));
//       Solver s = ctx.mkSolver();
      s.add(target);
      Status q = s.check();
//      if (LogicSupport.dumpLiaFormulas) {
        System.out.println("smt solver: " + q.toString());
//      }
      return switch (q) {
        case UNKNOWN -> "UNKNOWN";
        case SATISFIABLE -> "SAT";
        case UNSATISFIABLE -> "UNSAT";
      };
    }
  }

  String solveNestedLiastar(Liastar f) throws Exception {
//    if (LogicSupport.dumpLiaFormulas)
      System.out.println("liastar: " + f.toString());
    f.expandStar();
    f = f.simplifyIte();

//    if (LogicSupport.dumpLiaFormulas) {
      System.out.println("lia: " + f.toString());
      System.out.println("#variables in LIA without *: " + f.getVars().size());
//    }

    return solveLia(f);
  }


  String checkOverappWithK() {
    Liastar tmpFormula = liaFormula.deepcopy();
    tmpFormula.simplifyMult(new HashMap<>());
    tmpFormula.mergeMult(new HashMap<>());

    try {
      Status s = solveWithK(tmpFormula);
      if (s == Status.UNSATISFIABLE) return "UNSAT";
    } catch (Exception e) {

    }
    while(true);
  }

  Status solveWithK(Liastar e) {
    Set<String> vars = e.collectVarSet();
    Context ctx = new Context();
    Solver sol = ctx.mkSolver();
    for (String var : vars) {
      sol.add(ctx.mkGe(ctx.mkIntConst(var), ctx.mkInt(0)));
    }
    Expr expr = e.expandStarWithK(ctx, sol, "");
    Status status = sol.check(expr);
    return status;
  }

}
