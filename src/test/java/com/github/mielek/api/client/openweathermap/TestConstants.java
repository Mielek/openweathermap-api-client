package com.github.mielek.api.client.openweathermap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestConstants {
    private static final String API_KEY;
    static{
        try {
            API_KEY = Files.readAllLines(Paths.get("openweathermap-api-kay")).get(0);
        } catch (IOException e) {
            throw new RuntimeException("No file with api key in project root directory. " +
                    "Create openweathermap-api-kay file and fill it with your api key before running tests again.");
        }
    }

    public static String getAPIKey(){
        return API_KEY;
    }
}
