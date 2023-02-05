package persistence;


import model.ActionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ActionListJsonReaderTest{

    @Test
    void testReaderNonExistentFile() {
        ActionListJsonReader reader = new ActionListJsonReader("./data/noSuchFile.json");
        try {
            ActionList actionList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyActionList() {
        ActionListJsonReader reader = new ActionListJsonReader("./data/testReaderEmptyActionList.json");
        try {
            ActionList actionList = reader.read();
            assertEquals(0, actionList.countActionList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralActionList() {
        ActionListJsonReader reader = new ActionListJsonReader("./data/testReaderGeneralActionList.json");
        try {
            ActionList actionList = reader.read();
            assertEquals(2, actionList.countActionList());
            assertEquals("eat", actionList.getAction(0).getActionType());
            assertEquals("rice", actionList.getAction(0).getActionName());
            assertEquals(50, actionList.getAction(0).getAmountCal());
            assertEquals("exercise", actionList.getAction(1).getActionType());
            assertEquals("run", actionList.getAction(1).getActionName());
            assertEquals(400, actionList.getAction(1).getAmountCal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
