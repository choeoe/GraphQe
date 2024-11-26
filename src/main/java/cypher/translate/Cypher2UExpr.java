package cypher.translate;

import cypher.algebra.GraphAlgebraBuilder;
import cypher.algebra.translator.GraphAlgebraTranslator;
import cypher.ast.QueryPart;
import cypher.ast.SingleQuery;
import cypher.ast.Statement;
import cypher.ast.Union;
import cypher.ast.clause.Clause;
import cypher.ast.clause.Unwind;
import cypher.ast.clause.match.Match;
import cypher.ast.clause.match.pattern.NodePattern;
import cypher.ast.clause.match.pattern.PatternElement;
import cypher.ast.clause.match.pattern.RelationshipChain;
import cypher.ast.clause.projection.*;
import cypher.ast.expression.*;
import cypher.err.CypherSemanticError;
import cypher.err.CypherUExprTranslationError;
import org.apache.commons.lang3.tuple.Pair;
import representations.GraphAlgebra;
import representations.graphalgebra.Expand;
import representations.graphalgebra.Node;
import representations.graphalgebra.GraphPattern;
import representations.graphalgebra.GraphPatternPart;
import sqlsolver.uexpr.*;

import java.util.*;

import static cypher.translate.GraphPatternBuilder.*;
import static sqlsolver.uexpr.UExprSupport.*;
import static sqlsolver.uexpr.UKind.CONST;
import static sqlsolver.uexpr.UKind.SUMMATION;
import static sqlsolver.uexpr.UPred.PredKind.EQ;

public class Cypher2UExpr {
    private final Statement statement;
    public static UVar outVar;
    public static UVar feed = null;
    private boolean containsAGG = false;
    public static UTerm outTerm;
    public static boolean completed = false;
    public GraphAlgebraTranslator graphAlgebraTranslator;

    public static boolean OUTER = false;

    public static boolean CASE = false;

    public Map<UTerm, UTerm> renameMap = new HashMap<>();

    public Cypher2UExpr(Statement statement, String token) {
        this.statement = statement;
    }

    public Cypher2UExpr(GraphAlgebraTranslator translator) {
        graphAlgebraTranslator = translator;
        statement = null;
    }

    private int aggindex = 0;

    public UTerm translateExpr(Expression expr) {
//        completed = false;
//        UTerm result = new UNormalization(parseExpr2UTerm(expr)).normalizeTerm();
        return parseExpr2UTerm(expr);
//        if (OUTER && !CASE) {
//            return UPred.mkBinary(
//                    EQ,
//                    outTerm,
//                    result
//            );
//        } else {
//            CASE = false;
//            return result;
//        }

    }

    private static boolean hasNullInArithmeticExpression(Expression expr) {
        if (colIsNullPredicate(expr)) return false;

        if (expr instanceof Literal.Null) {
            return true;
        } else if (expr instanceof Binary b) {
            return hasNullInArithmeticExpression(b.lhs) || hasNullInArithmeticExpression(b.rhs);
        } else if (expr instanceof Unary unary) {
            return hasNullInArithmeticExpression(unary.lhs);
        }
        return false;
    }

    private static boolean colIsNullPredicate(Expression expr) {
        return expr instanceof Unary.IsNull;
    }

    private UTerm getRootTerm(UTerm term) {
        UTerm r = term;
        while (renameMap.containsKey(r)) {
            r = renameMap.get(r);
        }
        return r;
    }

    public UTerm parseExpr2UTerm(Expression expr) {
        if (expr instanceof Property property) {
            UTerm term = parseProp2UTerm(property);
            return term;
        } else if (expr instanceof Variable var) {
//            if (var.linked != null && !(var.linked instanceof ListExpression)) {
//                return parseExpr2UTerm(var.linked);
//            }
            UTerm varTerm = UVarTerm.mk(UVar.mkBase(UName.mk(var.name)));
            return getRootTerm(varTerm);
        } else if (expr instanceof Binary binary) {
            return parseBinary2UTerm(binary);
            //TODO left binary ops
        } else if (expr instanceof Unary unary) {
            UTerm body = parseExpr2UTerm(unary.lhs);
            return parsePredBody(unary, body);
        } else if (expr instanceof LabelExpression labelExpression) {
            UVar var = UVar.mkBase(UName.mk(labelExpression.var.name));
            UTerm term = Cypher2UExprHelper.label(var, labelExpression.labelNames.get(0).name);
            if (labelExpression.labelNames.size() > 1) {
                for (int i = 1; i < labelExpression.labelNames.size(); i++) {
                    term = UPred.mkBinary(EQ, Cypher2UExprHelper.label(var, labelExpression.labelNames.get(i).name), term);
                }
            }
            return term;
        } else if (expr instanceof RelTypeExpression relTypeExpression) {
            //TODO multiple rel type and? or?
            UVar var = UVar.mkBase(UName.mk(relTypeExpression.var.name));
            UTerm term = Cypher2UExprHelper.label(var, relTypeExpression.typeNames.get(0).name);
            if (relTypeExpression.typeNames.size() > 1) {
                for (int i = 1; i < relTypeExpression.typeNames.size(); i++) {
                    term = UPred.mkBinary(EQ, Cypher2UExprHelper.label(var, relTypeExpression.typeNames.get(i).name), term);
                }
            }
            return term;
        } else if (expr instanceof Literal l) {
            if (l instanceof Literal.Integer integer) {
                return UConst.mk((int) integer.value);
            } else if (l instanceof Literal.StringLiteral s) {
                return UString.mk(s.stringValue);
            } else if (l instanceof Literal.Null n) {
                return UConst.NULL;
            } else if (l instanceof Literal.True) {
                return UConst.one();
            } else if (l instanceof Literal.False) {
                return UConst.zero();
            }
            return Cypher2UExprHelper.addOrGetStringValue(l.stringValue);
        } else if (expr instanceof PatternElement element) {
            GraphPattern exists = new GraphPattern();
            if (element instanceof NodePattern nodePattern) {
                GraphPatternBuilder.parseNodePattern(nodePattern, exists);
            } else if (element instanceof RelationshipChain relationshipChain) {
                GraphPatternBuilder.parseRelChain(relationshipChain, exists);
            }
            translateGraphPatternPart2UExpr(exists);
            UTerm term = null;
            if (exists.getExprs().size() == 1) {
                term = parseExpr2UTerm(exists.getExprs().get(0));
            } else {
                term = exists.getExprs().stream().map(this::translateExpr).reduce(UMul::mk).orElse(null);
            }
            USum sum = USum.mk(exists.getSumBounds(), exists.getUTerms());
            if (term != null) {
                return USquash.mk(UMul.mk(term, sum));
            }
            return USquash.mk(sum);
        } else if (expr instanceof FunctionInvocation func) {
            List<UTerm> args = func.args.stream().map(this::translateExpr).toList();
//            if (func.isAGG()) {
//                containsAGG = true;
//                completed = true;
//                return graphAlgebraTranslator.translateAgg(outTerm, func);
//            }
            return UFunc.mk(UFunc.FuncKind.STRING, UName.mk(func.functionName.name), args);
        } else if (expr instanceof CaseExpression caseExpression) {
            GraphAlgebraTranslator.CaseUTerm caseUTerm = new GraphAlgebraTranslator.CaseUTerm();
            List<UTerm> alters = new ArrayList<>();
            List<UTerm> whens = new ArrayList<>();
//            ComposedUTerm caseWhen = ComposedUTerm.mk();
//            if(OUTER)
//                CASE = true;
            for (cypher.Pair<Expression, Expression> alter : caseExpression.alternatives) {
                whens.add(parseExpr2UTerm(alter.getLeft()));
                alters.add(parseExpr2UTerm(alter.getRight()));
            }
            for (int i = 0; i < caseExpression.alternatives.size(); i++) {
                final List<UTerm> preCondList = new ArrayList<>();
                for (int j = 0; j < i; j++)
                    preCondList.add(UNeg.mk(whens.get(j).copy()));
                preCondList.add(whens.get(i).copy());
                final UTerm preCond = UMul.mk(preCondList);
//                if (OUTER) {
//                    caseWhen.appendTermPair(preCond, UPred.mkBinary(
//                            EQ,
//                            outTerm,
//                            alters.get(i)
//                    ));
//                }else
//                    caseWhen.appendTermPair(preCond, alters.get(i));
                caseUTerm.addThenforWhen(new cypher.Pair<>(alters.get(i), preCond));
            }
            if (caseExpression.default_.isPresent()) {
                final List<UTerm> elsePreCondList = new ArrayList<>();
                whens.forEach(c -> elsePreCondList.add(UNeg.mk(c.copy())));
                UTerm elsePreCond = null;
                switch (elsePreCondList.size()) {
                    case 0: {
                        ArrayList<UTerm> args = new ArrayList<>();
                        args.add(UConst.ZERO);
                        args.add(UConst.ZERO);
                        elsePreCond = UPred.mk(EQ, UName.mk("="), args, true);
                        break;
                    }
                    case 1: {
                        elsePreCond = elsePreCondList.get(0);
                        break;
                    }
                    default: {
                        elsePreCond = UMul.mk(elsePreCondList);
                    }
                }
                caseUTerm.addThenforWhen(new cypher.Pair<>(parseExpr2UTerm(caseExpression.default_.get()), elsePreCond));
//                if (OUTER) {
//                    caseWhen.appendTermPair(elsePreCond,UPred.mkBinary(
//                            EQ,
//                            outTerm,
//                            parseExpr2UTerm(caseExpression.default_.get()))
//                    );
//                }else
//                    caseWhen.appendTermPair(elsePreCond, parseExpr2UTerm(caseExpression.default_.get()));

            }
//            System.out.println(caseWhen.toPredUTerm());
            return caseUTerm;
        } else if (expr instanceof ExistsFunction existsFunction) {
            if (existsFunction.arg != null) {
                return USquash.mk(parseExpr2UTerm(existsFunction.arg));
            } else {
                return USquash.mk(parseMatch2UTerm(existsFunction.match));
            }
        } else if (expr instanceof ListExpression list) {
            GraphAlgebraTranslator.ListUTerm listUTerm = new GraphAlgebraTranslator.ListUTerm();
            for (Expression col : list.exprs) {
                if (col instanceof MapExpression map) {
                    List<cypher.Pair<String, UTerm>> terms = new ArrayList<>();
                    for (int i = 0; i < map.props.size(); i++) {
                        cypher.Pair<PropertyKeyName, Expression> prop = map.props.get(i);
//                    terms.add(new cypher.Pair<>(prop.getLeft().name, translateExpr(prop.getRight())));
                        terms.add(new cypher.Pair<>("c" + (i + 1), translateExpr(prop.getRight())));
                    }
                    listUTerm.addElement(terms);
                } else {
                    List<cypher.Pair<String, UTerm>> terms = new ArrayList<>();
                    terms.add(new cypher.Pair<>(null, translateExpr(col)));
                }
            }
            return listUTerm;
        }
        throw new RuntimeException("expr parse error: " + expr.getClass() + ": " + expr);
    }

    private UTerm parseMatch2UTerm(Match match) {
        GraphAlgebraBuilder builder = new GraphAlgebraBuilder();
        GraphAlgebra algebra = builder.buildMatch(match);
        GraphAlgebraTranslator translator = new GraphAlgebraTranslator(algebra);
        return translator.translateWithOutProjec();
    }

    private UTerm parseUnary2UTerm(Unary unary, UTerm body) {
        if (unary instanceof Unary.Not) {
            return UNeg.mk(body);
        } else if (unary instanceof Unary.IsNotNull) {
            return UExprSupport.mkNotNullPred(body);
        } else if (body instanceof USquash) {
            return USquash.mk(body);
        } else if (unary instanceof Unary.IsNull) {
            if (body.equals(UConst.NULL)) {
                return UConst.one();
            }
            if (body instanceof UConst) {
                return UConst.zero();
            }
            return mkIsNullPred(body);
        } else if (unary instanceof Unary.Subtract) {
            Integer V = ((UConst) body).value();
            return UConst.mk(-V);
        }
        throw new CypherUExprTranslationError("FAILED at Unary:%s".formatted(unary.getClass()));
    }

    private UTerm parseBinary2UTerm(Binary binary) {
        UTerm left = parseExpr2UTerm(binary.lhs);
        UTerm right = parseExpr2UTerm(binary.rhs);

        left = getRootTerm(left);
        right = getRootTerm(right);
        if (binary instanceof Binary.Equals) {
            if (hasNullInArithmeticExpression(binary))
                return UConst.zero();
            return UPred.mkBinary(EQ, left, right);
        } else if (binary instanceof Binary.NotEquals) {
            if (hasNullInArithmeticExpression(binary))
                return UConst.zero();
            return UPred.mkBinary(UPred.PredKind.NEQ, left, right);
        } else if (binary instanceof Binary.InvalidNotEquals) {
            if (hasNullInArithmeticExpression(binary))
                return UConst.zero();
            return UPred.mkBinary(UPred.PredKind.NEQ, left, right);
        } else if (binary instanceof Binary.GreaterThan) {
            if (hasNullInArithmeticExpression(binary))
                return UConst.zero();
            return UPred.mkBinary(UPred.PredKind.GT, left, right);
        } else if (binary instanceof Binary.GreaterThanOrEqual greaterThanOrEqual) {
            if (hasNullInArithmeticExpression(binary))
                return UConst.zero();
            if (greaterThanOrEqual.lhs instanceof Binary.Subtract subtract) {
                return parseExpr2UTerm(new Binary.LessThan(subtract.lhs, new Binary.Add(greaterThanOrEqual.rhs, subtract.rhs)));
            }
            return UPred.mkBinary(UPred.PredKind.GE, left, right);
        } else if (binary instanceof Binary.LessThan lessThan) {
            if (lessThan.lhs instanceof Binary.Subtract subtract) {
                return parseExpr2UTerm(new Binary.LessThan(subtract.lhs, new Binary.Add(lessThan.rhs, subtract.rhs)));
            }
            return UPred.mkBinary(UPred.PredKind.LT, left, right);
        } else if (binary instanceof Binary.LessThanOrEqual) {
            if (hasNullInArithmeticExpression(binary))
                return UConst.zero();
            return UPred.mkBinary(UPred.PredKind.LE, left, right);
        } else if (binary instanceof Binary.And) {
            return UMul.mk(left, right);
        } else if (binary instanceof Binary.Or) {
            return USquash.mk(UAdd.mk(left, right));
        } else if (binary instanceof Binary.Divide divide) {
            if (divide.lhs instanceof Literal.Number n1 && divide.rhs instanceof Literal.Number n2) {
                return UConst.mk(Integer.parseInt(n1.stringValue) / Integer.parseInt(n2.stringValue));
            }
        } else if (binary instanceof Binary.Multiply) {
            return UMul.mk(left, right);
        } else if (binary instanceof Binary.In in) {
            System.out.println(in);
        } else if (binary instanceof Binary.Add) {
            return UAdd.mk(left, right);
        } else if (binary instanceof Binary.Modulo) {
            return null;
        } else if (binary instanceof Binary.Subtract) {
            return null;
        }
        throw new CypherUExprTranslationError("FAILED at Unary");
    }

    private UTerm parsePredBody(Unary expr, UTerm body) {
        if (body instanceof UAdd add) {
            List<UTerm> newAdd = new ArrayList<>();
            add.subTerms().forEach(t -> {
                newAdd.add(parsePredBody(expr, t));
            });
            return UAdd.mk(newAdd);
        } else if (body instanceof UMul mul) {
            List<UTerm> newMul = new ArrayList<>();
            mul.subTerms().forEach(t -> {
                if (!(t instanceof UPred)) {
                    newMul.add(parseUnary2UTerm(expr, t));
                } else newMul.add(t);
            });
            return UMul.mk(newMul);
        } else return parseUnary2UTerm(expr, body);
    }

    UTerm parseProp2UTerm(Property property) {
        if (property.map instanceof Variable variable) {
            while (variable.linked instanceof Variable) {
                variable = (Variable) variable.linked;
            }
            if (variable.linked instanceof CypherList cypherList) {
                if (cypherList.colNames.contains(property.propertyKey.name)) {
                    property.propertyKey.name = "c" + ((cypherList.colNames.indexOf(property.propertyKey.name) + 1));
                }
            } else if (variable.linked instanceof ListExpression listExpression) {
                int index = listExpression.getConstCols().indexOf(property.propertyKey.name);
                if (index != -1)
                    property.propertyKey.name = "c" + (index + 1);
            }
            return UVarTerm.mk(UVar.mkProj(UName.mk(property.propertyKey.name), UVar.mkBase(UName.mk(variable.name))));
        } else if (property.map instanceof Property property1) {
            return parseProp2UTerm(property1);
        }
        throw new RuntimeException("parse property error, property map type: " + property.map);
    }

    public UTerm toUExpr() {
        UTerm term = parseCypherGraphPatterns();
        System.out.println("UExpr: " + term);
        return term;
    }

    public UTerm parseCypherGraphPatterns() {
        return parseQuery(statement.query.part);
    }

    public UTerm parseQuery(QueryPart part) {
        if (part instanceof SingleQuery singleQuery) {
            return parseSingleQuery(singleQuery);
        } else if (part instanceof Union.All all) {
            UTerm l = parseQuery(all.lhs);
            UTerm r = parseQuery(all.rhs);
            return UAdd.mk(l, r);
        } else if (part instanceof Union.Distinct union) {
            UTerm l = parseQuery(union.lhs);
            UTerm r = parseQuery(union.rhs);
            return USquash.mk(UAdd.mk(l, r));
        }
        throw new CypherUExprTranslationError(CypherUExprTranslationError.NOT_SUPPORT);
    }

    private UTerm parseSingleQuery(SingleQuery query) {
        List<Clause> clauses = query.clauses;
        GraphPattern graphPattern = new GraphPattern();
        for (Clause clause : clauses) {
            if (clause instanceof Match m) {
                graphPattern.tmpVars.clear();
                assembleMATCH(m, graphPattern);
            }
            if (clause instanceof With w) {
                assembleWITH(w, graphPattern);
            }
            if (clause instanceof Return r) {
                graphPattern.setRETURN(r);
            }
            if (clause instanceof Unwind unwind) {
                assert unwind.expr instanceof Variable;
                parseList(((Variable) unwind.expr), unwind.alias, graphPattern);
            }
        }
        return translate(graphPattern);
    }

    public void parseList(Variable list, Variable rowVar, GraphPattern pattern) {
        CypherList tg = null;
        for (CypherList cypherList : pattern.lists) {
            if (cypherList.var.equals(list)) {
                tg = cypherList;
                break;
            }
        }
        pattern.addSumBound(UVar.mkBase(UName.mk(rowVar.name)));
        rowVar.linked = tg;
        assert tg != null;
        List<UTerm> listTerms = new ArrayList<>();
        if (tg.rows.size() == 0) {
            UTerm term = UConst.zero();
            listTerms.add(term);
        }
        for (List<Literal> row : tg.rows) {
            List<UTerm> rowTerm = new ArrayList<>();
            for (int i = 0; i < row.size(); i++) {
                UVar col = UVar.mkProj(UName.mk("c" + (i + 1)), UVar.mkBase(UName.mk(rowVar.name)));
                UTerm term = UPred.mkBinary(EQ, UVarTerm.mk(col), translateExpr(row.get(i)));
                rowTerm.add(term);
            }
            listTerms.add(UMul.mk(rowTerm));
        }
        pattern.addUterm(UAdd.mk(listTerms));
    }

    public UTerm translate(GraphPattern pattern) {
        translateGraphPatternPart2UExpr(pattern);
        if (outVar == null) {
            outVar = UVar.mkBase(UName.mk("queryTuple"));
        }
        //TODO: parse graph pattern all exprs
        for (Expression expr : pattern.getExprs()) {
            UTerm term = translateExpr(expr);
            pattern.addUterm(term);
        }
        translateRETURN2UExpr(pattern);
        translateAGGs(pattern);
        if (pattern.getSumBounds().size() > 0) {
            USum sum = USum.mk(pattern.getSumBounds(), pattern.getUTerms());
            pattern.setUTerms(sum);
        }
        return pattern.getUTerms();
    }

    private void translateAGGs(GraphPattern pattern) {
        List<UTerm> AGGTerms = new ArrayList<>();
        UTerm cond = USquash.mk(pattern.getUTerms());
        AGGTerms.add(cond);
        if (pattern.AGGs.size() == 0) {
            return;
        } else {
            for (Expression agg : pattern.AGGs) {
                UTerm tr = translateExpr(((Binary.Equals) agg).lhs);
                UTerm right = constructAGGTerm(tr, ((Binary.Equals) agg).rhs, pattern);
                AGGTerms.add(UPred.mkBinary(EQ, tr, right));
            }
        }
        pattern.setUTerms(UMul.mk(AGGTerms));
    }

    private UTerm constructAGGTerm(UTerm tr, Expression expr, GraphPattern pattern) {
        if (expr instanceof FunctionInvocation func) {
            assert func.args.size() == 1;
            Expression arg = func.args.get(0);
            if (func.functionName.name.equals("COUNT")) {
                UTerm cond = pattern.getUTerms().copy();
                if (!arg.toString().equals("*")) {
                    UVar agg;
//                    if ((arg instanceof Variable && ((Variable) arg).linked == null)
//                            || (arg instanceof Variable && ((Variable) arg).linked != null && ((Variable) arg).linked instanceof Variable)) {
                    if (arg instanceof Variable) {
                        agg = UVar.mkBase(UName.mk(((Variable) arg).name));
                    } else {
                        assert arg instanceof Property || (arg instanceof Variable && ((Variable) arg).linked instanceof Property);
                        if (!(arg instanceof Property)) {
                            arg = ((Property) ((Variable) arg).linked);
                        }
                        UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                        agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                    }
                    if (func.distinct) {

                    }
                    if (cond instanceof USum sum) {
                        sum.addMulSubTerm(mkNotNullPred(agg));
                    } else {
                        cond = UMul.mk(cond, mkNotNullPred(agg));
                        cond = USum.mk(pattern.getSumBounds(), cond).copy();
                    }
                } else {
//                    if (cond instanceof USum sum) {
//                        sum.addMulSubTerm(mkNotNullPred(agg));
//                    } else {
//                        cond = UMul.mk(cond, mkNotNullPred(agg));
//                        cond = USum.mk(pattern.getSumBounds(), cond).copy();
//                    }
                }
                cond = replaceAllBoundedVars(cond);
                return cond;
            } else if (func.functionName.name.equals("SUM")) {
                UTerm cond = pattern.getUTerms().copy();
                UVar agg;
                if (arg instanceof Variable variable) {
                    agg = UVar.mkBase(UName.mk(variable.name));
                } else {
                    assert arg instanceof Property;
                    assert ((Property) arg).map instanceof Variable;
                    UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                    agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                }
                if (func.distinct) {

                }
                UTerm sumTerm = UMul.mk(mkNotNullPred(agg), UVarTerm.mk(agg));
                if (cond instanceof USum sum) {
                    sum.addMulSubTerm(sumTerm);
                } else {
                    cond = UMul.mk(cond, sumTerm);
                }
                cond = replaceAllBoundedVars(cond);
                return cond;
            } else if (func.functionName.name.equals("AVG")) {
                FunctionInvocation SUM = ((FunctionInvocation) expr).copy();
                FunctionInvocation COUNT = ((FunctionInvocation) expr).copy();
                SUM.functionName.name = "SUM";
                COUNT.functionName.name = "COUNT";
                UTerm sumTerm = constructAGGTerm(tr, SUM, pattern);
                UTerm cntTerm = constructAGGTerm(tr, COUNT, pattern);
                return UPred.mkBinary(EQ,
                        UMul.mk(tr, cntTerm),
                        sumTerm
                );
            } else if (func.functionName.name.equals("MAX")) {
                UTerm cond1 = pattern.getUTerms().copy();
                UTerm cond2 = pattern.getUTerms().copy();
                if (arg instanceof Literal.Integer integer) {
                    return UConst.mk((int) integer.value);
                }
                assert ((Property) arg).map instanceof Variable;
                UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                UVar agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                UTerm subCond1 = UPred.mkBinary(UPred.PredKind.GT, UVarTerm.mk(agg), tr);
                UTerm subCond2 = UPred.mkBinary(EQ, UVarTerm.mk(agg), tr);
                if (cond1 instanceof USum sum) {
                    sum.addMulSubTerm(subCond1);
                } else {
                    cond1 = UMul.mk(cond1, subCond1);
                }
                if (cond2 instanceof USum sum) {
                    sum.addMulSubTerm(subCond2);
                } else {
                    cond2 = UMul.mk(cond2, subCond2);
                }
                System.err.println(cond1);
                System.err.println(cond2);
                cond1 = replaceAllBoundedVars(cond1);
                cond2 = replaceAllBoundedVars(cond2);
                UTerm cond = UMul.mk(UNeg.mk(cond1), USquash.mk(cond2));
                return cond;
            } else if (func.functionName.name.equals("MIN")) {
                UTerm cond1 = pattern.getUTerms().copy();
                UTerm cond2 = pattern.getUTerms().copy();
                assert arg instanceof Property;
                assert ((Property) arg).map instanceof Variable;
                UVar map = UVar.mkBase(UName.mk(((Variable) ((Property) arg).map).name));
                UVar agg = UVar.mkProj(UName.mk(((Property) arg).propertyKey.name), map);
                UTerm subCond1 = UPred.mkBinary(UPred.PredKind.LT, UVarTerm.mk(agg), tr);
                UTerm subCond2 = UPred.mkBinary(EQ, UVarTerm.mk(agg), tr);
                if (cond1 instanceof USum sum) {
                    sum.addMulSubTerm(subCond1);
                } else {
                    cond1 = UMul.mk(cond1, subCond1);
                }
                if (cond2 instanceof USum sum) {
                    sum.addMulSubTerm(subCond2);
                } else {
                    cond2 = UMul.mk(cond2, subCond2);
                }
                System.err.println(cond1);
                System.err.println(cond2);
                cond1 = replaceAllBoundedVars(cond1);
                cond2 = replaceAllBoundedVars(cond2);
                UTerm cond = UMul.mk(UNeg.mk(cond1), USquash.mk(cond2));
                return cond;
            }
        } else if (expr instanceof Binary binary) {
            if (binary instanceof Binary.Add add) {
                return UAdd.mk(constructAGGTerm(tr, add.lhs, pattern), constructAGGTerm(tr, add.rhs, pattern));
            }
        }
        return null;
    }


    public UTerm replaceAllBoundedVars(UTerm expr) {
        expr = transformSubTerms(expr, this::replaceAllBoundedVars);
        if (expr.kind() != SUMMATION) return expr;

        final Set<UVar> oldVars = new HashSet<>(((USum) expr).boundedVars());
        for (UVar oldVar : oldVars) {
            final UVar newVar = mkAGGVar();
            expr = expr.replaceVar(oldVar, newVar, true);
        }
        return expr;
    }

    public UVar mkAGGVar() {
        return UVar.mkBase(UName.mk("a" + aggindex++));
    }

    private void generateOrderBy(OrderBy orderBy, GraphPattern pattern) {
        FunctionName orderByName = new FunctionName("#orderby");
        for (SortItem item : orderBy.sortItems) {
//            pattern.addUterm(new FunctionInvocation(orderByName, false, parseExpr2UTerm(item.expression)));
        }
    }

    private void translateRETURN2UExpr(GraphPattern graphPattern) {
        Return re = graphPattern.getRETURN();
        re.orderBy.ifPresent(orderBy -> generateOrderBy(orderBy, graphPattern));
        List<ReturnItem> items = re.returnItems;
        if (re.distinct) {
            graphPattern.DISTINCT = true;
        }
        if (items.size() == 1) {
            Expression ret;
            if (items.get(0).expression instanceof Literal.Null) {
                ret = new Unary.IsNull(new Variable(outVar.name().toString()));
            } else {
                ret = new Binary.Equals(
                        new Variable(outVar.name().toString())
                        , items.get(0).expression
                );
            }
//            graphPattern.addUterm(UPred.mkBinary(UPred.PredKind.EQ, UVarTerm.mk(outVar),
//                    parseExpr2UTerm(items.get(0).expression)));
            UTerm term = translateExpr(ret);
            if (containsAGG) {
                graphPattern.AGGs.add(ret);
                containsAGG = false;
            } else {
                graphPattern.addUterm(term);
            }
        } else {
            for (int i = 0; i < items.size(); i++) {
                ReturnItem item = re.returnItems.get(i);
                if (items.get(i).expression instanceof CaseExpression caseExpression) {
                    re.returnItems.remove(item);
                    Expression easy = caseExpression.expression.orElse(null);
                    if (caseExpression.alternatives.size() == 1) {
                        if (easy != null) {
                            Binary.Equals equals = new Binary.Equals(easy, caseExpression.alternatives.get(0).getLeft());
                            graphPattern.addUterm(translateExpr(equals));
                            UVar attr = UVar.mkProj(UName.mk("#" + (i + 1)), outVar);
                            graphPattern.addUterm(UPred.mkBinary(EQ, UVarTerm.mk(attr),
                                    translateExpr(caseExpression.alternatives.get(0).getRight())));
                        }
                    } else {
                        for (cypher.Pair<Expression, Expression> exprs : caseExpression.alternatives) {
                            UTerm term = graphPattern.getUTerms();
                        }
                    }
                } else {
//                    UVar attr1 = UVar.mkProj(UName.mk("#" + (i + 1)), outVar);
                    Expression ret;
                    if (items.get(i).expression instanceof Literal.Null) {
                        ret = new Unary.IsNull(new Property(new Variable(outVar.name().toString()), new PropertyKeyName("#" + (i + 1))));
                    } else {
                        ret = new Binary.Equals(
                                new Property(new Variable(outVar.name().toString()), new PropertyKeyName("#" + (i + 1)))
                                , items.get(i).expression
                        );
                    }
                    UTerm term = translateExpr(ret);
                    if (containsAGG) {
                        graphPattern.AGGs.add(ret);
                        containsAGG = false;
                    } else {
                        graphPattern.addUterm(term);
                    }
//                    graphPattern.addUterm(UPred.mkBinary(UPred.PredKind.EQ, UVarTerm.mk(attr1),
//                            parseExpr2UTerm(items.get(i).expression)));
                }
            }
        }
    }

    private UTerm translateWHEREPattern2UExpr(PatternExpression expr, GraphPattern pattern) {
        List<UTerm> terms = new ArrayList<>();
        Set<UVar> insideSumBounds = new HashSet<>();
        for (GraphPatternPart part : expr.element) {
            UVar var = pattern.addSumBound(part.var.name);
            insideSumBounds.add(var);
            if (part instanceof Node node) {
                terms.add(Cypher2UExprHelper.node(var));
                if (node.properties.size() > 0) {
                    UTerm term2 = node.properties.stream().map(l -> translateExpr(l)).reduce(UMul::mk).get();
                    terms.add(term2);
                }
                if (node.labelConstraints != null) {
                    UTerm term = translateExpr(node.labelConstraints);
                    terms.add(term);
                }
            } else if (part instanceof Expand relation) {
                UVar rel = pattern.addSumBound(relation.var.name);
                insideSumBounds.add(rel);
                UVar left = pattern.addSumBound(relation.start.var.name);
                UVar right = pattern.addSumBound(relation.end.var.name);
                terms.add(UPred.mkBinary(EQ, left, Cypher2UExprHelper.relIn(rel)));
                terms.add(UPred.mkBinary(EQ, right, Cypher2UExprHelper.relOut(rel)));
                terms.add(Cypher2UExprHelper.rel(var));
                if (relation.properties.size() > 0) {
                    UTerm term2 = relation.properties.stream().map(l -> translateExpr(l)).reduce(UMul::mk).get();
                    terms.add(term2);
                }
                if (relation.labelConstraints != null) {
                    UTerm term = translateExpr(relation.labelConstraints);
                    terms.add(term);
                }
            }
        }
        if (terms.size() > 0) {
            UTerm joinTerm = terms.stream().reduce(UMul::mk).get();
            USum sum = USum.mk(insideSumBounds, joinTerm);
            return USquash.mk(sum);
        }
        throw new CypherSemanticError(CypherSemanticError.WHERE_CLAUSE_ERROR);
    }

    private UTerm translateGraphPatternPart2UExpr(GraphPattern graphPattern) {
        for (GraphPatternPart part : graphPattern.getAllGraphPatternParts()) {
            if (part instanceof Node node) {
                UVar var = graphPattern.addSumBound(node.var.name);
                graphPattern.addUterm(Cypher2UExprHelper.node(var));
                graphPattern.addSumBound(var);
                addPatternPartProp(part, graphPattern);
            }
            if (part instanceof Expand relation) {
                UVar rel = graphPattern.addSumBound(relation.var.name);
                graphPattern.addUterm(Cypher2UExprHelper.rel(rel));
                if (relation.start != null) {
                    UVar left = graphPattern.addSumBound(relation.start.var.name);
                    addPatternPartProp(relation.start, graphPattern);
                    graphPattern.addUterm(UPred.mkBinary(EQ, left, Cypher2UExprHelper.relIn(rel)));
                }
                if (relation.end != null) {
                    UVar right = graphPattern.addSumBound(relation.end.var.name);
                    addPatternPartProp(relation.end, graphPattern);
                    graphPattern.addUterm(UPred.mkBinary(EQ, right, Cypher2UExprHelper.relOut(rel)));
                }
                graphPattern.addSumBound(rel);
            }
        }
        return null;
    }

    private void addPatternPartProp(GraphPatternPart part, GraphPattern pattern) {
        for (Binary.Equals prop : part.properties) {
            pattern.addUterm(UPred.mkBinary(EQ, UVarTerm.mk(pattern.addSumBound(part.var.name)),
                    translateExpr(prop.rhs)));
        }
        if (part.labelConstraints != null) {
            pattern.addUterm(translateExpr(part.labelConstraints));
        }
    }

    static class ComposedUTerm {
        // (p1 /\ v1) \/ (p2 /\ v2) \/ ...
        private final List<Pair<UTerm, UTerm>> preCondAndValues;

        private boolean subQuery = false;

        private UVar subQueryOutVar = null;

        ComposedUTerm(List<Pair<UTerm, UTerm>> preCondAndValues) {
            this.preCondAndValues = preCondAndValues;
        }

        ComposedUTerm(UTerm preCond, UTerm value) {
            this.preCondAndValues = new ArrayList<>();
            preCondAndValues.add(Pair.of(preCond, value));
        }

        ComposedUTerm(UTerm value) {
            this.preCondAndValues = new ArrayList<>();
            preCondAndValues.add(Pair.of(UConst.one(), value));
        }

        ComposedUTerm(UTerm value, UVar subQueryOutVar) {
            this.preCondAndValues = new ArrayList<>();
            preCondAndValues.add(Pair.of(UConst.one(), value));
            this.subQuery = true;
            this.subQueryOutVar = subQueryOutVar;
        }

        ComposedUTerm() {
            this.preCondAndValues = new ArrayList<>();
        }

        void appendTermPair(UTerm preCond, UTerm value) {
            this.preCondAndValues.add(Pair.of(preCond, value));
        }

        UTerm toPredUTerm() {
            // return [p1] * v1 + [p2] * v2 + ...
            final List<UTerm> subTerms = new ArrayList<>();
            for (var pair : preCondAndValues) {
                subTerms.add(UMul.mk(pair.getLeft(), pair.getRight()).copy());
            }

            return UExprSupport.normalizeExpr(UAdd.mk(subTerms));
        }

        static ComposedUTerm doUnaryOp(ComposedUTerm pred0, UnaryOpKind opKind) {
            final List<Pair<UTerm, UTerm>> pair = new ArrayList<>();
            for (var pair0 : pred0.preCondAndValues) {
                final UTerm preCond = pair0.getLeft();
                final UTerm value = pair0.getRight();
                final UTerm finalValue;
                switch (opKind) {
                    case UNARY_MINUS -> {
                        if (value.kind() == CONST) {
                            final Integer V = ((UConst) value).value();
                            finalValue = UConst.mk(-V);
                        } else throw new IllegalArgumentException("Unsupported unary operator: " + opKind);
                    }
                    case NOT -> {
                        if (value.kind() == CONST) {
                            if (value.equals(UConst.nullVal()))
                                finalValue = value.copy();
                                // TODO: handler other cases for finalValue
                            else throw new IllegalArgumentException("Unsupported unary operator: " + opKind);
                        } else {
                            finalValue = UNeg.mk(value.copy());
                        }
                    }
                    default -> throw new IllegalArgumentException("Unsupported unary operator: " + opKind);
                }
                pair.add(Pair.of(preCond, finalValue));
            }
            return ComposedUTerm.mk(pair);
        }

        static ComposedUTerm doArithmeticOp(ComposedUTerm pred0, ComposedUTerm pred1, BinaryOpKind opKind) {
            assert opKind.isArithmetic();
            final List<Pair<UTerm, UTerm>> pair = new ArrayList<>();
            for (var pair0 : pred0.preCondAndValues) {
                for (var pair1 : pred1.preCondAndValues) {
                    final UTerm preCond0 = pair0.getLeft(), preCond1 = pair1.getLeft();
                    final UTerm value0 = pair0.getRight(), value1 = pair1.getRight();
                    final UTerm combinedPreCond;
                    if (preCond0.equals(UConst.ONE)) combinedPreCond = preCond1.copy();
                    else if (preCond1.equals(UConst.ONE)) combinedPreCond = preCond0.copy();
                    else combinedPreCond = UMul.mk(preCond0.copy(), preCond1.copy());
                    final UTerm combinedValue;
                    switch (opKind) {
                        case PLUS -> combinedValue = UAdd.mk(value0.copy(), value1.copy());
                        case MULT -> combinedValue = UMul.mk(value0.copy(), value1.copy());
                        case MINUS -> {
                            if (value0.kind() == CONST && value1.kind() == CONST) {
                                final Integer lhsV = ((UConst) value0).value(), rhsV = ((UConst) value1).value();
                                if (lhsV - rhsV >= 0)
                                    combinedValue = UConst.mk(lhsV - rhsV);
                                else throw new IllegalArgumentException("Unsupported binary operator: " + opKind);
                            } else {
                                UName funcName = UName.mk("minus");
                                combinedValue = UFunc.mk(UFunc.FuncKind.INTEGER, funcName, List.of(value0, value1));
                            }
                        }
                        case DIV -> {
                            if (value0.kind() == CONST && value1.kind() == CONST) {
                                final Integer lhsV = ((UConst) value0).value(), rhsV = ((UConst) value1).value();
                                if (rhsV * (lhsV / rhsV) == lhsV)
                                    combinedValue = UConst.mk(lhsV / rhsV);
                                else throw new IllegalArgumentException("Unsupported binary operator: " + opKind);
                            } else {
                                UName funcName = UName.mk("divide");
                                combinedValue = UFunc.mk(UFunc.FuncKind.INTEGER, funcName, List.of(value0, value1));
                            }
                        }
                        default -> throw new IllegalArgumentException("Unsupported binary operator: " + opKind);
                    }
                    pair.add(Pair.of(combinedPreCond, combinedValue));
                }
            }

            return ComposedUTerm.mk(pair);
        }

        static ComposedUTerm doLogicOp(ComposedUTerm pred0, ComposedUTerm pred1, BinaryOpKind opKind) {
            assert opKind.isLogic();
            final List<Pair<UTerm, UTerm>> pair = new ArrayList<>();
            for (var pair0 : pred0.preCondAndValues) {
                for (var pair1 : pred1.preCondAndValues) {
                    final UTerm preCond0 = pair0.getLeft(), preCond1 = pair1.getLeft();
                    final UTerm value0 = pair0.getRight(), value1 = pair1.getRight();
                    final UTerm combinedPreCond;
                    if (preCond0.equals(UConst.ONE)) combinedPreCond = preCond1.copy();
                    else if (preCond1.equals(UConst.ONE)) combinedPreCond = preCond0.copy();
                    else combinedPreCond = UMul.mk(preCond0.copy(), preCond1.copy());
                    final UTerm combinedValue;
                    switch (opKind) {
                        case OR -> combinedValue = UAdd.mk(value0.copy(), value1.copy());
                        case AND -> combinedValue = UMul.mk(value0.copy(), value1.copy());
                        default -> throw new IllegalArgumentException("Unsupported binary operator: " + opKind);
                    }
                    pair.add(Pair.of(combinedPreCond, combinedValue));
                }
            }

            return ComposedUTerm.mk(pair);
        }

        static UTerm mkFuncCall(UFunc.FuncKind funcKind, UName funcName, List<ComposedUTerm> arguments) {
            List<UTerm> funcArguments = new ArrayList<>();
            for (ComposedUTerm composedArg : arguments) {
//        assert composedArg.preCondAndValues.size() == 1;
//        final UTerm left = composedArg.preCondAndValues.get(0).getLeft();
//        assert left.equals(UConst.ONE);
                funcArguments.add(composedArg.toPredUTerm());
            }
            return UFunc.mk(funcKind, funcName, funcArguments);
        }

        UVar getSubQueryOutVar() {
            if (this.subQuery) return subQueryOutVar;
            return null;
        }

        void replaceVar(UVar baseVar, UVar repVar) {
            for (Pair<UTerm, UTerm> pair : this.preCondAndValues) {
                pair.getRight().replaceVarInplace(baseVar, repVar, false);
            }
        }

        static ComposedUTerm mk(List<Pair<UTerm, UTerm>> preCondAndValues) {
            return new ComposedUTerm(preCondAndValues);
        }

        static ComposedUTerm mk(UTerm preCond, UTerm value) {
            return new ComposedUTerm(preCond, value);
        }

        static ComposedUTerm mk(UTerm value) {
            return new ComposedUTerm(value);
        }

        static ComposedUTerm mk(UTerm value, UVar subQueryOutVar) {
            return new ComposedUTerm(value, subQueryOutVar);
        }

        static ComposedUTerm mk() {
            return new ComposedUTerm();
        }
    }
}
