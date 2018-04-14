package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class Wind {
    private double speed;
    @XmlElement(name = "deg")
    private double degree;
    private double gust;
}
