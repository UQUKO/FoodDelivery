package com.example.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class WeatherDataRepositoryTest {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @BeforeEach
    public void setUp() {
        // Insert test records into the in-memory database
        WeatherData weatherData1 = new WeatherData();
        weatherData1.setObservationTime(LocalDateTime.of(2024, 3, 31, 15, 30, 45));
        weatherData1.setWmocode(123);
        weatherData1.setPhenomenon("Light rain");
        weatherData1.setAirTemperature(25.5);
        weatherData1.setWindSpeed(10.2);
        weatherDataRepository.save(weatherData1);

    }

    @Test
    void findPhenomenonByWmocode() {
        int wmocode = 123;
        String expectedPhenomenon = "Light rain";

        String actualPhenomenon = weatherDataRepository.findPhenomenonByWmocode(wmocode);

        assertEquals(expectedPhenomenon, actualPhenomenon);
    }

    @Test
    void findAirtempByWmocode() {
        int wmocode = 123;
        double expectedTemp = 25.5;

        double actualTemp = weatherDataRepository.findAirtempByWmocode(wmocode);

        assertEquals(expectedTemp, actualTemp);
    }

    @Test
    void findWindspeedByWmocode() {
        int wmocode = 123;
        double expectedWindSpeed = 10.2;

        double actualWindSpeed = weatherDataRepository.findWindspeedByWmocode(wmocode);

        assertEquals(expectedWindSpeed, actualWindSpeed);
    }
}