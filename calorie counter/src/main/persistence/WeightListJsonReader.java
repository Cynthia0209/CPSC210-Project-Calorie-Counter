package persistence;

import model.WeightList;
import org.json.JSONArray;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads weightList from JSON data stored in file
// refer to JsonSerializationDemo
public class WeightListJsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public WeightListJsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads weightList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WeightList read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseWeightList(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses weightList from JSON array and returns it
    private WeightList parseWeightList(JSONArray jsonArray) {
        WeightList weightList = new WeightList();
        for (Object object : jsonArray) {
            weightList.addWeight(object.hashCode());
        }
        return weightList;
    }
}
