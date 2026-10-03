package org.example;

import java.io.IOException;

/**
 * Abstraction for reading text one line at a time.
 */
public interface ILineReader {
    // Reads the next available line.
    // IOException represents a possible input/output reading error.
    String readLine() throws IOException;
}
