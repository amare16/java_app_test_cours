package org.example;

import java.io.IOException;
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
        // No first line means that the file is empty.
        if (line == null) {
            return null;
        }

        // The cases currently implemented require
        // the first line to declare the project.
        if (!line.startsWith("project ")) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        // Q14.2:
        // Extract the project name from the declaration.
        String projectName = line.substring("project ".length());

        // Read the next line.
        String nextLine = reader.readLine();

        // Q14.2:
        // If there is no next line, the project
        // has no declared dependencies.
        if (nextLine == null) {
            return new Project(projectName, Set.of());
        }

        // Q14.3:
        // At this stage of TDD, we handle exactly
        // one dependency declaration.
        if (nextLine.startsWith("dependency ")) {

            // Extract the textual GAV coordinate.
            String gavText =
                    nextLine.substring("dependency ".length());

            // Convert the coordinate into a Gav object.
            Gav gav = Gav.parse(gavText);

            // Return a project containing this one dependency.
            return new Project(projectName, Set.of(gav));
        }

        // Empty lines and multiple dependencies
        // are intentionally not handled yet.
        // They belong to the next TDD cycle, Q14.4.
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
