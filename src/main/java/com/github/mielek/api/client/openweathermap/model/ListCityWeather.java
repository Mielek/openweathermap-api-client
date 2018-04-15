package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement
public class ListCityWeather {
    private int cod;
    @XmlElement(name = "calctime")
    private double calculationTime;
    @XmlElement(name = "cnt")
    private long listSize;
    @XmlElement(name = "count")
    private long listSize2;
    private String message;
    private List<CityWeather> list;
}
