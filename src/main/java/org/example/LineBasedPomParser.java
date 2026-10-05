package org.example;

import java.io.IOException;
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
        // No first line means that the file is empty.
        if (line == null) {
            return null;
        }

        // Q14.2:
        // The first line must declare the project.
        if (!line.startsWith("project ")) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        // Q14.2:
        // Extract the project name after the "project " prefix.
        String projectName = line.substring("project ".length());

        // Q14.4:
        // We now need a collection because the project may contain
        // several direct dependencies.
        Set<Gav> dependencies = new HashSet<>();

        // Read the first line after the project declaration.
        line = reader.readLine();

        // Q14.4:
        // Continue reading until the end of the input.
        // This extends Q14.3 from one dependency to several dependencies.
        while (line != null) {

            // Q14.4:
            // Empty lines are allowed and must simply be ignored.
            if (line.isEmpty()) {
                line = reader.readLine();
                continue;
            }

            // Q14.3:
            // A dependency line contains a GAV coordinate
            // after the "dependency " prefix.
            if (line.startsWith("dependency ")) {

                // Extract the textual GAV coordinate.
                String gavText =
                        line.substring("dependency ".length());

                // Convert and validate the textual coordinate.
                Gav gav = Gav.parse(gavText);

                // Q14.3 handled one dependency.
                // Q14.4 extends this behavior because the loop
                // allows several dependencies to be collected.
                dependencies.add(gav);
            }

            // Q14.4:
            // Read the next line before continuing the loop.
            line = reader.readLine();
        }

        // Q14.2 / Q14.3 / Q14.4:
        // Return the final Project containing its name
        // and all direct dependencies that were found.
        return new Project(projectName, dependencies);
    }
}
