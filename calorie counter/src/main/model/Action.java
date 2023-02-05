package model;

import org.json.JSONObject;

// Represents an action(either eat or exercise) with a name (either a food name or an exercise name)
// and given calorie(in cals)
public class Action {
    private final String actionType;
    private final String actionName;
    private final int amountCal;

    // REQUIRES: actionType is either "eat" or "exercise"
    //           actionName has a non-zero length
    //           both actonType and actionName should be lowercase
    //           amountCal has to be >= 0
    // EFFECTS: Construct an action (either eat or exercise) with its name and given calorie
    public Action(String actionType, String actionName, int amountCal) {
        this.actionType = actionType;
        this.actionName = actionName;
        this.amountCal = amountCal;
    }

    // EFFECTS: get the action type (either eat or exercise).
    public String getActionType() {
        return actionType;
    }

    // EFFECTS: get the action name.
    public String getActionName() {
        return actionName;
    }

    // EFFECTS: get the action calorie
    public int getAmountCal() {
        return amountCal;
    }

    // EFFECTS: returns Action as a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("actionType", actionType);
        json.put("actionName", actionName);
        json.put("amountCal", amountCal);
        return json;
    }
}
