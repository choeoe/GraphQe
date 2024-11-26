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
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CypherParser}.
 */
public interface CypherListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void enterCypher(CypherParser.CypherContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void exitCypher(CypherParser.CypherContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CypherParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CypherParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(CypherParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(CypherParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void enterRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void exitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void enterSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void exitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(CypherParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(CypherParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(CypherParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(CypherParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void enterMatch(CypherParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void exitMatch(CypherParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unwindClause}.
	 * @param ctx the parse tree
	 */
	void enterUnwindClause(CypherParser.UnwindClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unwindClause}.
	 * @param ctx the parse tree
	 */
	void exitUnwindClause(CypherParser.UnwindClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(CypherParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(CypherParser.WithContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#return_}.
	 * @param ctx the parse tree
	 */
	void enterReturn_(CypherParser.Return_Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#return_}.
	 * @param ctx the parse tree
	 */
	void exitReturn_(CypherParser.Return_Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(CypherParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(CypherParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#where_nospan}.
	 * @param ctx the parse tree
	 */
	void enterWhere_nospan(CypherParser.Where_nospanContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#where_nospan}.
	 * @param ctx the parse tree
	 */
	void exitWhere_nospan(CypherParser.Where_nospanContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void enterReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void exitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void enterReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void exitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void enterReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void exitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void enterOrder(CypherParser.OrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void exitOrder(CypherParser.OrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void enterSkip(CypherParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void exitSkip(CypherParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void enterLimit(CypherParser.LimitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void exitLimit(CypherParser.LimitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void enterSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void exitSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(CypherParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(CypherParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void enterPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void exitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void enterPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void exitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void enterNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void exitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void enterPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void exitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(CypherParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(CypherParser.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#props}.
	 * @param ctx the parse tree
	 */
	void enterProps(CypherParser.PropsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#props}.
	 * @param ctx the parse tree
	 */
	void exitProps(CypherParser.PropsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationTypes}.
	 * @param ctx the parse tree
	 */
	void enterRelationTypes(CypherParser.RelationTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationTypes}.
	 * @param ctx the parse tree
	 */
	void exitRelationTypes(CypherParser.RelationTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void enterRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void exitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void enterLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void exitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(CypherParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(CypherParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void enterMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void exitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapLiteral_nospan}.
	 * @param ctx the parse tree
	 */
	void enterMapLiteral_nospan(CypherParser.MapLiteral_nospanContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapLiteral_nospan}.
	 * @param ctx the parse tree
	 */
	void exitMapLiteral_nospan(CypherParser.MapLiteral_nospanContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapKeyValuePair}.
	 * @param ctx the parse tree
	 */
	void enterMapKeyValuePair(CypherParser.MapKeyValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapKeyValuePair}.
	 * @param ctx the parse tree
	 */
	void exitMapKeyValuePair(CypherParser.MapKeyValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void enterListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void exitListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listLit_nospan}.
	 * @param ctx the parse tree
	 */
	void enterListLit_nospan(CypherParser.ListLit_nospanContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listLit_nospan}.
	 * @param ctx the parse tree
	 */
	void exitListLit_nospan(CypherParser.ListLit_nospanContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression12}.
	 * @param ctx the parse tree
	 */
	void enterExpression12(CypherParser.Expression12Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression12}.
	 * @param ctx the parse tree
	 */
	void exitExpression12(CypherParser.Expression12Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression11}.
	 * @param ctx the parse tree
	 */
	void enterExpression11(CypherParser.Expression11Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression11}.
	 * @param ctx the parse tree
	 */
	void exitExpression11(CypherParser.Expression11Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 */
	void enterExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 */
	void exitExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 */
	void enterExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 */
	void exitExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 */
	void enterExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 */
	void exitExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 */
	void enterExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 */
	void exitExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 */
	void enterExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 */
	void exitExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 */
	void enterExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 */
	void exitExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 */
	void enterExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 */
	void exitExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(CypherParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(CypherParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 */
	void enterExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 */
	void exitExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 */
	void enterExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 */
	void exitExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#properSingle}.
	 * @param ctx the parse tree
	 */
	void enterProperSingle(CypherParser.ProperSingleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#properSingle}.
	 * @param ctx the parse tree
	 */
	void exitProperSingle(CypherParser.ProperSingleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#properLabel}.
	 * @param ctx the parse tree
	 */
	void enterProperLabel(CypherParser.ProperLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#properLabel}.
	 * @param ctx the parse tree
	 */
	void exitProperLabel(CypherParser.ProperLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression1}.
	 * @param ctx the parse tree
	 */
	void enterExpression1(CypherParser.Expression1Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression1}.
	 * @param ctx the parse tree
	 */
	void exitExpression1(CypherParser.Expression1Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression0}.
	 * @param ctx the parse tree
	 */
	void enterExpression0(CypherParser.Expression0Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression0}.
	 * @param ctx the parse tree
	 */
	void exitExpression0(CypherParser.Expression0Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#existsFunction}.
	 * @param ctx the parse tree
	 */
	void enterExistsFunction(CypherParser.ExistsFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#existsFunction}.
	 * @param ctx the parse tree
	 */
	void exitExistsFunction(CypherParser.ExistsFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#existsFunctionName}.
	 * @param ctx the parse tree
	 */
	void enterExistsFunctionName(CypherParser.ExistsFunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#existsFunctionName}.
	 * @param ctx the parse tree
	 */
	void exitExistsFunctionName(CypherParser.ExistsFunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void enterPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void exitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialArithmeticExpressionWithLowPrecedence}.
	 * @param ctx the parse tree
	 */
	void enterPartialArithmeticExpressionWithLowPrecedence(CypherParser.PartialArithmeticExpressionWithLowPrecedenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialArithmeticExpressionWithLowPrecedence}.
	 * @param ctx the parse tree
	 */
	void exitPartialArithmeticExpressionWithLowPrecedence(CypherParser.PartialArithmeticExpressionWithLowPrecedenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialArithmeticExpressionWithHighPrecedence}.
	 * @param ctx the parse tree
	 */
	void enterPartialArithmeticExpressionWithHighPrecedence(CypherParser.PartialArithmeticExpressionWithHighPrecedenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialArithmeticExpressionWithHighPrecedence}.
	 * @param ctx the parse tree
	 */
	void exitPartialArithmeticExpressionWithHighPrecedence(CypherParser.PartialArithmeticExpressionWithHighPrecedenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialArithmeticPow}.
	 * @param ctx the parse tree
	 */
	void enterPartialArithmeticPow(CypherParser.PartialArithmeticPowContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialArithmeticPow}.
	 * @param ctx the parse tree
	 */
	void exitPartialArithmeticPow(CypherParser.PartialArithmeticPowContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CypherParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CypherParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void enterCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void exitCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#shortestPathPatternFunction}.
	 * @param ctx the parse tree
	 */
	void enterShortestPathPatternFunction(CypherParser.ShortestPathPatternFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#shortestPathPatternFunction}.
	 * @param ctx the parse tree
	 */
	void exitShortestPathPatternFunction(CypherParser.ShortestPathPatternFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#numberLit}.
	 * @param ctx the parse tree
	 */
	void enterNumberLit(CypherParser.NumberLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#numberLit}.
	 * @param ctx the parse tree
	 */
	void exitNumberLit(CypherParser.NumberLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#integerLit}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLit(CypherParser.IntegerLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#integerLit}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLit(CypherParser.IntegerLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#integerLit_nospan}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLit_nospan(CypherParser.IntegerLit_nospanContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#integerLit_nospan}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLit_nospan(CypherParser.IntegerLit_nospanContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#doubleLit}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLit(CypherParser.DoubleLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#doubleLit}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLit(CypherParser.DoubleLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#real}.
	 * @param ctx the parse tree
	 */
	void enterReal(CypherParser.RealContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#real}.
	 * @param ctx the parse tree
	 */
	void exitReal(CypherParser.RealContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void enterSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void exitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#symbols}.
	 * @param ctx the parse tree
	 */
	void enterSymbols(CypherParser.SymbolsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#symbols}.
	 * @param ctx the parse tree
	 */
	void exitSymbols(CypherParser.SymbolsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#sp}.
	 * @param ctx the parse tree
	 */
	void enterSp(CypherParser.SpContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#sp}.
	 * @param ctx the parse tree
	 */
	void exitSp(CypherParser.SpContext ctx);
}