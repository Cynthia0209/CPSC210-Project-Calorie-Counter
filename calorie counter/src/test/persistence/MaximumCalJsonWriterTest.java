package persistence;

import model.ActionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MaximumCalJsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            ActionList actionList = new ActionList();
            MaximumCalJsonWriter writer = new MaximumCalJsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMaximumCal() {
        try {
            ActionList actionList = new ActionList();
            MaximumCalJsonWriter writer = new MaximumCalJsonWriter("./data/testWriterEmptyMaximumCal.json");
            writer.open();
            writer.write(actionList);
            writer.close();

            MaximumCalJsonReader reader = new MaximumCalJsonReader("./data/testWriterEmptyMaximumCal.json");
            int max = reader.read();
            assertEquals(2000, max);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralMaximumCal() {
        try {
            ActionList actionList = new ActionList();
            actionList.setMaximumCal(1200);
            MaximumCalJsonWriter writer = new MaximumCalJsonWriter("./data/testWriterGeneralMaximumCal.json");
            writer.open();
            writer.write(actionList);
            writer.close();

            MaximumCalJsonReader reader = new MaximumCalJsonReader("./data/testWriterGeneralMaximumCal.json");
            int max = reader.read();
            assertEquals(1200, max);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
