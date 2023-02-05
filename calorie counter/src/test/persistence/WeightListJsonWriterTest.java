package persistence;

import model.WeightList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WeightListJsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            WeightList weightList = new WeightList();
            WeightListJsonWriter writer = new WeightListJsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testWriterEmptyWeightList() {
        try {
            WeightList weightList = new WeightList();
            WeightListJsonWriter writer = new WeightListJsonWriter("./data/testWriterEmptyWeightList.json");
            writer.open();
            writer.write(weightList);
            writer.close();

            WeightListJsonReader reader = new WeightListJsonReader("./data/testWriterEmptyWeightList.json");
            weightList = reader.read();
            assertEquals(0, weightList.countWeightList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWeightList() {
        try {
            WeightList weightList = new WeightList();
            weightList.addWeight(30);
            weightList.addWeight(40);
            weightList.addWeight(70);
            WeightListJsonWriter writer = new WeightListJsonWriter("./data/testWriterGeneralWeightList.json");
            writer.open();
            writer.write(weightList);
            writer.close();

            WeightListJsonReader reader = new WeightListJsonReader("./data/testWriterGeneralWeightList.json");
            weightList = reader.read();
            assertEquals(3,weightList.countWeightList());
            assertEquals(30, weightList.getWeight(0));
            assertEquals(40, weightList.getWeight(1));
            assertEquals(70, weightList.getWeight(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
