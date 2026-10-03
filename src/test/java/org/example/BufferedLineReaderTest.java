package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for BufferedLineReader.
 *
 * We use a StringReader instead of a real file,
 * as requested by the TD.
 */
public class BufferedLineReaderTest {
    @Test
    void readLineShouldReturnLinesOneByOne() throws IOException {

        // Simulate text containing two lines.
        // No physical file is needed.
        StringReader stringReader = new StringReader(
                "first line\nsecond line"
        );

        // Create the component that we want to test.
        // This class does not exist yet, so this is our RED stage.
        ILineReader reader = new BufferedLineReader(stringReader);

        // Each call to readLine() should return the next line.
        assertEquals("first line", reader.readLine());
        assertEquals("second line", reader.readLine());
    }
}
