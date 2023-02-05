package model;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

// A list of actions
public class ActionList {

    private final ArrayList<Action> actionList;
    private int maximumCal = 2000;

    // EFFECTS: create a new list of actions
    public ActionList() {
        actionList = new ArrayList<>();
    }

    // REQUIRES: type is either "eat" or "exercise"
    //           name has a non-zero length
    //           both type and name should be lowercase
    //           cal has to be >= 0
    // MODIFIES: actionList
    // EFFECTS: add an action to the actionList.
    public void addAction(String type, String name, int cal) {
        actionList.add(new Action(type, name, cal));
        if (type.equals("eat")) {
            EventLog.getInstance().logEvent(new Event("one eat action added to action list"));
        } else {
            EventLog.getInstance().logEvent(new Event("one exercise action added to action list"));
        }
    }

    // REQUIRES: amount > 0
    // MODIFIES: maximumCal
    // EFFECTS: set up a maximum calorie
    public void setMaximumCal(int amount) {
        maximumCal = amount;
    }

    // EFFECTS: return MaximumCal()
    public int getMaximumCal() {
        return maximumCal;
    }

    // MODIFIES: this
    // EFFECTS: get the deficit which is the maximum calorie - calorie burn + calorie eat.
    public int getDeficit() {
        int currentCalorie = 0;
        for (Action action : actionList) {
            if (action.getActionType().equals("eat")) {
                currentCalorie = currentCalorie + action.getAmountCal();
            } else {
                currentCalorie = currentCalorie - action.getAmountCal();
            }
        }
        return maximumCal - currentCalorie;
    }

    // EFFECTS: count how many actions are in the actionList
    public int countActionList() {
        return actionList.size();
    }

    // REQUIRES: i <= actionList.size - 1
    // EFFECTS: get the (i+1)th Action in the actionList
    public Action getAction(int i) {
        return actionList.get(i);
    }

    // MODIFIES: this
    // EFFECTS: turn the actionList into string
    public String getString() {
        String str = "";
        for (Action action : actionList) {
            if (action.getActionType().equals("eat")) {
                str = str + "Ate " + action.getActionName() + " intake " + action.getAmountCal()
                        + " Cals" + "\n";
            } else {
                str = str + "Did exercise " + action.getActionName() + " burn "
                        + action.getAmountCal() + " Cals" + "\n";
            }
        }
        return str;
    }

    // MODIFIES: this
    // EFFECTS: change the actionList into List<String>
    public List<String> getListString() {
        List<String> str = new ArrayList<>();
        for (Action action : actionList) {
            if (action.getActionType().equals("eat")) {
                str.add("Ate " + action.getActionName() + " intake " + action.getAmountCal()
                        + " Cals");
            } else {
                str.add("Did exercise " + action.getActionName() + " burn "
                        + action.getAmountCal() + " Cals");
            }
        }
        return str;
    }

    // EFFECTS: returns ActionList as a JSON array
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        for (Action action : actionList) {
            jsonArray.put(action.toJson());
        }
        return jsonArray;
    }

}
