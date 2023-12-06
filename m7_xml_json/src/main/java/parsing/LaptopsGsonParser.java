package parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Laptops;

import java.io.*;
import java.time.LocalDate;

public class LaptopsGsonParser {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.setPrettyPrinting().registerTypeAdapter(LocalDate.class , new LocalDateGsonAdapter().nullSafe()).create();

    public static void writeJson(Laptops laptops, String fileName) {
        // -- adapter
        String jsonString = gson.toJson(laptops);
        try (FileWriter jsonWriter = new FileWriter(fileName)) {
            jsonWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Laptops readJson(String fileName) {
        try (BufferedReader data = new BufferedReader(new FileReader(fileName))) {
            return gson.fromJson(data, Laptops.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
