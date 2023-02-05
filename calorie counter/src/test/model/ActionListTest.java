package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionListTest {
    private ActionList actionList;

    @BeforeEach
    void runBefore() {
        actionList = new ActionList();
    }


    @Test
    void testAddAction() {
        assertEquals(0, actionList.countActionList());
        actionList.addAction("eat", "rice", 50);
        assertEquals("eat", actionList.getAction(0).getActionType());
        assertEquals("rice", actionList.getAction(0).getActionName());
        assertEquals(50, actionList.getAction(0).getAmountCal());
        assertEquals(1, actionList.countActionList());
        actionList.addAction("exercise", "run", 400);
        assertEquals(2, actionList.countActionList());
        assertEquals("exercise", actionList.getAction(1).getActionType());
        assertEquals("run", actionList.getAction(1).getActionName());
        assertEquals(400, actionList.getAction(1).getAmountCal());
    }

    @Test
    void testSetMaximumCal(){
        assertEquals(2000, actionList.getMaximumCal());
        actionList.setMaximumCal(1000);
        assertEquals(1000, actionList.getMaximumCal());
        actionList.setMaximumCal(2000);
        assertEquals(2000, actionList.getMaximumCal());
    }

    @Test
    void testGetDeficit(){
        assertEquals(2000, actionList.getDeficit());
        actionList.addAction("eat", "rice", 50);
        assertEquals(1950, actionList.getDeficit());
        actionList.addAction("exercise", "run", 400);
        assertEquals((1950 + 400), actionList.getDeficit());
        actionList.addAction("eat", "hotpot", 3000);
        assertEquals((1950+400-3000),actionList.getDeficit());
        actionList.setMaximumCal(4000);
        assertEquals((4000 - 50 + 400 - 3000),actionList.getDeficit());
        actionList.addAction("eat","noodle", 1350);
        assertEquals(0, actionList.getDeficit());
    }

    @Test
    void testCountActionList(){
        assertEquals(0, actionList.countActionList());
        actionList.addAction("eat", "rice", 50);
        assertEquals(1, actionList.countActionList());
        actionList.addAction("exercise", "run", 400);
        assertEquals(2, actionList.countActionList());
    }

    @Test
    void testGetAction(){
        actionList.addAction("eat", "rice", 50);
        assertEquals("eat", actionList.getAction(0).getActionType());
        assertEquals("rice", actionList.getAction(0).getActionName());
        assertEquals(50, actionList.getAction(0).getAmountCal());
        actionList.addAction("exercise", "run", 400);
        assertEquals("exercise", actionList.getAction(1).getActionType());
        assertEquals("run", actionList.getAction(1).getActionName());
        assertEquals(400, actionList.getAction(1).getAmountCal());
    }

    @Test
    void testGetString(){
        assertEquals("", actionList.getString());
        actionList.addAction("eat", "rice", 50);
        assertEquals("Ate rice intake 50 Cals\n", actionList.getString());
        actionList.addAction("exercise", "run", 400);
        assertEquals("Ate rice intake 50 Cals\nDid exercise run burn 400 Cals\n", actionList.getString());
    }

    @Test
    void testGetListString(){
        assertEquals(0, actionList.getListString().size());
        actionList.addAction("eat", "rice", 50);
        assertEquals("Ate rice intake 50 Cals", actionList.getListString().get(0));
        assertEquals(1, actionList.getListString().size());
        actionList.addAction("exercise", "run", 400);
        assertEquals("Ate rice intake 50 Cals", actionList.getListString().get(0));
        assertEquals("Did exercise run burn 400 Cals", actionList.getListString().get(1));
        assertEquals(2, actionList.getListString().size());
    }

    @Test
    void testToJson(){
        actionList.addAction("eat", "rice", 50);
        actionList.addAction("exercise", "run", 400);
        JSONArray json = actionList.toJson();
        JSONObject eatRice = (JSONObject) json.get(0);
        JSONObject run = (JSONObject) json.get(1);
        assertEquals("eat", eatRice.getString("actionType"));
        assertEquals("rice", eatRice.getString("actionName"));
        assertEquals(50, eatRice.getInt("amountCal"));
        assertEquals("exercise", run.getString("actionType"));
        assertEquals("run", run.getString("actionName"));
        assertEquals(400, run.getInt("amountCal"));
    }


    @Test
    void testEvent() {
        List<Event> l = new ArrayList<Event>();
        actionList.addAction("eat", "rice", 50);
        actionList.addAction("exercise", "run", 300);
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals(2, l.size());
        assertEquals("one eat action added to action list", l.get(0).getDescription());
        assertEquals("one exercise action added to action list", l.get(1).getDescription());
    }


}
