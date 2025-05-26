package Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDataReader {
    /**
     * Reads the content of a JSON file as a String.
     *
     * @param filePath path to the JSON file
     * @return JSON content as a String
     * @throws IOException if the file is not found or unreadable
     */
    public static String getJsonFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
