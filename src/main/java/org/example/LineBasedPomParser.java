package org.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * Parses a minibuild file line by line.
 */
public class LineBasedPomParser implements IPomParser {
    /**
     * Parses the content provided by an ILineReader.
     *
     * For Q14.1, we only handle the empty-file case.
     */

    @Override
    public Project parse(ILineReader reader) throws IOException {

        // Read the first line of the build file.
        String line = reader.readLine();

        // Q14.1:
        // If there is no first line, the file is empty.
        if (line == null) {
            return null;
        }

        // Q14.2:
        // A line beginning with "project " declares the project name.
        if (line.startsWith("project ")) {

            // Remove the "project " prefix to obtain only the project name.
            String projectName = line.substring("project ".length());

            // At this stage of TDD, dependencies are not handled yet.
            // Therefore, the project contains an empty dependency set.
            return new Project(projectName, Set.of());
        }

        // Other input formats will be handled in later TDD cycles.
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
