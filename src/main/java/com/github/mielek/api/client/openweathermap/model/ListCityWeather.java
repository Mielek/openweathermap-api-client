package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
public class ListCityWeather {
    private String cod;
    @XmlElement(name = "calctime")
    private double calculationTime;
    @XmlElement(name = "cnt")
    private long listSize;
    private List<CityWeather> list;
}
