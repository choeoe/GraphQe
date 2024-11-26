package cypher.parser;


import org.neo4j.shell.cli.Format;
import org.neo4j.shell.prettyprint.OutputFormatter;

public class StringLinePrinter implements Printer {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void printOut(String line) {
        sb.append(line).append(OutputFormatter.NEWLINE);
    }

    public void clear() {
        sb.setLength(0);
    }

    public String output() {
        return sb.toString();
    }

    @Override
    public void printError(Throwable throwable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printError(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFormat(Format format) {
        throw new UnsupportedOperationException();
    }
}