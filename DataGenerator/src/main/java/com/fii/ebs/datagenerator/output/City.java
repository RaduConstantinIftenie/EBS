package com.fii.ebs.datagenerator.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum City {
    BUCHAREST("Bucharest", 1),
    BERLIN("Berlin", 2),
    LONDON("London", 3),
    PARIS("Paris", 4),
    PRAGUE("Prague", 5);

    private final String cityName;
    private final int cityId;
}