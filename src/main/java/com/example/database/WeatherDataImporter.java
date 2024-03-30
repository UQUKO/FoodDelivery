package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WeatherDataImporter {

    private final WeatherDataRepository weatherDataRepository;

    @Autowired
    public WeatherDataImporter(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    public void importWeatherData(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);
    }
}
