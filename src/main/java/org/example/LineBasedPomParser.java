package org.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
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
        // The first line must declare the project.
        if (line.startsWith("project ")) {
            // Extract the project name after "project ".
            String projectName = line.substring("project ".length());

            // Store the direct dependencies found in the file.
            Set<Gav> dependencies = new HashSet<>();

            // Q14.3:
            // Continue reading the remaining lines.
            line = reader.readLine();

            while (line != null) {

                // A dependency line contains a GAV coordinate
                // after the "dependency " prefix.
                if (line.startsWith("dependency ")) {

                    String gavText =
                            line.substring("dependency ".length());

                    // Gav.parse converts the textual coordinate
                    // into a Gav object.
                    Gav gav = Gav.parse(gavText);

                    dependencies.add(gav);
                }

                // Read the next line.
                line = reader.readLine();
            }

            // Return the final state produced by the parser.
            return new Project(projectName, dependencies);
        }

        // Other malformed inputs will be handled in later TDD cycles.
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
