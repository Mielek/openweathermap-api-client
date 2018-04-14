package com.github.mielek.api.client.openweathermap.model;


import lombok.Getter;

public enum UnitType {
    STANDARD("K", "m/s"), METRIC("C", "m/s"), IMPERIAL("F", "mph");

    @Getter
    private String temperature;
    @Getter
    private String humidity = "%";
    @Getter
    private String pressure = "hPa";
    @Getter
    private String windSpeed;
    @Getter
    private String windDirection = "\u00b0";
    @Getter
    private String cloudiness = "%";
    @Getter
    private String precipitationLevel = "mm";
    @Getter
    private String time = "UTC";

    UnitType(String temperature, String windSpeed) {
        this.temperature = "\u00b0" + temperature;
        this.windSpeed = windSpeed;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public static UnitType getDefault() {
        return STANDARD;
    }

}
