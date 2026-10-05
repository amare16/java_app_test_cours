package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for LineBasedPomParser.
 *
 * ILineReader will be replaced by a Mockito test double,
 * so these tests do not depend on a real file.
 */

public class LineBasedPomParserTest {
    /**
     * Q14.1 - Empty file.
     *
     * Test description:
     * [ILineReader | readLine ↦ ⊥]s
     * ⊢ parse(reader) ⇒ ⊥
     */
    @Test
    void parseShouldReturnNullForEmptyFile() throws IOException {
        // Create a test double for the parser's collaborator.
        ILineReader reader = mock(ILineReader.class);

        // Configure the double as a Stub:
        // null means that there is no line to read, so the file is empty.
        when(reader.readLine()).thenReturn(null);

        // SUT: the real component that we want to test.
        LineBasedPomParser parser = new LineBasedPomParser();

        // Execute the behavior under test.
        Project project = parser.parse(reader);

        // State-oriented assertion:
        // for Q14.1, we choose null as the result for an empty file.
        assertNull(project);
    }

    @Test
    void parseShouldReturnProjectWithNoDependencies() throws IOException {

        // Create a test double for the parser's collaborator.
        ILineReader reader = mock(ILineReader.class);

        // Configure it as a Stub:
        // first call -> the project declaration
        // second call -> null, meaning end of input.
        when(reader.readLine()).thenReturn(
                "project mon-app",
                null
        );

        // SUT: the real parser that we are testing.
        LineBasedPomParser parser = new LineBasedPomParser();

        // Parse the simulated build file.
        Project project = parser.parse(reader);

        // State-oriented assertions:
        // we verify the Project returned by the parser.
        assertEquals("mon-app", project.name());
        assertEquals(Set.of(), project.dependencies());
    }

    /**
     * Q14.3 - File with one dependency.
     *
     * Test description:
     * [ILineReader | readLine ↦ ⟨
     *     "project mon-app",
     *     "dependency org.acme:lib-a:1.0.0",
     *     ⊥
     * ⟩]s
     * ⊢ parse(reader) ⇒ Project(
     *     "mon-app",
     *     {org.acme:lib-a:1.0.0}
     * )
     */

    @Test
    void parseShouldReturnProjectWithOneDependency() throws IOException {

        // Create a test double for the parser's collaborator.
        ILineReader reader = mock(ILineReader.class);

        // Configure the double as a Stub.
        // It simulates a build file containing:
        // 1. the project declaration,
        // 2. one dependency,
        // 3. the end of the file.
        when(reader.readLine()).thenReturn(
                "project mon-app",
                "dependency org.acme:lib-a:1.0.0",
                (String) null
        );

        // SUT: the real parser.
        LineBasedPomParser parser = new LineBasedPomParser();

        // Parse the simulated build file.
        Project project = parser.parse(reader);

        // State-oriented assertions:
        // verify the final Project returned by the parser.
        assertEquals("mon-app", project.name());
        assertEquals(
                Set.of(Gav.parse("org.acme:lib-a:1.0.0")),
                project.dependencies()
        );
    }

    /**
     * Q14.4 - File with multiple dependencies and empty lines.
     *
     * Test description:
     * [ILineReader | readLine ↦ ⟨
     *     "project mon-app",
     *     "",
     *     "dependency org.acme:lib-a:1.0.0",
     *     "",
     *     "dependency org.acme:lib-b:2.1.0",
     *     ⊥
     * ⟩]s
     * ⊢ parse(reader) ⇒ Project(
     *     "mon-app",
     *     {org.acme:lib-a:1.0.0, org.acme:lib-b:2.1.0}
     * )
     */
    @Test
    void parseShouldHandleMultipleDependenciesAndEmptyLines() throws IOException {

        // Create a test double for the parser's collaborator.
        ILineReader reader = mock(ILineReader.class);

        // Configure the double as a Stub.
        // Empty strings represent empty lines in the build file.
        when(reader.readLine()).thenReturn(
                "project mon-app",
                "",
                "dependency org.acme:lib-a:1.0.0",
                "",
                "dependency org.acme:lib-b:2.1.0",
                (String) null
        );

        // SUT: the real parser.
        LineBasedPomParser parser = new LineBasedPomParser();

        // Parse the simulated build file.
        Project project = parser.parse(reader);

        // State-oriented assertions:
        // verify the project name and all direct dependencies.
        assertEquals("mon-app", project.name());

        assertEquals(
                Set.of(
                        Gav.parse("org.acme:lib-a:1.0.0"),
                        Gav.parse("org.acme:lib-b:2.1.0")
                ),
                project.dependencies()
        );
    }

    /**
     * Q14.5 - Malformed file: unknown line.
     *
     * Test description:
     * [ILineReader | readLine ↦ ⟨
     *     "project mon-app",
     *     "something invalid",
     *     ⊥
     * ⟩]s
     * ⊢ parse(reader) ⇒ ↯ IllegalArgumentException
     */
    @Test
    void parseShouldRejectUnknownLine() throws IOException {

        // Create a test double for the parser's collaborator.
        ILineReader reader = mock(ILineReader.class);

        // Configure the Stub to simulate a malformed build file.
        // The second line is neither an empty line nor
        // a valid "dependency ..." declaration.
        when(reader.readLine()).thenReturn(
                "project mon-app",
                "something invalid",
                (String) null
        );

        // SUT: the real parser.
        LineBasedPomParser parser = new LineBasedPomParser();

        // A malformed line must be rejected.
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(reader)
        );
    }
}
