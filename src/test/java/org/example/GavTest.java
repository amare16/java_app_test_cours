package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GavTest {
    @Test
    void parseGavShouldReturnCorrectGroup() {
        Gav gav = Gav.parse("org.acme:lib-a:1.0.0");

        assertEquals("org.acme", gav.group());
    }
}
