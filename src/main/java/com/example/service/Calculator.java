package com.example.service;

import com.example.config.AppConfig;
import com.example.database.WeatherDataService;
import com.example.task.WeatherError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Calculator {

    private final WeatherDataService weatherDataService;

    @Autowired
    public Calculator(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    public void run() throws WeatherError {
        Linn tartu = new Tartu(weatherDataService);
        Linn parnu = new Pärnu(weatherDataService);
        Linn tallinn = new Tallinn(weatherDataService);
        double test = calculateFee(tartu, "Bike");
        System.out.println(test);
    }

    public static void main(String[] args) throws WeatherError {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator calculator = context.getBean(Calculator.class);
        calculator.run();
    }


    public static double calculateFee(Linn linn, String vehicle) throws WeatherError {
        if(vehicle.equals("Bike") && linn.getWindspeed() > 20 ||
                (linn.getPhenomenon().toLowerCase().contains("glaze") ||
                linn.getPhenomenon().toLowerCase().contains("hail") ||
                linn.getPhenomenon().toLowerCase().contains("thunder")) &&
                !vehicle.equals("Car")) {
            throw new WeatherError("Usage of selected vehicle type is forbidden!");
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
