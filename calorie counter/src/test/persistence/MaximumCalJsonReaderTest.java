package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MaximumCalJsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        MaximumCalJsonReader reader= new MaximumCalJsonReader("./data/noSuchFile.json");
        try {
            int max = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testReaderGeneralMaximumCal() {
        MaximumCalJsonReader reader= new MaximumCalJsonReader("./data/testGeneralMaximumCal.json");
        try {
            int max = reader.read();
            assertEquals(2000, max);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
