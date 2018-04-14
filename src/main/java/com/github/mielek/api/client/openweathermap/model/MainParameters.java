package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class MainParameters {
    @XmlElement(name = "temp")
    private double temperature;
    private double pressure;
    private double humidity;
    @XmlElement(name = "temp_min")
    private double minimumTemperature;
    @XmlElement(name = "temp_max")
    private double maximumTemperature;
    @XmlElement(name = "sea_level")
    private double seaLevelPressure;
    @XmlElement(name = "grnd_level")
    private double groundLevelPressure;
}
