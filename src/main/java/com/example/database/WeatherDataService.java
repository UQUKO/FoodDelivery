package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherDataService {


    @Autowired
    private WeatherDataRepository weatherDataRepository;

    public String getDataByWmocode(int wmocode) {
        return weatherDataRepository.findDataByWmocode(wmocode);
    }

    public String getPhenomenonByWmocode(int wmocode) {
        return weatherDataRepository.findPhenomenonByWmocode(wmocode);
    }

    public double getAirtempByWmocode(int wmocode) {
        return weatherDataRepository.findAirtempByWmocode(wmocode);
    }

    public double getWindspeedByWmocode(int wmocode) {
        return weatherDataRepository.findWindspeedByWmocode(wmocode);
    }

}
