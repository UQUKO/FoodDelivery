package com.example.service;

import com.example.dao.ReadDatabase;

import java.util.List;

public class Pärnu implements Linn{

    private static final int wmocode = 41803;

    private static double rbf = 2.0;

    private List<String> data = ReadDatabase.retrieveData(wmocode);

    private String phenomenon = data.get(3);

    private double airtemp = Double.parseDouble(data.get(4));

    private double windspeed = Double.parseDouble(data.get(5));


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
