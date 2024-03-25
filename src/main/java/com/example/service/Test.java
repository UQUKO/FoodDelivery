package com.example.service;

import com.example.task.WeatherError;

public class Test {
    public static void main(String[] args) throws WeatherError {
        Linn tartu = new Tartu("Light rain", 1.4, 1.8);
        double test = Calculator.calculateFee(tartu, "Bike");
        System.out.println(test);
    }
}
