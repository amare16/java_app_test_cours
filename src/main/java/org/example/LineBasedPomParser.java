package org.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Parses a minibuild file line by line.
 */
public class LineBasedPomParser {
    /**
     * Parses the content provided by an ILineReader.
     *
     * For Q14.1, we only handle the empty-file case.
     */
    public Project parse(ILineReader reader) throws IOException {
        // Read the first line of the build file.
        String line = reader.readLine();

        // Q14.1: if there is no first line,
        // the build file is empty.
        if (line == null) {
            return null;
        }

        // Other cases will be implemented in the next TDD cycles.
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
