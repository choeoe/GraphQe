package cypher;// Generated from .\\Cypher.g4 by ANTLR 4.5

    import java.util.*;
    import java.util.stream.Collectors;
    import cypher.Pair;
    import cypher.ast.*;
    import cypher.ast.expression.*;
    import cypher.ast.clause.*;
    import cypher.ast.clause.match.*;
    import cypher.ast.clause.match.pattern.*;
    import cypher.ast.clause.projection.*;
    import cypher.ast.expression.*;
    import static cypher.parser.SpanUtil.makeSpan;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CypherParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		UNION=25, ALL=26, OPTIONAL=27, MATCH=28, WHERE=29, CALL=30, AS=31, WITH=32, 
		UNWIND=33, DISTINCT=34, RETURN=35, ORDER=36, BY=37, SKIP_=38, LIMIT=39, 
		EXISTS=40, DESC=41, ASC=42, OR=43, XOR=44, AND=45, NOT=46, IN=47, STARTS=48, 
		ENDS=49, END=50, CONTAINS=51, IS=52, NULL=53, TRUE=54, FALSE=55, CASE=56, 
		ELSE=57, WHEN=58, THEN=59, COUNT=60, FILTER=61, EXTRACT=62, SHORTESTPATH=63, 
		ALLSHORTESTPATHS=64, WHITESPACE=65, COMMENT=66, UnescapedSymbolicName=67, 
		EscapedSymbolicName=68, HexInteger=69, DecimalInteger=70, OctalInteger=71, 
		HexString=72, DigitString=73, OctalString=74, HexDigit=75, Digit=76, OctDigit=77, 
		STAR=78, ASSIGN=79, StringLit=80, EscapedChar=81, Exponent=82;
	public static final int
		RULE_cypher = 0, RULE_statement = 1, RULE_query = 2, RULE_regularQuery = 3, 
		RULE_singleQuery = 4, RULE_union = 5, RULE_clause = 6, RULE_match = 7, 
		RULE_unwindClause = 8, RULE_with = 9, RULE_return_ = 10, RULE_where = 11, 
		RULE_where_nospan = 12, RULE_returnBody = 13, RULE_returnItems = 14, RULE_returnItem = 15, 
		RULE_order = 16, RULE_skip = 17, RULE_limit = 18, RULE_sortItem = 19, 
		RULE_pattern = 20, RULE_patternPart = 21, RULE_patternElement = 22, RULE_nodePattern = 23, 
		RULE_nodeLabels = 24, RULE_nodeLabel = 25, RULE_patternElementChain = 26, 
		RULE_relationshipPattern = 27, RULE_relationshipDetail = 28, RULE_range = 29, 
		RULE_props = 30, RULE_relationTypes = 31, RULE_relTypeName = 32, RULE_labelName = 33, 
		RULE_variable = 34, RULE_relationshipsPattern = 35, RULE_mapLiteral = 36, 
		RULE_mapLiteral_nospan = 37, RULE_mapKeyValuePair = 38, RULE_listLiteral = 39, 
		RULE_listLit_nospan = 40, RULE_expression = 41, RULE_expression12 = 42, 
		RULE_expression11 = 43, RULE_expression10 = 44, RULE_expression9 = 45, 
		RULE_expression8 = 46, RULE_expression7 = 47, RULE_expression6 = 48, RULE_expression5 = 49, 
		RULE_expression4 = 50, RULE_unaryOp = 51, RULE_expression3 = 52, RULE_expression2 = 53, 
		RULE_properSingle = 54, RULE_properLabel = 55, RULE_expression1 = 56, 
		RULE_expression0 = 57, RULE_existsFunction = 58, RULE_existsFunctionName = 59, 
		RULE_propertyLookup = 60, RULE_partialArithmeticExpressionWithLowPrecedence = 61, 
		RULE_partialArithmeticExpressionWithHighPrecedence = 62, RULE_partialArithmeticPow = 63, 
		RULE_partialComparisonExpression = 64, RULE_atom = 65, RULE_caseExpression = 66, 
		RULE_caseAlternatives = 67, RULE_parenthesizedExpression = 68, RULE_functionInvocation = 69, 
		RULE_functionName = 70, RULE_shortestPathPatternFunction = 71, RULE_propertyKeyName = 72, 
		RULE_numberLit = 73, RULE_integerLit = 74, RULE_integerLit_nospan = 75, 
		RULE_doubleLit = 76, RULE_real = 77, RULE_symbolicName = 78, RULE_symbols = 79, 
		RULE_sp = 80;
	public static final String[] ruleNames = {
		"cypher", "statement", "query", "regularQuery", "singleQuery", "union", 
		"clause", "match", "unwindClause", "with", "return_", "where", "where_nospan", 
		"returnBody", "returnItems", "returnItem", "order", "skip", "limit", "sortItem", 
		"pattern", "patternPart", "patternElement", "nodePattern", "nodeLabels", 
		"nodeLabel", "patternElementChain", "relationshipPattern", "relationshipDetail", 
		"range", "props", "relationTypes", "relTypeName", "labelName", "variable", 
		"relationshipsPattern", "mapLiteral", "mapLiteral_nospan", "mapKeyValuePair", 
		"listLiteral", "listLit_nospan", "expression", "expression12", "expression11", 
		"expression10", "expression9", "expression8", "expression7", "expression6", 
		"expression5", "expression4", "unaryOp", "expression3", "expression2", 
		"properSingle", "properLabel", "expression1", "expression0", "existsFunction", 
		"existsFunctionName", "propertyLookup", "partialArithmeticExpressionWithLowPrecedence", 
		"partialArithmeticExpressionWithHighPrecedence", "partialArithmeticPow", 
		"partialComparisonExpression", "atom", "caseExpression", "caseAlternatives", 
		"parenthesizedExpression", "functionInvocation", "functionName", "shortestPathPatternFunction", 
		"propertyKeyName", "numberLit", "integerLit", "integerLit_nospan", "doubleLit", 
		"real", "symbolicName", "symbols", "sp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "':'", "'<'", "'-'", "'>'", "'['", "']'", 
		"'..'", "'|'", "'{'", "'}'", "'+'", "'=~'", "'.'", "'/'", "'%'", "'^'", 
		"'<>'", "'!='", "'<='", "'>='", null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "'*'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "UNION", "ALL", "OPTIONAL", "MATCH", "WHERE", "CALL", "AS", "WITH", 
		"UNWIND", "DISTINCT", "RETURN", "ORDER", "BY", "SKIP_", "LIMIT", "EXISTS", 
		"DESC", "ASC", "OR", "XOR", "AND", "NOT", "IN", "STARTS", "ENDS", "END", 
		"CONTAINS", "IS", "NULL", "TRUE", "FALSE", "CASE", "ELSE", "WHEN", "THEN", 
		"COUNT", "FILTER", "EXTRACT", "SHORTESTPATH", "ALLSHORTESTPATHS", "WHITESPACE", 
		"COMMENT", "UnescapedSymbolicName", "EscapedSymbolicName", "HexInteger", 
		"DecimalInteger", "OctalInteger", "HexString", "DigitString", "OctalString", 
		"HexDigit", "Digit", "OctDigit", "STAR", "ASSIGN", "StringLit", "EscapedChar", 
		"Exponent"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Cypher.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CypherParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CypherContext extends ParserRuleContext {
		public Statement res;
		public StatementContext st;
		public TerminalNode EOF() { return getToken(CypherParser.EOF, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public CypherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cypher; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCypher(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCypher(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCypher(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CypherContext cypher() throws RecognitionException {
		CypherContext _localctx = new CypherContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cypher);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(162);
				sp();
				}
			}

			setState(165);
			((CypherContext)_localctx).st = statement();
			setState(167);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(166);
				sp();
				}
				break;
			}
			setState(173);
			_la = _input.LA(1);
			if (_la==T__0 || _la==WHITESPACE) {
				{
				setState(170);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(169);
					sp();
					}
				}

				setState(172);
				match(T__0);
				}
			}

			setState(175);
			match(EOF);

			    ((CypherContext)_localctx).res =  ((CypherContext)_localctx).st.res;
			    ((CypherContext)_localctx).st.res.span = makeSpan((((CypherContext)_localctx).st!=null?(((CypherContext)_localctx).st.start):null), (((CypherContext)_localctx).st!=null?(((CypherContext)_localctx).st.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Statement res;
		public QueryContext q;
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			((StatementContext)_localctx).q = query();

			    ((StatementContext)_localctx).res =  new Statement(((StatementContext)_localctx).q.res);
			    ((StatementContext)_localctx).q.res.span = makeSpan((((StatementContext)_localctx).q!=null?(((StatementContext)_localctx).q.start):null), (((StatementContext)_localctx).q!=null?(((StatementContext)_localctx).q.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public Query res;
		public RegularQueryContext rg;
		public RegularQueryContext regularQuery() {
			return getRuleContext(RegularQueryContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			((QueryContext)_localctx).rg = regularQuery();

			    ((QueryContext)_localctx).res =  new Query(((QueryContext)_localctx).rg.res);
			    ((QueryContext)_localctx).rg.res.span = makeSpan((((QueryContext)_localctx).rg!=null?(((QueryContext)_localctx).rg.start):null), (((QueryContext)_localctx).rg!=null?(((QueryContext)_localctx).rg.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegularQueryContext extends ParserRuleContext {
		public QueryPart res;
		public SingleQueryContext sq;
		public UnionContext union;
		public List<UnionContext> un = new ArrayList<UnionContext>();
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public List<UnionContext> union() {
			return getRuleContexts(UnionContext.class);
		}
		public UnionContext union(int i) {
			return getRuleContext(UnionContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public RegularQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regularQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRegularQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRegularQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRegularQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegularQueryContext regularQuery() throws RecognitionException {
		RegularQueryContext _localctx = new RegularQueryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_regularQuery);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			((RegularQueryContext)_localctx).sq = singleQuery();
			setState(191);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(186);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(185);
						sp();
						}
					}

					setState(188);
					((RegularQueryContext)_localctx).union = union();
					((RegularQueryContext)_localctx).un.add(((RegularQueryContext)_localctx).union);
					}
					} 
				}
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}

			    ((RegularQueryContext)_localctx).res = ((RegularQueryContext)_localctx).un.stream().map(x->(QueryPart)x.res).reduce(((RegularQueryContext)_localctx).sq.res,(lhs,rhs)->{
			        ((Union)rhs).lhs=lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SingleQueryContext extends ParserRuleContext {
		public SingleQuery res;
		public ClauseContext clause;
		public List<ClauseContext> cls = new ArrayList<ClauseContext>();
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public SingleQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSingleQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSingleQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSingleQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleQueryContext singleQuery() throws RecognitionException {
		SingleQueryContext _localctx = new SingleQueryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_singleQuery);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			((SingleQueryContext)_localctx).clause = clause();
			((SingleQueryContext)_localctx).cls.add(((SingleQueryContext)_localctx).clause);
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(198);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(197);
						sp();
						}
					}

					setState(200);
					((SingleQueryContext)_localctx).clause = clause();
					((SingleQueryContext)_localctx).cls.add(((SingleQueryContext)_localctx).clause);
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}

			    Span span = new Span(
			        ((SingleQueryContext)_localctx).cls.get(0).getStart().getStartIndex(),
			        ((SingleQueryContext)_localctx).cls.get(((SingleQueryContext)_localctx).cls.size() - 1).getStop().getStopIndex()
			    );
			    ((SingleQueryContext)_localctx).cls.forEach(cl -> cl.res.span = makeSpan(cl.start, cl.stop));
			    ((SingleQueryContext)_localctx).res =  new SingleQuery(((SingleQueryContext)_localctx).cls.stream().map(cl -> cl.res).collect(Collectors.toList()));
			    _localctx.res.span = span;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public QueryPart res;
		public SingleQueryContext sq;
		public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_union);
		int _la;
		try {
			setState(226);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(208);
				match(UNION);
				setState(209);
				sp();
				setState(210);
				match(ALL);
				setState(212);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(211);
					sp();
					}
				}

				setState(214);
				((UnionContext)_localctx).sq = singleQuery();
				}

				         ((UnionContext)_localctx).res = new Union.All(null, ((UnionContext)_localctx).sq.res);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(218);
				match(UNION);
				setState(220);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(219);
					sp();
					}
				}

				setState(222);
				((UnionContext)_localctx).sq = singleQuery();
				}

				         ((UnionContext)_localctx).res = new Union.Distinct(null, ((UnionContext)_localctx).sq.res);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClauseContext extends ParserRuleContext {
		public Clause res;
		public MatchContext m;
		public WithContext w;
		public Return_Context r;
		public UnwindClauseContext u;
		public MatchContext match() {
			return getRuleContext(MatchContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public Return_Context return_() {
			return getRuleContext(Return_Context.class,0);
		}
		public UnwindClauseContext unwindClause() {
			return getRuleContext(UnwindClauseContext.class,0);
		}
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_clause);
		try {
			setState(240);
			switch (_input.LA(1)) {
			case OPTIONAL:
			case MATCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				((ClauseContext)_localctx).m = match();
				 ((ClauseContext)_localctx).res =  ((ClauseContext)_localctx).m.res; 
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				((ClauseContext)_localctx).w = with();
				 ((ClauseContext)_localctx).res =  ((ClauseContext)_localctx).w.res; 
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
				((ClauseContext)_localctx).r = return_();
				 ((ClauseContext)_localctx).res =  ((ClauseContext)_localctx).r.res; 
				}
				break;
			case UNWIND:
				enterOuterAlt(_localctx, 4);
				{
				setState(237);
				((ClauseContext)_localctx).u = unwindClause();
				 ((ClauseContext)_localctx).res = ((ClauseContext)_localctx).u.res; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchContext extends ParserRuleContext {
		public Match res;
		public boolean opt = false;
		public Where wh = null;
		public PatternContext pt;
		public WhereContext w;
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public MatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMatch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchContext match() throws RecognitionException {
		MatchContext _localctx = new MatchContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_match);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(242);
				match(OPTIONAL);
				 ((MatchContext)_localctx).opt = true; 
				setState(244);
				sp();
				}
			}

			setState(247);
			match(MATCH);
			setState(249);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(248);
				sp();
				}
			}

			setState(251);
			((MatchContext)_localctx).pt = pattern();
			setState(258);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(253);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(252);
					sp();
					}
				}

				setState(255);
				((MatchContext)_localctx).w = where();
				 ((MatchContext)_localctx).wh =  ((MatchContext)_localctx).w.res; 
				}
				break;
			}

			        ((MatchContext)_localctx).pt.res.span = makeSpan((((MatchContext)_localctx).pt!=null?(((MatchContext)_localctx).pt.start):null), (((MatchContext)_localctx).pt!=null?(((MatchContext)_localctx).pt.stop):null));
			        ((MatchContext)_localctx).res =  new Match(_localctx.opt, ((MatchContext)_localctx).pt.res, _localctx.wh);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnwindClauseContext extends ParserRuleContext {
		public Unwind res;
		public ExpressionContext e;
		public VariableContext v;
		public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public UnwindClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unwindClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnwindClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnwindClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnwindClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnwindClauseContext unwindClause() throws RecognitionException {
		UnwindClauseContext _localctx = new UnwindClauseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_unwindClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(UNWIND);
			setState(264);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(263);
				sp();
				}
				break;
			}
			setState(266);
			((UnwindClauseContext)_localctx).e = expression();
			setState(267);
			sp();
			setState(268);
			match(AS);
			setState(269);
			sp();
			setState(270);
			((UnwindClauseContext)_localctx).v = variable();
			 ((UnwindClauseContext)_localctx).res =  new Unwind(((UnwindClauseContext)_localctx).e.res,((UnwindClauseContext)_localctx).v.res); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WithContext extends ParserRuleContext {
		public With res;
		public boolean distinct = false;
		public Where wh = null;
		public ReturnBodyContext rb;
		public WhereContext w;
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public ReturnBodyContext returnBody() {
			return getRuleContext(ReturnBodyContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public WithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWith(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitWith(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithContext with() throws RecognitionException {
		WithContext _localctx = new WithContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_with);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(WITH);
			setState(274);
			sp();
			setState(278);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(275);
				match(DISTINCT);
				 ((WithContext)_localctx).distinct =  true; 
				setState(277);
				sp();
				}
				break;
			}
			setState(280);
			((WithContext)_localctx).rb = returnBody();
			setState(287);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(282);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(281);
					sp();
					}
				}

				setState(284);
				((WithContext)_localctx).w = where();
				 ((WithContext)_localctx).wh =  ((WithContext)_localctx).w.res; 
				}
				break;
			}

			        ((WithContext)_localctx).res =  new With(_localctx.distinct, ((WithContext)_localctx).rb.ritems, ((WithContext)_localctx).rb.rorder, ((WithContext)_localctx).rb.rskip, ((WithContext)_localctx).rb.rlimit, _localctx.wh);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_Context extends ParserRuleContext {
		public Return res;
		public boolean distinct = false;
		public ReturnBodyContext rb;
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public ReturnBodyContext returnBody() {
			return getRuleContext(ReturnBodyContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public Return_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturn_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturn_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturn_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_Context return_() throws RecognitionException {
		Return_Context _localctx = new Return_Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_return_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(RETURN);
			setState(292);
			sp();
			setState(296);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(293);
				match(DISTINCT);
				 ((Return_Context)_localctx).distinct =  true; 
				setState(295);
				sp();
				}
				break;
			}
			setState(298);
			((Return_Context)_localctx).rb = returnBody();

			        ((Return_Context)_localctx).res =  new Return(_localctx.distinct, ((Return_Context)_localctx).rb.ritems, ((Return_Context)_localctx).rb.rorder, ((Return_Context)_localctx).rb.rskip, ((Return_Context)_localctx).rb.rlimit);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereContext extends ParserRuleContext {
		public Where res;
		public Where_nospanContext w;
		public Where_nospanContext where_nospan() {
			return getRuleContext(Where_nospanContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWhere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWhere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitWhere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			((WhereContext)_localctx).w = where_nospan();
			 ((WhereContext)_localctx).res =  ((WhereContext)_localctx).w.res; _localctx.res.span = makeSpan((((WhereContext)_localctx).w!=null?(((WhereContext)_localctx).w.start):null), (((WhereContext)_localctx).w!=null?(((WhereContext)_localctx).w.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Where_nospanContext extends ParserRuleContext {
		public Where res;
		public ExpressionContext e;
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Where_nospanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_nospan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWhere_nospan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWhere_nospan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitWhere_nospan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Where_nospanContext where_nospan() throws RecognitionException {
		Where_nospanContext _localctx = new Where_nospanContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_where_nospan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(WHERE);
			setState(305);
			sp();
			setState(306);
			((Where_nospanContext)_localctx).e = expression();
			 ((Where_nospanContext)_localctx).res =  new Where(((Where_nospanContext)_localctx).e.res); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnBodyContext extends ParserRuleContext {
		public List<ReturnItem> ritems;
		public OrderBy rorder =  null;
		public Limit rlimit =  null;
		public Skip rskip =  null;
		public ReturnItemsContext ri;
		public OrderContext o;
		public SkipContext s;
		public LimitContext l;
		public ReturnItemsContext returnItems() {
			return getRuleContext(ReturnItemsContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public OrderContext order() {
			return getRuleContext(OrderContext.class,0);
		}
		public SkipContext skip() {
			return getRuleContext(SkipContext.class,0);
		}
		public LimitContext limit() {
			return getRuleContext(LimitContext.class,0);
		}
		public ReturnBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnBodyContext returnBody() throws RecognitionException {
		ReturnBodyContext _localctx = new ReturnBodyContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_returnBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			((ReturnBodyContext)_localctx).ri = returnItems();
			 ((ReturnBodyContext)_localctx).ritems =  ((ReturnBodyContext)_localctx).ri.res; 
			setState(315);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(311);
				sp();
				setState(312);
				((ReturnBodyContext)_localctx).o = order();
				 ((ReturnBodyContext)_localctx).rorder =  ((ReturnBodyContext)_localctx).o.res; _localctx.rorder.span = makeSpan((((ReturnBodyContext)_localctx).o!=null?(((ReturnBodyContext)_localctx).o.start):null), (((ReturnBodyContext)_localctx).o!=null?(((ReturnBodyContext)_localctx).o.stop):null)); 
				}
				break;
			}
			setState(321);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(317);
				sp();
				setState(318);
				((ReturnBodyContext)_localctx).s = skip();
				 ((ReturnBodyContext)_localctx).rskip =  ((ReturnBodyContext)_localctx).s.res; _localctx.rskip.span  = makeSpan((((ReturnBodyContext)_localctx).s!=null?(((ReturnBodyContext)_localctx).s.start):null), (((ReturnBodyContext)_localctx).s!=null?(((ReturnBodyContext)_localctx).s.stop):null)); 
				}
				break;
			}
			setState(327);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(323);
				sp();
				setState(324);
				((ReturnBodyContext)_localctx).l = limit();
				 ((ReturnBodyContext)_localctx).rlimit =  ((ReturnBodyContext)_localctx).l.res; _localctx.rlimit.span = makeSpan((((ReturnBodyContext)_localctx).l!=null?(((ReturnBodyContext)_localctx).l.start):null), (((ReturnBodyContext)_localctx).l!=null?(((ReturnBodyContext)_localctx).l.stop):null)); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnItemsContext extends ParserRuleContext {
		public List<ReturnItem> res;
		public ReturnItemContext returnItem;
		public List<ReturnItemContext> ris = new ArrayList<ReturnItemContext>();
		public List<ReturnItemContext> returnItem() {
			return getRuleContexts(ReturnItemContext.class);
		}
		public ReturnItemContext returnItem(int i) {
			return getRuleContext(ReturnItemContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public ReturnItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnItemsContext returnItems() throws RecognitionException {
		ReturnItemsContext _localctx = new ReturnItemsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_returnItems);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			((ReturnItemsContext)_localctx).returnItem = returnItem();
			((ReturnItemsContext)_localctx).ris.add(((ReturnItemsContext)_localctx).returnItem);
			setState(340);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(331);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(330);
						sp();
						}
					}

					setState(333);
					match(T__1);
					setState(335);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						setState(334);
						sp();
						}
						break;
					}
					setState(337);
					((ReturnItemsContext)_localctx).returnItem = returnItem();
					((ReturnItemsContext)_localctx).ris.add(((ReturnItemsContext)_localctx).returnItem);
					}
					} 
				}
				setState(342);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}

			    ((ReturnItemsContext)_localctx).ris.forEach(ri -> ri.res.span = makeSpan(ri.start, ri.stop));
			    ((ReturnItemsContext)_localctx).res =  ((ReturnItemsContext)_localctx).ris.stream().map(ri -> ri.res).collect(Collectors.toList());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnItemContext extends ParserRuleContext {
		public ReturnItem res;
		public ExpressionContext e;
		public VariableContext v;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode STAR() { return getToken(CypherParser.STAR, 0); }
		public ReturnItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnItemContext returnItem() throws RecognitionException {
		ReturnItemContext _localctx = new ReturnItemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnItem);
		try {
			setState(358);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(345);
				((ReturnItemContext)_localctx).e = expression();
				setState(346);
				sp();
				setState(347);
				match(AS);
				setState(348);
				sp();
				setState(349);
				((ReturnItemContext)_localctx).v = variable();
				}
				 ((ReturnItemContext)_localctx).res =  new ReturnItem.Aliased(((ReturnItemContext)_localctx).e.res, ((ReturnItemContext)_localctx).v.res); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				((ReturnItemContext)_localctx).e = expression();
				 ((ReturnItemContext)_localctx).res =  new ReturnItem.Unaliased(((ReturnItemContext)_localctx).e.res, (((ReturnItemContext)_localctx).e!=null?_input.getText(((ReturnItemContext)_localctx).e.start,((ReturnItemContext)_localctx).e.stop):null));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(356);
				match(STAR);
				 ((ReturnItemContext)_localctx).res =  new ReturnItem.Unaliased(new Literal.Star(), "*");   
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderContext extends ParserRuleContext {
		public OrderBy res;
		public SortItemContext sortItem;
		public List<SortItemContext> sis = new ArrayList<SortItemContext>();
		public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode BY() { return getToken(CypherParser.BY, 0); }
		public List<SortItemContext> sortItem() {
			return getRuleContexts(SortItemContext.class);
		}
		public SortItemContext sortItem(int i) {
			return getRuleContext(SortItemContext.class,i);
		}
		public OrderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitOrder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitOrder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderContext order() throws RecognitionException {
		OrderContext _localctx = new OrderContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_order);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(ORDER);
			setState(361);
			sp();
			setState(362);
			match(BY);
			setState(363);
			sp();
			setState(364);
			((OrderContext)_localctx).sortItem = sortItem();
			((OrderContext)_localctx).sis.add(((OrderContext)_localctx).sortItem);
			setState(375);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(366);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(365);
						sp();
						}
					}

					setState(368);
					match(T__1);
					setState(370);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						setState(369);
						sp();
						}
						break;
					}
					setState(372);
					((OrderContext)_localctx).sortItem = sortItem();
					((OrderContext)_localctx).sis.add(((OrderContext)_localctx).sortItem);
					}
					} 
				}
				setState(377);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}

			    ((OrderContext)_localctx).sis.forEach(si -> si.res.span = makeSpan(si.start, si.stop));
			    ((OrderContext)_localctx).res =  new OrderBy(((OrderContext)_localctx).sis.stream().map(si -> si.res).collect(Collectors.toList()));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SkipContext extends ParserRuleContext {
		public Skip res;
		public ExpressionContext e;
		public TerminalNode SKIP_() { return getToken(CypherParser.SKIP_, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SkipContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skip; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSkip(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSkip(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSkip(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkipContext skip() throws RecognitionException {
		SkipContext _localctx = new SkipContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_skip);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(SKIP_);
			setState(381);
			sp();
			setState(382);
			((SkipContext)_localctx).e = expression();
			 ((SkipContext)_localctx).res =  new Skip(((SkipContext)_localctx).e.res); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitContext extends ParserRuleContext {
		public Limit res;
		public ExpressionContext e;
		public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LimitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLimit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLimit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLimit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitContext limit() throws RecognitionException {
		LimitContext _localctx = new LimitContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_limit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			match(LIMIT);
			setState(386);
			sp();
			setState(387);
			((LimitContext)_localctx).e = expression();
			 ((LimitContext)_localctx).res =  new Limit(((LimitContext)_localctx).e.res);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortItemContext extends ParserRuleContext {
		public SortItem res;
		public ExpressionContext e;
		public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }
		public SortItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sortItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSortItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSortItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSortItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortItemContext sortItem() throws RecognitionException {
		SortItemContext _localctx = new SortItemContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_sortItem);
		int _la;
		try {
			setState(407);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(390);
				((SortItemContext)_localctx).e = expression();
				setState(392);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(391);
					sp();
					}
				}

				setState(394);
				match(DESC);
				}
				 ((SortItemContext)_localctx).res =  new SortItem.Desc(((SortItemContext)_localctx).e.res); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(398);
				((SortItemContext)_localctx).e = expression();
				setState(400);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(399);
					sp();
					}
					break;
				}
				setState(403);
				_la = _input.LA(1);
				if (_la==ASC) {
					{
					setState(402);
					match(ASC);
					}
				}

				}
				 ((SortItemContext)_localctx).res =  new SortItem.Asc(((SortItemContext)_localctx).e.res); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternContext extends ParserRuleContext {
		public Pattern res;
		public PatternPartContext patternPart;
		public List<PatternPartContext> pp = new ArrayList<PatternPartContext>();
		public List<PatternPartContext> patternPart() {
			return getRuleContexts(PatternPartContext.class);
		}
		public PatternPartContext patternPart(int i) {
			return getRuleContext(PatternPartContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_pattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			((PatternContext)_localctx).patternPart = patternPart();
			((PatternContext)_localctx).pp.add(((PatternContext)_localctx).patternPart);
			setState(420);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(411);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(410);
						sp();
						}
					}

					setState(413);
					match(T__1);
					setState(415);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(414);
						sp();
						}
					}

					setState(417);
					((PatternContext)_localctx).patternPart = patternPart();
					((PatternContext)_localctx).pp.add(((PatternContext)_localctx).patternPart);
					}
					} 
				}
				setState(422);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}

			    ((PatternContext)_localctx).pp.forEach(pp -> pp.res.span = makeSpan(pp.start, pp.stop));
			    ((PatternContext)_localctx).res =  new Pattern(((PatternContext)_localctx).pp.stream().map(p -> p.res).collect(Collectors.toList()));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternPartContext extends ParserRuleContext {
		public PatternPart res;
		public VariableContext v;
		public PatternElementContext pe;
		public ShortestPathPatternFunctionContext s;
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public ShortestPathPatternFunctionContext shortestPathPatternFunction() {
			return getRuleContext(ShortestPathPatternFunctionContext.class,0);
		}
		public PatternPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternPartContext patternPart() throws RecognitionException {
		PatternPartContext _localctx = new PatternPartContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_patternPart);
		int _la;
		try {
			setState(453);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(425);
				((PatternPartContext)_localctx).v = variable();
				setState(427);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(426);
					sp();
					}
				}

				setState(429);
				match(ASSIGN);
				setState(431);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(430);
					sp();
					}
				}

				}
				setState(433);
				((PatternPartContext)_localctx).pe = patternElement();
				 ((PatternPartContext)_localctx).res =  new NamedPatternPart(((PatternPartContext)_localctx).pe.res, ((PatternPartContext)_localctx).v.res);
				                                                  ((PatternPartContext)_localctx).pe.res.span = makeSpan((((PatternPartContext)_localctx).pe!=null?(((PatternPartContext)_localctx).pe.start):null), (((PatternPartContext)_localctx).pe!=null?(((PatternPartContext)_localctx).pe.stop):null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(436);
				((PatternPartContext)_localctx).pe = patternElement();
				 ((PatternPartContext)_localctx).res =  new PatternPart(((PatternPartContext)_localctx).pe.res);
				                                                  ((PatternPartContext)_localctx).pe.res.span = makeSpan((((PatternPartContext)_localctx).pe!=null?(((PatternPartContext)_localctx).pe.start):null), (((PatternPartContext)_localctx).pe!=null?(((PatternPartContext)_localctx).pe.stop):null)); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(439);
				((PatternPartContext)_localctx).s = shortestPathPatternFunction();
				 ((PatternPartContext)_localctx).res =  ((PatternPartContext)_localctx).s.res;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(442);
				((PatternPartContext)_localctx).v = variable();
				setState(444);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(443);
					sp();
					}
				}

				setState(446);
				match(ASSIGN);
				setState(448);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(447);
					sp();
					}
				}

				}
				setState(450);
				((PatternPartContext)_localctx).s = shortestPathPatternFunction();
				 ((PatternPartContext)_localctx).res =  ((PatternPartContext)_localctx).s.res; ((PatternPartContext)_localctx).s.res.variable = ((PatternPartContext)_localctx).v.res;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternElementContext extends ParserRuleContext {
		public PatternElement res;
		public NodePatternContext np;
		public PatternElementChainContext patternElementChain;
		public List<PatternElementChainContext> pc = new ArrayList<PatternElementChainContext>();
		public PatternElementContext pe;
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<PatternElementChainContext> patternElementChain() {
			return getRuleContexts(PatternElementChainContext.class);
		}
		public PatternElementChainContext patternElementChain(int i) {
			return getRuleContext(PatternElementChainContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public PatternElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElementContext patternElement() throws RecognitionException {
		PatternElementContext _localctx = new PatternElementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_patternElement);
		int _la;
		try {
			int _alt;
			setState(473);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(455);
				((PatternElementContext)_localctx).np = nodePattern();
				setState(462);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(457);
						_la = _input.LA(1);
						if (_la==WHITESPACE) {
							{
							setState(456);
							sp();
							}
						}

						setState(459);
						((PatternElementContext)_localctx).patternElementChain = patternElementChain();
						((PatternElementContext)_localctx).pc.add(((PatternElementContext)_localctx).patternElementChain);
						}
						} 
					}
					setState(464);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
				}
				}

				        ((PatternElementContext)_localctx).np.res.span = makeSpan((((PatternElementContext)_localctx).np!=null?(((PatternElementContext)_localctx).np.start):null), (((PatternElementContext)_localctx).np!=null?(((PatternElementContext)_localctx).np.stop):null));
				        ((PatternElementContext)_localctx).pc.forEach(rc -> rc.res.span = makeSpan(rc.start, rc.stop));
				        ((PatternElementContext)_localctx).res =  ((PatternElementContext)_localctx).pc.stream().map(rc -> (PatternElement) rc.res).reduce(((PatternElementContext)_localctx).np.res, (leftpe, rc) -> {
				            ((RelationshipChain) rc).element = leftpe;
				            return rc;
				        });
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(467);
				match(T__2);
				setState(468);
				((PatternElementContext)_localctx).pe = patternElement();
				setState(469);
				match(T__3);
				}
				 ((PatternElementContext)_localctx).res =  ((PatternElementContext)_localctx).pe.res; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodePatternContext extends ParserRuleContext {
		public NodePattern res;
		public Variable v;
		public List<LabelName> ls =  Collections.emptyList();
		public MapExpression p =  null;
		public VariableContext var;
		public NodeLabelsContext nls;
		public PropsContext ps;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public PropsContext props() {
			return getRuleContext(PropsContext.class,0);
		}
		public NodePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodePattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodePatternContext nodePattern() throws RecognitionException {
		NodePatternContext _localctx = new NodePatternContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_nodePattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			match(T__2);
			setState(477);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(476);
				sp();
				}
			}

			setState(485);
			_la = _input.LA(1);
			if (((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & ((1L << (UNION - 25)) | (1L << (ALL - 25)) | (1L << (OPTIONAL - 25)) | (1L << (MATCH - 25)) | (1L << (WHERE - 25)) | (1L << (AS - 25)) | (1L << (WITH - 25)) | (1L << (DISTINCT - 25)) | (1L << (RETURN - 25)) | (1L << (ORDER - 25)) | (1L << (BY - 25)) | (1L << (SKIP_ - 25)) | (1L << (LIMIT - 25)) | (1L << (DESC - 25)) | (1L << (ASC - 25)) | (1L << (OR - 25)) | (1L << (XOR - 25)) | (1L << (AND - 25)) | (1L << (NOT - 25)) | (1L << (IN - 25)) | (1L << (STARTS - 25)) | (1L << (ENDS - 25)) | (1L << (CONTAINS - 25)) | (1L << (IS - 25)) | (1L << (NULL - 25)) | (1L << (TRUE - 25)) | (1L << (FALSE - 25)) | (1L << (COUNT - 25)) | (1L << (FILTER - 25)) | (1L << (EXTRACT - 25)) | (1L << (UnescapedSymbolicName - 25)) | (1L << (EscapedSymbolicName - 25)))) != 0)) {
				{
				setState(479);
				((NodePatternContext)_localctx).var = variable();
				setState(481);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(480);
					sp();
					}
				}

				 ((NodePatternContext)_localctx).v =  ((NodePatternContext)_localctx).var.res; 
				}
			}

			setState(493);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(487);
				((NodePatternContext)_localctx).nls = nodeLabels();
				setState(489);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(488);
					sp();
					}
				}

				 ((NodePatternContext)_localctx).ls =  ((NodePatternContext)_localctx).nls.res; 
				}
			}

			setState(501);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(495);
				((NodePatternContext)_localctx).ps = props();
				setState(497);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(496);
					sp();
					}
				}

				 ((NodePatternContext)_localctx).p =  ((NodePatternContext)_localctx).ps.res; 
				}
			}

			setState(503);
			match(T__3);
			 ((NodePatternContext)_localctx).res =  new NodePattern(_localctx.v, _localctx.ls, _localctx.p); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeLabelsContext extends ParserRuleContext {
		public List<LabelName> res;
		public NodeLabelContext nodeLabel;
		public List<NodeLabelContext> nls = new ArrayList<NodeLabelContext>();
		public List<NodeLabelContext> nodeLabel() {
			return getRuleContexts(NodeLabelContext.class);
		}
		public NodeLabelContext nodeLabel(int i) {
			return getRuleContext(NodeLabelContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public NodeLabelsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLabels; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabels(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabels(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodeLabels(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLabelsContext nodeLabels() throws RecognitionException {
		NodeLabelsContext _localctx = new NodeLabelsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_nodeLabels);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(506);
			((NodeLabelsContext)_localctx).nodeLabel = nodeLabel();
			((NodeLabelsContext)_localctx).nls.add(((NodeLabelsContext)_localctx).nodeLabel);
			setState(513);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(508);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(507);
						sp();
						}
					}

					setState(510);
					((NodeLabelsContext)_localctx).nodeLabel = nodeLabel();
					((NodeLabelsContext)_localctx).nls.add(((NodeLabelsContext)_localctx).nodeLabel);
					}
					} 
				}
				setState(515);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}

			    ((NodeLabelsContext)_localctx).res =  ((NodeLabelsContext)_localctx).nls.stream().map(nl -> nl.res).collect(Collectors.toList());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeLabelContext extends ParserRuleContext {
		public LabelName res;
		public LabelNameContext lb;
		public LabelNameContext labelName() {
			return getRuleContext(LabelNameContext.class,0);
		}
		public NodeLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodeLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLabelContext nodeLabel() throws RecognitionException {
		NodeLabelContext _localctx = new NodeLabelContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_nodeLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			match(T__4);
			setState(519);
			((NodeLabelContext)_localctx).lb = labelName();
			 ((NodeLabelContext)_localctx).res =  ((NodeLabelContext)_localctx).lb.res; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternElementChainContext extends ParserRuleContext {
		public RelationshipChain res;
		public RelationshipPatternContext rp;
		public NodePatternContext rn;
		public RelationshipPatternContext relationshipPattern() {
			return getRuleContext(RelationshipPatternContext.class,0);
		}
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public PatternElementChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElementChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElementChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElementChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternElementChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElementChainContext patternElementChain() throws RecognitionException {
		PatternElementChainContext _localctx = new PatternElementChainContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_patternElementChain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			((PatternElementChainContext)_localctx).rp = relationshipPattern();
			setState(524);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(523);
				sp();
				}
			}

			setState(526);
			((PatternElementChainContext)_localctx).rn = nodePattern();

			        ((PatternElementChainContext)_localctx).rn.res.span = makeSpan((((PatternElementChainContext)_localctx).rn!=null?(((PatternElementChainContext)_localctx).rn.start):null), (((PatternElementChainContext)_localctx).rn!=null?(((PatternElementChainContext)_localctx).rn.stop):null));
			        ((PatternElementChainContext)_localctx).rp.res.span = makeSpan((((PatternElementChainContext)_localctx).rp!=null?(((PatternElementChainContext)_localctx).rp.start):null), (((PatternElementChainContext)_localctx).rp!=null?(((PatternElementChainContext)_localctx).rp.stop):null));
			        ((PatternElementChainContext)_localctx).res =  new RelationshipChain(null, ((PatternElementChainContext)_localctx).rp.res, ((PatternElementChainContext)_localctx).rn.res);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipPatternContext extends ParserRuleContext {
		public RelationshipPattern res =  new RelationshipPattern(null, null, null, Collections.emptyList());
		public RelationshipDetailContext d;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public RelationshipDetailContext relationshipDetail() {
			return getRuleContext(RelationshipDetailContext.class,0);
		}
		public RelationshipPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipPatternContext relationshipPattern() throws RecognitionException {
		RelationshipPatternContext _localctx = new RelationshipPatternContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_relationshipPattern);
		int _la;
		try {
			setState(601);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(529);
				match(T__5);
				setState(531);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(530);
					sp();
					}
				}

				setState(533);
				match(T__6);
				setState(535);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(534);
					sp();
					}
				}

				setState(542);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(537);
					((RelationshipPatternContext)_localctx).d = relationshipDetail();
					 ((RelationshipPatternContext)_localctx).res =  ((RelationshipPatternContext)_localctx).d.res; 
					setState(540);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(539);
						sp();
						}
					}

					}
				}

				setState(544);
				match(T__6);
				setState(546);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(545);
					sp();
					}
				}

				setState(548);
				match(T__7);
				}
				 _localctx.res.direction = RelationshipPattern.SemanticDirection.BOTH; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(551);
				match(T__5);
				setState(553);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(552);
					sp();
					}
				}

				setState(555);
				match(T__6);
				setState(557);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(556);
					sp();
					}
				}

				setState(564);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(559);
					((RelationshipPatternContext)_localctx).d = relationshipDetail();
					 ((RelationshipPatternContext)_localctx).res =  ((RelationshipPatternContext)_localctx).d.res; 
					setState(562);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(561);
						sp();
						}
					}

					}
				}

				setState(566);
				match(T__6);
				}
				 _localctx.res.direction = RelationshipPattern.SemanticDirection.INCOMING; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(569);
				match(T__6);
				setState(571);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(570);
					sp();
					}
				}

				setState(578);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(573);
					((RelationshipPatternContext)_localctx).d = relationshipDetail();
					 ((RelationshipPatternContext)_localctx).res =  ((RelationshipPatternContext)_localctx).d.res; 
					setState(576);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(575);
						sp();
						}
					}

					}
				}

				setState(580);
				match(T__6);
				setState(582);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(581);
					sp();
					}
				}

				setState(584);
				match(T__7);
				}
				 _localctx.res.direction = RelationshipPattern.SemanticDirection.OUTGOING; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(587);
				match(T__6);
				setState(589);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(588);
					sp();
					}
				}

				setState(596);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(591);
					((RelationshipPatternContext)_localctx).d = relationshipDetail();
					 ((RelationshipPatternContext)_localctx).res =  ((RelationshipPatternContext)_localctx).d.res; 
					setState(594);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(593);
						sp();
						}
					}

					}
				}

				setState(598);
				match(T__6);
				}
				 _localctx.res.direction = RelationshipPattern.SemanticDirection.BOTH; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipDetailContext extends ParserRuleContext {
		public RelationshipPattern res;
		public Variable v =  null;
		public List<RelTypeName> types =  null;
		public Optional<Range> r =  null;
		public MapExpression p =  null;
		public VariableContext var;
		public RelationTypesContext rt;
		public RangeContext rag;
		public PropsContext ps;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RelationTypesContext relationTypes() {
			return getRuleContext(RelationTypesContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public PropsContext props() {
			return getRuleContext(PropsContext.class,0);
		}
		public RelationshipDetailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipDetail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipDetail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipDetail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipDetail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipDetailContext relationshipDetail() throws RecognitionException {
		RelationshipDetailContext _localctx = new RelationshipDetailContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_relationshipDetail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			match(T__8);
			setState(605);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(604);
				sp();
				}
				break;
			}
			setState(610);
			_la = _input.LA(1);
			if (((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & ((1L << (UNION - 25)) | (1L << (ALL - 25)) | (1L << (OPTIONAL - 25)) | (1L << (MATCH - 25)) | (1L << (WHERE - 25)) | (1L << (AS - 25)) | (1L << (WITH - 25)) | (1L << (DISTINCT - 25)) | (1L << (RETURN - 25)) | (1L << (ORDER - 25)) | (1L << (BY - 25)) | (1L << (SKIP_ - 25)) | (1L << (LIMIT - 25)) | (1L << (DESC - 25)) | (1L << (ASC - 25)) | (1L << (OR - 25)) | (1L << (XOR - 25)) | (1L << (AND - 25)) | (1L << (NOT - 25)) | (1L << (IN - 25)) | (1L << (STARTS - 25)) | (1L << (ENDS - 25)) | (1L << (CONTAINS - 25)) | (1L << (IS - 25)) | (1L << (NULL - 25)) | (1L << (TRUE - 25)) | (1L << (FALSE - 25)) | (1L << (COUNT - 25)) | (1L << (FILTER - 25)) | (1L << (EXTRACT - 25)) | (1L << (UnescapedSymbolicName - 25)) | (1L << (EscapedSymbolicName - 25)))) != 0)) {
				{
				setState(607);
				((RelationshipDetailContext)_localctx).var = variable();
				 ((RelationshipDetailContext)_localctx).v =  ((RelationshipDetailContext)_localctx).var.res; 
				}
			}

			setState(613);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				{
				setState(612);
				sp();
				}
				break;
			}
			setState(618);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(615);
				((RelationshipDetailContext)_localctx).rt = relationTypes();
				 ((RelationshipDetailContext)_localctx).types =  ((RelationshipDetailContext)_localctx).rt.res; 
				}
			}

			setState(621);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(620);
				sp();
				}
				break;
			}
			setState(627);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(623);
				match(STAR);
				setState(624);
				((RelationshipDetailContext)_localctx).rag = range();
				 ((RelationshipDetailContext)_localctx).r =  ((RelationshipDetailContext)_localctx).rag.res; 
				}
			}

			setState(630);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(629);
				sp();
				}
				break;
			}
			setState(635);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(632);
				((RelationshipDetailContext)_localctx).ps = props();
				 ((RelationshipDetailContext)_localctx).p =  ((RelationshipDetailContext)_localctx).ps.res; 
				}
			}

			setState(638);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(637);
				sp();
				}
			}

			setState(640);
			match(T__9);

			        ((RelationshipDetailContext)_localctx).types =  (_localctx.types == null) ? Collections.emptyList(): _localctx.types;
			        ((RelationshipDetailContext)_localctx).res =  new RelationshipPattern(_localctx.v, _localctx.r, _localctx.p, _localctx.types);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeContext extends ParserRuleContext {
		public Optional<Range> res =  Optional.empty();
		public IntegerLitContext ilit;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<IntegerLitContext> integerLit() {
			return getRuleContexts(IntegerLitContext.class);
		}
		public IntegerLitContext integerLit(int i) {
			return getRuleContext(IntegerLitContext.class,i);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_range);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(644);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(643);
				sp();
				}
				break;
			}
			setState(652);
			_la = _input.LA(1);
			if (((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (HexInteger - 69)) | (1L << (DecimalInteger - 69)) | (1L << (OctalInteger - 69)))) != 0)) {
				{
				setState(646);
				((RangeContext)_localctx).ilit = integerLit();
				setState(648);
				switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(647);
					sp();
					}
					break;
				}
				 ((RangeContext)_localctx).res =  Optional.of(new Range(((RangeContext)_localctx).ilit.res, ((RangeContext)_localctx).ilit.res)); 
				}
			}

			setState(666);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(654);
				match(T__10);
				setState(656);
				switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(655);
					sp();
					}
					break;
				}
				setState(664);
				_la = _input.LA(1);
				if (((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (HexInteger - 69)) | (1L << (DecimalInteger - 69)) | (1L << (OctalInteger - 69)))) != 0)) {
					{
					setState(658);
					((RangeContext)_localctx).ilit = integerLit();
					setState(660);
					switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
					case 1:
						{
						setState(659);
						sp();
						}
						break;
					}

					            ((RangeContext)_localctx).res =  Optional.of(_localctx.res.orElseGet(() -> new Range(null, null)));
					            _localctx.res.get().upper = (((RangeContext)_localctx).ilit.res == null) ? Optional.empty(): Optional.of(((RangeContext)_localctx).ilit.res);
					            
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropsContext extends ParserRuleContext {
		public MapExpression res;
		public MapLiteralContext m;
		public MapLiteralContext mapLiteral() {
			return getRuleContext(MapLiteralContext.class,0);
		}
		public PropsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_props; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProps(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitProps(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropsContext props() throws RecognitionException {
		PropsContext _localctx = new PropsContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_props);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668);
			((PropsContext)_localctx).m = mapLiteral();
			 ((PropsContext)_localctx).res =  ((PropsContext)_localctx).m.res; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationTypesContext extends ParserRuleContext {
		public List<RelTypeName> res;
		public RelTypeNameContext relTypeName;
		public List<RelTypeNameContext> rts = new ArrayList<RelTypeNameContext>();
		public List<RelTypeNameContext> relTypeName() {
			return getRuleContexts(RelTypeNameContext.class);
		}
		public RelTypeNameContext relTypeName(int i) {
			return getRuleContext(RelTypeNameContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public RelationTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationTypesContext relationTypes() throws RecognitionException {
		RelationTypesContext _localctx = new RelationTypesContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_relationTypes);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(671);
			match(T__4);
			setState(672);
			((RelationTypesContext)_localctx).relTypeName = relTypeName();
			((RelationTypesContext)_localctx).rts.add(((RelationTypesContext)_localctx).relTypeName);
			setState(686);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(674);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(673);
						sp();
						}
					}

					setState(676);
					match(T__11);
					setState(678);
					_la = _input.LA(1);
					if (_la==T__4) {
						{
						setState(677);
						match(T__4);
						}
					}

					setState(681);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(680);
						sp();
						}
					}

					setState(683);
					((RelationTypesContext)_localctx).relTypeName = relTypeName();
					((RelationTypesContext)_localctx).rts.add(((RelationTypesContext)_localctx).relTypeName);
					}
					} 
				}
				setState(688);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
			}

			        ((RelationTypesContext)_localctx).res =  ((RelationTypesContext)_localctx).rts.stream().map(rt -> rt.res).collect(Collectors.toList());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelTypeNameContext extends ParserRuleContext {
		public RelTypeName res;
		public SymbolicNameContext s;
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public RelTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelTypeNameContext relTypeName() throws RecognitionException {
		RelTypeNameContext _localctx = new RelTypeNameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_relTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(691);
			((RelTypeNameContext)_localctx).s = symbolicName();

			    ((RelTypeNameContext)_localctx).res =  new RelTypeName(((RelTypeNameContext)_localctx).s.res);
			    _localctx.res.span = makeSpan((((RelTypeNameContext)_localctx).s!=null?(((RelTypeNameContext)_localctx).s.start):null), (((RelTypeNameContext)_localctx).s!=null?(((RelTypeNameContext)_localctx).s.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelNameContext extends ParserRuleContext {
		public LabelName res;
		public SymbolicNameContext s;
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public LabelNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLabelName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLabelName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLabelName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelNameContext labelName() throws RecognitionException {
		LabelNameContext _localctx = new LabelNameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_labelName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694);
			((LabelNameContext)_localctx).s = symbolicName();

			    ((LabelNameContext)_localctx).res =  new LabelName(((LabelNameContext)_localctx).s.res);
			    _localctx.res.span = makeSpan((((LabelNameContext)_localctx).s!=null?(((LabelNameContext)_localctx).s.start):null), (((LabelNameContext)_localctx).s!=null?(((LabelNameContext)_localctx).s.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public Variable res;
		public SymbolicNameContext s;
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(697);
			((VariableContext)_localctx).s = symbolicName();

			    ((VariableContext)_localctx).res =  new Variable(((VariableContext)_localctx).s.res);
			    //((VariableContext)_localctx).res =  Variable.getVariable(((VariableContext)_localctx).s.res);
			    _localctx.res.span = makeSpan((((VariableContext)_localctx).s!=null?(((VariableContext)_localctx).s.start):null), (((VariableContext)_localctx).s!=null?(((VariableContext)_localctx).s.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipsPatternContext extends ParserRuleContext {
		public PatternElement res;
		public NodePatternContext np;
		public PatternElementChainContext patternElementChain;
		public List<PatternElementChainContext> pc = new ArrayList<PatternElementChainContext>();
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<PatternElementChainContext> patternElementChain() {
			return getRuleContexts(PatternElementChainContext.class);
		}
		public PatternElementChainContext patternElementChain(int i) {
			return getRuleContext(PatternElementChainContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public RelationshipsPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipsPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipsPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipsPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipsPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipsPatternContext relationshipsPattern() throws RecognitionException {
		RelationshipsPatternContext _localctx = new RelationshipsPatternContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_relationshipsPattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(700);
			((RelationshipsPatternContext)_localctx).np = nodePattern();
			setState(705); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(702);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(701);
						sp();
						}
					}

					setState(704);
					((RelationshipsPatternContext)_localctx).patternElementChain = patternElementChain();
					((RelationshipsPatternContext)_localctx).pc.add(((RelationshipsPatternContext)_localctx).patternElementChain);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(707); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );

			             ((RelationshipsPatternContext)_localctx).np.res.span = makeSpan((((RelationshipsPatternContext)_localctx).np!=null?(((RelationshipsPatternContext)_localctx).np.start):null), (((RelationshipsPatternContext)_localctx).np!=null?(((RelationshipsPatternContext)_localctx).np.stop):null));
			             ((RelationshipsPatternContext)_localctx).pc.forEach(rc -> rc.res.span = makeSpan(rc.start, rc.stop));
			             ((RelationshipsPatternContext)_localctx).res =  ((RelationshipsPatternContext)_localctx).pc.stream().map(rc -> (PatternElement) rc.res).reduce(((RelationshipsPatternContext)_localctx).np.res, (leftpe, rc) -> {
			                 ((RelationshipChain) rc).element = leftpe;
			                 return rc;
			             });
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapLiteralContext extends ParserRuleContext {
		public MapExpression res;
		public MapLiteral_nospanContext m;
		public MapLiteral_nospanContext mapLiteral_nospan() {
			return getRuleContext(MapLiteral_nospanContext.class,0);
		}
		public MapLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMapLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMapLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMapLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapLiteralContext mapLiteral() throws RecognitionException {
		MapLiteralContext _localctx = new MapLiteralContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_mapLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(711);
			((MapLiteralContext)_localctx).m = mapLiteral_nospan();
			 ((MapLiteralContext)_localctx).res =  ((MapLiteralContext)_localctx).m.res; _localctx.res.span = makeSpan((((MapLiteralContext)_localctx).m!=null?(((MapLiteralContext)_localctx).m.start):null), (((MapLiteralContext)_localctx).m!=null?(((MapLiteralContext)_localctx).m.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapLiteral_nospanContext extends ParserRuleContext {
		public MapExpression res;
		public MapKeyValuePairContext mapKeyValuePair;
		public List<MapKeyValuePairContext> kvs = new ArrayList<MapKeyValuePairContext>();
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<MapKeyValuePairContext> mapKeyValuePair() {
			return getRuleContexts(MapKeyValuePairContext.class);
		}
		public MapKeyValuePairContext mapKeyValuePair(int i) {
			return getRuleContext(MapKeyValuePairContext.class,i);
		}
		public MapLiteral_nospanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapLiteral_nospan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMapLiteral_nospan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMapLiteral_nospan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMapLiteral_nospan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapLiteral_nospanContext mapLiteral_nospan() throws RecognitionException {
		MapLiteral_nospanContext _localctx = new MapLiteral_nospanContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_mapLiteral_nospan);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(714);
			match(T__12);
			setState(716);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(715);
				sp();
				}
			}

			setState(729);
			_la = _input.LA(1);
			if (((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & ((1L << (UNION - 25)) | (1L << (ALL - 25)) | (1L << (OPTIONAL - 25)) | (1L << (MATCH - 25)) | (1L << (WHERE - 25)) | (1L << (AS - 25)) | (1L << (WITH - 25)) | (1L << (DISTINCT - 25)) | (1L << (RETURN - 25)) | (1L << (ORDER - 25)) | (1L << (BY - 25)) | (1L << (SKIP_ - 25)) | (1L << (LIMIT - 25)) | (1L << (DESC - 25)) | (1L << (ASC - 25)) | (1L << (OR - 25)) | (1L << (XOR - 25)) | (1L << (AND - 25)) | (1L << (NOT - 25)) | (1L << (IN - 25)) | (1L << (STARTS - 25)) | (1L << (ENDS - 25)) | (1L << (CONTAINS - 25)) | (1L << (IS - 25)) | (1L << (NULL - 25)) | (1L << (TRUE - 25)) | (1L << (FALSE - 25)) | (1L << (COUNT - 25)) | (1L << (FILTER - 25)) | (1L << (EXTRACT - 25)) | (1L << (UnescapedSymbolicName - 25)) | (1L << (EscapedSymbolicName - 25)))) != 0)) {
				{
				setState(718);
				((MapLiteral_nospanContext)_localctx).mapKeyValuePair = mapKeyValuePair();
				((MapLiteral_nospanContext)_localctx).kvs.add(((MapLiteral_nospanContext)_localctx).mapKeyValuePair);
				setState(726);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(719);
					match(T__1);
					setState(721);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(720);
						sp();
						}
					}

					setState(723);
					((MapLiteral_nospanContext)_localctx).mapKeyValuePair = mapKeyValuePair();
					((MapLiteral_nospanContext)_localctx).kvs.add(((MapLiteral_nospanContext)_localctx).mapKeyValuePair);
					}
					}
					setState(728);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(731);
			match(T__13);

			        ((MapLiteral_nospanContext)_localctx).res =  new MapExpression(((MapLiteral_nospanContext)_localctx).kvs.stream().map(kv -> kv.res).collect(Collectors.toList()));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapKeyValuePairContext extends ParserRuleContext {
		public Pair<PropertyKeyName, Expression> res;
		public PropertyKeyNameContext p;
		public ExpressionContext e;
		public PropertyKeyNameContext propertyKeyName() {
			return getRuleContext(PropertyKeyNameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public MapKeyValuePairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapKeyValuePair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMapKeyValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMapKeyValuePair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMapKeyValuePair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapKeyValuePairContext mapKeyValuePair() throws RecognitionException {
		MapKeyValuePairContext _localctx = new MapKeyValuePairContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_mapKeyValuePair);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734);
			((MapKeyValuePairContext)_localctx).p = propertyKeyName();
			setState(736);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(735);
				sp();
				}
			}

			setState(738);
			match(T__4);
			setState(740);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				{
				setState(739);
				sp();
				}
				break;
			}
			setState(742);
			((MapKeyValuePairContext)_localctx).e = expression();
			setState(744);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(743);
				sp();
				}
			}


			        ((MapKeyValuePairContext)_localctx).res =  new Pair<>(((MapKeyValuePairContext)_localctx).p.res, ((MapKeyValuePairContext)_localctx).e.res);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListLiteralContext extends ParserRuleContext {
		public ListExpression res;
		public ListLit_nospanContext l;
		public ListLit_nospanContext listLit_nospan() {
			return getRuleContext(ListLit_nospanContext.class,0);
		}
		public ListLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterListLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitListLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitListLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListLiteralContext listLiteral() throws RecognitionException {
		ListLiteralContext _localctx = new ListLiteralContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_listLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(748);
			((ListLiteralContext)_localctx).l = listLit_nospan();
			 ((ListLiteralContext)_localctx).res =  ((ListLiteralContext)_localctx).l.res; _localctx.res.span = makeSpan((((ListLiteralContext)_localctx).l!=null?(((ListLiteralContext)_localctx).l.start):null), (((ListLiteralContext)_localctx).l!=null?(((ListLiteralContext)_localctx).l.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListLit_nospanContext extends ParserRuleContext {
		public ListExpression res;
		public ExpressionContext expression;
		public List<ExpressionContext> vs = new ArrayList<ExpressionContext>();
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ListLit_nospanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listLit_nospan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterListLit_nospan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitListLit_nospan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitListLit_nospan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListLit_nospanContext listLit_nospan() throws RecognitionException {
		ListLit_nospanContext _localctx = new ListLit_nospanContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_listLit_nospan);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(751);
			match(T__8);
			setState(753);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
			case 1:
				{
				setState(752);
				sp();
				}
				break;
			}
			setState(766);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__8) | (1L << T__12) | (1L << T__14) | (1L << T__16) | (1L << UNION) | (1L << ALL) | (1L << OPTIONAL) | (1L << MATCH) | (1L << WHERE) | (1L << AS) | (1L << WITH) | (1L << DISTINCT) | (1L << RETURN) | (1L << ORDER) | (1L << BY) | (1L << SKIP_) | (1L << LIMIT) | (1L << EXISTS) | (1L << DESC) | (1L << ASC) | (1L << OR) | (1L << XOR) | (1L << AND) | (1L << NOT) | (1L << IN) | (1L << STARTS) | (1L << ENDS) | (1L << CONTAINS) | (1L << IS) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << CASE) | (1L << COUNT) | (1L << FILTER) | (1L << EXTRACT))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (WHITESPACE - 65)) | (1L << (UnescapedSymbolicName - 65)) | (1L << (EscapedSymbolicName - 65)) | (1L << (HexInteger - 65)) | (1L << (DecimalInteger - 65)) | (1L << (OctalInteger - 65)) | (1L << (DigitString - 65)) | (1L << (StringLit - 65)))) != 0)) {
				{
				setState(755);
				((ListLit_nospanContext)_localctx).expression = expression();
				((ListLit_nospanContext)_localctx).vs.add(((ListLit_nospanContext)_localctx).expression);
				setState(763);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(756);
					match(T__1);
					setState(758);
					switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
					case 1:
						{
						setState(757);
						sp();
						}
						break;
					}
					setState(760);
					((ListLit_nospanContext)_localctx).expression = expression();
					((ListLit_nospanContext)_localctx).vs.add(((ListLit_nospanContext)_localctx).expression);
					}
					}
					setState(765);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(768);
			match(T__9);

			        ((ListLit_nospanContext)_localctx).res =  new ListExpression(((ListLit_nospanContext)_localctx).vs.stream().map(v -> v.res).collect(Collectors.toList()));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression res;
		public Expression12Context e;
		public Expression12Context expression12() {
			return getRuleContext(Expression12Context.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(771);
			((ExpressionContext)_localctx).e = expression12();
			 ((ExpressionContext)_localctx).res =  ((ExpressionContext)_localctx).e.res; _localctx.res.span = makeSpan((((ExpressionContext)_localctx).e!=null?(((ExpressionContext)_localctx).e.start):null), (((ExpressionContext)_localctx).e!=null?(((ExpressionContext)_localctx).e.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression12Context extends ParserRuleContext {
		public Expression res;
		public Expression11Context l;
		public Expression11Context expression11;
		public List<Expression11Context> oths = new ArrayList<Expression11Context>();
		public List<Expression11Context> expression11() {
			return getRuleContexts(Expression11Context.class);
		}
		public Expression11Context expression11(int i) {
			return getRuleContext(Expression11Context.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(CypherParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(CypherParser.OR, i);
		}
		public Expression12Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression12; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression12(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression12(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression12(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression12Context expression12() throws RecognitionException {
		Expression12Context _localctx = new Expression12Context(_ctx, getState());
		enterRule(_localctx, 84, RULE_expression12);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(774);
			((Expression12Context)_localctx).l = expression11();
			setState(782);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(775);
					sp();
					setState(776);
					match(OR);
					setState(777);
					sp();
					setState(778);
					((Expression12Context)_localctx).expression11 = expression11();
					((Expression12Context)_localctx).oths.add(((Expression12Context)_localctx).expression11);
					}
					} 
				}
				setState(784);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			}

			    ((Expression12Context)_localctx).l.res.span = makeSpan((((Expression12Context)_localctx).l!=null?(((Expression12Context)_localctx).l.start):null), (((Expression12Context)_localctx).l!=null?(((Expression12Context)_localctx).l.stop):null));
			    ((Expression12Context)_localctx).oths.forEach(b -> b.res.span = makeSpan(b.start, b.stop));
			    ((Expression12Context)_localctx).res =  ((Expression12Context)_localctx).oths.stream().map(b -> (Expression) new Binary.Or(null, b.res)).reduce(((Expression12Context)_localctx).l.res, (lhs, rhs) -> {
			        ((Binary) rhs).lhs = lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression11Context extends ParserRuleContext {
		public Expression res;
		public Expression10Context l;
		public Expression10Context expression10;
		public List<Expression10Context> oths = new ArrayList<Expression10Context>();
		public List<Expression10Context> expression10() {
			return getRuleContexts(Expression10Context.class);
		}
		public Expression10Context expression10(int i) {
			return getRuleContext(Expression10Context.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> XOR() { return getTokens(CypherParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(CypherParser.XOR, i);
		}
		public Expression11Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression11; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression11(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression11(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression11(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression11Context expression11() throws RecognitionException {
		Expression11Context _localctx = new Expression11Context(_ctx, getState());
		enterRule(_localctx, 86, RULE_expression11);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(787);
			((Expression11Context)_localctx).l = expression10();
			setState(795);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,107,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(788);
					sp();
					setState(789);
					match(XOR);
					setState(790);
					sp();
					setState(791);
					((Expression11Context)_localctx).expression10 = expression10();
					((Expression11Context)_localctx).oths.add(((Expression11Context)_localctx).expression10);
					}
					} 
				}
				setState(797);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,107,_ctx);
			}

			    ((Expression11Context)_localctx).l.res.span = makeSpan((((Expression11Context)_localctx).l!=null?(((Expression11Context)_localctx).l.start):null), (((Expression11Context)_localctx).l!=null?(((Expression11Context)_localctx).l.stop):null));
			    ((Expression11Context)_localctx).oths.forEach(b -> b.res.span = makeSpan(b.start, b.stop));
			    ((Expression11Context)_localctx).res =  ((Expression11Context)_localctx).oths.stream().map(b -> (Expression) new Binary.Xor(null, b.res)).reduce(((Expression11Context)_localctx).l.res, (lhs, rhs) -> {
			        ((Binary) rhs).lhs = lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression10Context extends ParserRuleContext {
		public Expression res;
		public Expression9Context l;
		public Expression9Context expression9;
		public List<Expression9Context> oths = new ArrayList<Expression9Context>();
		public List<Expression9Context> expression9() {
			return getRuleContexts(Expression9Context.class);
		}
		public Expression9Context expression9(int i) {
			return getRuleContext(Expression9Context.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(CypherParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(CypherParser.AND, i);
		}
		public Expression10Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression10; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression10(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression10(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression10(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression10Context expression10() throws RecognitionException {
		Expression10Context _localctx = new Expression10Context(_ctx, getState());
		enterRule(_localctx, 88, RULE_expression10);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(800);
			((Expression10Context)_localctx).l = expression9();
			setState(808);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,108,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(801);
					sp();
					setState(802);
					match(AND);
					setState(803);
					sp();
					setState(804);
					((Expression10Context)_localctx).expression9 = expression9();
					((Expression10Context)_localctx).oths.add(((Expression10Context)_localctx).expression9);
					}
					} 
				}
				setState(810);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,108,_ctx);
			}

			    ((Expression10Context)_localctx).l.res.span = makeSpan((((Expression10Context)_localctx).l!=null?(((Expression10Context)_localctx).l.start):null), (((Expression10Context)_localctx).l!=null?(((Expression10Context)_localctx).l.stop):null));
			    ((Expression10Context)_localctx).oths.forEach(b -> b.res.span = makeSpan(b.start, b.stop));
			    ((Expression10Context)_localctx).res =  ((Expression10Context)_localctx).oths.stream().map(b -> (Expression) new Binary.And(null, b.res)).reduce(((Expression10Context)_localctx).l.res, (lhs, rhs) -> {
			        ((Binary) rhs).lhs = lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression9Context extends ParserRuleContext {
		public Expression res;
		public Token NOT;
		public List<Token> nt = new ArrayList<Token>();
		public Expression8Context e;
		public Expression8Context expression8() {
			return getRuleContext(Expression8Context.class,0);
		}
		public List<TerminalNode> NOT() { return getTokens(CypherParser.NOT); }
		public TerminalNode NOT(int i) {
			return getToken(CypherParser.NOT, i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public Expression9Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression9; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression9(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression9(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression9(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression9Context expression9() throws RecognitionException {
		Expression9Context _localctx = new Expression9Context(_ctx, getState());
		enterRule(_localctx, 90, RULE_expression9);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(822);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(814);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(813);
						sp();
						}
					}

					setState(816);
					((Expression9Context)_localctx).NOT = match(NOT);
					((Expression9Context)_localctx).nt.add(((Expression9Context)_localctx).NOT);
					setState(818);
					switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
					case 1:
						{
						setState(817);
						sp();
						}
						break;
					}
					}
					} 
				}
				setState(824);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			}
			setState(825);
			((Expression9Context)_localctx).e = expression8();

			    ((Expression9Context)_localctx).e.res.span = makeSpan((((Expression9Context)_localctx).e!=null?(((Expression9Context)_localctx).e.start):null), (((Expression9Context)_localctx).e!=null?(((Expression9Context)_localctx).e.stop):null));
			    ((Expression9Context)_localctx).res =  ((Expression9Context)_localctx).nt.stream().map(_i -> (Expression) null).reduce(((Expression9Context)_localctx).e.res, (lhs, _i) -> new Unary.Not(lhs));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression8Context extends ParserRuleContext {
		public Expression res;
		public Expression7Context l;
		public PartialComparisonExpressionContext partialComparisonExpression;
		public List<PartialComparisonExpressionContext> oths = new ArrayList<PartialComparisonExpressionContext>();
		public Expression7Context expression7() {
			return getRuleContext(Expression7Context.class,0);
		}
		public List<PartialComparisonExpressionContext> partialComparisonExpression() {
			return getRuleContexts(PartialComparisonExpressionContext.class);
		}
		public PartialComparisonExpressionContext partialComparisonExpression(int i) {
			return getRuleContext(PartialComparisonExpressionContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public Expression8Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression8; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression8(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression8(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression8(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression8Context expression8() throws RecognitionException {
		Expression8Context _localctx = new Expression8Context(_ctx, getState());
		enterRule(_localctx, 92, RULE_expression8);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(828);
			((Expression8Context)_localctx).l = expression7();
			setState(835);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(830);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(829);
						sp();
						}
					}

					setState(832);
					((Expression8Context)_localctx).partialComparisonExpression = partialComparisonExpression();
					((Expression8Context)_localctx).oths.add(((Expression8Context)_localctx).partialComparisonExpression);
					}
					} 
				}
				setState(837);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			}

			    ((Expression8Context)_localctx).l.res.span = makeSpan((((Expression8Context)_localctx).l!=null?(((Expression8Context)_localctx).l.start):null), (((Expression8Context)_localctx).l!=null?(((Expression8Context)_localctx).l.stop):null));
			    ((Expression8Context)_localctx).oths.forEach(b -> b.res.span = makeSpan(b.start, b.stop));
			    ((Expression8Context)_localctx).res =  ((Expression8Context)_localctx).oths.stream().map(b -> (Expression) b.res).reduce(((Expression8Context)_localctx).l.res, (lhs, rhs) -> {
			        ((Binary) rhs).lhs = lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression7Context extends ParserRuleContext {
		public Expression res;
		public Expression6Context l;
		public PartialArithmeticExpressionWithLowPrecedenceContext partialArithmeticExpressionWithLowPrecedence;
		public List<PartialArithmeticExpressionWithLowPrecedenceContext> oths = new ArrayList<PartialArithmeticExpressionWithLowPrecedenceContext>();
		public Expression6Context expression6() {
			return getRuleContext(Expression6Context.class,0);
		}
		public List<PartialArithmeticExpressionWithLowPrecedenceContext> partialArithmeticExpressionWithLowPrecedence() {
			return getRuleContexts(PartialArithmeticExpressionWithLowPrecedenceContext.class);
		}
		public PartialArithmeticExpressionWithLowPrecedenceContext partialArithmeticExpressionWithLowPrecedence(int i) {
			return getRuleContext(PartialArithmeticExpressionWithLowPrecedenceContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public Expression7Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression7; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression7(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression7(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression7(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression7Context expression7() throws RecognitionException {
		Expression7Context _localctx = new Expression7Context(_ctx, getState());
		enterRule(_localctx, 94, RULE_expression7);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(840);
			((Expression7Context)_localctx).l = expression6();
			setState(847);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,115,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(842);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(841);
						sp();
						}
					}

					setState(844);
					((Expression7Context)_localctx).partialArithmeticExpressionWithLowPrecedence = partialArithmeticExpressionWithLowPrecedence();
					((Expression7Context)_localctx).oths.add(((Expression7Context)_localctx).partialArithmeticExpressionWithLowPrecedence);
					}
					} 
				}
				setState(849);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,115,_ctx);
			}

			    ((Expression7Context)_localctx).l.res.span = makeSpan((((Expression7Context)_localctx).l!=null?(((Expression7Context)_localctx).l.start):null), (((Expression7Context)_localctx).l!=null?(((Expression7Context)_localctx).l.stop):null));
			    ((Expression7Context)_localctx).oths.forEach(b -> b.res.span = makeSpan(b.start, b.stop));
			    ((Expression7Context)_localctx).res =  ((Expression7Context)_localctx).oths.stream().map(b -> (Expression) b.res).reduce(((Expression7Context)_localctx).l.res, (lhs, rhs) -> {
			        ((Binary) rhs).lhs = lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression6Context extends ParserRuleContext {
		public Expression res;
		public Expression5Context l;
		public PartialArithmeticExpressionWithHighPrecedenceContext partialArithmeticExpressionWithHighPrecedence;
		public List<PartialArithmeticExpressionWithHighPrecedenceContext> oths = new ArrayList<PartialArithmeticExpressionWithHighPrecedenceContext>();
		public Expression5Context expression5() {
			return getRuleContext(Expression5Context.class,0);
		}
		public List<PartialArithmeticExpressionWithHighPrecedenceContext> partialArithmeticExpressionWithHighPrecedence() {
			return getRuleContexts(PartialArithmeticExpressionWithHighPrecedenceContext.class);
		}
		public PartialArithmeticExpressionWithHighPrecedenceContext partialArithmeticExpressionWithHighPrecedence(int i) {
			return getRuleContext(PartialArithmeticExpressionWithHighPrecedenceContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public Expression6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression6; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression6(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression6Context expression6() throws RecognitionException {
		Expression6Context _localctx = new Expression6Context(_ctx, getState());
		enterRule(_localctx, 96, RULE_expression6);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(852);
			((Expression6Context)_localctx).l = expression5();
			setState(859);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(854);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(853);
						sp();
						}
					}

					setState(856);
					((Expression6Context)_localctx).partialArithmeticExpressionWithHighPrecedence = partialArithmeticExpressionWithHighPrecedence();
					((Expression6Context)_localctx).oths.add(((Expression6Context)_localctx).partialArithmeticExpressionWithHighPrecedence);
					}
					} 
				}
				setState(861);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
			}

			    ((Expression6Context)_localctx).l.res.span = makeSpan((((Expression6Context)_localctx).l!=null?(((Expression6Context)_localctx).l.start):null), (((Expression6Context)_localctx).l!=null?(((Expression6Context)_localctx).l.stop):null));
			    ((Expression6Context)_localctx).oths.forEach(b -> b.res.span = makeSpan(b.start, b.stop));
			    ((Expression6Context)_localctx).res =  ((Expression6Context)_localctx).oths.stream().map(b -> (Expression) b.res).reduce(((Expression6Context)_localctx).l.res, (lhs, rhs) -> {
			        ((Binary) rhs).lhs = lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression5Context extends ParserRuleContext {
		public Expression res;
		public Expression4Context l;
		public PartialArithmeticPowContext partialArithmeticPow;
		public List<PartialArithmeticPowContext> oths = new ArrayList<PartialArithmeticPowContext>();
		public Expression4Context expression4() {
			return getRuleContext(Expression4Context.class,0);
		}
		public List<PartialArithmeticPowContext> partialArithmeticPow() {
			return getRuleContexts(PartialArithmeticPowContext.class);
		}
		public PartialArithmeticPowContext partialArithmeticPow(int i) {
			return getRuleContext(PartialArithmeticPowContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public Expression5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression5(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression5Context expression5() throws RecognitionException {
		Expression5Context _localctx = new Expression5Context(_ctx, getState());
		enterRule(_localctx, 98, RULE_expression5);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(864);
			((Expression5Context)_localctx).l = expression4();
			setState(871);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(866);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(865);
						sp();
						}
					}

					setState(868);
					((Expression5Context)_localctx).partialArithmeticPow = partialArithmeticPow();
					((Expression5Context)_localctx).oths.add(((Expression5Context)_localctx).partialArithmeticPow);
					}
					} 
				}
				setState(873);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
			}

			    ((Expression5Context)_localctx).l.res.span = makeSpan((((Expression5Context)_localctx).l!=null?(((Expression5Context)_localctx).l.start):null), (((Expression5Context)_localctx).l!=null?(((Expression5Context)_localctx).l.stop):null));
			    ((Expression5Context)_localctx).oths.forEach(b -> b.res.span = makeSpan(b.start, b.stop));
			    ((Expression5Context)_localctx).res =  ((Expression5Context)_localctx).oths.stream().map(b -> (Expression) b.res).reduce(((Expression5Context)_localctx).l.res, (lhs, rhs) -> {
			        ((Binary) rhs).lhs = lhs;
			        return rhs;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression4Context extends ParserRuleContext {
		public Expression res;
		public UnaryOpContext unaryOp;
		public List<UnaryOpContext> us = new ArrayList<UnaryOpContext>();
		public Expression3Context e;
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public List<UnaryOpContext> unaryOp() {
			return getRuleContexts(UnaryOpContext.class);
		}
		public UnaryOpContext unaryOp(int i) {
			return getRuleContext(UnaryOpContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public Expression4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression4Context expression4() throws RecognitionException {
		Expression4Context _localctx = new Expression4Context(_ctx, getState());
		enterRule(_localctx, 100, RULE_expression4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(882);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==T__14) {
				{
				{
				setState(876);
				((Expression4Context)_localctx).unaryOp = unaryOp();
				((Expression4Context)_localctx).us.add(((Expression4Context)_localctx).unaryOp);
				setState(878);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(877);
					sp();
					}
				}

				}
				}
				setState(884);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(885);
			((Expression4Context)_localctx).e = expression3();

			    ((Expression4Context)_localctx).e.res.span = makeSpan((((Expression4Context)_localctx).e!=null?(((Expression4Context)_localctx).e.start):null), (((Expression4Context)_localctx).e!=null?(((Expression4Context)_localctx).e.stop):null));
			    ((Expression4Context)_localctx).res =  ((Expression4Context)_localctx).us.stream().map(u -> (Expression) u.res).reduce(((Expression4Context)_localctx).e.res, (lhs, u) -> {
			        ((Unary) u).lhs = lhs;
			        return u;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOpContext extends ParserRuleContext {
		public Unary res;
		public UnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOpContext unaryOp() throws RecognitionException {
		UnaryOpContext _localctx = new UnaryOpContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_unaryOp);
		try {
			setState(892);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(888);
				match(T__14);
				 ((UnaryOpContext)_localctx).res =  new Unary.Add(null); 
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(890);
				match(T__6);
				 ((UnaryOpContext)_localctx).res =  new Unary.Subtract(null); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression3Context extends ParserRuleContext {
		public Expression res;
		public Expression2Context l;
		public Expression1Context expression1;
		public List<Expression1Context> oths = new ArrayList<Expression1Context>();
		public Expression2Context expression2() {
			return getRuleContext(Expression2Context.class,0);
		}
		public List<Expression1Context> expression1() {
			return getRuleContexts(Expression1Context.class);
		}
		public Expression1Context expression1(int i) {
			return getRuleContext(Expression1Context.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public Expression3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression3Context expression3() throws RecognitionException {
		Expression3Context _localctx = new Expression3Context(_ctx, getState());
		enterRule(_localctx, 104, RULE_expression3);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(894);
			((Expression3Context)_localctx).l = expression2();
			setState(901);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(896);
					switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
					case 1:
						{
						setState(895);
						sp();
						}
						break;
					}
					setState(898);
					((Expression3Context)_localctx).expression1 = expression1();
					((Expression3Context)_localctx).oths.add(((Expression3Context)_localctx).expression1);
					}
					} 
				}
				setState(903);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
			}

			    ((Expression3Context)_localctx).l.res.span = makeSpan((((Expression3Context)_localctx).l!=null?(((Expression3Context)_localctx).l.start):null), (((Expression3Context)_localctx).l!=null?(((Expression3Context)_localctx).l.stop):null));
			    ((Expression3Context)_localctx).res =  ((Expression3Context)_localctx).oths.stream().map(u -> (Expression) u.res).reduce(((Expression3Context)_localctx).l.res, (lhs, u) -> {
			        ((Unary) u).lhs = lhs;
			        return u;
			    });

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression2Context extends ParserRuleContext {
		public Expression res;
		public AtomContext a;
		public ProperSingleContext properSingle;
		public List<ProperSingleContext> lks = new ArrayList<ProperSingleContext>();
		public ProperLabelContext properLabel;
		public List<ProperLabelContext> labels = new ArrayList<ProperLabelContext>();
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<ProperSingleContext> properSingle() {
			return getRuleContexts(ProperSingleContext.class);
		}
		public ProperSingleContext properSingle(int i) {
			return getRuleContext(ProperSingleContext.class,i);
		}
		public List<ProperLabelContext> properLabel() {
			return getRuleContexts(ProperLabelContext.class);
		}
		public ProperLabelContext properLabel(int i) {
			return getRuleContext(ProperLabelContext.class,i);
		}
		public Expression2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression2Context expression2() throws RecognitionException {
		Expression2Context _localctx = new Expression2Context(_ctx, getState());
		enterRule(_localctx, 106, RULE_expression2);
		int _la;
		try {
			int _alt;
			setState(924);
			switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(906);
				((Expression2Context)_localctx).a = atom();
				setState(910);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(907);
						((Expression2Context)_localctx).properSingle = properSingle();
						((Expression2Context)_localctx).lks.add(((Expression2Context)_localctx).properSingle);
						}
						} 
					}
					setState(912);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
				}

				        if(((Expression2Context)_localctx).lks.size()>0){
				            ((Expression2Context)_localctx).lks.forEach(p -> p.res.span = makeSpan(p.start, p.stop));
				            ((Expression2Context)_localctx).res =  ((Expression2Context)_localctx).lks.stream().map(t->t.res).reduce(((Expression2Context)_localctx).a.res, (lhs, rhs) -> {
				                if(rhs instanceof Property){
				                    ((Property) rhs).map = lhs;
				                    return rhs;
				                }else{return null;}
				//                else{
				//                    Expression current=new Binary.Equals( new Property(((Expression2Context)_localctx).a.res, new PropertyKeyName("label")),
				//                                                         Variable.getVariable(((LabelExpression)rhs).labelNames.get(0).name)
				//                                                         );
				//                    if(((LabelExpression)rhs).labelNames.size()>1){
				//                        for(int i=1;i<((LabelExpression)rhs).labelNames.size();i++){
				//                           Binary.Equals eq=new Binary.Equals( new Property(((Expression2Context)_localctx).a.res, new PropertyKeyName("label")),
				//                                                               Variable.getVariable(((LabelExpression)rhs).labelNames.get(i).name)
				//                                                                );
				//                           current=new Binary.And(current,eq);
				//                        }
				//                    }
				//                    return current;
				//               }
				            });
				        }
				        else{
				            ((Expression2Context)_localctx).res = ((Expression2Context)_localctx).a.res;
				        }
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(915);
				((Expression2Context)_localctx).a = atom();
				setState(919);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(916);
					((Expression2Context)_localctx).properLabel = properLabel();
					((Expression2Context)_localctx).labels.add(((Expression2Context)_localctx).properLabel);
					}
					}
					setState(921);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}

				        if(((Expression2Context)_localctx).a.res instanceof Variable){
				            Variable var = (Variable) ((Expression2Context)_localctx).a.res;
				            if(((Expression2Context)_localctx).labels.size()>1){
				                ((Expression2Context)_localctx).res =  ((Expression2Context)_localctx).labels.stream().map(t->t.res).reduce((lhs, rhs) -> {
				                    ((LabelExpression)rhs).labelNames.addAll(((LabelExpression)lhs).labelNames);
				                    ((LabelExpression)rhs).var=var;
				                    return rhs;
				                }).get();
				            }else{
				                LabelExpression l1 = (LabelExpression)(((Expression2Context)_localctx).labels.get(0).res);
				                l1.var=var;
				                ((Expression2Context)_localctx).res =  l1;
				            }
				        }else{
				              ((Expression2Context)_localctx).res = ((Expression2Context)_localctx).a.res;
				        }
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProperSingleContext extends ParserRuleContext {
		public Expression res;
		public PropertyLookupContext p;
		public PropertyLookupContext propertyLookup() {
			return getRuleContext(PropertyLookupContext.class,0);
		}
		public ProperSingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properSingle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProperSingle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProperSingle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitProperSingle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProperSingleContext properSingle() throws RecognitionException {
		ProperSingleContext _localctx = new ProperSingleContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_properSingle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(926);
			((ProperSingleContext)_localctx).p = propertyLookup();
			((ProperSingleContext)_localctx).res = ((ProperSingleContext)_localctx).p.res;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProperLabelContext extends ParserRuleContext {
		public Expression res;
		public NodeLabelsContext n;
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public ProperLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProperLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProperLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitProperLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProperLabelContext properLabel() throws RecognitionException {
		ProperLabelContext _localctx = new ProperLabelContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_properLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(929);
			((ProperLabelContext)_localctx).n = nodeLabels();
			((ProperLabelContext)_localctx).res = new LabelExpression(null,((ProperLabelContext)_localctx).n.res);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression1Context extends ParserRuleContext {
		public Unary res;
		public Expression0Context e;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode IS() { return getToken(CypherParser.IS, 0); }
		public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public Expression1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression1Context expression1() throws RecognitionException {
		Expression1Context _localctx = new Expression1Context(_ctx, getState());
		enterRule(_localctx, 112, RULE_expression1);
		int _la;
		try {
			setState(964);
			switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(932);
				match(T__8);
				setState(933);
				expression();
				setState(934);
				match(T__9);
				 ((Expression1Context)_localctx).res =  null; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(937);
				match(T__8);
				setState(939);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__8) | (1L << T__12) | (1L << T__14) | (1L << T__16) | (1L << UNION) | (1L << ALL) | (1L << OPTIONAL) | (1L << MATCH) | (1L << WHERE) | (1L << AS) | (1L << WITH) | (1L << DISTINCT) | (1L << RETURN) | (1L << ORDER) | (1L << BY) | (1L << SKIP_) | (1L << LIMIT) | (1L << EXISTS) | (1L << DESC) | (1L << ASC) | (1L << OR) | (1L << XOR) | (1L << AND) | (1L << NOT) | (1L << IN) | (1L << STARTS) | (1L << ENDS) | (1L << CONTAINS) | (1L << IS) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << CASE) | (1L << COUNT) | (1L << FILTER) | (1L << EXTRACT))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (WHITESPACE - 65)) | (1L << (UnescapedSymbolicName - 65)) | (1L << (EscapedSymbolicName - 65)) | (1L << (HexInteger - 65)) | (1L << (DecimalInteger - 65)) | (1L << (OctalInteger - 65)) | (1L << (DigitString - 65)) | (1L << (StringLit - 65)))) != 0)) {
					{
					setState(938);
					expression();
					}
				}

				setState(941);
				match(T__10);
				setState(943);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__6) | (1L << T__8) | (1L << T__12) | (1L << T__14) | (1L << T__16) | (1L << UNION) | (1L << ALL) | (1L << OPTIONAL) | (1L << MATCH) | (1L << WHERE) | (1L << AS) | (1L << WITH) | (1L << DISTINCT) | (1L << RETURN) | (1L << ORDER) | (1L << BY) | (1L << SKIP_) | (1L << LIMIT) | (1L << EXISTS) | (1L << DESC) | (1L << ASC) | (1L << OR) | (1L << XOR) | (1L << AND) | (1L << NOT) | (1L << IN) | (1L << STARTS) | (1L << ENDS) | (1L << CONTAINS) | (1L << IS) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << CASE) | (1L << COUNT) | (1L << FILTER) | (1L << EXTRACT))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (WHITESPACE - 65)) | (1L << (UnescapedSymbolicName - 65)) | (1L << (EscapedSymbolicName - 65)) | (1L << (HexInteger - 65)) | (1L << (DecimalInteger - 65)) | (1L << (OctalInteger - 65)) | (1L << (DigitString - 65)) | (1L << (StringLit - 65)))) != 0)) {
					{
					setState(942);
					expression();
					}
				}

				setState(945);
				match(T__9);
				 ((Expression1Context)_localctx).res =  null; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(947);
				((Expression1Context)_localctx).e = expression0();
				 ((Expression1Context)_localctx).res =  ((Expression1Context)_localctx).e.res; _localctx.res.span = makeSpan((((Expression1Context)_localctx).e!=null?(((Expression1Context)_localctx).e.start):null), (((Expression1Context)_localctx).e!=null?(((Expression1Context)_localctx).e.stop):null)); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(950);
				sp();
				setState(951);
				match(IS);
				setState(952);
				sp();
				setState(953);
				match(NULL);
				 ((Expression1Context)_localctx).res =  new Unary.IsNull(null); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(956);
				sp();
				setState(957);
				match(IS);
				setState(958);
				sp();
				setState(959);
				match(NOT);
				setState(960);
				sp();
				setState(961);
				match(NULL);
				 ((Expression1Context)_localctx).res =  new Unary.IsNotNull(null); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression0Context extends ParserRuleContext {
		public Binary res;
		public Expression2Context e;
		public Expression2Context expression2() {
			return getRuleContext(Expression2Context.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public TerminalNode STARTS() { return getToken(CypherParser.STARTS, 0); }
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode ENDS() { return getToken(CypherParser.ENDS, 0); }
		public TerminalNode CONTAINS() { return getToken(CypherParser.CONTAINS, 0); }
		public Expression0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression0; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression0(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression0(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression0(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression0Context expression0() throws RecognitionException {
		Expression0Context _localctx = new Expression0Context(_ctx, getState());
		enterRule(_localctx, 114, RULE_expression0);
		int _la;
		try {
			setState(1012);
			switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(967);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(966);
					sp();
					}
				}

				setState(969);
				match(T__15);
				setState(971);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(970);
					sp();
					}
				}

				setState(973);
				((Expression0Context)_localctx).e = expression2();
				 ((Expression0Context)_localctx).res =  new Binary.RegexMatch(null, ((Expression0Context)_localctx).e.res); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(976);
				sp();
				setState(977);
				match(IN);
				setState(979);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(978);
					sp();
					}
				}

				setState(981);
				((Expression0Context)_localctx).e = expression2();
				 ((Expression0Context)_localctx).res =  new Binary.In(null, ((Expression0Context)_localctx).e.res); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(984);
				sp();
				setState(985);
				match(STARTS);
				setState(986);
				sp();
				setState(987);
				match(WITH);
				setState(989);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(988);
					sp();
					}
				}

				setState(991);
				((Expression0Context)_localctx).e = expression2();
				 ((Expression0Context)_localctx).res =  new Binary.StartsWith(null, ((Expression0Context)_localctx).e.res); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(994);
				sp();
				setState(995);
				match(ENDS);
				setState(996);
				sp();
				setState(997);
				match(WITH);
				setState(999);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(998);
					sp();
					}
				}

				setState(1001);
				((Expression0Context)_localctx).e = expression2();
				 ((Expression0Context)_localctx).res =  new Binary.EndsWith(null, ((Expression0Context)_localctx).e.res); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1004);
				sp();
				setState(1005);
				match(CONTAINS);
				setState(1007);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1006);
					sp();
					}
				}

				setState(1009);
				((Expression0Context)_localctx).e = expression2();
				 ((Expression0Context)_localctx).res =  new Binary.Contains(null, ((Expression0Context)_localctx).e.res); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistsFunctionContext extends ParserRuleContext {
		public ExistsFunction res;
		public ExpressionContext e;
		public MatchContext m;
		public ExistsFunctionNameContext existsFunctionName() {
			return getRuleContext(ExistsFunctionNameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public MatchContext match() {
			return getRuleContext(MatchContext.class,0);
		}
		public ExistsFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existsFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExistsFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExistsFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExistsFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExistsFunctionContext existsFunction() throws RecognitionException {
		ExistsFunctionContext _localctx = new ExistsFunctionContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_existsFunction);
		int _la;
		try {
			setState(1045);
			switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1014);
				existsFunctionName();
				setState(1016);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1015);
					sp();
					}
				}

				setState(1018);
				match(T__2);
				setState(1020);
				switch ( getInterpreter().adaptivePredict(_input,139,_ctx) ) {
				case 1:
					{
					setState(1019);
					sp();
					}
					break;
				}
				setState(1022);
				((ExistsFunctionContext)_localctx).e = expression();
				setState(1024);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1023);
					sp();
					}
				}

				setState(1026);
				match(T__3);
				}
				 ((ExistsFunctionContext)_localctx).res =  new ExistsFunction(((ExistsFunctionContext)_localctx).e.res); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1030);
				existsFunctionName();
				setState(1032);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1031);
					sp();
					}
				}

				setState(1034);
				match(T__12);
				setState(1036);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1035);
					sp();
					}
				}

				setState(1038);
				((ExistsFunctionContext)_localctx).m = match();
				setState(1040);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1039);
					sp();
					}
				}

				setState(1042);
				match(T__13);
				 ((ExistsFunctionContext)_localctx).res =  new ExistsFunction(((ExistsFunctionContext)_localctx).m.res); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistsFunctionNameContext extends ParserRuleContext {
		public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }
		public ExistsFunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existsFunctionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExistsFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExistsFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExistsFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExistsFunctionNameContext existsFunctionName() throws RecognitionException {
		ExistsFunctionNameContext _localctx = new ExistsFunctionNameContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_existsFunctionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1047);
			match(EXISTS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyLookupContext extends ParserRuleContext {
		public Property res;
		public PropertyKeyNameContext p;
		public PropertyKeyNameContext propertyKeyName() {
			return getRuleContext(PropertyKeyNameContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public PropertyLookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyLookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPropertyLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyLookupContext propertyLookup() throws RecognitionException {
		PropertyLookupContext _localctx = new PropertyLookupContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_propertyLookup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1050);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(1049);
				sp();
				}
			}

			setState(1052);
			match(T__16);
			setState(1054);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(1053);
				sp();
				}
			}

			setState(1056);
			((PropertyLookupContext)_localctx).p = propertyKeyName();

			    ((PropertyLookupContext)_localctx).res =  new Property(null, ((PropertyLookupContext)_localctx).p.res);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartialArithmeticExpressionWithLowPrecedenceContext extends ParserRuleContext {
		public Binary res;
		public Expression6Context e;
		public Expression6Context expression6() {
			return getRuleContext(Expression6Context.class,0);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public PartialArithmeticExpressionWithLowPrecedenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partialArithmeticExpressionWithLowPrecedence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPartialArithmeticExpressionWithLowPrecedence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPartialArithmeticExpressionWithLowPrecedence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPartialArithmeticExpressionWithLowPrecedence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartialArithmeticExpressionWithLowPrecedenceContext partialArithmeticExpressionWithLowPrecedence() throws RecognitionException {
		PartialArithmeticExpressionWithLowPrecedenceContext _localctx = new PartialArithmeticExpressionWithLowPrecedenceContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_partialArithmeticExpressionWithLowPrecedence);
		int _la;
		try {
			setState(1073);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(1059);
				match(T__14);
				setState(1061);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1060);
					sp();
					}
				}

				setState(1063);
				((PartialArithmeticExpressionWithLowPrecedenceContext)_localctx).e = expression6();
				 ((PartialArithmeticExpressionWithLowPrecedenceContext)_localctx).res =  new Binary.Add(null, ((PartialArithmeticExpressionWithLowPrecedenceContext)_localctx).e.res); 
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(1066);
				match(T__6);
				setState(1068);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1067);
					sp();
					}
				}

				setState(1070);
				((PartialArithmeticExpressionWithLowPrecedenceContext)_localctx).e = expression6();
				 ((PartialArithmeticExpressionWithLowPrecedenceContext)_localctx).res =  new Binary.Subtract(null, ((PartialArithmeticExpressionWithLowPrecedenceContext)_localctx).e.res); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartialArithmeticExpressionWithHighPrecedenceContext extends ParserRuleContext {
		public Binary res;
		public Expression5Context e;
		public Expression5Context expression5() {
			return getRuleContext(Expression5Context.class,0);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public PartialArithmeticExpressionWithHighPrecedenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partialArithmeticExpressionWithHighPrecedence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPartialArithmeticExpressionWithHighPrecedence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPartialArithmeticExpressionWithHighPrecedence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPartialArithmeticExpressionWithHighPrecedence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartialArithmeticExpressionWithHighPrecedenceContext partialArithmeticExpressionWithHighPrecedence() throws RecognitionException {
		PartialArithmeticExpressionWithHighPrecedenceContext _localctx = new PartialArithmeticExpressionWithHighPrecedenceContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_partialArithmeticExpressionWithHighPrecedence);
		int _la;
		try {
			setState(1096);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(1075);
				match(STAR);
				setState(1077);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1076);
					sp();
					}
				}

				setState(1079);
				((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).e = expression5();
				 ((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).res =  new Binary.Multiply(null, ((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).e.res); 
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(1082);
				match(T__17);
				setState(1084);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1083);
					sp();
					}
				}

				setState(1086);
				((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).e = expression5();
				 ((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).res =  new Binary.Divide(null, ((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).e.res); 
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(1089);
				match(T__18);
				setState(1091);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1090);
					sp();
					}
				}

				setState(1093);
				((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).e = expression5();
				 ((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).res =  new Binary.Modulo(null, ((PartialArithmeticExpressionWithHighPrecedenceContext)_localctx).e.res); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartialArithmeticPowContext extends ParserRuleContext {
		public Binary res;
		public Expression4Context e;
		public Expression4Context expression4() {
			return getRuleContext(Expression4Context.class,0);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public PartialArithmeticPowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partialArithmeticPow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPartialArithmeticPow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPartialArithmeticPow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPartialArithmeticPow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartialArithmeticPowContext partialArithmeticPow() throws RecognitionException {
		PartialArithmeticPowContext _localctx = new PartialArithmeticPowContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_partialArithmeticPow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1098);
			match(T__19);
			setState(1100);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(1099);
				sp();
				}
			}

			setState(1102);
			((PartialArithmeticPowContext)_localctx).e = expression4();
			 ((PartialArithmeticPowContext)_localctx).res =  new Binary.Pow(null, ((PartialArithmeticPowContext)_localctx).e.res); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartialComparisonExpressionContext extends ParserRuleContext {
		public Binary res;
		public Expression7Context e;
		public Expression7Context expression7() {
			return getRuleContext(Expression7Context.class,0);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public PartialComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partialComparisonExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPartialComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPartialComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPartialComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartialComparisonExpressionContext partialComparisonExpression() throws RecognitionException {
		PartialComparisonExpressionContext _localctx = new PartialComparisonExpressionContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_partialComparisonExpression);
		int _la;
		try {
			setState(1161);
			switch (_input.LA(1)) {
			case ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1105);
				match(ASSIGN);
				setState(1107);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1106);
					sp();
					}
				}

				setState(1109);
				((PartialComparisonExpressionContext)_localctx).e = expression7();
				}
				 ((PartialComparisonExpressionContext)_localctx).res =  new Binary.Equals(null, ((PartialComparisonExpressionContext)_localctx).e.res); 
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1113);
				match(T__20);
				setState(1115);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1114);
					sp();
					}
				}

				setState(1117);
				((PartialComparisonExpressionContext)_localctx).e = expression7();
				}
				 ((PartialComparisonExpressionContext)_localctx).res =  new Binary.NotEquals(null, ((PartialComparisonExpressionContext)_localctx).e.res); 
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(1121);
				match(T__21);
				setState(1123);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1122);
					sp();
					}
				}

				setState(1125);
				((PartialComparisonExpressionContext)_localctx).e = expression7();
				}
				 ((PartialComparisonExpressionContext)_localctx).res =  new Binary.InvalidNotEquals(null, ((PartialComparisonExpressionContext)_localctx).e.res); 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(1129);
				match(T__5);
				setState(1131);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1130);
					sp();
					}
				}

				setState(1133);
				((PartialComparisonExpressionContext)_localctx).e = expression7();
				}
				 ((PartialComparisonExpressionContext)_localctx).res =  new Binary.LessThan(null, ((PartialComparisonExpressionContext)_localctx).e.res); 
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(1137);
				match(T__7);
				setState(1139);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1138);
					sp();
					}
				}

				setState(1141);
				((PartialComparisonExpressionContext)_localctx).e = expression7();
				}
				 ((PartialComparisonExpressionContext)_localctx).res =  new Binary.GreaterThan(null, ((PartialComparisonExpressionContext)_localctx).e.res); 
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(1145);
				match(T__22);
				setState(1147);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1146);
					sp();
					}
				}

				setState(1149);
				((PartialComparisonExpressionContext)_localctx).e = expression7();
				}
				 ((PartialComparisonExpressionContext)_localctx).res =  new Binary.LessThanOrEqual(null, ((PartialComparisonExpressionContext)_localctx).e.res); 
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(1153);
				match(T__23);
				setState(1155);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1154);
					sp();
					}
				}

				setState(1157);
				((PartialComparisonExpressionContext)_localctx).e = expression7();
				}
				 ((PartialComparisonExpressionContext)_localctx).res =  new Binary.GreaterThanOrEqual(null, ((PartialComparisonExpressionContext)_localctx).e.res); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Expression res;
		public NumberLitContext n;
		public Token s;
		public MapLiteralContext m;
		public ListLiteralContext l;
		public ParenthesizedExpressionContext p;
		public RelationshipsPatternContext r;
		public FunctionInvocationContext f;
		public VariableContext v;
		public CaseExpressionContext c;
		public ExistsFunctionContext e;
		public NumberLitContext numberLit() {
			return getRuleContext(NumberLitContext.class,0);
		}
		public TerminalNode StringLit() { return getToken(CypherParser.StringLit, 0); }
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public TerminalNode STAR() { return getToken(CypherParser.STAR, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public MapLiteralContext mapLiteral() {
			return getRuleContext(MapLiteralContext.class,0);
		}
		public ListLiteralContext listLiteral() {
			return getRuleContext(ListLiteralContext.class,0);
		}
		public ParenthesizedExpressionContext parenthesizedExpression() {
			return getRuleContext(ParenthesizedExpressionContext.class,0);
		}
		public RelationshipsPatternContext relationshipsPattern() {
			return getRuleContext(RelationshipsPatternContext.class,0);
		}
		public FunctionInvocationContext functionInvocation() {
			return getRuleContext(FunctionInvocationContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public CaseExpressionContext caseExpression() {
			return getRuleContext(CaseExpressionContext.class,0);
		}
		public ExistsFunctionContext existsFunction() {
			return getRuleContext(ExistsFunctionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_atom);
		int _la;
		try {
			setState(1213);
			switch ( getInterpreter().adaptivePredict(_input,166,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1163);
				((AtomContext)_localctx).n = numberLit();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).n.res; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1166);
				((AtomContext)_localctx).s = match(StringLit);
				 ((AtomContext)_localctx).res =  new Literal.StringLiteral((((AtomContext)_localctx).s!=null?((AtomContext)_localctx).s.getText():null));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1168);
				match(TRUE);
				 ((AtomContext)_localctx).res =  new Literal.True(); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1170);
				match(FALSE);
				 ((AtomContext)_localctx).res =  new Literal.False(); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1172);
				match(NULL);
				 ((AtomContext)_localctx).res =  new Literal.Null(); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(1174);
				match(COUNT);
				setState(1176);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1175);
					sp();
					}
				}

				setState(1178);
				match(T__2);
				setState(1180);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1179);
					sp();
					}
				}

				setState(1182);
				match(STAR);
				setState(1184);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1183);
					sp();
					}
				}

				setState(1186);
				match(T__3);
				}
				  FunctionName count = new FunctionName("COUNT");
				                                                ((AtomContext)_localctx).res =  new FunctionInvocation(count,false,
				                                                new ArrayList<>(Collections.singleton(new Literal.Star())));  
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1189);
				((AtomContext)_localctx).m = mapLiteral();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).m.res; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1192);
				((AtomContext)_localctx).l = listLiteral();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).l.res; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1195);
				((AtomContext)_localctx).p = parenthesizedExpression();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).p.res; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1198);
				((AtomContext)_localctx).r = relationshipsPattern();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).r.res; 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1201);
				((AtomContext)_localctx).f = functionInvocation();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).f.res; 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1204);
				((AtomContext)_localctx).v = variable();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).v.res; 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1207);
				((AtomContext)_localctx).c = caseExpression();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).c.res; 
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1210);
				((AtomContext)_localctx).e = existsFunction();
				 ((AtomContext)_localctx).res =  ((AtomContext)_localctx).e.res; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseExpressionContext extends ParserRuleContext {
		public CaseExpression res;
		public CaseAlternativesContext caseAlternatives;
		public List<CaseAlternativesContext> c = new ArrayList<CaseAlternativesContext>();
		public ExpressionContext de;
		public ExpressionContext e;
		public TerminalNode CASE() { return getToken(CypherParser.CASE, 0); }
		public TerminalNode END() { return getToken(CypherParser.END, 0); }
		public TerminalNode ELSE() { return getToken(CypherParser.ELSE, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<CaseAlternativesContext> caseAlternatives() {
			return getRuleContexts(CaseAlternativesContext.class);
		}
		public CaseAlternativesContext caseAlternatives(int i) {
			return getRuleContext(CaseAlternativesContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseExpressionContext caseExpression() throws RecognitionException {
		CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_caseExpression);
		int _la;
		try {
			int _alt;
			setState(1269);
			switch ( getInterpreter().adaptivePredict(_input,180,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1215);
				match(CASE);
				setState(1220); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1217);
						_la = _input.LA(1);
						if (_la==WHITESPACE) {
							{
							setState(1216);
							sp();
							}
						}

						setState(1219);
						((CaseExpressionContext)_localctx).caseAlternatives = caseAlternatives();
						((CaseExpressionContext)_localctx).c.add(((CaseExpressionContext)_localctx).caseAlternatives);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1222); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,168,_ctx);
				} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
				setState(1232);
				switch ( getInterpreter().adaptivePredict(_input,171,_ctx) ) {
				case 1:
					{
					setState(1225);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(1224);
						sp();
						}
					}

					setState(1227);
					match(ELSE);
					setState(1229);
					switch ( getInterpreter().adaptivePredict(_input,170,_ctx) ) {
					case 1:
						{
						setState(1228);
						sp();
						}
						break;
					}
					setState(1231);
					((CaseExpressionContext)_localctx).de = expression();
					}
					break;
				}
				setState(1235);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1234);
					sp();
					}
				}

				setState(1237);
				match(END);
				 ((CaseExpressionContext)_localctx).res = new CaseExpression(null, ((CaseExpressionContext)_localctx).de.res, ((CaseExpressionContext)_localctx).c.stream().map(u -> u.res).collect(Collectors.toList())); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1240);
				match(CASE);
				setState(1242);
				switch ( getInterpreter().adaptivePredict(_input,173,_ctx) ) {
				case 1:
					{
					setState(1241);
					sp();
					}
					break;
				}
				setState(1244);
				((CaseExpressionContext)_localctx).e = expression();
				setState(1249); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1246);
						_la = _input.LA(1);
						if (_la==WHITESPACE) {
							{
							setState(1245);
							sp();
							}
						}

						setState(1248);
						((CaseExpressionContext)_localctx).caseAlternatives = caseAlternatives();
						((CaseExpressionContext)_localctx).c.add(((CaseExpressionContext)_localctx).caseAlternatives);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1251); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,175,_ctx);
				} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
				setState(1261);
				switch ( getInterpreter().adaptivePredict(_input,178,_ctx) ) {
				case 1:
					{
					setState(1254);
					_la = _input.LA(1);
					if (_la==WHITESPACE) {
						{
						setState(1253);
						sp();
						}
					}

					setState(1256);
					match(ELSE);
					setState(1258);
					switch ( getInterpreter().adaptivePredict(_input,177,_ctx) ) {
					case 1:
						{
						setState(1257);
						sp();
						}
						break;
					}
					setState(1260);
					((CaseExpressionContext)_localctx).de = expression();
					}
					break;
				}
				setState(1264);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1263);
					sp();
					}
				}

				setState(1266);
				match(END);
				 ((CaseExpressionContext)_localctx).res = new CaseExpression(((CaseExpressionContext)_localctx).e.res, ((CaseExpressionContext)_localctx).de.res, ((CaseExpressionContext)_localctx).c.stream().map(u -> u.res).collect(Collectors.toList())); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseAlternativesContext extends ParserRuleContext {
		public Pair<Expression, Expression> res;
		public ExpressionContext e1;
		public ExpressionContext e2;
		public TerminalNode WHEN() { return getToken(CypherParser.WHEN, 0); }
		public TerminalNode THEN() { return getToken(CypherParser.THEN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public CaseAlternativesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseAlternatives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCaseAlternatives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCaseAlternatives(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCaseAlternatives(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseAlternativesContext caseAlternatives() throws RecognitionException {
		CaseAlternativesContext _localctx = new CaseAlternativesContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_caseAlternatives);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1271);
			match(WHEN);
			setState(1273);
			switch ( getInterpreter().adaptivePredict(_input,181,_ctx) ) {
			case 1:
				{
				setState(1272);
				sp();
				}
				break;
			}
			setState(1275);
			((CaseAlternativesContext)_localctx).e1 = expression();
			setState(1277);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(1276);
				sp();
				}
			}

			setState(1279);
			match(THEN);
			setState(1281);
			switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
			case 1:
				{
				setState(1280);
				sp();
				}
				break;
			}
			setState(1283);
			((CaseAlternativesContext)_localctx).e2 = expression();
			 ((CaseAlternativesContext)_localctx).res = new Pair<>(((CaseAlternativesContext)_localctx).e1.res,((CaseAlternativesContext)_localctx).e2.res);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthesizedExpressionContext extends ParserRuleContext {
		public Expression res;
		public ExpressionContext e;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public ParenthesizedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesizedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesizedExpressionContext parenthesizedExpression() throws RecognitionException {
		ParenthesizedExpressionContext _localctx = new ParenthesizedExpressionContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_parenthesizedExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1286);
			match(T__2);
			setState(1288);
			switch ( getInterpreter().adaptivePredict(_input,184,_ctx) ) {
			case 1:
				{
				setState(1287);
				sp();
				}
				break;
			}
			setState(1290);
			((ParenthesizedExpressionContext)_localctx).e = expression();
			setState(1292);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(1291);
				sp();
				}
			}

			setState(1294);
			match(T__3);
			 ((ParenthesizedExpressionContext)_localctx).res =  ((ParenthesizedExpressionContext)_localctx).e.res; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionInvocationContext extends ParserRuleContext {
		public FunctionInvocation res;
		public FunctionNameContext fn;
		public Token DISTINCT;
		public ExpressionContext expression;
		public List<ExpressionContext> args = new ArrayList<ExpressionContext>();
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFunctionInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFunctionInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitFunctionInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionInvocationContext functionInvocation() throws RecognitionException {
		FunctionInvocationContext _localctx = new FunctionInvocationContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_functionInvocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1297);
			((FunctionInvocationContext)_localctx).fn = functionName();
			setState(1299);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(1298);
				sp();
				}
			}

			setState(1301);
			match(T__2);
			setState(1303);
			switch ( getInterpreter().adaptivePredict(_input,187,_ctx) ) {
			case 1:
				{
				setState(1302);
				sp();
				}
				break;
			}
			setState(1306);
			switch ( getInterpreter().adaptivePredict(_input,188,_ctx) ) {
			case 1:
				{
				setState(1305);
				((FunctionInvocationContext)_localctx).DISTINCT = match(DISTINCT);
				}
				break;
			}
			setState(1309);
			switch ( getInterpreter().adaptivePredict(_input,189,_ctx) ) {
			case 1:
				{
				setState(1308);
				sp();
				}
				break;
			}
			setState(1322);
			switch ( getInterpreter().adaptivePredict(_input,192,_ctx) ) {
			case 1:
				{
				setState(1311);
				((FunctionInvocationContext)_localctx).expression = expression();
				((FunctionInvocationContext)_localctx).args.add(((FunctionInvocationContext)_localctx).expression);
				setState(1319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(1312);
					match(T__1);
					setState(1314);
					switch ( getInterpreter().adaptivePredict(_input,190,_ctx) ) {
					case 1:
						{
						setState(1313);
						sp();
						}
						break;
					}
					setState(1316);
					((FunctionInvocationContext)_localctx).expression = expression();
					((FunctionInvocationContext)_localctx).args.add(((FunctionInvocationContext)_localctx).expression);
					}
					}
					setState(1321);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(1325);
			_la = _input.LA(1);
			if (_la==WHITESPACE) {
				{
				setState(1324);
				sp();
				}
			}

			setState(1327);
			match(T__3);

			        ((FunctionInvocationContext)_localctx).res =  new FunctionInvocation(((FunctionInvocationContext)_localctx).fn.res, ((FunctionInvocationContext)_localctx).DISTINCT != null,
			            ((FunctionInvocationContext)_localctx).args.stream().map(arg -> arg.res).collect(Collectors.toList())
			        );
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionNameContext extends ParserRuleContext {
		public FunctionName res;
		public Token u;
		public Token e;
		public TerminalNode UnescapedSymbolicName() { return getToken(CypherParser.UnescapedSymbolicName, 0); }
		public TerminalNode EscapedSymbolicName() { return getToken(CypherParser.EscapedSymbolicName, 0); }
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_functionName);
		try {
			setState(1336);
			switch (_input.LA(1)) {
			case UnescapedSymbolicName:
				enterOuterAlt(_localctx, 1);
				{
				setState(1330);
				((FunctionNameContext)_localctx).u = match(UnescapedSymbolicName);
				((FunctionNameContext)_localctx).res =  new FunctionName((((FunctionNameContext)_localctx).u!=null?((FunctionNameContext)_localctx).u.getText():null));
				}
				break;
			case EscapedSymbolicName:
				enterOuterAlt(_localctx, 2);
				{
				setState(1332);
				((FunctionNameContext)_localctx).e = match(EscapedSymbolicName);
				((FunctionNameContext)_localctx).res =  new FunctionName((((FunctionNameContext)_localctx).e!=null?((FunctionNameContext)_localctx).e.getText():null));
				}
				break;
			case COUNT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1334);
				match(COUNT);
				((FunctionNameContext)_localctx).res =  new FunctionName("COUNT");
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShortestPathPatternFunctionContext extends ParserRuleContext {
		public ShortestPathFunc res;
		public PatternElementContext e;
		public TerminalNode SHORTESTPATH() { return getToken(CypherParser.SHORTESTPATH, 0); }
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode ALLSHORTESTPATHS() { return getToken(CypherParser.ALLSHORTESTPATHS, 0); }
		public ShortestPathPatternFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortestPathPatternFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterShortestPathPatternFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitShortestPathPatternFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitShortestPathPatternFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortestPathPatternFunctionContext shortestPathPatternFunction() throws RecognitionException {
		ShortestPathPatternFunctionContext _localctx = new ShortestPathPatternFunctionContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_shortestPathPatternFunction);
		int _la;
		try {
			setState(1368);
			switch (_input.LA(1)) {
			case SHORTESTPATH:
				enterOuterAlt(_localctx, 1);
				{
				setState(1338);
				match(SHORTESTPATH);
				setState(1340);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1339);
					sp();
					}
				}

				setState(1342);
				match(T__2);
				setState(1344);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1343);
					sp();
					}
				}

				setState(1346);
				((ShortestPathPatternFunctionContext)_localctx).e = patternElement();
				setState(1348);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1347);
					sp();
					}
				}

				setState(1350);
				match(T__3);

				                                ((ShortestPathPatternFunctionContext)_localctx).res = new ShortestPathFunc(((ShortestPathPatternFunctionContext)_localctx).e.res);
				                            
				}
				break;
			case ALLSHORTESTPATHS:
				enterOuterAlt(_localctx, 2);
				{
				setState(1353);
				match(ALLSHORTESTPATHS);
				setState(1355);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1354);
					sp();
					}
				}

				setState(1357);
				match(T__2);
				setState(1359);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1358);
					sp();
					}
				}

				setState(1361);
				((ShortestPathPatternFunctionContext)_localctx).e = patternElement();
				setState(1363);
				_la = _input.LA(1);
				if (_la==WHITESPACE) {
					{
					setState(1362);
					sp();
					}
				}

				setState(1365);
				match(T__3);

				                                ((ShortestPathPatternFunctionContext)_localctx).res = new ShortestPathFunc(((ShortestPathPatternFunctionContext)_localctx).e.res);
				                            
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyKeyNameContext extends ParserRuleContext {
		public PropertyKeyName res;
		public SymbolicNameContext s;
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public PropertyKeyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyKeyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyKeyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyKeyName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPropertyKeyName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyKeyNameContext propertyKeyName() throws RecognitionException {
		PropertyKeyNameContext _localctx = new PropertyKeyNameContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_propertyKeyName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1370);
			((PropertyKeyNameContext)_localctx).s = symbolicName();

			    ((PropertyKeyNameContext)_localctx).res =  new PropertyKeyName(((PropertyKeyNameContext)_localctx).s.res);
			    _localctx.res.span = makeSpan((((PropertyKeyNameContext)_localctx).s!=null?(((PropertyKeyNameContext)_localctx).s.start):null), (((PropertyKeyNameContext)_localctx).s!=null?(((PropertyKeyNameContext)_localctx).s.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberLitContext extends ParserRuleContext {
		public Literal.Number res;
		public DoubleLitContext d;
		public IntegerLitContext i;
		public DoubleLitContext doubleLit() {
			return getRuleContext(DoubleLitContext.class,0);
		}
		public IntegerLitContext integerLit() {
			return getRuleContext(IntegerLitContext.class,0);
		}
		public NumberLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNumberLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNumberLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNumberLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberLitContext numberLit() throws RecognitionException {
		NumberLitContext _localctx = new NumberLitContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_numberLit);
		try {
			setState(1379);
			switch (_input.LA(1)) {
			case T__16:
			case DigitString:
				enterOuterAlt(_localctx, 1);
				{
				setState(1373);
				((NumberLitContext)_localctx).d = doubleLit();
				 ((NumberLitContext)_localctx).res =  ((NumberLitContext)_localctx).d.res; 
				}
				break;
			case HexInteger:
			case DecimalInteger:
			case OctalInteger:
				enterOuterAlt(_localctx, 2);
				{
				setState(1376);
				((NumberLitContext)_localctx).i = integerLit();
				 ((NumberLitContext)_localctx).res =  ((NumberLitContext)_localctx).i.res; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLitContext extends ParserRuleContext {
		public Literal.Integer res;
		public IntegerLit_nospanContext i;
		public IntegerLit_nospanContext integerLit_nospan() {
			return getRuleContext(IntegerLit_nospanContext.class,0);
		}
		public IntegerLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIntegerLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIntegerLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIntegerLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLitContext integerLit() throws RecognitionException {
		IntegerLitContext _localctx = new IntegerLitContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_integerLit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1381);
			((IntegerLitContext)_localctx).i = integerLit_nospan();

			    ((IntegerLitContext)_localctx).res =  ((IntegerLitContext)_localctx).i.res;
			    _localctx.res.span = makeSpan((((IntegerLitContext)_localctx).i!=null?(((IntegerLitContext)_localctx).i.start):null), (((IntegerLitContext)_localctx).i!=null?(((IntegerLitContext)_localctx).i.stop):null));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLit_nospanContext extends ParserRuleContext {
		public Literal.Integer res;
		public Token i;
		public TerminalNode HexInteger() { return getToken(CypherParser.HexInteger, 0); }
		public TerminalNode OctalInteger() { return getToken(CypherParser.OctalInteger, 0); }
		public TerminalNode DecimalInteger() { return getToken(CypherParser.DecimalInteger, 0); }
		public IntegerLit_nospanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLit_nospan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIntegerLit_nospan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIntegerLit_nospan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIntegerLit_nospan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLit_nospanContext integerLit_nospan() throws RecognitionException {
		IntegerLit_nospanContext _localctx = new IntegerLit_nospanContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_integerLit_nospan);
		try {
			setState(1390);
			switch (_input.LA(1)) {
			case HexInteger:
				enterOuterAlt(_localctx, 1);
				{
				setState(1384);
				((IntegerLit_nospanContext)_localctx).i = match(HexInteger);
				 ((IntegerLit_nospanContext)_localctx).res =  new Literal.HexInteger((((IntegerLit_nospanContext)_localctx).i!=null?((IntegerLit_nospanContext)_localctx).i.getText():null)); 
				}
				break;
			case OctalInteger:
				enterOuterAlt(_localctx, 2);
				{
				setState(1386);
				((IntegerLit_nospanContext)_localctx).i = match(OctalInteger);
				 ((IntegerLit_nospanContext)_localctx).res =  new Literal.OctalInteger((((IntegerLit_nospanContext)_localctx).i!=null?((IntegerLit_nospanContext)_localctx).i.getText():null)); 
				}
				break;
			case DecimalInteger:
				enterOuterAlt(_localctx, 3);
				{
				setState(1388);
				((IntegerLit_nospanContext)_localctx).i = match(DecimalInteger);
				 ((IntegerLit_nospanContext)_localctx).res =  new Literal.UnsignedInteger((((IntegerLit_nospanContext)_localctx).i!=null?((IntegerLit_nospanContext)_localctx).i.getText():null)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleLitContext extends ParserRuleContext {
		public Literal.DecimalDouble res;
		public RealContext e;
		public RealContext real() {
			return getRuleContext(RealContext.class,0);
		}
		public DoubleLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDoubleLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDoubleLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDoubleLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleLitContext doubleLit() throws RecognitionException {
		DoubleLitContext _localctx = new DoubleLitContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_doubleLit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1392);
			((DoubleLitContext)_localctx).e = real();
			 ((DoubleLitContext)_localctx).res =  new Literal.DecimalDouble((((DoubleLitContext)_localctx).e!=null?_input.getText(((DoubleLitContext)_localctx).e.start,((DoubleLitContext)_localctx).e.stop):null)); _localctx.res.span = makeSpan((((DoubleLitContext)_localctx).e!=null?(((DoubleLitContext)_localctx).e.start):null), (((DoubleLitContext)_localctx).e!=null?(((DoubleLitContext)_localctx).e.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RealContext extends ParserRuleContext {
		public TerminalNode DigitString() { return getToken(CypherParser.DigitString, 0); }
		public List<TerminalNode> Digit() { return getTokens(CypherParser.Digit); }
		public TerminalNode Digit(int i) {
			return getToken(CypherParser.Digit, i);
		}
		public TerminalNode Exponent() { return getToken(CypherParser.Exponent, 0); }
		public RealContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_real; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RealContext real() throws RecognitionException {
		RealContext _localctx = new RealContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_real);
		int _la;
		try {
			setState(1415);
			switch ( getInterpreter().adaptivePredict(_input,208,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1395);
				match(DigitString);
				setState(1396);
				match(T__16);
				setState(1400);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Digit) {
					{
					{
					setState(1397);
					match(Digit);
					}
					}
					setState(1402);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1404);
				_la = _input.LA(1);
				if (_la==Exponent) {
					{
					setState(1403);
					match(Exponent);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1406);
				match(T__16);
				setState(1407);
				match(DigitString);
				setState(1409);
				_la = _input.LA(1);
				if (_la==Exponent) {
					{
					setState(1408);
					match(Exponent);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1411);
				match(DigitString);
				setState(1413);
				_la = _input.LA(1);
				if (_la==Exponent) {
					{
					setState(1412);
					match(Exponent);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolicNameContext extends ParserRuleContext {
		public String res;
		public SymbolsContext s;
		public SymbolsContext symbols() {
			return getRuleContext(SymbolsContext.class,0);
		}
		public SymbolicNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolicName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSymbolicName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSymbolicName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSymbolicName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolicNameContext symbolicName() throws RecognitionException {
		SymbolicNameContext _localctx = new SymbolicNameContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_symbolicName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1417);
			((SymbolicNameContext)_localctx).s = symbols();
			 ((SymbolicNameContext)_localctx).res =  (((SymbolicNameContext)_localctx).s!=null?_input.getText(((SymbolicNameContext)_localctx).s.start,((SymbolicNameContext)_localctx).s.stop):null); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolsContext extends ParserRuleContext {
		public TerminalNode UnescapedSymbolicName() { return getToken(CypherParser.UnescapedSymbolicName, 0); }
		public TerminalNode EscapedSymbolicName() { return getToken(CypherParser.EscapedSymbolicName, 0); }
		public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(CypherParser.BY, 0); }
		public TerminalNode SKIP_() { return getToken(CypherParser.SKIP_, 0); }
		public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }
		public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }
		public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }
		public TerminalNode OR() { return getToken(CypherParser.OR, 0); }
		public TerminalNode XOR() { return getToken(CypherParser.XOR, 0); }
		public TerminalNode AND() { return getToken(CypherParser.AND, 0); }
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public TerminalNode STARTS() { return getToken(CypherParser.STARTS, 0); }
		public TerminalNode ENDS() { return getToken(CypherParser.ENDS, 0); }
		public TerminalNode CONTAINS() { return getToken(CypherParser.CONTAINS, 0); }
		public TerminalNode IS() { return getToken(CypherParser.IS, 0); }
		public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public TerminalNode FILTER() { return getToken(CypherParser.FILTER, 0); }
		public TerminalNode EXTRACT() { return getToken(CypherParser.EXTRACT, 0); }
		public SymbolsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbols; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSymbols(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSymbols(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSymbols(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolsContext symbols() throws RecognitionException {
		SymbolsContext _localctx = new SymbolsContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_symbols);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1420);
			_la = _input.LA(1);
			if ( !(((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & ((1L << (UNION - 25)) | (1L << (ALL - 25)) | (1L << (OPTIONAL - 25)) | (1L << (MATCH - 25)) | (1L << (WHERE - 25)) | (1L << (AS - 25)) | (1L << (WITH - 25)) | (1L << (DISTINCT - 25)) | (1L << (RETURN - 25)) | (1L << (ORDER - 25)) | (1L << (BY - 25)) | (1L << (SKIP_ - 25)) | (1L << (LIMIT - 25)) | (1L << (DESC - 25)) | (1L << (ASC - 25)) | (1L << (OR - 25)) | (1L << (XOR - 25)) | (1L << (AND - 25)) | (1L << (NOT - 25)) | (1L << (IN - 25)) | (1L << (STARTS - 25)) | (1L << (ENDS - 25)) | (1L << (CONTAINS - 25)) | (1L << (IS - 25)) | (1L << (NULL - 25)) | (1L << (TRUE - 25)) | (1L << (FALSE - 25)) | (1L << (COUNT - 25)) | (1L << (FILTER - 25)) | (1L << (EXTRACT - 25)) | (1L << (UnescapedSymbolicName - 25)) | (1L << (EscapedSymbolicName - 25)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(CypherParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CypherParser.WHITESPACE, i);
		}
		public SpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpContext sp() throws RecognitionException {
		SpContext _localctx = new SpContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_sp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1423); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1422);
					match(WHITESPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1425); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,209,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3T\u0596\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\5\2\u00a6"+
		"\n\2\3\2\3\2\5\2\u00aa\n\2\3\2\5\2\u00ad\n\2\3\2\5\2\u00b0\n\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\5\5\u00bd\n\5\3\5\7\5\u00c0\n\5\f"+
		"\5\16\5\u00c3\13\5\3\5\3\5\3\6\3\6\5\6\u00c9\n\6\3\6\7\6\u00cc\n\6\f\6"+
		"\16\6\u00cf\13\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7\u00d7\n\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7\u00df\n\7\3\7\3\7\3\7\3\7\5\7\u00e5\n\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00f3\n\b\3\t\3\t\3\t\5\t\u00f8\n\t"+
		"\3\t\3\t\5\t\u00fc\n\t\3\t\3\t\5\t\u0100\n\t\3\t\3\t\3\t\5\t\u0105\n\t"+
		"\3\t\3\t\3\n\3\n\5\n\u010b\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\5\13\u0119\n\13\3\13\3\13\5\13\u011d\n\13\3\13\3\13\3\13"+
		"\5\13\u0122\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\f\u012b\n\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\5\17\u013e\n\17\3\17\3\17\3\17\3\17\5\17\u0144\n\17\3\17\3\17\3\17\3"+
		"\17\5\17\u014a\n\17\3\20\3\20\5\20\u014e\n\20\3\20\3\20\5\20\u0152\n\20"+
		"\3\20\7\20\u0155\n\20\f\20\16\20\u0158\13\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0169\n\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\5\22\u0171\n\22\3\22\3\22\5\22\u0175\n\22\3"+
		"\22\7\22\u0178\n\22\f\22\16\22\u017b\13\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\5\25\u018b\n\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\5\25\u0193\n\25\3\25\5\25\u0196\n\25\3\25\3\25\5"+
		"\25\u019a\n\25\3\26\3\26\5\26\u019e\n\26\3\26\3\26\5\26\u01a2\n\26\3\26"+
		"\7\26\u01a5\n\26\f\26\16\26\u01a8\13\26\3\26\3\26\3\27\3\27\5\27\u01ae"+
		"\n\27\3\27\3\27\5\27\u01b2\n\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u01bf\n\27\3\27\3\27\5\27\u01c3\n\27\3\27\3\27\3"+
		"\27\5\27\u01c8\n\27\3\30\3\30\5\30\u01cc\n\30\3\30\7\30\u01cf\n\30\f\30"+
		"\16\30\u01d2\13\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u01dc"+
		"\n\30\3\31\3\31\5\31\u01e0\n\31\3\31\3\31\5\31\u01e4\n\31\3\31\3\31\5"+
		"\31\u01e8\n\31\3\31\3\31\5\31\u01ec\n\31\3\31\3\31\5\31\u01f0\n\31\3\31"+
		"\3\31\5\31\u01f4\n\31\3\31\3\31\5\31\u01f8\n\31\3\31\3\31\3\31\3\32\3"+
		"\32\5\32\u01ff\n\32\3\32\7\32\u0202\n\32\f\32\16\32\u0205\13\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\34\3\34\5\34\u020f\n\34\3\34\3\34\3\34\3\35"+
		"\3\35\5\35\u0216\n\35\3\35\3\35\5\35\u021a\n\35\3\35\3\35\3\35\5\35\u021f"+
		"\n\35\5\35\u0221\n\35\3\35\3\35\5\35\u0225\n\35\3\35\3\35\3\35\3\35\3"+
		"\35\5\35\u022c\n\35\3\35\3\35\5\35\u0230\n\35\3\35\3\35\3\35\5\35\u0235"+
		"\n\35\5\35\u0237\n\35\3\35\3\35\3\35\3\35\3\35\5\35\u023e\n\35\3\35\3"+
		"\35\3\35\5\35\u0243\n\35\5\35\u0245\n\35\3\35\3\35\5\35\u0249\n\35\3\35"+
		"\3\35\3\35\3\35\3\35\5\35\u0250\n\35\3\35\3\35\3\35\5\35\u0255\n\35\5"+
		"\35\u0257\n\35\3\35\3\35\3\35\5\35\u025c\n\35\3\36\3\36\5\36\u0260\n\36"+
		"\3\36\3\36\3\36\5\36\u0265\n\36\3\36\5\36\u0268\n\36\3\36\3\36\3\36\5"+
		"\36\u026d\n\36\3\36\5\36\u0270\n\36\3\36\3\36\3\36\3\36\5\36\u0276\n\36"+
		"\3\36\5\36\u0279\n\36\3\36\3\36\3\36\5\36\u027e\n\36\3\36\5\36\u0281\n"+
		"\36\3\36\3\36\3\36\3\37\5\37\u0287\n\37\3\37\3\37\5\37\u028b\n\37\3\37"+
		"\3\37\5\37\u028f\n\37\3\37\3\37\5\37\u0293\n\37\3\37\3\37\5\37\u0297\n"+
		"\37\3\37\3\37\5\37\u029b\n\37\5\37\u029d\n\37\3 \3 \3 \3!\3!\3!\5!\u02a5"+
		"\n!\3!\3!\5!\u02a9\n!\3!\5!\u02ac\n!\3!\7!\u02af\n!\f!\16!\u02b2\13!\3"+
		"!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\5%\u02c1\n%\3%\6%\u02c4\n%\r"+
		"%\16%\u02c5\3%\3%\3&\3&\3&\3\'\3\'\5\'\u02cf\n\'\3\'\3\'\3\'\5\'\u02d4"+
		"\n\'\3\'\7\'\u02d7\n\'\f\'\16\'\u02da\13\'\5\'\u02dc\n\'\3\'\3\'\3\'\3"+
		"(\3(\5(\u02e3\n(\3(\3(\5(\u02e7\n(\3(\3(\5(\u02eb\n(\3(\3(\3)\3)\3)\3"+
		"*\3*\5*\u02f4\n*\3*\3*\3*\5*\u02f9\n*\3*\7*\u02fc\n*\f*\16*\u02ff\13*"+
		"\5*\u0301\n*\3*\3*\3*\3+\3+\3+\3,\3,\3,\3,\3,\3,\7,\u030f\n,\f,\16,\u0312"+
		"\13,\3,\3,\3-\3-\3-\3-\3-\3-\7-\u031c\n-\f-\16-\u031f\13-\3-\3-\3.\3."+
		"\3.\3.\3.\3.\7.\u0329\n.\f.\16.\u032c\13.\3.\3.\3/\5/\u0331\n/\3/\3/\5"+
		"/\u0335\n/\7/\u0337\n/\f/\16/\u033a\13/\3/\3/\3/\3\60\3\60\5\60\u0341"+
		"\n\60\3\60\7\60\u0344\n\60\f\60\16\60\u0347\13\60\3\60\3\60\3\61\3\61"+
		"\5\61\u034d\n\61\3\61\7\61\u0350\n\61\f\61\16\61\u0353\13\61\3\61\3\61"+
		"\3\62\3\62\5\62\u0359\n\62\3\62\7\62\u035c\n\62\f\62\16\62\u035f\13\62"+
		"\3\62\3\62\3\63\3\63\5\63\u0365\n\63\3\63\7\63\u0368\n\63\f\63\16\63\u036b"+
		"\13\63\3\63\3\63\3\64\3\64\5\64\u0371\n\64\7\64\u0373\n\64\f\64\16\64"+
		"\u0376\13\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\5\65\u037f\n\65\3\66\3"+
		"\66\5\66\u0383\n\66\3\66\7\66\u0386\n\66\f\66\16\66\u0389\13\66\3\66\3"+
		"\66\3\67\3\67\7\67\u038f\n\67\f\67\16\67\u0392\13\67\3\67\3\67\3\67\3"+
		"\67\7\67\u0398\n\67\f\67\16\67\u039b\13\67\3\67\3\67\5\67\u039f\n\67\3"+
		"8\38\38\39\39\39\3:\3:\3:\3:\3:\3:\3:\5:\u03ae\n:\3:\3:\5:\u03b2\n:\3"+
		":\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\5:\u03c7\n:\3"+
		";\5;\u03ca\n;\3;\3;\5;\u03ce\n;\3;\3;\3;\3;\3;\3;\5;\u03d6\n;\3;\3;\3"+
		";\3;\3;\3;\3;\3;\5;\u03e0\n;\3;\3;\3;\3;\3;\3;\3;\3;\5;\u03ea\n;\3;\3"+
		";\3;\3;\3;\3;\5;\u03f2\n;\3;\3;\3;\5;\u03f7\n;\3<\3<\5<\u03fb\n<\3<\3"+
		"<\5<\u03ff\n<\3<\3<\5<\u0403\n<\3<\3<\3<\3<\3<\3<\5<\u040b\n<\3<\3<\5"+
		"<\u040f\n<\3<\3<\5<\u0413\n<\3<\3<\3<\5<\u0418\n<\3=\3=\3>\5>\u041d\n"+
		">\3>\3>\5>\u0421\n>\3>\3>\3>\3?\3?\5?\u0428\n?\3?\3?\3?\3?\3?\5?\u042f"+
		"\n?\3?\3?\3?\5?\u0434\n?\3@\3@\5@\u0438\n@\3@\3@\3@\3@\3@\5@\u043f\n@"+
		"\3@\3@\3@\3@\3@\5@\u0446\n@\3@\3@\3@\5@\u044b\n@\3A\3A\5A\u044f\nA\3A"+
		"\3A\3A\3B\3B\5B\u0456\nB\3B\3B\3B\3B\3B\3B\5B\u045e\nB\3B\3B\3B\3B\3B"+
		"\3B\5B\u0466\nB\3B\3B\3B\3B\3B\3B\5B\u046e\nB\3B\3B\3B\3B\3B\3B\5B\u0476"+
		"\nB\3B\3B\3B\3B\3B\3B\5B\u047e\nB\3B\3B\3B\3B\3B\3B\5B\u0486\nB\3B\3B"+
		"\3B\3B\5B\u048c\nB\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\5C\u049b\nC"+
		"\3C\3C\5C\u049f\nC\3C\3C\5C\u04a3\nC\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C"+
		"\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\5C\u04c0\nC\3D\3D\5D"+
		"\u04c4\nD\3D\6D\u04c7\nD\rD\16D\u04c8\3D\5D\u04cc\nD\3D\3D\5D\u04d0\n"+
		"D\3D\5D\u04d3\nD\3D\5D\u04d6\nD\3D\3D\3D\3D\3D\5D\u04dd\nD\3D\3D\5D\u04e1"+
		"\nD\3D\6D\u04e4\nD\rD\16D\u04e5\3D\5D\u04e9\nD\3D\3D\5D\u04ed\nD\3D\5"+
		"D\u04f0\nD\3D\5D\u04f3\nD\3D\3D\3D\5D\u04f8\nD\3E\3E\5E\u04fc\nE\3E\3"+
		"E\5E\u0500\nE\3E\3E\5E\u0504\nE\3E\3E\3E\3F\3F\5F\u050b\nF\3F\3F\5F\u050f"+
		"\nF\3F\3F\3F\3G\3G\5G\u0516\nG\3G\3G\5G\u051a\nG\3G\5G\u051d\nG\3G\5G"+
		"\u0520\nG\3G\3G\3G\5G\u0525\nG\3G\7G\u0528\nG\fG\16G\u052b\13G\5G\u052d"+
		"\nG\3G\5G\u0530\nG\3G\3G\3G\3H\3H\3H\3H\3H\3H\5H\u053b\nH\3I\3I\5I\u053f"+
		"\nI\3I\3I\5I\u0543\nI\3I\3I\5I\u0547\nI\3I\3I\3I\3I\3I\5I\u054e\nI\3I"+
		"\3I\5I\u0552\nI\3I\3I\5I\u0556\nI\3I\3I\3I\5I\u055b\nI\3J\3J\3J\3K\3K"+
		"\3K\3K\3K\3K\5K\u0566\nK\3L\3L\3L\3M\3M\3M\3M\3M\3M\5M\u0571\nM\3N\3N"+
		"\3N\3O\3O\3O\7O\u0579\nO\fO\16O\u057c\13O\3O\5O\u057f\nO\3O\3O\3O\5O\u0584"+
		"\nO\3O\3O\5O\u0588\nO\5O\u058a\nO\3P\3P\3P\3Q\3Q\3R\6R\u0592\nR\rR\16"+
		"R\u0593\3R\2\2S\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0"+
		"\u00a2\2\3\t\2\33\37!\"$)+\63\659>@EF\u0638\2\u00a5\3\2\2\2\4\u00b4\3"+
		"\2\2\2\6\u00b7\3\2\2\2\b\u00ba\3\2\2\2\n\u00c6\3\2\2\2\f\u00e4\3\2\2\2"+
		"\16\u00f2\3\2\2\2\20\u00f7\3\2\2\2\22\u0108\3\2\2\2\24\u0113\3\2\2\2\26"+
		"\u0125\3\2\2\2\30\u012f\3\2\2\2\32\u0132\3\2\2\2\34\u0137\3\2\2\2\36\u014b"+
		"\3\2\2\2 \u0168\3\2\2\2\"\u016a\3\2\2\2$\u017e\3\2\2\2&\u0183\3\2\2\2"+
		"(\u0199\3\2\2\2*\u019b\3\2\2\2,\u01c7\3\2\2\2.\u01db\3\2\2\2\60\u01dd"+
		"\3\2\2\2\62\u01fc\3\2\2\2\64\u0208\3\2\2\2\66\u020c\3\2\2\28\u025b\3\2"+
		"\2\2:\u025d\3\2\2\2<\u0286\3\2\2\2>\u029e\3\2\2\2@\u02a1\3\2\2\2B\u02b5"+
		"\3\2\2\2D\u02b8\3\2\2\2F\u02bb\3\2\2\2H\u02be\3\2\2\2J\u02c9\3\2\2\2L"+
		"\u02cc\3\2\2\2N\u02e0\3\2\2\2P\u02ee\3\2\2\2R\u02f1\3\2\2\2T\u0305\3\2"+
		"\2\2V\u0308\3\2\2\2X\u0315\3\2\2\2Z\u0322\3\2\2\2\\\u0338\3\2\2\2^\u033e"+
		"\3\2\2\2`\u034a\3\2\2\2b\u0356\3\2\2\2d\u0362\3\2\2\2f\u0374\3\2\2\2h"+
		"\u037e\3\2\2\2j\u0380\3\2\2\2l\u039e\3\2\2\2n\u03a0\3\2\2\2p\u03a3\3\2"+
		"\2\2r\u03c6\3\2\2\2t\u03f6\3\2\2\2v\u0417\3\2\2\2x\u0419\3\2\2\2z\u041c"+
		"\3\2\2\2|\u0433\3\2\2\2~\u044a\3\2\2\2\u0080\u044c\3\2\2\2\u0082\u048b"+
		"\3\2\2\2\u0084\u04bf\3\2\2\2\u0086\u04f7\3\2\2\2\u0088\u04f9\3\2\2\2\u008a"+
		"\u0508\3\2\2\2\u008c\u0513\3\2\2\2\u008e\u053a\3\2\2\2\u0090\u055a\3\2"+
		"\2\2\u0092\u055c\3\2\2\2\u0094\u0565\3\2\2\2\u0096\u0567\3\2\2\2\u0098"+
		"\u0570\3\2\2\2\u009a\u0572\3\2\2\2\u009c\u0589\3\2\2\2\u009e\u058b\3\2"+
		"\2\2\u00a0\u058e\3\2\2\2\u00a2\u0591\3\2\2\2\u00a4\u00a6\5\u00a2R\2\u00a5"+
		"\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\5\4"+
		"\3\2\u00a8\u00aa\5\u00a2R\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00af\3\2\2\2\u00ab\u00ad\5\u00a2R\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad"+
		"\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\7\3\2\2\u00af\u00ac\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\2\2\3\u00b2\u00b3\b\2"+
		"\1\2\u00b3\3\3\2\2\2\u00b4\u00b5\5\6\4\2\u00b5\u00b6\b\3\1\2\u00b6\5\3"+
		"\2\2\2\u00b7\u00b8\5\b\5\2\u00b8\u00b9\b\4\1\2\u00b9\7\3\2\2\2\u00ba\u00c1"+
		"\5\n\6\2\u00bb\u00bd\5\u00a2R\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2"+
		"\2\u00bd\u00be\3\2\2\2\u00be\u00c0\5\f\7\2\u00bf\u00bc\3\2\2\2\u00c0\u00c3"+
		"\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c4\u00c5\b\5\1\2\u00c5\t\3\2\2\2\u00c6\u00cd\5\16\b"+
		"\2\u00c7\u00c9\5\u00a2R\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\u00cc\5\16\b\2\u00cb\u00c8\3\2\2\2\u00cc\u00cf\3"+
		"\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00d0\u00d1\b\6\1\2\u00d1\13\3\2\2\2\u00d2\u00d3\7\33\2"+
		"\2\u00d3\u00d4\5\u00a2R\2\u00d4\u00d6\7\34\2\2\u00d5\u00d7\5\u00a2R\2"+
		"\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9"+
		"\5\n\6\2\u00d9\u00da\3\2\2\2\u00da\u00db\b\7\1\2\u00db\u00e5\3\2\2\2\u00dc"+
		"\u00de\7\33\2\2\u00dd\u00df\5\u00a2R\2\u00de\u00dd\3\2\2\2\u00de\u00df"+
		"\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\5\n\6\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e3\b\7\1\2\u00e3\u00e5\3\2\2\2\u00e4\u00d2\3\2\2\2\u00e4\u00dc\3\2"+
		"\2\2\u00e5\r\3\2\2\2\u00e6\u00e7\5\20\t\2\u00e7\u00e8\b\b\1\2\u00e8\u00f3"+
		"\3\2\2\2\u00e9\u00ea\5\24\13\2\u00ea\u00eb\b\b\1\2\u00eb\u00f3\3\2\2\2"+
		"\u00ec\u00ed\5\26\f\2\u00ed\u00ee\b\b\1\2\u00ee\u00f3\3\2\2\2\u00ef\u00f0"+
		"\5\22\n\2\u00f0\u00f1\b\b\1\2\u00f1\u00f3\3\2\2\2\u00f2\u00e6\3\2\2\2"+
		"\u00f2\u00e9\3\2\2\2\u00f2\u00ec\3\2\2\2\u00f2\u00ef\3\2\2\2\u00f3\17"+
		"\3\2\2\2\u00f4\u00f5\7\35\2\2\u00f5\u00f6\b\t\1\2\u00f6\u00f8\5\u00a2"+
		"R\2\u00f7\u00f4\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fb\7\36\2\2\u00fa\u00fc\5\u00a2R\2\u00fb\u00fa\3\2\2\2\u00fb\u00fc"+
		"\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u0104\5*\26\2\u00fe\u0100\5\u00a2R"+
		"\2\u00ff\u00fe\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102"+
		"\5\30\r\2\u0102\u0103\b\t\1\2\u0103\u0105\3\2\2\2\u0104\u00ff\3\2\2\2"+
		"\u0104\u0105\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\b\t\1\2\u0107\21"+
		"\3\2\2\2\u0108\u010a\7#\2\2\u0109\u010b\5\u00a2R\2\u010a\u0109\3\2\2\2"+
		"\u010a\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\5T+\2\u010d\u010e"+
		"\5\u00a2R\2\u010e\u010f\7!\2\2\u010f\u0110\5\u00a2R\2\u0110\u0111\5F$"+
		"\2\u0111\u0112\b\n\1\2\u0112\23\3\2\2\2\u0113\u0114\7\"\2\2\u0114\u0118"+
		"\5\u00a2R\2\u0115\u0116\7$\2\2\u0116\u0117\b\13\1\2\u0117\u0119\5\u00a2"+
		"R\2\u0118\u0115\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\u0121\5\34\17\2\u011b\u011d\5\u00a2R\2\u011c\u011b\3\2\2\2\u011c\u011d"+
		"\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\5\30\r\2\u011f\u0120\b\13\1\2"+
		"\u0120\u0122\3\2\2\2\u0121\u011c\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\u0124\b\13\1\2\u0124\25\3\2\2\2\u0125\u0126\7%\2\2\u0126"+
		"\u012a\5\u00a2R\2\u0127\u0128\7$\2\2\u0128\u0129\b\f\1\2\u0129\u012b\5"+
		"\u00a2R\2\u012a\u0127\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2"+
		"\u012c\u012d\5\34\17\2\u012d\u012e\b\f\1\2\u012e\27\3\2\2\2\u012f\u0130"+
		"\5\32\16\2\u0130\u0131\b\r\1\2\u0131\31\3\2\2\2\u0132\u0133\7\37\2\2\u0133"+
		"\u0134\5\u00a2R\2\u0134\u0135\5T+\2\u0135\u0136\b\16\1\2\u0136\33\3\2"+
		"\2\2\u0137\u0138\5\36\20\2\u0138\u013d\b\17\1\2\u0139\u013a\5\u00a2R\2"+
		"\u013a\u013b\5\"\22\2\u013b\u013c\b\17\1\2\u013c\u013e\3\2\2\2\u013d\u0139"+
		"\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u0143\3\2\2\2\u013f\u0140\5\u00a2R"+
		"\2\u0140\u0141\5$\23\2\u0141\u0142\b\17\1\2\u0142\u0144\3\2\2\2\u0143"+
		"\u013f\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0149\3\2\2\2\u0145\u0146\5\u00a2"+
		"R\2\u0146\u0147\5&\24\2\u0147\u0148\b\17\1\2\u0148\u014a\3\2\2\2\u0149"+
		"\u0145\3\2\2\2\u0149\u014a\3\2\2\2\u014a\35\3\2\2\2\u014b\u0156\5 \21"+
		"\2\u014c\u014e\5\u00a2R\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e"+
		"\u014f\3\2\2\2\u014f\u0151\7\4\2\2\u0150\u0152\5\u00a2R\2\u0151\u0150"+
		"\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0155\5 \21\2\u0154"+
		"\u014d\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2"+
		"\2\2\u0157\u0159\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u015a\b\20\1\2\u015a"+
		"\37\3\2\2\2\u015b\u015c\5T+\2\u015c\u015d\5\u00a2R\2\u015d\u015e\7!\2"+
		"\2\u015e\u015f\5\u00a2R\2\u015f\u0160\5F$\2\u0160\u0161\3\2\2\2\u0161"+
		"\u0162\b\21\1\2\u0162\u0169\3\2\2\2\u0163\u0164\5T+\2\u0164\u0165\b\21"+
		"\1\2\u0165\u0169\3\2\2\2\u0166\u0167\7P\2\2\u0167\u0169\b\21\1\2\u0168"+
		"\u015b\3\2\2\2\u0168\u0163\3\2\2\2\u0168\u0166\3\2\2\2\u0169!\3\2\2\2"+
		"\u016a\u016b\7&\2\2\u016b\u016c\5\u00a2R\2\u016c\u016d\7\'\2\2\u016d\u016e"+
		"\5\u00a2R\2\u016e\u0179\5(\25\2\u016f\u0171\5\u00a2R\2\u0170\u016f\3\2"+
		"\2\2\u0170\u0171\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0174\7\4\2\2\u0173"+
		"\u0175\5\u00a2R\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176"+
		"\3\2\2\2\u0176\u0178\5(\25\2\u0177\u0170\3\2\2\2\u0178\u017b\3\2\2\2\u0179"+
		"\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017c\3\2\2\2\u017b\u0179\3\2"+
		"\2\2\u017c\u017d\b\22\1\2\u017d#\3\2\2\2\u017e\u017f\7(\2\2\u017f\u0180"+
		"\5\u00a2R\2\u0180\u0181\5T+\2\u0181\u0182\b\23\1\2\u0182%\3\2\2\2\u0183"+
		"\u0184\7)\2\2\u0184\u0185\5\u00a2R\2\u0185\u0186\5T+\2\u0186\u0187\b\24"+
		"\1\2\u0187\'\3\2\2\2\u0188\u018a\5T+\2\u0189\u018b\5\u00a2R\2\u018a\u0189"+
		"\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\7+\2\2\u018d"+
		"\u018e\3\2\2\2\u018e\u018f\b\25\1\2\u018f\u019a\3\2\2\2\u0190\u0192\5"+
		"T+\2\u0191\u0193\5\u00a2R\2\u0192\u0191\3\2\2\2\u0192\u0193\3\2\2\2\u0193"+
		"\u0195\3\2\2\2\u0194\u0196\7,\2\2\u0195\u0194\3\2\2\2\u0195\u0196\3\2"+
		"\2\2\u0196\u0197\3\2\2\2\u0197\u0198\b\25\1\2\u0198\u019a\3\2\2\2\u0199"+
		"\u0188\3\2\2\2\u0199\u0190\3\2\2\2\u019a)\3\2\2\2\u019b\u01a6\5,\27\2"+
		"\u019c\u019e\5\u00a2R\2\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e"+
		"\u019f\3\2\2\2\u019f\u01a1\7\4\2\2\u01a0\u01a2\5\u00a2R\2\u01a1\u01a0"+
		"\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a5\5,\27\2\u01a4"+
		"\u019d\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2"+
		"\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a9\u01aa\b\26\1\2\u01aa"+
		"+\3\2\2\2\u01ab\u01ad\5F$\2\u01ac\u01ae\5\u00a2R\2\u01ad\u01ac\3\2\2\2"+
		"\u01ad\u01ae\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01b1\7Q\2\2\u01b0\u01b2"+
		"\5\u00a2R\2\u01b1\u01b0\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\3\2\2"+
		"\2\u01b3\u01b4\5.\30\2\u01b4\u01b5\b\27\1\2\u01b5\u01c8\3\2\2\2\u01b6"+
		"\u01b7\5.\30\2\u01b7\u01b8\b\27\1\2\u01b8\u01c8\3\2\2\2\u01b9\u01ba\5"+
		"\u0090I\2\u01ba\u01bb\b\27\1\2\u01bb\u01c8\3\2\2\2\u01bc\u01be\5F$\2\u01bd"+
		"\u01bf\5\u00a2R\2\u01be\u01bd\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c0"+
		"\3\2\2\2\u01c0\u01c2\7Q\2\2\u01c1\u01c3\5\u00a2R\2\u01c2\u01c1\3\2\2\2"+
		"\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\5\u0090I\2\u01c5"+
		"\u01c6\b\27\1\2\u01c6\u01c8\3\2\2\2\u01c7\u01ab\3\2\2\2\u01c7\u01b6\3"+
		"\2\2\2\u01c7\u01b9\3\2\2\2\u01c7\u01bc\3\2\2\2\u01c8-\3\2\2\2\u01c9\u01d0"+
		"\5\60\31\2\u01ca\u01cc\5\u00a2R\2\u01cb\u01ca\3\2\2\2\u01cb\u01cc\3\2"+
		"\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01cf\5\66\34\2\u01ce\u01cb\3\2\2\2\u01cf"+
		"\u01d2\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d3\3\2"+
		"\2\2\u01d2\u01d0\3\2\2\2\u01d3\u01d4\b\30\1\2\u01d4\u01dc\3\2\2\2\u01d5"+
		"\u01d6\7\5\2\2\u01d6\u01d7\5.\30\2\u01d7\u01d8\7\6\2\2\u01d8\u01d9\3\2"+
		"\2\2\u01d9\u01da\b\30\1\2\u01da\u01dc\3\2\2\2\u01db\u01c9\3\2\2\2\u01db"+
		"\u01d5\3\2\2\2\u01dc/\3\2\2\2\u01dd\u01df\7\5\2\2\u01de\u01e0\5\u00a2"+
		"R\2\u01df\u01de\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e7\3\2\2\2\u01e1"+
		"\u01e3\5F$\2\u01e2\u01e4\5\u00a2R\2\u01e3\u01e2\3\2\2\2\u01e3\u01e4\3"+
		"\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6\b\31\1\2\u01e6\u01e8\3\2\2\2\u01e7"+
		"\u01e1\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01ef\3\2\2\2\u01e9\u01eb\5\62"+
		"\32\2\u01ea\u01ec\5\u00a2R\2\u01eb\u01ea\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec"+
		"\u01ed\3\2\2\2\u01ed\u01ee\b\31\1\2\u01ee\u01f0\3\2\2\2\u01ef\u01e9\3"+
		"\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f7\3\2\2\2\u01f1\u01f3\5> \2\u01f2"+
		"\u01f4\5\u00a2R\2\u01f3\u01f2\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f5"+
		"\3\2\2\2\u01f5\u01f6\b\31\1\2\u01f6\u01f8\3\2\2\2\u01f7\u01f1\3\2\2\2"+
		"\u01f7\u01f8\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fa\7\6\2\2\u01fa\u01fb"+
		"\b\31\1\2\u01fb\61\3\2\2\2\u01fc\u0203\5\64\33\2\u01fd\u01ff\5\u00a2R"+
		"\2\u01fe\u01fd\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0200\3\2\2\2\u0200\u0202"+
		"\5\64\33\2\u0201\u01fe\3\2\2\2\u0202\u0205\3\2\2\2\u0203\u0201\3\2\2\2"+
		"\u0203\u0204\3\2\2\2\u0204\u0206\3\2\2\2\u0205\u0203\3\2\2\2\u0206\u0207"+
		"\b\32\1\2\u0207\63\3\2\2\2\u0208\u0209\7\7\2\2\u0209\u020a\5D#\2\u020a"+
		"\u020b\b\33\1\2\u020b\65\3\2\2\2\u020c\u020e\58\35\2\u020d\u020f\5\u00a2"+
		"R\2\u020e\u020d\3\2\2\2\u020e\u020f\3\2\2\2\u020f\u0210\3\2\2\2\u0210"+
		"\u0211\5\60\31\2\u0211\u0212\b\34\1\2\u0212\67\3\2\2\2\u0213\u0215\7\b"+
		"\2\2\u0214\u0216\5\u00a2R\2\u0215\u0214\3\2\2\2\u0215\u0216\3\2\2\2\u0216"+
		"\u0217\3\2\2\2\u0217\u0219\7\t\2\2\u0218\u021a\5\u00a2R\2\u0219\u0218"+
		"\3\2\2\2\u0219\u021a\3\2\2\2\u021a\u0220\3\2\2\2\u021b\u021c\5:\36\2\u021c"+
		"\u021e\b\35\1\2\u021d\u021f\5\u00a2R\2\u021e\u021d\3\2\2\2\u021e\u021f"+
		"\3\2\2\2\u021f\u0221\3\2\2\2\u0220\u021b\3\2\2\2\u0220\u0221\3\2\2\2\u0221"+
		"\u0222\3\2\2\2\u0222\u0224\7\t\2\2\u0223\u0225\5\u00a2R\2\u0224\u0223"+
		"\3\2\2\2\u0224\u0225\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0227\7\n\2\2\u0227"+
		"\u0228\3\2\2\2\u0228\u025c\b\35\1\2\u0229\u022b\7\b\2\2\u022a\u022c\5"+
		"\u00a2R\2\u022b\u022a\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022d\3\2\2\2"+
		"\u022d\u022f\7\t\2\2\u022e\u0230\5\u00a2R\2\u022f\u022e\3\2\2\2\u022f"+
		"\u0230\3\2\2\2\u0230\u0236\3\2\2\2\u0231\u0232\5:\36\2\u0232\u0234\b\35"+
		"\1\2\u0233\u0235\5\u00a2R\2\u0234\u0233\3\2\2\2\u0234\u0235\3\2\2\2\u0235"+
		"\u0237\3\2\2\2\u0236\u0231\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0238\3\2"+
		"\2\2\u0238\u0239\7\t\2\2\u0239\u023a\3\2\2\2\u023a\u025c\b\35\1\2\u023b"+
		"\u023d\7\t\2\2\u023c\u023e\5\u00a2R\2\u023d\u023c\3\2\2\2\u023d\u023e"+
		"\3\2\2\2\u023e\u0244\3\2\2\2\u023f\u0240\5:\36\2\u0240\u0242\b\35\1\2"+
		"\u0241\u0243\5\u00a2R\2\u0242\u0241\3\2\2\2\u0242\u0243\3\2\2\2\u0243"+
		"\u0245\3\2\2\2\u0244\u023f\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0246\3\2"+
		"\2\2\u0246\u0248\7\t\2\2\u0247\u0249\5\u00a2R\2\u0248\u0247\3\2\2\2\u0248"+
		"\u0249\3\2\2\2\u0249\u024a\3\2\2\2\u024a\u024b\7\n\2\2\u024b\u024c\3\2"+
		"\2\2\u024c\u025c\b\35\1\2\u024d\u024f\7\t\2\2\u024e\u0250\5\u00a2R\2\u024f"+
		"\u024e\3\2\2\2\u024f\u0250\3\2\2\2\u0250\u0256\3\2\2\2\u0251\u0252\5:"+
		"\36\2\u0252\u0254\b\35\1\2\u0253\u0255\5\u00a2R\2\u0254\u0253\3\2\2\2"+
		"\u0254\u0255\3\2\2\2\u0255\u0257\3\2\2\2\u0256\u0251\3\2\2\2\u0256\u0257"+
		"\3\2\2\2\u0257\u0258\3\2\2\2\u0258\u0259\7\t\2\2\u0259\u025a\3\2\2\2\u025a"+
		"\u025c\b\35\1\2\u025b\u0213\3\2\2\2\u025b\u0229\3\2\2\2\u025b\u023b\3"+
		"\2\2\2\u025b\u024d\3\2\2\2\u025c9\3\2\2\2\u025d\u025f\7\13\2\2\u025e\u0260"+
		"\5\u00a2R\2\u025f\u025e\3\2\2\2\u025f\u0260\3\2\2\2\u0260\u0264\3\2\2"+
		"\2\u0261\u0262\5F$\2\u0262\u0263\b\36\1\2\u0263\u0265\3\2\2\2\u0264\u0261"+
		"\3\2\2\2\u0264\u0265\3\2\2\2\u0265\u0267\3\2\2\2\u0266\u0268\5\u00a2R"+
		"\2\u0267\u0266\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u026c\3\2\2\2\u0269\u026a"+
		"\5@!\2\u026a\u026b\b\36\1\2\u026b\u026d\3\2\2\2\u026c\u0269\3\2\2\2\u026c"+
		"\u026d\3\2\2\2\u026d\u026f\3\2\2\2\u026e\u0270\5\u00a2R\2\u026f\u026e"+
		"\3\2\2\2\u026f\u0270\3\2\2\2\u0270\u0275\3\2\2\2\u0271\u0272\7P\2\2\u0272"+
		"\u0273\5<\37\2\u0273\u0274\b\36\1\2\u0274\u0276\3\2\2\2\u0275\u0271\3"+
		"\2\2\2\u0275\u0276\3\2\2\2\u0276\u0278\3\2\2\2\u0277\u0279\5\u00a2R\2"+
		"\u0278\u0277\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u027d\3\2\2\2\u027a\u027b"+
		"\5> \2\u027b\u027c\b\36\1\2\u027c\u027e\3\2\2\2\u027d\u027a\3\2\2\2\u027d"+
		"\u027e\3\2\2\2\u027e\u0280\3\2\2\2\u027f\u0281\5\u00a2R\2\u0280\u027f"+
		"\3\2\2\2\u0280\u0281\3\2\2\2\u0281\u0282\3\2\2\2\u0282\u0283\7\f\2\2\u0283"+
		"\u0284\b\36\1\2\u0284;\3\2\2\2\u0285\u0287\5\u00a2R\2\u0286\u0285\3\2"+
		"\2\2\u0286\u0287\3\2\2\2\u0287\u028e\3\2\2\2\u0288\u028a\5\u0096L\2\u0289"+
		"\u028b\5\u00a2R\2\u028a\u0289\3\2\2\2\u028a\u028b\3\2\2\2\u028b\u028c"+
		"\3\2\2\2\u028c\u028d\b\37\1\2\u028d\u028f\3\2\2\2\u028e\u0288\3\2\2\2"+
		"\u028e\u028f\3\2\2\2\u028f\u029c\3\2\2\2\u0290\u0292\7\r\2\2\u0291\u0293"+
		"\5\u00a2R\2\u0292\u0291\3\2\2\2\u0292\u0293\3\2\2\2\u0293\u029a\3\2\2"+
		"\2\u0294\u0296\5\u0096L\2\u0295\u0297\5\u00a2R\2\u0296\u0295\3\2\2\2\u0296"+
		"\u0297\3\2\2\2\u0297\u0298\3\2\2\2\u0298\u0299\b\37\1\2\u0299\u029b\3"+
		"\2\2\2\u029a\u0294\3\2\2\2\u029a\u029b\3\2\2\2\u029b\u029d\3\2\2\2\u029c"+
		"\u0290\3\2\2\2\u029c\u029d\3\2\2\2\u029d=\3\2\2\2\u029e\u029f\5J&\2\u029f"+
		"\u02a0\b \1\2\u02a0?\3\2\2\2\u02a1\u02a2\7\7\2\2\u02a2\u02b0\5B\"\2\u02a3"+
		"\u02a5\5\u00a2R\2\u02a4\u02a3\3\2\2\2\u02a4\u02a5\3\2\2\2\u02a5\u02a6"+
		"\3\2\2\2\u02a6\u02a8\7\16\2\2\u02a7\u02a9\7\7\2\2\u02a8\u02a7\3\2\2\2"+
		"\u02a8\u02a9\3\2\2\2\u02a9\u02ab\3\2\2\2\u02aa\u02ac\5\u00a2R\2\u02ab"+
		"\u02aa\3\2\2\2\u02ab\u02ac\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02af\5B"+
		"\"\2\u02ae\u02a4\3\2\2\2\u02af\u02b2\3\2\2\2\u02b0\u02ae\3\2\2\2\u02b0"+
		"\u02b1\3\2\2\2\u02b1\u02b3\3\2\2\2\u02b2\u02b0\3\2\2\2\u02b3\u02b4\b!"+
		"\1\2\u02b4A\3\2\2\2\u02b5\u02b6\5\u009eP\2\u02b6\u02b7\b\"\1\2\u02b7C"+
		"\3\2\2\2\u02b8\u02b9\5\u009eP\2\u02b9\u02ba\b#\1\2\u02baE\3\2\2\2\u02bb"+
		"\u02bc\5\u009eP\2\u02bc\u02bd\b$\1\2\u02bdG\3\2\2\2\u02be\u02c3\5\60\31"+
		"\2\u02bf\u02c1\5\u00a2R\2\u02c0\u02bf\3\2\2\2\u02c0\u02c1\3\2\2\2\u02c1"+
		"\u02c2\3\2\2\2\u02c2\u02c4\5\66\34\2\u02c3\u02c0\3\2\2\2\u02c4\u02c5\3"+
		"\2\2\2\u02c5\u02c3\3\2\2\2\u02c5\u02c6\3\2\2\2\u02c6\u02c7\3\2\2\2\u02c7"+
		"\u02c8\b%\1\2\u02c8I\3\2\2\2\u02c9\u02ca\5L\'\2\u02ca\u02cb\b&\1\2\u02cb"+
		"K\3\2\2\2\u02cc\u02ce\7\17\2\2\u02cd\u02cf\5\u00a2R\2\u02ce\u02cd\3\2"+
		"\2\2\u02ce\u02cf\3\2\2\2\u02cf\u02db\3\2\2\2\u02d0\u02d8\5N(\2\u02d1\u02d3"+
		"\7\4\2\2\u02d2\u02d4\5\u00a2R\2\u02d3\u02d2\3\2\2\2\u02d3\u02d4\3\2\2"+
		"\2\u02d4\u02d5\3\2\2\2\u02d5\u02d7\5N(\2\u02d6\u02d1\3\2\2\2\u02d7\u02da"+
		"\3\2\2\2\u02d8\u02d6\3\2\2\2\u02d8\u02d9\3\2\2\2\u02d9\u02dc\3\2\2\2\u02da"+
		"\u02d8\3\2\2\2\u02db\u02d0\3\2\2\2\u02db\u02dc\3\2\2\2\u02dc\u02dd\3\2"+
		"\2\2\u02dd\u02de\7\20\2\2\u02de\u02df\b\'\1\2\u02dfM\3\2\2\2\u02e0\u02e2"+
		"\5\u0092J\2\u02e1\u02e3\5\u00a2R\2\u02e2\u02e1\3\2\2\2\u02e2\u02e3\3\2"+
		"\2\2\u02e3\u02e4\3\2\2\2\u02e4\u02e6\7\7\2\2\u02e5\u02e7\5\u00a2R\2\u02e6"+
		"\u02e5\3\2\2\2\u02e6\u02e7\3\2\2\2\u02e7\u02e8\3\2\2\2\u02e8\u02ea\5T"+
		"+\2\u02e9\u02eb\5\u00a2R\2\u02ea\u02e9\3\2\2\2\u02ea\u02eb\3\2\2\2\u02eb"+
		"\u02ec\3\2\2\2\u02ec\u02ed\b(\1\2\u02edO\3\2\2\2\u02ee\u02ef\5R*\2\u02ef"+
		"\u02f0\b)\1\2\u02f0Q\3\2\2\2\u02f1\u02f3\7\13\2\2\u02f2\u02f4\5\u00a2"+
		"R\2\u02f3\u02f2\3\2\2\2\u02f3\u02f4\3\2\2\2\u02f4\u0300\3\2\2\2\u02f5"+
		"\u02fd\5T+\2\u02f6\u02f8\7\4\2\2\u02f7\u02f9\5\u00a2R\2\u02f8\u02f7\3"+
		"\2\2\2\u02f8\u02f9\3\2\2\2\u02f9\u02fa\3\2\2\2\u02fa\u02fc\5T+\2\u02fb"+
		"\u02f6\3\2\2\2\u02fc\u02ff\3\2\2\2\u02fd\u02fb\3\2\2\2\u02fd\u02fe\3\2"+
		"\2\2\u02fe\u0301\3\2\2\2\u02ff\u02fd\3\2\2\2\u0300\u02f5\3\2\2\2\u0300"+
		"\u0301\3\2\2\2\u0301\u0302\3\2\2\2\u0302\u0303\7\f\2\2\u0303\u0304\b*"+
		"\1\2\u0304S\3\2\2\2\u0305\u0306\5V,\2\u0306\u0307\b+\1\2\u0307U\3\2\2"+
		"\2\u0308\u0310\5X-\2\u0309\u030a\5\u00a2R\2\u030a\u030b\7-\2\2\u030b\u030c"+
		"\5\u00a2R\2\u030c\u030d\5X-\2\u030d\u030f\3\2\2\2\u030e\u0309\3\2\2\2"+
		"\u030f\u0312\3\2\2\2\u0310\u030e\3\2\2\2\u0310\u0311\3\2\2\2\u0311\u0313"+
		"\3\2\2\2\u0312\u0310\3\2\2\2\u0313\u0314\b,\1\2\u0314W\3\2\2\2\u0315\u031d"+
		"\5Z.\2\u0316\u0317\5\u00a2R\2\u0317\u0318\7.\2\2\u0318\u0319\5\u00a2R"+
		"\2\u0319\u031a\5Z.\2\u031a\u031c\3\2\2\2\u031b\u0316\3\2\2\2\u031c\u031f"+
		"\3\2\2\2\u031d\u031b\3\2\2\2\u031d\u031e\3\2\2\2\u031e\u0320\3\2\2\2\u031f"+
		"\u031d\3\2\2\2\u0320\u0321\b-\1\2\u0321Y\3\2\2\2\u0322\u032a\5\\/\2\u0323"+
		"\u0324\5\u00a2R\2\u0324\u0325\7/\2\2\u0325\u0326\5\u00a2R\2\u0326\u0327"+
		"\5\\/\2\u0327\u0329\3\2\2\2\u0328\u0323\3\2\2\2\u0329\u032c\3\2\2\2\u032a"+
		"\u0328\3\2\2\2\u032a\u032b\3\2\2\2\u032b\u032d\3\2\2\2\u032c\u032a\3\2"+
		"\2\2\u032d\u032e\b.\1\2\u032e[\3\2\2\2\u032f\u0331\5\u00a2R\2\u0330\u032f"+
		"\3\2\2\2\u0330\u0331\3\2\2\2\u0331\u0332\3\2\2\2\u0332\u0334\7\60\2\2"+
		"\u0333\u0335\5\u00a2R\2\u0334\u0333\3\2\2\2\u0334\u0335\3\2\2\2\u0335"+
		"\u0337\3\2\2\2\u0336\u0330\3\2\2\2\u0337\u033a\3\2\2\2\u0338\u0336\3\2"+
		"\2\2\u0338\u0339\3\2\2\2\u0339\u033b\3\2\2\2\u033a\u0338\3\2\2\2\u033b"+
		"\u033c\5^\60\2\u033c\u033d\b/\1\2\u033d]\3\2\2\2\u033e\u0345\5`\61\2\u033f"+
		"\u0341\5\u00a2R\2\u0340\u033f\3\2\2\2\u0340\u0341\3\2\2\2\u0341\u0342"+
		"\3\2\2\2\u0342\u0344\5\u0082B\2\u0343\u0340\3\2\2\2\u0344\u0347\3\2\2"+
		"\2\u0345\u0343\3\2\2\2\u0345\u0346\3\2\2\2\u0346\u0348\3\2\2\2\u0347\u0345"+
		"\3\2\2\2\u0348\u0349\b\60\1\2\u0349_\3\2\2\2\u034a\u0351\5b\62\2\u034b"+
		"\u034d\5\u00a2R\2\u034c\u034b\3\2\2\2\u034c\u034d\3\2\2\2\u034d\u034e"+
		"\3\2\2\2\u034e\u0350\5|?\2\u034f\u034c\3\2\2\2\u0350\u0353\3\2\2\2\u0351"+
		"\u034f\3\2\2\2\u0351\u0352\3\2\2\2\u0352\u0354\3\2\2\2\u0353\u0351\3\2"+
		"\2\2\u0354\u0355\b\61\1\2\u0355a\3\2\2\2\u0356\u035d\5d\63\2\u0357\u0359"+
		"\5\u00a2R\2\u0358\u0357\3\2\2\2\u0358\u0359\3\2\2\2\u0359\u035a\3\2\2"+
		"\2\u035a\u035c\5~@\2\u035b\u0358\3\2\2\2\u035c\u035f\3\2\2\2\u035d\u035b"+
		"\3\2\2\2\u035d\u035e\3\2\2\2\u035e\u0360\3\2\2\2\u035f\u035d\3\2\2\2\u0360"+
		"\u0361\b\62\1\2\u0361c\3\2\2\2\u0362\u0369\5f\64\2\u0363\u0365\5\u00a2"+
		"R\2\u0364\u0363\3\2\2\2\u0364\u0365\3\2\2\2\u0365\u0366\3\2\2\2\u0366"+
		"\u0368\5\u0080A\2\u0367\u0364\3\2\2\2\u0368\u036b\3\2\2\2\u0369\u0367"+
		"\3\2\2\2\u0369\u036a\3\2\2\2\u036a\u036c\3\2\2\2\u036b\u0369\3\2\2\2\u036c"+
		"\u036d\b\63\1\2\u036de\3\2\2\2\u036e\u0370\5h\65\2\u036f\u0371\5\u00a2"+
		"R\2\u0370\u036f\3\2\2\2\u0370\u0371\3\2\2\2\u0371\u0373\3\2\2\2\u0372"+
		"\u036e\3\2\2\2\u0373\u0376\3\2\2\2\u0374\u0372\3\2\2\2\u0374\u0375\3\2"+
		"\2\2\u0375\u0377\3\2\2\2\u0376\u0374\3\2\2\2\u0377\u0378\5j\66\2\u0378"+
		"\u0379\b\64\1\2\u0379g\3\2\2\2\u037a\u037b\7\21\2\2\u037b\u037f\b\65\1"+
		"\2\u037c\u037d\7\t\2\2\u037d\u037f\b\65\1\2\u037e\u037a\3\2\2\2\u037e"+
		"\u037c\3\2\2\2\u037fi\3\2\2\2\u0380\u0387\5l\67\2\u0381\u0383\5\u00a2"+
		"R\2\u0382\u0381\3\2\2\2\u0382\u0383\3\2\2\2\u0383\u0384\3\2\2\2\u0384"+
		"\u0386\5r:\2\u0385\u0382\3\2\2\2\u0386\u0389\3\2\2\2\u0387\u0385\3\2\2"+
		"\2\u0387\u0388\3\2\2\2\u0388\u038a\3\2\2\2\u0389\u0387\3\2\2\2\u038a\u038b"+
		"\b\66\1\2\u038bk\3\2\2\2\u038c\u0390\5\u0084C\2\u038d\u038f\5n8\2\u038e"+
		"\u038d\3\2\2\2\u038f\u0392\3\2\2\2\u0390\u038e\3\2\2\2\u0390\u0391\3\2"+
		"\2\2\u0391\u0393\3\2\2\2\u0392\u0390\3\2\2\2\u0393\u0394\b\67\1\2\u0394"+
		"\u039f\3\2\2\2\u0395\u0399\5\u0084C\2\u0396\u0398\5p9\2\u0397\u0396\3"+
		"\2\2\2\u0398\u039b\3\2\2\2\u0399\u0397\3\2\2\2\u0399\u039a\3\2\2\2\u039a"+
		"\u039c\3\2\2\2\u039b\u0399\3\2\2\2\u039c\u039d\b\67\1\2\u039d\u039f\3"+
		"\2\2\2\u039e\u038c\3\2\2\2\u039e\u0395\3\2\2\2\u039fm\3\2\2\2\u03a0\u03a1"+
		"\5z>\2\u03a1\u03a2\b8\1\2\u03a2o\3\2\2\2\u03a3\u03a4\5\62\32\2\u03a4\u03a5"+
		"\b9\1\2\u03a5q\3\2\2\2\u03a6\u03a7\7\13\2\2\u03a7\u03a8\5T+\2\u03a8\u03a9"+
		"\7\f\2\2\u03a9\u03aa\b:\1\2\u03aa\u03c7\3\2\2\2\u03ab\u03ad\7\13\2\2\u03ac"+
		"\u03ae\5T+\2\u03ad\u03ac\3\2\2\2\u03ad\u03ae\3\2\2\2\u03ae\u03af\3\2\2"+
		"\2\u03af\u03b1\7\r\2\2\u03b0\u03b2\5T+\2\u03b1\u03b0\3\2\2\2\u03b1\u03b2"+
		"\3\2\2\2\u03b2\u03b3\3\2\2\2\u03b3\u03b4\7\f\2\2\u03b4\u03c7\b:\1\2\u03b5"+
		"\u03b6\5t;\2\u03b6\u03b7\b:\1\2\u03b7\u03c7\3\2\2\2\u03b8\u03b9\5\u00a2"+
		"R\2\u03b9\u03ba\7\66\2\2\u03ba\u03bb\5\u00a2R\2\u03bb\u03bc\7\67\2\2\u03bc"+
		"\u03bd\b:\1\2\u03bd\u03c7\3\2\2\2\u03be\u03bf\5\u00a2R\2\u03bf\u03c0\7"+
		"\66\2\2\u03c0\u03c1\5\u00a2R\2\u03c1\u03c2\7\60\2\2\u03c2\u03c3\5\u00a2"+
		"R\2\u03c3\u03c4\7\67\2\2\u03c4\u03c5\b:\1\2\u03c5\u03c7\3\2\2\2\u03c6"+
		"\u03a6\3\2\2\2\u03c6\u03ab\3\2\2\2\u03c6\u03b5\3\2\2\2\u03c6\u03b8\3\2"+
		"\2\2\u03c6\u03be\3\2\2\2\u03c7s\3\2\2\2\u03c8\u03ca\5\u00a2R\2\u03c9\u03c8"+
		"\3\2\2\2\u03c9\u03ca\3\2\2\2\u03ca\u03cb\3\2\2\2\u03cb\u03cd\7\22\2\2"+
		"\u03cc\u03ce\5\u00a2R\2\u03cd\u03cc\3\2\2\2\u03cd\u03ce\3\2\2\2\u03ce"+
		"\u03cf\3\2\2\2\u03cf\u03d0\5l\67\2\u03d0\u03d1\b;\1\2\u03d1\u03f7\3\2"+
		"\2\2\u03d2\u03d3\5\u00a2R\2\u03d3\u03d5\7\61\2\2\u03d4\u03d6\5\u00a2R"+
		"\2\u03d5\u03d4\3\2\2\2\u03d5\u03d6\3\2\2\2\u03d6\u03d7\3\2\2\2\u03d7\u03d8"+
		"\5l\67\2\u03d8\u03d9\b;\1\2\u03d9\u03f7\3\2\2\2\u03da\u03db\5\u00a2R\2"+
		"\u03db\u03dc\7\62\2\2\u03dc\u03dd\5\u00a2R\2\u03dd\u03df\7\"\2\2\u03de"+
		"\u03e0\5\u00a2R\2\u03df\u03de\3\2\2\2\u03df\u03e0\3\2\2\2\u03e0\u03e1"+
		"\3\2\2\2\u03e1\u03e2\5l\67\2\u03e2\u03e3\b;\1\2\u03e3\u03f7\3\2\2\2\u03e4"+
		"\u03e5\5\u00a2R\2\u03e5\u03e6\7\63\2\2\u03e6\u03e7\5\u00a2R\2\u03e7\u03e9"+
		"\7\"\2\2\u03e8\u03ea\5\u00a2R\2\u03e9\u03e8\3\2\2\2\u03e9\u03ea\3\2\2"+
		"\2\u03ea\u03eb\3\2\2\2\u03eb\u03ec\5l\67\2\u03ec\u03ed\b;\1\2\u03ed\u03f7"+
		"\3\2\2\2\u03ee\u03ef\5\u00a2R\2\u03ef\u03f1\7\65\2\2\u03f0\u03f2\5\u00a2"+
		"R\2\u03f1\u03f0\3\2\2\2\u03f1\u03f2\3\2\2\2\u03f2\u03f3\3\2\2\2\u03f3"+
		"\u03f4\5l\67\2\u03f4\u03f5\b;\1\2\u03f5\u03f7\3\2\2\2\u03f6\u03c9\3\2"+
		"\2\2\u03f6\u03d2\3\2\2\2\u03f6\u03da\3\2\2\2\u03f6\u03e4\3\2\2\2\u03f6"+
		"\u03ee\3\2\2\2\u03f7u\3\2\2\2\u03f8\u03fa\5x=\2\u03f9\u03fb\5\u00a2R\2"+
		"\u03fa\u03f9\3\2\2\2\u03fa\u03fb\3\2\2\2\u03fb\u03fc\3\2\2\2\u03fc\u03fe"+
		"\7\5\2\2\u03fd\u03ff\5\u00a2R\2\u03fe\u03fd\3\2\2\2\u03fe\u03ff\3\2\2"+
		"\2\u03ff\u0400\3\2\2\2\u0400\u0402\5T+\2\u0401\u0403\5\u00a2R\2\u0402"+
		"\u0401\3\2\2\2\u0402\u0403\3\2\2\2\u0403\u0404\3\2\2\2\u0404\u0405\7\6"+
		"\2\2\u0405\u0406\3\2\2\2\u0406\u0407\b<\1\2\u0407\u0418\3\2\2\2\u0408"+
		"\u040a\5x=\2\u0409\u040b\5\u00a2R\2\u040a\u0409\3\2\2\2\u040a\u040b\3"+
		"\2\2\2\u040b\u040c\3\2\2\2\u040c\u040e\7\17\2\2\u040d\u040f\5\u00a2R\2"+
		"\u040e\u040d\3\2\2\2\u040e\u040f\3\2\2\2\u040f\u0410\3\2\2\2\u0410\u0412"+
		"\5\20\t\2\u0411\u0413\5\u00a2R\2\u0412\u0411\3\2\2\2\u0412\u0413\3\2\2"+
		"\2\u0413\u0414\3\2\2\2\u0414\u0415\7\20\2\2\u0415\u0416\b<\1\2\u0416\u0418"+
		"\3\2\2\2\u0417\u03f8\3\2\2\2\u0417\u0408\3\2\2\2\u0418w\3\2\2\2\u0419"+
		"\u041a\7*\2\2\u041ay\3\2\2\2\u041b\u041d\5\u00a2R\2\u041c\u041b\3\2\2"+
		"\2\u041c\u041d\3\2\2\2\u041d\u041e\3\2\2\2\u041e\u0420\7\23\2\2\u041f"+
		"\u0421\5\u00a2R\2\u0420\u041f\3\2\2\2\u0420\u0421\3\2\2\2\u0421\u0422"+
		"\3\2\2\2\u0422\u0423\5\u0092J\2\u0423\u0424\b>\1\2\u0424{\3\2\2\2\u0425"+
		"\u0427\7\21\2\2\u0426\u0428\5\u00a2R\2\u0427\u0426\3\2\2\2\u0427\u0428"+
		"\3\2\2\2\u0428\u0429\3\2\2\2\u0429\u042a\5b\62\2\u042a\u042b\b?\1\2\u042b"+
		"\u0434\3\2\2\2\u042c\u042e\7\t\2\2\u042d\u042f\5\u00a2R\2\u042e\u042d"+
		"\3\2\2\2\u042e\u042f\3\2\2\2\u042f\u0430\3\2\2\2\u0430\u0431\5b\62\2\u0431"+
		"\u0432\b?\1\2\u0432\u0434\3\2\2\2\u0433\u0425\3\2\2\2\u0433\u042c\3\2"+
		"\2\2\u0434}\3\2\2\2\u0435\u0437\7P\2\2\u0436\u0438\5\u00a2R\2\u0437\u0436"+
		"\3\2\2\2\u0437\u0438\3\2\2\2\u0438\u0439\3\2\2\2\u0439\u043a\5d\63\2\u043a"+
		"\u043b\b@\1\2\u043b\u044b\3\2\2\2\u043c\u043e\7\24\2\2\u043d\u043f\5\u00a2"+
		"R\2\u043e\u043d\3\2\2\2\u043e\u043f\3\2\2\2\u043f\u0440\3\2\2\2\u0440"+
		"\u0441\5d\63\2\u0441\u0442\b@\1\2\u0442\u044b\3\2\2\2\u0443\u0445\7\25"+
		"\2\2\u0444\u0446\5\u00a2R\2\u0445\u0444\3\2\2\2\u0445\u0446\3\2\2\2\u0446"+
		"\u0447\3\2\2\2\u0447\u0448\5d\63\2\u0448\u0449\b@\1\2\u0449\u044b\3\2"+
		"\2\2\u044a\u0435\3\2\2\2\u044a\u043c\3\2\2\2\u044a\u0443\3\2\2\2\u044b"+
		"\177\3\2\2\2\u044c\u044e\7\26\2\2\u044d\u044f\5\u00a2R\2\u044e\u044d\3"+
		"\2\2\2\u044e\u044f\3\2\2\2\u044f\u0450\3\2\2\2\u0450\u0451\5f\64\2\u0451"+
		"\u0452\bA\1\2\u0452\u0081\3\2\2\2\u0453\u0455\7Q\2\2\u0454\u0456\5\u00a2"+
		"R\2\u0455\u0454\3\2\2\2\u0455\u0456\3\2\2\2\u0456\u0457\3\2\2\2\u0457"+
		"\u0458\5`\61\2\u0458\u0459\3\2\2\2\u0459\u045a\bB\1\2\u045a\u048c\3\2"+
		"\2\2\u045b\u045d\7\27\2\2\u045c\u045e\5\u00a2R\2\u045d\u045c\3\2\2\2\u045d"+
		"\u045e\3\2\2\2\u045e\u045f\3\2\2\2\u045f\u0460\5`\61\2\u0460\u0461\3\2"+
		"\2\2\u0461\u0462\bB\1\2\u0462\u048c\3\2\2\2\u0463\u0465\7\30\2\2\u0464"+
		"\u0466\5\u00a2R\2\u0465\u0464\3\2\2\2\u0465\u0466\3\2\2\2\u0466\u0467"+
		"\3\2\2\2\u0467\u0468\5`\61\2\u0468\u0469\3\2\2\2\u0469\u046a\bB\1\2\u046a"+
		"\u048c\3\2\2\2\u046b\u046d\7\b\2\2\u046c\u046e\5\u00a2R\2\u046d\u046c"+
		"\3\2\2\2\u046d\u046e\3\2\2\2\u046e\u046f\3\2\2\2\u046f\u0470\5`\61\2\u0470"+
		"\u0471\3\2\2\2\u0471\u0472\bB\1\2\u0472\u048c\3\2\2\2\u0473\u0475\7\n"+
		"\2\2\u0474\u0476\5\u00a2R\2\u0475\u0474\3\2\2\2\u0475\u0476\3\2\2\2\u0476"+
		"\u0477\3\2\2\2\u0477\u0478\5`\61\2\u0478\u0479\3\2\2\2\u0479\u047a\bB"+
		"\1\2\u047a\u048c\3\2\2\2\u047b\u047d\7\31\2\2\u047c\u047e\5\u00a2R\2\u047d"+
		"\u047c\3\2\2\2\u047d\u047e\3\2\2\2\u047e\u047f\3\2\2\2\u047f\u0480\5`"+
		"\61\2\u0480\u0481\3\2\2\2\u0481\u0482\bB\1\2\u0482\u048c\3\2\2\2\u0483"+
		"\u0485\7\32\2\2\u0484\u0486\5\u00a2R\2\u0485\u0484\3\2\2\2\u0485\u0486"+
		"\3\2\2\2\u0486\u0487\3\2\2\2\u0487\u0488\5`\61\2\u0488\u0489\3\2\2\2\u0489"+
		"\u048a\bB\1\2\u048a\u048c\3\2\2\2\u048b\u0453\3\2\2\2\u048b\u045b\3\2"+
		"\2\2\u048b\u0463\3\2\2\2\u048b\u046b\3\2\2\2\u048b\u0473\3\2\2\2\u048b"+
		"\u047b\3\2\2\2\u048b\u0483\3\2\2\2\u048c\u0083\3\2\2\2\u048d\u048e\5\u0094"+
		"K\2\u048e\u048f\bC\1\2\u048f\u04c0\3\2\2\2\u0490\u0491\7R\2\2\u0491\u04c0"+
		"\bC\1\2\u0492\u0493\78\2\2\u0493\u04c0\bC\1\2\u0494\u0495\79\2\2\u0495"+
		"\u04c0\bC\1\2\u0496\u0497\7\67\2\2\u0497\u04c0\bC\1\2\u0498\u049a\7>\2"+
		"\2\u0499\u049b\5\u00a2R\2\u049a\u0499\3\2\2\2\u049a\u049b\3\2\2\2\u049b"+
		"\u049c\3\2\2\2\u049c\u049e\7\5\2\2\u049d\u049f\5\u00a2R\2\u049e\u049d"+
		"\3\2\2\2\u049e\u049f\3\2\2\2\u049f\u04a0\3\2\2\2\u04a0\u04a2\7P\2\2\u04a1"+
		"\u04a3\5\u00a2R\2\u04a2\u04a1\3\2\2\2\u04a2\u04a3\3\2\2\2\u04a3\u04a4"+
		"\3\2\2\2\u04a4\u04a5\7\6\2\2\u04a5\u04a6\3\2\2\2\u04a6\u04c0\bC\1\2\u04a7"+
		"\u04a8\5J&\2\u04a8\u04a9\bC\1\2\u04a9\u04c0\3\2\2\2\u04aa\u04ab\5P)\2"+
		"\u04ab\u04ac\bC\1\2\u04ac\u04c0\3\2\2\2\u04ad\u04ae\5\u008aF\2\u04ae\u04af"+
		"\bC\1\2\u04af\u04c0\3\2\2\2\u04b0\u04b1\5H%\2\u04b1\u04b2\bC\1\2\u04b2"+
		"\u04c0\3\2\2\2\u04b3\u04b4\5\u008cG\2\u04b4\u04b5\bC\1\2\u04b5\u04c0\3"+
		"\2\2\2\u04b6\u04b7\5F$\2\u04b7\u04b8\bC\1\2\u04b8\u04c0\3\2\2\2\u04b9"+
		"\u04ba\5\u0086D\2\u04ba\u04bb\bC\1\2\u04bb\u04c0\3\2\2\2\u04bc\u04bd\5"+
		"v<\2\u04bd\u04be\bC\1\2\u04be\u04c0\3\2\2\2\u04bf\u048d\3\2\2\2\u04bf"+
		"\u0490\3\2\2\2\u04bf\u0492\3\2\2\2\u04bf\u0494\3\2\2\2\u04bf\u0496\3\2"+
		"\2\2\u04bf\u0498\3\2\2\2\u04bf\u04a7\3\2\2\2\u04bf\u04aa\3\2\2\2\u04bf"+
		"\u04ad\3\2\2\2\u04bf\u04b0\3\2\2\2\u04bf\u04b3\3\2\2\2\u04bf\u04b6\3\2"+
		"\2\2\u04bf\u04b9\3\2\2\2\u04bf\u04bc\3\2\2\2\u04c0\u0085\3\2\2\2\u04c1"+
		"\u04c6\7:\2\2\u04c2\u04c4\5\u00a2R\2\u04c3\u04c2\3\2\2\2\u04c3\u04c4\3"+
		"\2\2\2\u04c4\u04c5\3\2\2\2\u04c5\u04c7\5\u0088E\2\u04c6\u04c3\3\2\2\2"+
		"\u04c7\u04c8\3\2\2\2\u04c8\u04c6\3\2\2\2\u04c8\u04c9\3\2\2\2\u04c9\u04d2"+
		"\3\2\2\2\u04ca\u04cc\5\u00a2R\2\u04cb\u04ca\3\2\2\2\u04cb\u04cc\3\2\2"+
		"\2\u04cc\u04cd\3\2\2\2\u04cd\u04cf\7;\2\2\u04ce\u04d0\5\u00a2R\2\u04cf"+
		"\u04ce\3\2\2\2\u04cf\u04d0\3\2\2\2\u04d0\u04d1\3\2\2\2\u04d1\u04d3\5T"+
		"+\2\u04d2\u04cb\3\2\2\2\u04d2\u04d3\3\2\2\2\u04d3\u04d5\3\2\2\2\u04d4"+
		"\u04d6\5\u00a2R\2\u04d5\u04d4\3\2\2\2\u04d5\u04d6\3\2\2\2\u04d6\u04d7"+
		"\3\2\2\2\u04d7\u04d8\7\64\2\2\u04d8\u04d9\bD\1\2\u04d9\u04f8\3\2\2\2\u04da"+
		"\u04dc\7:\2\2\u04db\u04dd\5\u00a2R\2\u04dc\u04db\3\2\2\2\u04dc\u04dd\3"+
		"\2\2\2\u04dd\u04de\3\2\2\2\u04de\u04e3\5T+\2\u04df\u04e1\5\u00a2R\2\u04e0"+
		"\u04df\3\2\2\2\u04e0\u04e1\3\2\2\2\u04e1\u04e2\3\2\2\2\u04e2\u04e4\5\u0088"+
		"E\2\u04e3\u04e0\3\2\2\2\u04e4\u04e5\3\2\2\2\u04e5\u04e3\3\2\2\2\u04e5"+
		"\u04e6\3\2\2\2\u04e6\u04ef\3\2\2\2\u04e7\u04e9\5\u00a2R\2\u04e8\u04e7"+
		"\3\2\2\2\u04e8\u04e9\3\2\2\2\u04e9\u04ea\3\2\2\2\u04ea\u04ec\7;\2\2\u04eb"+
		"\u04ed\5\u00a2R\2\u04ec\u04eb\3\2\2\2\u04ec\u04ed\3\2\2\2\u04ed\u04ee"+
		"\3\2\2\2\u04ee\u04f0\5T+\2\u04ef\u04e8\3\2\2\2\u04ef\u04f0\3\2\2\2\u04f0"+
		"\u04f2\3\2\2\2\u04f1\u04f3\5\u00a2R\2\u04f2\u04f1\3\2\2\2\u04f2\u04f3"+
		"\3\2\2\2\u04f3\u04f4\3\2\2\2\u04f4\u04f5\7\64\2\2\u04f5\u04f6\bD\1\2\u04f6"+
		"\u04f8\3\2\2\2\u04f7\u04c1\3\2\2\2\u04f7\u04da\3\2\2\2\u04f8\u0087\3\2"+
		"\2\2\u04f9\u04fb\7<\2\2\u04fa\u04fc\5\u00a2R\2\u04fb\u04fa\3\2\2\2\u04fb"+
		"\u04fc\3\2\2\2\u04fc\u04fd\3\2\2\2\u04fd\u04ff\5T+\2\u04fe\u0500\5\u00a2"+
		"R\2\u04ff\u04fe\3\2\2\2\u04ff\u0500\3\2\2\2\u0500\u0501\3\2\2\2\u0501"+
		"\u0503\7=\2\2\u0502\u0504\5\u00a2R\2\u0503\u0502\3\2\2\2\u0503\u0504\3"+
		"\2\2\2\u0504\u0505\3\2\2\2\u0505\u0506\5T+\2\u0506\u0507\bE\1\2\u0507"+
		"\u0089\3\2\2\2\u0508\u050a\7\5\2\2\u0509\u050b\5\u00a2R\2\u050a\u0509"+
		"\3\2\2\2\u050a\u050b\3\2\2\2\u050b\u050c\3\2\2\2\u050c\u050e\5T+\2\u050d"+
		"\u050f\5\u00a2R\2\u050e\u050d\3\2\2\2\u050e\u050f\3\2\2\2\u050f\u0510"+
		"\3\2\2\2\u0510\u0511\7\6\2\2\u0511\u0512\bF\1\2\u0512\u008b\3\2\2\2\u0513"+
		"\u0515\5\u008eH\2\u0514\u0516\5\u00a2R\2\u0515\u0514\3\2\2\2\u0515\u0516"+
		"\3\2\2\2\u0516\u0517\3\2\2\2\u0517\u0519\7\5\2\2\u0518\u051a\5\u00a2R"+
		"\2\u0519\u0518\3\2\2\2\u0519\u051a\3\2\2\2\u051a\u051c\3\2\2\2\u051b\u051d"+
		"\7$\2\2\u051c\u051b\3\2\2\2\u051c\u051d\3\2\2\2\u051d\u051f\3\2\2\2\u051e"+
		"\u0520\5\u00a2R\2\u051f\u051e\3\2\2\2\u051f\u0520\3\2\2\2\u0520\u052c"+
		"\3\2\2\2\u0521\u0529\5T+\2\u0522\u0524\7\4\2\2\u0523\u0525\5\u00a2R\2"+
		"\u0524\u0523\3\2\2\2\u0524\u0525\3\2\2\2\u0525\u0526\3\2\2\2\u0526\u0528"+
		"\5T+\2\u0527\u0522\3\2\2\2\u0528\u052b\3\2\2\2\u0529\u0527\3\2\2\2\u0529"+
		"\u052a\3\2\2\2\u052a\u052d\3\2\2\2\u052b\u0529\3\2\2\2\u052c\u0521\3\2"+
		"\2\2\u052c\u052d\3\2\2\2\u052d\u052f\3\2\2\2\u052e\u0530\5\u00a2R\2\u052f"+
		"\u052e\3\2\2\2\u052f\u0530\3\2\2\2\u0530\u0531\3\2\2\2\u0531\u0532\7\6"+
		"\2\2\u0532\u0533\bG\1\2\u0533\u008d\3\2\2\2\u0534\u0535\7E\2\2\u0535\u053b"+
		"\bH\1\2\u0536\u0537\7F\2\2\u0537\u053b\bH\1\2\u0538\u0539\7>\2\2\u0539"+
		"\u053b\bH\1\2\u053a\u0534\3\2\2\2\u053a\u0536\3\2\2\2\u053a\u0538\3\2"+
		"\2\2\u053b\u008f\3\2\2\2\u053c\u053e\7A\2\2\u053d\u053f\5\u00a2R\2\u053e"+
		"\u053d\3\2\2\2\u053e\u053f\3\2\2\2\u053f\u0540\3\2\2\2\u0540\u0542\7\5"+
		"\2\2\u0541\u0543\5\u00a2R\2\u0542\u0541\3\2\2\2\u0542\u0543\3\2\2\2\u0543"+
		"\u0544\3\2\2\2\u0544\u0546\5.\30\2\u0545\u0547\5\u00a2R\2\u0546\u0545"+
		"\3\2\2\2\u0546\u0547\3\2\2\2\u0547\u0548\3\2\2\2\u0548\u0549\7\6\2\2\u0549"+
		"\u054a\bI\1\2\u054a\u055b\3\2\2\2\u054b\u054d\7B\2\2\u054c\u054e\5\u00a2"+
		"R\2\u054d\u054c\3\2\2\2\u054d\u054e\3\2\2\2\u054e\u054f\3\2\2\2\u054f"+
		"\u0551\7\5\2\2\u0550\u0552\5\u00a2R\2\u0551\u0550\3\2\2\2\u0551\u0552"+
		"\3\2\2\2\u0552\u0553\3\2\2\2\u0553\u0555\5.\30\2\u0554\u0556\5\u00a2R"+
		"\2\u0555\u0554\3\2\2\2\u0555\u0556\3\2\2\2\u0556\u0557\3\2\2\2\u0557\u0558"+
		"\7\6\2\2\u0558\u0559\bI\1\2\u0559\u055b\3\2\2\2\u055a\u053c\3\2\2\2\u055a"+
		"\u054b\3\2\2\2\u055b\u0091\3\2\2\2\u055c\u055d\5\u009eP\2\u055d\u055e"+
		"\bJ\1\2\u055e\u0093\3\2\2\2\u055f\u0560\5\u009aN\2\u0560\u0561\bK\1\2"+
		"\u0561\u0566\3\2\2\2\u0562\u0563\5\u0096L\2\u0563\u0564\bK\1\2\u0564\u0566"+
		"\3\2\2\2\u0565\u055f\3\2\2\2\u0565\u0562\3\2\2\2\u0566\u0095\3\2\2\2\u0567"+
		"\u0568\5\u0098M\2\u0568\u0569\bL\1\2\u0569\u0097\3\2\2\2\u056a\u056b\7"+
		"G\2\2\u056b\u0571\bM\1\2\u056c\u056d\7I\2\2\u056d\u0571\bM\1\2\u056e\u056f"+
		"\7H\2\2\u056f\u0571\bM\1\2\u0570\u056a\3\2\2\2\u0570\u056c\3\2\2\2\u0570"+
		"\u056e\3\2\2\2\u0571\u0099\3\2\2\2\u0572\u0573\5\u009cO\2\u0573\u0574"+
		"\bN\1\2\u0574\u009b\3\2\2\2\u0575\u0576\7K\2\2\u0576\u057a\7\23\2\2\u0577"+
		"\u0579\7N\2\2\u0578\u0577\3\2\2\2\u0579\u057c\3\2\2\2\u057a\u0578\3\2"+
		"\2\2\u057a\u057b\3\2\2\2\u057b\u057e\3\2\2\2\u057c\u057a\3\2\2\2\u057d"+
		"\u057f\7T\2\2\u057e\u057d\3\2\2\2\u057e\u057f\3\2\2\2\u057f\u058a\3\2"+
		"\2\2\u0580\u0581\7\23\2\2\u0581\u0583\7K\2\2\u0582\u0584\7T\2\2\u0583"+
		"\u0582\3\2\2\2\u0583\u0584\3\2\2\2\u0584\u058a\3\2\2\2\u0585\u0587\7K"+
		"\2\2\u0586\u0588\7T\2\2\u0587\u0586\3\2\2\2\u0587\u0588\3\2\2\2\u0588"+
		"\u058a\3\2\2\2\u0589\u0575\3\2\2\2\u0589\u0580\3\2\2\2\u0589\u0585\3\2"+
		"\2\2\u058a\u009d\3\2\2\2\u058b\u058c\5\u00a0Q\2\u058c\u058d\bP\1\2\u058d"+
		"\u009f\3\2\2\2\u058e\u058f\t\2\2\2\u058f\u00a1\3\2\2\2\u0590\u0592\7C"+
		"\2\2\u0591\u0590\3\2\2\2\u0592\u0593\3\2\2\2\u0593\u0591\3\2\2\2\u0593"+
		"\u0594\3\2\2\2\u0594\u00a3\3\2\2\2\u00d4\u00a5\u00a9\u00ac\u00af\u00bc"+
		"\u00c1\u00c8\u00cd\u00d6\u00de\u00e4\u00f2\u00f7\u00fb\u00ff\u0104\u010a"+
		"\u0118\u011c\u0121\u012a\u013d\u0143\u0149\u014d\u0151\u0156\u0168\u0170"+
		"\u0174\u0179\u018a\u0192\u0195\u0199\u019d\u01a1\u01a6\u01ad\u01b1\u01be"+
		"\u01c2\u01c7\u01cb\u01d0\u01db\u01df\u01e3\u01e7\u01eb\u01ef\u01f3\u01f7"+
		"\u01fe\u0203\u020e\u0215\u0219\u021e\u0220\u0224\u022b\u022f\u0234\u0236"+
		"\u023d\u0242\u0244\u0248\u024f\u0254\u0256\u025b\u025f\u0264\u0267\u026c"+
		"\u026f\u0275\u0278\u027d\u0280\u0286\u028a\u028e\u0292\u0296\u029a\u029c"+
		"\u02a4\u02a8\u02ab\u02b0\u02c0\u02c5\u02ce\u02d3\u02d8\u02db\u02e2\u02e6"+
		"\u02ea\u02f3\u02f8\u02fd\u0300\u0310\u031d\u032a\u0330\u0334\u0338\u0340"+
		"\u0345\u034c\u0351\u0358\u035d\u0364\u0369\u0370\u0374\u037e\u0382\u0387"+
		"\u0390\u0399\u039e\u03ad\u03b1\u03c6\u03c9\u03cd\u03d5\u03df\u03e9\u03f1"+
		"\u03f6\u03fa\u03fe\u0402\u040a\u040e\u0412\u0417\u041c\u0420\u0427\u042e"+
		"\u0433\u0437\u043e\u0445\u044a\u044e\u0455\u045d\u0465\u046d\u0475\u047d"+
		"\u0485\u048b\u049a\u049e\u04a2\u04bf\u04c3\u04c8\u04cb\u04cf\u04d2\u04d5"+
		"\u04dc\u04e0\u04e5\u04e8\u04ec\u04ef\u04f2\u04f7\u04fb\u04ff\u0503\u050a"+
		"\u050e\u0515\u0519\u051c\u051f\u0524\u0529\u052c\u052f\u053a\u053e\u0542"+
		"\u0546\u054d\u0551\u0555\u055a\u0565\u0570\u057a\u057e\u0583\u0587\u0589"+
		"\u0593";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}