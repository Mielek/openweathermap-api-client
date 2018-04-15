package com.github.mielek.api.client.openweathermap.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;

@ToString
@EqualsAndHashCode
public class Clouds {
    @XmlElement(name = "all")
    private double cloudiness1 = Double.NEGATIVE_INFINITY;
    @XmlElement(name = "today")
    private double cloudiness2 = Double.NEGATIVE_INFINITY;

    public double getCloudiness() {
        return cloudiness1>Double.NEGATIVE_INFINITY?cloudiness1:cloudiness2;
    }
}
