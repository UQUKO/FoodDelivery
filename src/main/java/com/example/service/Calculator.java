package com.example.service;

import com.example.dao.ReadDatabase;
import com.example.task.WeatherError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

public class Calculator {
    private static int tartuWMOCode = 26242;

    private static int tallinnWMOCode = 26038;

    private static int parnuWMOCode = 41803;


    public static void main(String[] args) throws WeatherError {
//        Linn tartu = new Tartu();
        Linn parnu = new Pärnu();
        Linn tallinn = new Tallinn();
//        double test = calculateFee(tartu, "Bike");
//        System.out.println(test);
    }


    public static double calculateFee(Linn linn, String vehicle) throws WeatherError {
        if(vehicle=="Bike" && linn.getWindspeed() >20 ||
                (linn.getPhenomenon().toLowerCase().contains("glaze") ||
                linn.getPhenomenon().toLowerCase().contains("hail") ||
                linn.getPhenomenon().toLowerCase().contains("thunder")) &&
                !vehicle.equals("Car")) {
            throw new WeatherError("Usage of selected vehicle type is forbidden");
        }
        double rbf = calculateRBF(linn, vehicle);
        double atef = calculateATEF(linn, vehicle);
        double wsef = calculateWSEF(linn, vehicle);
        double wpef = calculateWPEF(linn, vehicle);
        return rbf + atef + wsef + wpef;
    }

    private static double calculateWPEF(Linn linn, String vehicle) {
        double wpef = 0.0;
        if(vehicle.equals("Scooter") || vehicle.equals("Bike")){
            if (linn.getPhenomenon().contains("snow")) wpef += 1.0;
            else if (linn.getPhenomenon().contains("sleet")) wpef += 1.0;
            else if (linn.getPhenomenon().contains("rain")) wpef += 0.5;
            else if (linn.getPhenomenon().contains("shower")) wpef += 0.5;
        }
        return wpef;
    }

    private static double calculateWSEF(Linn linn, String vehicle) {
        if(vehicle.equals("Bike")){
            if (linn.getWindspeed()>10 && linn.getWindspeed()< 20) return 0.5;
        }
        return 0;
    }

    private static double calculateATEF(Linn linn, String vehicle) {
        double atef = 0.0;
        if(vehicle.equals("Scooter") || vehicle.equals("Bike")){
            if (linn.getAirtemp() < -10.0) atef += 1;
            else if (linn.getAirtemp() < 0.0) atef += 0.5;
        }
        return atef;
    }

    private static double calculateRBF(Linn linn, String vehicle) {
        double rbf = linn.getRbf();
        switch (vehicle) {
            case "Car":
                return rbf + 1;
            case "Scooter":
                return rbf + 0.5;
        }

        return rbf;
    }
}
