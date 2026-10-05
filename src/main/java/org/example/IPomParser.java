package org.example;


import java.io.IOException;

/**
 * Contract for components that parse a minibuild project.
 */
public interface IPomParser {
    // Parse the input provided by an ILineReader
    // and return the corresponding Project.
    Project parse(ILineReader reader) throws IOException;
}
