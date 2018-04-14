package com.github.mielek.api.client.openweathermap.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class Precipitation {
    @XmlElement(name = "3h")
    private int last3HLevel;
}
