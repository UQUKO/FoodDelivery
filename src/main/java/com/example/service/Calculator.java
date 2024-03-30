package com.example.service;

import com.example.database.WeatherDataService;
import com.example.task.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Calculator {

    private final WeatherDataService weatherDataService;

    @Autowired
    public Calculator(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    public static double calculateFee(Linn linn, String vehicle) throws CustomError{
        if(vehicle.equalsIgnoreCase("bike") && linn.getWindspeed() > 20 ||
                (linn.getPhenomenon().toLowerCase().contains("glaze") ||
                linn.getPhenomenon().toLowerCase().contains("hail") ||
                linn.getPhenomenon().toLowerCase().contains("thunder")) &&
                !vehicle.equalsIgnoreCase("Car")) {
            throw new CustomError("Usage of selected vehicle type is forbidden!");
        }
        double rbf = calculateRBF(linn, vehicle);
        double atef = calculateATEF(linn, vehicle);
        double wsef = calculateWSEF(linn, vehicle);
        double wpef = calculateWPEF(linn, vehicle);
        return rbf + atef + wsef + wpef;
    }

    private static double calculateWPEF(Linn linn, String vehicle) {
        double wpef = 0.0;
        if(vehicle.equalsIgnoreCase("Scooter") || vehicle.equalsIgnoreCase("Bike")){
            if (linn.getPhenomenon().contains("snow")) wpef += 1.0;
            else if (linn.getPhenomenon().contains("sleet")) wpef += 1.0;
            else if (linn.getPhenomenon().contains("rain")) wpef += 0.5;
            else if (linn.getPhenomenon().contains("shower")) wpef += 0.5;
        }
        return wpef;
    }

    private static double calculateWSEF(Linn linn, String vehicle) {
        if(vehicle.equalsIgnoreCase("Bike")){
            if (linn.getWindspeed()>10 && linn.getWindspeed()< 20) return 0.5;
        }
        return 0;
    }

    private static double calculateATEF(Linn linn, String vehicle) {
        double atef = 0.0;
        if(vehicle.equalsIgnoreCase("Scooter") || vehicle.equalsIgnoreCase("Bike")){
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
