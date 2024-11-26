package cypher.parser;

import cypher.CypherBaseListener;
import cypher.CypherBaseVisitor;
import cypher.CypherLexer;
import cypher.CypherParser;
import cypher.ast.Statement;
import cypher.ast.clause.match.pattern.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
public class CypherASTBuilder extends ASTBuilder<Statement> {

    /**
     * Parse the provided Cypher string and converts it into an AST.
     *
     * @param cypher is the cypher to parse.
     * @return Returns the generated AST.
     */
    public Statement parse(String cypher) {
        ANTLRInputStream input = new ANTLRInputStream(cypher);
        CypherLexer lexer = new CypherLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CypherParser parser = new CypherParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new VerboseErrorListener());
        return parser.cypher().res;
    }

    public static void testParse(String cypher) {
        ANTLRInputStream input = new ANTLRInputStream(cypher);
        CypherLexer lexer = new CypherLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CypherParser parser = new CypherParser(tokens);
        CypherParser.QueryContext queryContext = parser.query();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new CypherBaseListener(), queryContext);
        Object accept = queryContext.accept(new CypherBaseVisitor<>());
        System.out.println(queryContext.toStringTree(parser));
    }

    /**
     * Parse the provided pattern string and converts it into an AST.
     *
     * @param pattern is the pattern to parse.
     * @return Returns the generated AST.
     */
    public static Pattern parsePattern(String pattern) {
        ANTLRInputStream input = new ANTLRInputStream(pattern);
        CypherLexer lexer = new CypherLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CypherParser parser = new CypherParser(tokens);
        return parser.pattern().res;
    }
}
