package persistence;

import model.Action;
import model.ActionList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads actionList from JSON data stored in file
// refer to JsonSerializationDemo
public class ActionListJsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public ActionListJsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads actionList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ActionList read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseActionList(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses actionList from JSON array and returns it
    private ActionList parseActionList(JSONArray jsonArray) {
        ActionList actionList = new ActionList();
        for (Object object : jsonArray) {
            Action action = parseAction((JSONObject) object);
            actionList.addAction(action.getActionType(), action.getActionName(), action.getAmountCal());
        }
        return actionList;
    }

    // EFFECTS: parses Action from JSON object and returns it
    private Action parseAction(JSONObject jsonObject) {
        String type = jsonObject.getString("actionType");
        String name = jsonObject.getString("actionName");
        int amount = jsonObject.getInt("amountCal");
        Action action = new Action(type,name,amount);
        return action;
    }
}


