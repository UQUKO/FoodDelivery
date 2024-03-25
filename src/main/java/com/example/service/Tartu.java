package com.example.service;

import com.example.dao.ReadDatabase;

import java.util.List;

public class Tartu implements Linn{

    private static final int wmocode = 26242;

    private static double rbf = 2.5;

    private List<String> data = ReadDatabase.retrieveData(wmocode);

    private String phenomenon;

    private double airtemp;

    private double windspeed;

    public Tartu(String phenomenon, double airtemp, double windspeed) {
        this.phenomenon = phenomenon;
        this.airtemp = airtemp;
        this.windspeed = windspeed;
    }
    public Tartu(){
        this.phenomenon = data.get(3);
        this.airtemp = Double.parseDouble(data.get(4));
        this.windspeed = Double.parseDouble(data.get(5));
    };

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
