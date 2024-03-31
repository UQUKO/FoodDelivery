package com.example.database;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeatherDataTest {

    @Test
    public void testWeatherDataCreation() {
        WeatherData weatherData = new WeatherData();
        assertNotNull(weatherData);
    }

    @Test
    void setObservationTime() {
        WeatherData weatherData = new WeatherData();
        weatherData.setObservationTime(LocalDateTime.of(2024, 3, 31, 15, 30, 45));
        assertEquals(LocalDateTime.of(2024, 3, 31, 15, 30, 45), weatherData.getObservationTime());
    }

    @Test
    void setStation() {
        WeatherData weatherData = new WeatherData();
        weatherData.setStation("Test Station");
        assertEquals("Test Station", weatherData.getStation());
    }

    @Test
    void setWmocode() {
        WeatherData weatherData = new WeatherData();
        weatherData.setWmocode(26242);
        assertEquals(26242, weatherData.getWmocode());
    }

    @Test
    void setPhenomenon() {
        WeatherData weatherData = new WeatherData();
        weatherData.setPhenomenon("Light rain");
        assertEquals("Light rain", weatherData.getPhenomenon());
    }

    @Test
    void setAirTemperature() {
        WeatherData weatherData = new WeatherData();
        weatherData.setAirTemperature(-10.4);
        assertEquals(-10.4, weatherData.getAirTemperature());
    }

    @Test
    void setWindSpeed() {
        WeatherData weatherData = new WeatherData();
        weatherData.setWindSpeed(5.7);
        assertEquals(5.7, weatherData.getWindSpeed());
    }
}