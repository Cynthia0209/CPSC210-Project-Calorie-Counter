package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {
    private Action eatRice;
    private Action run;

    @BeforeEach
    void runBefore() {
        eatRice = new Action("eat", "rice", 50);
        run = new Action("exercise", "run", 400);
    }

    @Test
    void testGetter(){
        assertEquals("eat", eatRice.getActionType());
        assertEquals("rice", eatRice.getActionName());
        assertEquals(50, eatRice.getAmountCal());
        assertEquals("exercise",run.getActionType());
        assertEquals("run", run.getActionName());
        assertEquals(400, run.getAmountCal());
    }

    @Test
    void testToJson(){
        JSONObject json1 = eatRice.toJson();
        JSONObject json2 = run.toJson();
        assertEquals("eat", json1.getString("actionType"));
        assertEquals("rice", json1.getString("actionName"));
        assertEquals(50,json1.getInt("amountCal"));
        assertEquals("exercise", json2.getString("actionType"));
        assertEquals("run", json2.getString("actionName"));
        assertEquals(400,json2.getInt("amountCal"));
    }
}
