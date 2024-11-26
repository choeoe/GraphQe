package cypher.parser;


import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class VerboseErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");

        for (String s : lines) {
            System.err.println(s);
        }
        System.err.println(msg);
//        String errorLine = lines[line-1];
//
//        System.err.println("line " + line + ":" + charPositionInLine + " at " +
//                offendingSymbol + ": " + msg);
//        System.err.println("Error Line: " + errorLine);
    }
}