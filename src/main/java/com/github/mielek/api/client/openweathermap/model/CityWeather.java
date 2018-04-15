package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
public class CityWeather {
    private long id;
    @XmlElement(name = "dt")
    private long timeOfCalculation;
    private String name;
    @XmlElement(name = "coord")
    private Coordinates coordinates;
    @XmlElement(name = "sys")
    private SystemParameters system;
    private MainParameters main;
    private Wind wind;
    private Clouds clouds;
    private List<Weather> weather;
    private Precipitation rain;
    private Precipitation snow;
    private double visibility;

    private String base;
    private int cod;
}
