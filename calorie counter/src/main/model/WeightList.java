package model;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

// A list of weight (in kg)
public class WeightList {

    private final ArrayList<Integer> weightList;

    // Create a list of weight
    public WeightList() {
        weightList = new ArrayList<>();
    }

    // REQUIRES: weight >= 0
    // MODIFIES: weightList
    // EFFECTS: add a weight to the weightList.
    public void addWeight(int weight) {
        weightList.add(weight);
        EventLog.getInstance().logEvent(new Event("one " + weight + " kg weight added to weight list"));
    }

    // EFFECTS: count how many weights are in the weightList.
    public int countWeightList() {
        return weightList.size();
    }

    // REQUIRES: i <= weightList.size - 1
    // EFFECTS: get the (i+1)th weight in the weightList
    public int getWeight(int i) {
        return weightList.get(i);
    }

    // MODIFIES: this
    // EFFECTS: change the weightList into String
    public String getString() {
        String str = "";
        int count = 1;
        for (int i : weightList) {
            str = str + "Day " + count + ": " + i + "kg " + "\n";
            count = count + 1;
        }
        return str;
    }

    // MODIFIES: this
    // EFFECTS: change the weightList into List<String>
    public List<String> getListString() {
        List<String> str = new ArrayList<>();
        int count = 1;
        for (int i : weightList) {
            str.add("Day " + count + ": " + i + "kg ");
            count = count + 1;
        }
        return str;
    }

    // REQUIRES: weightList has to have at least two elements
    // EFFECTS: return the difference between the first weight and the last weight
    public int getTotalChange() {
        int size = weightList.size();
        return weightList.get(0) - weightList.get(size - 1);
    }

    // REQUIRES: weightList has to have at least two elements
    // EFFECTS: return the difference between the last weight and the second last weight
    public int getRecentChange() {
        int size = weightList.size();
        return weightList.get(size - 2) - weightList.get(size - 1);
    }

    // EFFECTS: returns weightList as a JSON array
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        for (int w: weightList) {
            jsonArray.put(w);
        }
        return jsonArray;
    }
}




