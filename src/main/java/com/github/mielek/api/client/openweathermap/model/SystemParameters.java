package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class SystemParameters {
    private int type;
    private int id;
    private double message;
    private String country;
    private long sunrise;
    private long sunset;

    public String getFormattedSunriseTime(){
        return LocalDateTime.ofEpochSecond(sunrise,0, ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public String getFormattedSunsetTime(){
        return LocalDateTime.ofEpochSecond(sunset,0, ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
