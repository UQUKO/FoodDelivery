package com.example.service;

import com.example.config.AppConfig;
import com.example.database.WeatherDataService;

public class Tartu implements Linn {

    private final WeatherDataService weatherDataService;
    private int wmocode = 26242;
    private static double rbf = 2.5;

    private String phenomenon;
    private double airtemp;
    private double windspeed;


    public Tartu(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
        initializeData();
    }

    private void initializeData() {
        String stringWithData = weatherDataService.getDataByWmocode(wmocode);
        String[] data = stringWithData.strip().split(",");
        this.phenomenon = data[3];
        this.airtemp = Double.parseDouble(data[1]);
        this.windspeed = Double.parseDouble(data[5]);
    }

    @Override
    public double getRbf() {
        return rbf;
    }

    @Override
    public double getAirtemp() {
        return airtemp;
    }

    @Override
    public double getWindspeed() {
        return windspeed;
    }

    @Override
    public String getPhenomenon() {
        return phenomenon;
    }
}
