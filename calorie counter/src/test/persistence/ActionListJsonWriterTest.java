package persistence;

import model.ActionList;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ActionListJsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            ActionList actionList= new ActionList();
            ActionListJsonWriter writer = new ActionListJsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testWriterEmptyActionList() {
        try {
            ActionList actionList = new ActionList();
            ActionListJsonWriter writer = new ActionListJsonWriter("./data/testWriterEmptyActionList.json");
            writer.open();
            writer.write(actionList);
            writer.close();

            ActionListJsonReader reader = new ActionListJsonReader("./data/testWriterEmptyActionList.json");
            actionList = reader.read();
            assertEquals(0, actionList.countActionList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralActionList() {
        try {
            ActionList actionList = new ActionList();
            actionList.addAction("eat", "rice", 50);
            actionList.addAction("exercise", "run", 400);
            ActionListJsonWriter writer = new ActionListJsonWriter("./data/testWriterGeneralActionList.json");
            writer.open();
            writer.write(actionList);
            writer.close();

            ActionListJsonReader reader = new ActionListJsonReader("./data/testWriterGeneralActionList.json");
            actionList = reader.read();
            assertEquals(2, actionList.countActionList());
            assertEquals("eat", actionList.getAction(0).getActionType());
            assertEquals("rice", actionList.getAction(0).getActionName());
            assertEquals(50, actionList.getAction(0).getAmountCal());
            assertEquals("exercise", actionList.getAction(1).getActionType());
            assertEquals("run", actionList.getAction(1).getActionName());
            assertEquals(400, actionList.getAction(1).getAmountCal());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
