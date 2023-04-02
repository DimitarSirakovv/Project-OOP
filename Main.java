import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JSONFileProcessor {

    public static void main(String[] args) {
        String filename = "example.json";
        try {
            // Open the file
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
            String jsonString = jsonBuilder.toString();

            // Validate the JSON syntax
            Gson gson = new Gson();
            try {
                JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
                JsonParser parser = new JsonParser();
                JsonElement parsedJsonElement = parser.parse(jsonString);
                if (!parsedJsonElement.equals(jsonElement)) {
                    throw new JsonSyntaxException("The JSON syntax is invalid");
                }
            } catch (JsonSyntaxException e) {
                System.err.println("The JSON syntax is invalid: " + e.getMessage());
                return;
            }

            // Print the content of the object in a readable form
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            Object object = gson.fromJson(jsonString, Object.class);
            String prettyJsonString = prettyGson.toJson(object);
            System.out.println(prettyJsonString);

            // Perform other operations here

        } catch (IOException e) {
            System.err.println("Error opening the file: " + e.getMessage());
        }
    }
}