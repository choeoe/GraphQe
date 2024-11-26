package cypher;// Generated from .\\Cypher.g4 by ANTLR 4.5

    import java.util.*;
    import java.util.stream.Collectors;
    import org.apache.commons.lang3.tuple.Pair;
    import org.apache.commons.lang3.tuple.ImmutablePair;
    import cypher.ast.*;
    import cypher.ast.expression.*;
    import cypher.ast.clause.*;
    import cypher.ast.clause.match.*;
    import cypher.ast.clause.match.pattern.*;
    import cypher.ast.clause.projection.*;
    import cypher.ast.expression.*;
    import static cypher.parser.SpanUtil.makeSpan;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CypherParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CypherVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCypher(CypherParser.CypherContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CypherParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(CypherParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnion(CypherParser.UnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause(CypherParser.ClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatch(CypherParser.MatchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unwindClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnwindClause(CypherParser.UnwindClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith(CypherParser.WithContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#return_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_(CypherParser.Return_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(CypherParser.WhereContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#where_nospan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere_nospan(CypherParser.Where_nospanContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder(CypherParser.OrderContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(CypherParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit(CypherParser.LimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(CypherParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(CypherParser.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#props}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProps(CypherParser.PropsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationTypes(CypherParser.RelationTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(CypherParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapLiteral_nospan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapLiteral_nospan(CypherParser.MapLiteral_nospanContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapKeyValuePair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapKeyValuePair(CypherParser.MapKeyValuePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listLit_nospan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLit_nospan(CypherParser.ListLit_nospanContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression12}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression12(CypherParser.Expression12Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression11}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression11(CypherParser.Expression11Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(CypherParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#properSingle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperSingle(CypherParser.ProperSingleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#properLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperLabel(CypherParser.ProperLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression1(CypherParser.Expression1Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression0(CypherParser.Expression0Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#existsFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistsFunction(CypherParser.ExistsFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#existsFunctionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistsFunctionName(CypherParser.ExistsFunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#partialArithmeticExpressionWithLowPrecedence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialArithmeticExpressionWithLowPrecedence(CypherParser.PartialArithmeticExpressionWithLowPrecedenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#partialArithmeticExpressionWithHighPrecedence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialArithmeticExpressionWithHighPrecedence(CypherParser.PartialArithmeticExpressionWithHighPrecedenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#partialArithmeticPow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialArithmeticPow(CypherParser.PartialArithmeticPowContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(CypherParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#shortestPathPatternFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortestPathPatternFunction(CypherParser.ShortestPathPatternFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#numberLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLit(CypherParser.NumberLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#integerLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLit(CypherParser.IntegerLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#integerLit_nospan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLit_nospan(CypherParser.IntegerLit_nospanContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#doubleLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLit(CypherParser.DoubleLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#real}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal(CypherParser.RealContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#symbols}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbols(CypherParser.SymbolsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#sp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSp(CypherParser.SpContext ctx);
}