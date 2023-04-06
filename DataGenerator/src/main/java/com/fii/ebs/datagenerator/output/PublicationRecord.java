package com.fii.ebs.datagenerator.output;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
public class PublicationRecord {
    private final int stationId;
    private final String city;
    private final int temp;
    private final double rain;
    private final int wind;
    private final String direction;
    private final String date;
    
    private static final EnumGenerator<City> cityGenerator =
        new EnumGenerator<>(new SecureRandom(), City.class);
    
    private static final SecureRandom randomNumber = new SecureRandom();
    
    private static final EnumGenerator<Direction> directionGenerator =
        new EnumGenerator<>(new SecureRandom(), Direction.class);
    
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneOffset.UTC);
    
    @Override
    public String toString() {
        return "{(stationId," + stationId +
            ");(city,\"" + city +
            "\");(temp," + temp +
            ");(rain," + rain +
            ");(wind," + wind +
            ");(direction,\"" + direction +
            "\");(date," + date + ")}";
    }
    
    public static PublicationRecord generateRecord() {
        City city = generateCity();
        
        return PublicationRecord.builder()
            .stationId(city.getCityId())
            .city(city.getCityName())
            .temp(generateTemp())
            .rain(generateRain())
            .wind(generateWind())
            .direction(generateDirection().toString())
            .date(DATE_FORMATTER.format(generateDate()))
            .build();
    }
    
    private static City generateCity() {
        return cityGenerator.generate();
    }
    
    private static int generateTemp() {
        return randomNumber.nextInt(71) - 25;
    }
    
    private static double generateRain() {
        return randomNumber.nextDouble() * 100;
    }
    
    private static int generateWind() {
        return randomNumber.nextInt(101);
    }
    
    private static Direction generateDirection() {
        return directionGenerator.generate();
    }
    
    public static LocalDate generateDate() {
        long startDayInclusive = LocalDate.of(2023, Month.JANUARY, 1).toEpochDay();
        long endDayExclusive = LocalDate.of(2023, Month.APRIL, 1).toEpochDay();

        return LocalDate.ofEpochDay(
            ThreadLocalRandom.current().nextLong(startDayInclusive, endDayExclusive));
    }
}