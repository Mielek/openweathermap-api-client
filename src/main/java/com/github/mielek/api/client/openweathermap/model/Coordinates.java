package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class Coordinates {
    @XmlElement(name = "lon")
    private double longitude;
    @XmlElement(name = "lat")
    private double latitude;
}
