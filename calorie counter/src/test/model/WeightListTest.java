package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightListTest {
    private WeightList weightList;

    @BeforeEach
    void runBefore() {
        weightList = new WeightList();
    }


    @Test
    void testAddWeight(){
        assertEquals(0, weightList.countWeightList());
        weightList.addWeight(64);
        assertEquals(64, weightList.getWeight(0));
        assertEquals(1, weightList.countWeightList());
        weightList.addWeight(56);
        assertEquals(2, weightList.countWeightList());
        assertEquals(56, weightList.getWeight(1));
    }

    @Test
    void testCountWeightList(){
        assertEquals(0, weightList.countWeightList());
        weightList.addWeight(64);
        assertEquals(1, weightList.countWeightList());
        weightList.addWeight(56);
        assertEquals(2, weightList.countWeightList());
    }

    @Test
    void testGetWeight(){
        weightList.addWeight(64);
        assertEquals(64, weightList.getWeight(0));
        weightList.addWeight(56);
        assertEquals(56, weightList.getWeight(1));
    }

    @Test
    void testGetString(){
        assertEquals("", weightList.getString());
        weightList.addWeight(30);
        assertEquals("Day 1: 30kg \n", weightList.getString());
        weightList.addWeight(34);
        assertEquals("Day 1: 30kg \nDay 2: 34kg \n", weightList.getString());
    }

    @Test
    void testGetListString() {
        assertEquals(0, weightList.getListString().size());
        weightList.addWeight(30);
        assertEquals("Day 1: 30kg ", weightList.getListString().get(0));
        assertEquals(1, weightList.getListString().size());
        weightList.addWeight(34);
        assertEquals("Day 2: 34kg ", weightList.getListString().get(1));
        assertEquals(2, weightList.getListString().size());
    }

    @Test
    void testGetTotalChange(){
        weightList.addWeight(30);
        weightList.addWeight(40);
        weightList.addWeight(70);
        assertEquals(-40, weightList.getTotalChange());
        weightList.addWeight(50);
        assertEquals(-20, weightList.getTotalChange());
        weightList.addWeight(30);
        assertEquals(0, weightList.getTotalChange());
        weightList.addWeight(10);
        assertEquals(20,weightList.getTotalChange());
    }
    @Test
    void testGetRecentChange(){
        weightList.addWeight(30);
        weightList.addWeight(40);
        weightList.addWeight(70);
        assertEquals(-30, weightList.getRecentChange());
        weightList.addWeight(50);
        assertEquals(20, weightList.getRecentChange());
        weightList.addWeight(50);
        assertEquals(0, weightList.getRecentChange());
        weightList.addWeight(10);
        assertEquals(40,weightList.getRecentChange());
    }

    @Test
    void testToJson(){
        weightList.addWeight(30);
        weightList.addWeight(40);
        weightList.addWeight(70);
        JSONArray json = weightList.toJson();
        assertEquals(30, json.get(0).hashCode());
        assertEquals(40, json.get(1).hashCode());
        assertEquals(70, json.get(2).hashCode());
    }

    @Test
    void testEvent() {
        List<Event> l = new ArrayList<Event>();
        weightList.addWeight(65);
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertEquals(9, l.size());
        assertEquals("one 64 kg weight added to weight list", l.get(0).getDescription());
    }
}
