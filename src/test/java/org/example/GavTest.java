package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GavTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/gav-valid.csv")

    void parseGavShouldReturnCorrectValues(
            String input,
            String expectedGroup,
            String expectedArtifact,
            String expectedVersion
    ) {
        Gav gav = Gav.parse(input);

        assertEquals(expectedGroup, gav.group());
        assertEquals(expectedArtifact, gav.artifact());
        assertEquals(expectedVersion, gav.version());
    }

    @Test
    void parseShouldThrowExceptionWhenGroupIsMissing() {
        // This GAV is invalid because the group (before the first ":") is empty.
        String invalidGav = ":lib-a:1.0.0";

        // assertThrows verifies that Gav.parse() throws the expected exception.
        // For now, our implementation does not correctly validate this case,
        // so this test should help us reach the RED stage.
        assertThrows(
                IllegalArgumentException.class,
                () -> Gav.parse(invalidGav)
        );
    }

    @Test
    void parseShouldThrowExceptionWhenArtifactIsMissing() {
        // This GAV is invalid because the artifact
        // between the two ":" characters is empty.
        String invalidGav = "org.acme::1.0.0";

        // The parser should reject the malformed GAV
        // by throwing an IllegalArgumentException.
        assertThrows(
                IllegalArgumentException.class,
                () -> Gav.parse(invalidGav)
        );
    }

    @Test
    void parseShouldThrowExceptionWhenVersionIsMissing() {

        // This GAV is invalid because the version
        // after the last ":" is empty.
        String invalidGav = "org.acme:lib-a:";

        // The parser should reject this malformed GAV
        // by throwing an IllegalArgumentException.
        assertThrows(
                IllegalArgumentException.class,
                () -> Gav.parse(invalidGav)
        );
    }

    @Test
    void parseShouldThrowExceptionWhenThereAreTooFewParts() {

        // A valid GAV must contain exactly:
        // group : artifact : version
        //
        // This input contains only group and artifact.
        String invalidGav = "org.acme:lib-a";

        // Gav.parse() should reject the malformed coordinate
        // with the exception required by our contract.
        assertThrows(
                IllegalArgumentException.class,
                () -> Gav.parse(invalidGav)
        );
    }

    @Test
    void parseShouldThrowExceptionWhenThereAreTooManyParts() {

        // A valid GAV must contain exactly three parts:
        // group : artifact : version
        //
        // This input has a fourth unexpected part ("extra").
        String invalidGav = "org.acme:lib-a:1.0.0:extra";

        // Gav.parse() should reject a coordinate containing
        // more than the three expected parts.
        assertThrows(
                IllegalArgumentException.class,
                () -> Gav.parse(invalidGav)
        );
    }
}
