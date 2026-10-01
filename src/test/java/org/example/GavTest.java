package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GavTest {
    @ParameterizedTest
    @CsvSource({
            "org.acme:lib-a:1.0.0, org.acme, lib-a, 1.0.0",
            "org.other:lib-c:3.0.0, org.other, lib-c, 3.0.0"
    })

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
}
