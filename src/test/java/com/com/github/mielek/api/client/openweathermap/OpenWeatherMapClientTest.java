package com.com.github.mielek.api.client.openweathermap;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.github.mielek.api.client.openweathermap.OpenWeatherMapClient;
import com.github.mielek.api.client.openweathermap.model.CityWeather;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class OpenWeatherMapClientTest {

    private static final String NY_CITY_NAME = "New York";
    private static final int NY_CITY_ID = 5128581;

    private static Client webClient;
    private static String apiCode = TestConstants.getAPIKey();

    @BeforeClass
    public static void setUp() {
        webClient = ClientBuilder.newClient().register(JacksonJaxbJsonProvider.class);
    }

    @AfterClass
    public static void shutdown() {
        webClient.close();
    }

    @Test
    public void getCurrentWeatherByCityNameForNewYork() {
        OpenWeatherMapClient client = new OpenWeatherMapClient(webClient, apiCode);
        CityWeather weather = null;
        try {
            weather = client.getCurrentWeatherByCity(NY_CITY_NAME);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThat(weather.getName()).isEqualTo(NY_CITY_NAME);
        assertThat(weather.getId()).isEqualTo(NY_CITY_ID);
    }

    @Test
    public void getCurrentWeatherByCityNameWithCountryForNewYork() {
        OpenWeatherMapClient client = new OpenWeatherMapClient(webClient, apiCode);
        CityWeather weather = null;
        try {
            weather = client.getCurrentWeatherByCity(NY_CITY_NAME, "us");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThat(weather.getName()).isEqualTo(NY_CITY_NAME);
        assertThat(weather.getId()).isEqualTo(NY_CITY_ID);
    }

    @Test
    public void getCurrentWeatherByCityCodeForNewYork() {
        OpenWeatherMapClient client = new OpenWeatherMapClient(webClient, apiCode);

        CityWeather weather = null;
        try {
            weather = client.getCurrentWeatherByCityID(NY_CITY_ID);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThat(weather.getName()).isEqualTo(NY_CITY_NAME);
        assertThat(weather.getId()).isEqualTo(NY_CITY_ID);
    }

    @Test
    public void getCurrentWeatherByZipCodeForNewYork() {
        OpenWeatherMapClient client = new OpenWeatherMapClient(webClient, apiCode);

        CityWeather weather = null;
        try {
            weather = client.getCurrentWeatherByZipCode("10001", "us");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThat(weather.getName()).isEqualTo(NY_CITY_NAME);
        assertThat(weather.getId()).isEqualTo(420027013); // really strange, it seems that cities can have multiple codes if you search them by zip code
    }

    @Test
    public void getCurrentWeatherByCoordinatesForNewYork() {
        OpenWeatherMapClient client = new OpenWeatherMapClient(webClient, apiCode);

        CityWeather weather = null;
        try {
            weather = client.getCurrentWeatherByCoordinates(40.73, -73.99);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThat(weather.getName()).isEqualTo(NY_CITY_NAME);
        assertThat(weather.getId()).isEqualTo(NY_CITY_ID);
    }

    @Test
    public void getCurrentWeatherForCitiesByListOfIdForWestEastAndCentralNewYork(){
        OpenWeatherMapClient client = new OpenWeatherMapClient(webClient, apiCode);
        List<CityWeather> weathers = null;
        try {
            weathers = client.getCurrentWeatherByCityID(5106292, 5115985, NY_CITY_ID);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThat(weathers).isNotNull().isNotEmpty().hasSize(3)
                .extracting(cityWeather -> cityWeather.getId()).doesNotHaveDuplicates().containsOnly(5106292L, 5115985L, 0l+NY_CITY_ID);
        assertThat(weathers).extracting(cityWeather -> cityWeather.getName())
                .doesNotHaveDuplicates().containsOnly("East New York", "West New York", "New York");
    }


}
