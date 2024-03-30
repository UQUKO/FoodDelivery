package com.example.service;

import com.example.database.WeatherDataService;

public class Pärnu implements Linn {

    private WeatherDataService weatherDataService;
    private int wmocode = 41803;
    private static double rbf = 2.0;

    private String phenomenon;
    private double airtemp;
    private double windspeed;

    public Pärnu(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
        initializeData();
    }

    private void initializeData() {
        this.phenomenon = weatherDataService.getPhenomenonByWmocode(wmocode);
        this.airtemp = weatherDataService.getAirtempByWmocode(wmocode);
        this.windspeed = weatherDataService.getWindspeedByWmocode(wmocode);
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
