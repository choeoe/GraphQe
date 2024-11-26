package cypher;

import cypher.algebra.GraphAlgebraBuilder;
import cypher.algebra.normalizer.ReturnNormalizer;
import cypher.algebra.translator.GraphAlgebraTranslator;
import cypher.parser.CypherStringVisitor;
import cypher.translate.Cypher2UExpr;
import cypher.translate.CypherPair;
import cypher.translate.UExprNormalizer;
import representations.GraphAlgebra;
import sqlsolver.logic.SqlSolver;
import sqlsolver.uexpr.UExprSupport;
import sqlsolver.uexpr.UName;
import sqlsolver.uexpr.UTerm;
import sqlsolver.uexpr.UVar;

import java.util.List;
import java.util.Map;

public class CypherSolver {
    private final CypherPair pair;
    public static long total;
    public CypherSolver(CypherPair pair) {
        this.pair = pair;
    }

    public int solveByAlgebra() {
        CypherStringVisitor visitor1 = new CypherStringVisitor();
        CypherStringVisitor visitor2 = new CypherStringVisitor();
        visitor1.visit(pair.getStmt1());
        visitor2.visit(pair.getStmt2());
        visitor1.print();
        visitor2.print();
        if (visitor1.get().equals(visitor2.get())) {
            System.out.println("Normalized Equivalent");
            return SqlSolver.EQ;
        }
        GraphAlgebraBuilder builder1 = new GraphAlgebraBuilder(pair.getStmt1());
        GraphAlgebraBuilder builder2 = new GraphAlgebraBuilder(pair.getStmt2());
//        Cypher2UExpr.OUTER = false;
//        Cypher2UExpr.CASE = false;
        GraphAlgebra algebra1 = builder1.build();
        GraphAlgebra algebra2 = builder2.build();

        ReturnNormalizer normalizer = new ReturnNormalizer(algebra1, algebra2);
        normalizer.normalize();
        List<List<Pair<Integer,Integer>>> maps = normalizer.getMaps();
        if (builder1.subQueries.size() != builder2.subQueries.size()) {
            System.out.println("NEQ");
            return SqlSolver.NEQ;
        } else {
            for (int i = 0; i < builder1.subQueries.size(); i++) {
                GraphAlgebra a1 = builder1.subQueries.get(i).copy();
                GraphAlgebra a2 = builder2.subQueries.get(i).copy();
                System.out.println("SUB_ALGE1: "+a1);
                System.out.println("SUB_ALGE2: "+a2);
                ReturnNormalizer subNormalizer = new ReturnNormalizer(a1, a2);
                subNormalizer.normalize();
                List<List<Pair<Integer,Integer>>> subMaps = subNormalizer.getMaps();
                GraphAlgebraTranslator.setReturnMaps(subMaps);
                boolean subEQ = false;
                while (!GraphAlgebraTranslator.getMaps().isEmpty()) {
                    GraphAlgebraTranslator t1 = new GraphAlgebraTranslator(a1);
                    GraphAlgebraTranslator t2 = new GraphAlgebraTranslator(a2);
                    UTerm expr1 = t1.translate();
                    UTerm expr2 = t2.translate();
                    System.out.printf("SUBU1: %s%n", expr1);
                    System.out.printf("SUBU2: %s%n", expr2);
                    UExprNormalizer normalizer1 = new UExprNormalizer();
                    normalizer1.tmpVars = t1.tmpVars;
                    normalizer1.renameMap = t1.renameMap;

                    UExprNormalizer normalizer2 = new UExprNormalizer();
                    normalizer2.tmpVars = t2.tmpVars;
                    normalizer2.renameMap = t2.renameMap;

                    expr1 = normalizer1.normalizeTerm(expr1);
                    expr1 = UExprSupport.normalizeExpr(expr1);

                    expr2 = normalizer2.normalizeTerm(expr2);
                    expr2 = UExprSupport.normalizeExpr(expr2);
                    int result = solveByLia(expr1, expr2);
                    if (result == SqlSolver.EQ) {
                        GraphAlgebraTranslator.clearMaps();
                        subEQ = true;
                        System.out.println("Sub EQ: "+i);
                        break;
                    }
                    GraphAlgebraTranslator.getMaps().poll();
                }
                if (!subEQ) {
                    return -1;
                }
            }
        }
        if (maps.isEmpty()) {
            System.err.println("No Valid Mappings for Returning Elements. Trying Quick Verifying");
            GraphAlgebraTranslator translator1 = new GraphAlgebraTranslator(algebra1);
            translator1.isKey = true;
            Cypher2UExpr.OUTER = false;
            Cypher2UExpr.CASE = false;
            GraphAlgebraTranslator translator2 = new GraphAlgebraTranslator(algebra2);
            UTerm expr1 = translator1.translate();
            UTerm expr2 = translator2.translate();
            System.out.printf("U1: %s%n", expr1);
            System.out.printf("U2: %s%n", expr2);
            System.out.printf("U1: %s%n", expr1);
            System.out.printf("U2: %s%n", expr2);
            UExprNormalizer normalizer1 = new UExprNormalizer();
            normalizer1.tmpVars = translator1.tmpVars;
            normalizer1.renameMap = translator1.renameMap;

            UExprNormalizer normalizer2 = new UExprNormalizer();
            normalizer2.tmpVars = translator2.tmpVars;
            normalizer2.renameMap = translator2.renameMap;

            long startTime = System.currentTimeMillis();
            expr1 = normalizer1.normalizeTerm(expr1);
            expr1 = UExprSupport.normalizeExpr(expr1);

            expr2 = normalizer2.normalizeTerm(expr2);
            expr2 = UExprSupport.normalizeExpr(expr2);
            System.out.printf("U1: %s%n", expr1);
            System.out.printf("U2: %s%n", expr2);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            total += elapsedTime;

            int result = solveByLia(expr1, expr2);
            return result;
        }
        GraphAlgebraTranslator.setReturnMaps(maps);
        System.out.println("Total maps: %d".formatted(maps.size()));
        int time = 1;
        while (!GraphAlgebraTranslator.getMaps().isEmpty()) {
            GraphAlgebraTranslator translator1 = new GraphAlgebraTranslator(algebra1);
            translator1.isKey = true;
            Cypher2UExpr.OUTER = false;
            Cypher2UExpr.CASE = false;
            GraphAlgebraTranslator translator2 = new GraphAlgebraTranslator(algebra2);
            System.out.println("Current Map:");
            for (Pair<Integer, Integer> pair : GraphAlgebraTranslator.getMaps().peek()) {
                System.out.println("Index in List1: " + pair.getLeft() + ", Index in List2: " + pair.getRight());
            }
            UTerm expr1 = translator1.translate();
            UTerm expr2 = translator2.translate();
            System.out.printf("U1: %s%n", expr1);
            System.out.printf("U2: %s%n", expr2);
            UExprNormalizer normalizer1 = new UExprNormalizer();
            normalizer1.tmpVars = translator1.tmpVars;
            normalizer1.renameMap = translator1.renameMap;

            UExprNormalizer normalizer2 = new UExprNormalizer();
            normalizer2.tmpVars = translator2.tmpVars;
            normalizer2.renameMap = translator2.renameMap;

            long startTime = System.currentTimeMillis();
            expr1 = normalizer1.normalizeTerm(expr1);
            expr1 = UExprSupport.normalizeExpr(expr1);

            expr2 = normalizer2.normalizeTerm(expr2);
            expr2 = UExprSupport.normalizeExpr(expr2);
//U1: [#1(t) = ∑{x1,x3,x4}(([SAL(x1) < 11] + not([SAL(x1) < 11])) * [Node(x1) = 1] * ([#1(t) = 11] * [SAL(x1) < 11] + [#1(t) = -1 * SAL(x1)] * not([SAL(x1) < 11])))] * ([#0(t) = 11] * [SAL(x1) < 11] + [#0(t) = -1 * SAL(x1)] * not([SAL(x1) < 11]))
//U2: [#1(t) = ∑{x1,x2,x3}(([#1(t) = 11] * [SAL(x3) < 11] + [#1(t) = -1 * SAL(x3)] * not([SAL(x3) < 11])))] * ([#0(t) = 11] * [SAL(x3) < 11] + [#0(t) = -1 * SAL(x3)] * not([SAL(x3) < 11]))
            System.out.printf("U1: %s%n", expr1);
            System.out.printf("U2: %s%n", expr2);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            total += elapsedTime;

            int result = solveByLia(expr1, expr2);
//            if (result != SqlSolver.EQ) {
//                if (translator1.sumBounds.size() == 1 || translator2.sumBounds.size() == 1) {
//                    GraphAlgebraTranslator.peel = true;
//                }
//                expr1 = translator1.translate();
//                expr2 = translator2.translate();
//                System.out.printf("U1: %s%n", expr1);
//                System.out.printf("U2: %s%n", expr2);
//                result = solveByLia(expr1, expr2);
//            }
            if(result == 0) {
                GraphAlgebraTranslator.clearMaps();
                System.out.println("Success in mapping:%d ".formatted(time));
                return result;
            }
            time++;
            GraphAlgebraTranslator.getMaps().poll();
        }
        GraphAlgebraTranslator.clearMaps();
        System.out.println("Failed in mapping:%d ".formatted(time));
        return -1;
    }
    public int solve() {

        Cypher2UExpr q1 = new Cypher2UExpr(pair.getStmt1(), "x");
        Cypher2UExpr q2 = new Cypher2UExpr(pair.getStmt2(), "x");

        UTerm expr1 = q1.toUExpr();
        UTerm expr2 = q2.toUExpr();

        int result = solveByLia(expr1, expr2);
        return result;
    }

    public int solveByLia(UTerm expr1,UTerm expr2) {
        SqlSolver sqlSolver = new SqlSolver(expr1, expr2
                , UVar.mkBase(UName.mk("queryTuple")), UVar.mkBase(UName.mk("queryTuple")));
        SqlSolver.initialize();
        int result = sqlSolver.proveEq();
        System.out.println(result == SqlSolver.EQ? "EQ" : "NEQ");
        return result;
    }
}
