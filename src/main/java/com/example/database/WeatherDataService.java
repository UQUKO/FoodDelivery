package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherDataService {

    @Autowired
    private WeatherDataImporter weatherDataImporter;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    public void importDataToDatabase(String data) {
        String[] sortedData = sortData(data);
        WeatherData weatherData = createWeatherData(sortedData);
        weatherDataImporter.importWeatherData(weatherData);
    }

    public String getDataByWmocode(int wmocode) {
        return weatherDataRepository.findDataByWmocode(wmocode);
    }

    private WeatherData createWeatherData(String[] sortedData) {
        WeatherData weatherData = new WeatherData();
        weatherData.setObservationTime(LocalDateTime.now());
        weatherData.setStation(sortedData[0]);
        weatherData.setWmocode(Integer.parseInt(sortedData[1]));
        weatherData.setPhenomenon(sortedData[2]);
        weatherData.setAirTemperature(Double.parseDouble(sortedData[3]));
        weatherData.setWindSpeed(Double.parseDouble(sortedData[4]));
        return weatherData;
    }

    private String[] sortData(String data) {
        String[] andmed = data.strip().split("\n");
        return new String[] {
                andmed[0].strip(),
                andmed[1].strip(),
                andmed[4].strip(),
                andmed[9].strip(),
                andmed[11].strip()
        };
    }
}
