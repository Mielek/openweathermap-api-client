package com.com.github.mielek.api.client.openweathermap;

import com.github.mielek.api.client.openweathermap.OpenWeatherMapApiException;
import com.github.mielek.api.client.openweathermap.OpenWeatherMapClient;
import com.github.mielek.api.client.openweathermap.model.CityWeather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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

    public static void main() {
        Client webClient = ClientBuilder.newClient();
        OpenWeatherMapClient weatherClient = new OpenWeatherMapClient(webClient, getAPIKey());
        try {
            CityWeather cityWeather = weatherClient.getCurrentWeatherByCity("New York");
            System.out.println(cityWeather);
        } catch (OpenWeatherMapApiException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getAPIKey(){
        return API_KEY;
    }
}
