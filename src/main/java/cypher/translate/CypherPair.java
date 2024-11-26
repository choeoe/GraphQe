package cypher.translate;

import cypher.ast.Statement;
import cypher.parser.CypherASTBuilder;
import cypher.parser.CypherStringVisitor;

public class CypherPair {
    private String c1, c2;

    private Statement stmt1, stmt2;
    private CypherASTBuilder builder;

    private CypherPair(String c1, String c2) {
        this.c1 = c1;
        this.c2 = c2;
        builder = new CypherASTBuilder();
    }

    private void parse() {
        stmt1 = builder.parse(c1);
        stmt2 = builder.parse(c2);
        normalize();
        System.out.println("Normalized==============================");
    }

    public static CypherPair read(String c1, String c2) {
        CypherPair pair = new CypherPair(c1, c2);
        pair.parse();
        return pair;
    }

    private void normalize() {
        CypherNormalizer.normalize(stmt1, "x");
        CypherNormalizer.normalize(stmt2, "x");
    }

    public Statement getStmt1() {
        return stmt1;
    }

    public Statement getStmt2() {
        return stmt2;
    }
}
