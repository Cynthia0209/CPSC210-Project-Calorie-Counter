package persistence;

import model.WeightList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WeightListJsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        WeightListJsonReader reader = new WeightListJsonReader("./data/noSuchFile.json");
        try {
            WeightList weightList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWeightList() {
        WeightListJsonReader reader = new WeightListJsonReader("./data/testReaderEmptyWeightList.json");
        try {
            WeightList weightList = reader.read();
            assertEquals(0, weightList.countWeightList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWeightList() {
        WeightListJsonReader reader = new WeightListJsonReader("./data/testReaderGeneralWeightList.json");
        try {
            WeightList weightList = reader.read();
            assertEquals(3,weightList.countWeightList());
            assertEquals(30, weightList.getWeight(0));
            assertEquals(40, weightList.getWeight(1));
            assertEquals(70, weightList.getWeight(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
