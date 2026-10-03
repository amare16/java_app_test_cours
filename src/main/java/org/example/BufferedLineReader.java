package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Implementation of ILineReader that reads text
 * by delegating the work to a BufferedReader.
 */
public class BufferedLineReader implements ILineReader {
    // The real Java reader to which this class will delegate reading.
    private final BufferedReader reader;

    /**
     * Creates a BufferedLineReader from any Java Reader.
     *
     * Using Reader instead of a specific type such as StringReader
     * keeps this class flexible: the source could later be a file,
     * a string, or another kind of character stream.
     */
    public BufferedLineReader(Reader reader) {

        // Wrap the provided Reader in Java's BufferedReader.
        this.reader = new BufferedReader(reader);
    }

    @Override
    public String readLine() throws IOException {

        // Delegate the actual reading to Java's BufferedReader.
        // BufferedReader.readLine() returns the next line,
        // or null when there are no more lines.
        return reader.readLine();
    }

}
