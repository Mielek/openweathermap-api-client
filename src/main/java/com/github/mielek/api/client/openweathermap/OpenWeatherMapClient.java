package com.github.mielek.api.client.openweathermap;

import com.github.mielek.api.client.openweathermap.model.CityWeather;
import com.github.mielek.api.client.openweathermap.model.ErrorMessage;
import com.github.mielek.api.client.openweathermap.model.ListCityWeather;
import com.github.mielek.api.client.openweathermap.model.UnitType;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Locale;

public class OpenWeatherMapClient {
    private static final String API_ENDPOINT = "http://api.openweathermap.org/data/2.5";

    private WebTarget apiTarget;

    public OpenWeatherMapClient(Client webClient, String apiCode) {
        this.apiTarget = webClient.target(API_ENDPOINT).queryParam("appid", apiCode);
    }

    public OpenWeatherMapClient setUnits(UnitType unitTypes) {
        apiTarget = apiTarget.queryParam("units", unitTypes.getName());
        return this;
    }

    public OpenWeatherMapClient setLanguage(Locale locale) {
        String langCode = locale.getLanguage();
        if (langCode != null && !langCode.isEmpty())
            apiTarget = apiTarget.queryParam("lang", langCode);
        return this;
    }

    public CityWeather getCurrentWeatherByCityID(int id) throws OpenWeatherMapApiException {
        WebTarget weatherTarget = apiTarget.path("weather")
                .queryParam("id", id);
        return getCurrentWeather(weatherTarget);
    }

    public CityWeather getCurrentWeatherByCity(String city) throws OpenWeatherMapApiException {
        WebTarget weatherTarget = apiTarget.path("weather")
                .queryParam("q", city);
        return getCurrentWeather(weatherTarget);
    }

    public CityWeather getCurrentWeatherByCity(String city, String countryCode) throws OpenWeatherMapApiException {
        WebTarget weatherTarget = apiTarget.path("weather")
                .queryParam("q", String.format("%s,%s", city, countryCode));
        return getCurrentWeather(weatherTarget);
    }

    public CityWeather getCurrentWeatherByZipCode(String zipCode, String countryCode) throws OpenWeatherMapApiException {
        WebTarget weatherTarget = apiTarget.path("weather")
                .queryParam("zip", String.format("%s,%s", zipCode, countryCode));
        return getCurrentWeather(weatherTarget);
    }

    public CityWeather getCurrentWeatherByCoordinates(double latitude, double longitude) throws OpenWeatherMapApiException {
        WebTarget weatherTarget = apiTarget.path("weather")
                .queryParam("lat", Double.toString(latitude))
                .queryParam("lon", Double.toString(longitude));
        return getCurrentWeather(weatherTarget);
    }

    public List<CityWeather> getCurrentWeatherForCitiesInBox(double latitudeA, double longitudeA, double latitudeB, double longitudeB) throws OpenWeatherMapApiException {
        WebTarget weatherTarget = apiTarget.path("box/city")
                .queryParam("bbox", latitudeA, longitudeA, latitudeB, longitudeB);
        return getCurrentWeather(apiTarget, ListCityWeather.class).getList();
    }

    public List<CityWeather> getCurrentWeatherForCitiesInCircleCloseToPoint(double latitude, double longitude, int expectedNumberOfCities) throws OpenWeatherMapApiException {
        if(expectedNumberOfCities<10 || expectedNumberOfCities>50){
            throw new IllegalArgumentException("Expected number of cities need to be between 10 and 50. Passed value: "+expectedNumberOfCities);
        }
        WebTarget weatherTarget = apiTarget.path("find")
                .queryParam("lat", Double.toString(latitude))
                .queryParam("lon", Double.toString(longitude))
                .queryParam("cnt", Integer.toString(expectedNumberOfCities));
        return getCurrentWeather(apiTarget, ListCityWeather.class).getList();
    }

    public List<CityWeather> getCurrentWeatherByCityID(int... ids) throws OpenWeatherMapApiException {
        WebTarget weatherTarget = apiTarget.path("group")
                .queryParam("id", toStringList(ids));
        return getCurrentWeather(weatherTarget, ListCityWeather.class).getList();
    }

    private String toStringList(int[] ids) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; ; i++) {
            sb.append(ids[i]);
            if (i == ids.length-1)
                return sb.toString();
            sb.append(",");
        }
    }

    private CityWeather getCurrentWeather(WebTarget weatherTarget) throws OpenWeatherMapApiException {
        return getCurrentWeather(weatherTarget, CityWeather.class);
    }

    private <T> T getCurrentWeather(WebTarget weatherTarget, Class<T> returnedClass) throws OpenWeatherMapApiException {
        Invocation.Builder builder = weatherTarget.request();
        Response res = builder.get();
        if (!Response.Status.Family.SUCCESSFUL.equals(res.getStatusInfo().getFamily())) {
            ErrorMessage errorMessage = res.readEntity(ErrorMessage.class);
            throw new OpenWeatherMapApiException(res.getStatusInfo(), errorMessage, weatherTarget.getUri());
        }
        return res.readEntity(returnedClass);
    }

}
