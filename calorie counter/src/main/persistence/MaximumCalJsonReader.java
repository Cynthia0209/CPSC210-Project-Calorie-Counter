package persistence;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads maximumCal from JSON data stored in file
// refer to JsonSerializationDemo
public class MaximumCalJsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public MaximumCalJsonReader(String source) {
        this.source = source;
    }

    // REQUIRES: source can not be empty
    // EFFECTS: reads maximumCal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public int read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMaximumCal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses maximumCal from JSON object and returns it
    private int parseMaximumCal(JSONObject jsonObject) {
        return jsonObject.getInt("maximum calorie");
    }
}
