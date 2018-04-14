package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class Clouds {
    @XmlElement(name = "all")
    public double cloudiness;
}
