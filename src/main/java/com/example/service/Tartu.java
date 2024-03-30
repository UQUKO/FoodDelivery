package com.example.service;

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
