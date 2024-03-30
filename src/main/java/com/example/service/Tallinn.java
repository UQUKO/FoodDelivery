package com.example.service;

import com.example.database.WeatherDataService;

public class Tallinn implements Linn {

    private WeatherDataService weatherDataService;
    private int wmocode = 26038;
    private static double rbf = 3.0;

    private String phenomenon;
    private double airtemp;
    private double windspeed;

    public Tallinn(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
        initializeData();
    }

    /**
     * Initializes weather data for the current station.
     * <p>
     * This method fetches weather data from the {@link WeatherDataService} based on the station's
     * WMO code. It retrieves the phenomenon, air temperature,
     * and wind speed for the current station and initializes the corresponding fields.
     * </p>
     * <p>
     * This method is called during the initialization process of the current object to populate
     * the weather-related fields.
     * </p>
     */
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
