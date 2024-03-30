package com.example.controller;

import com.example.database.WeatherDataService;
import com.example.service.*;
import com.example.task.WeatherError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private static double fee;
    private Linn linn;

    @Autowired
    private WeatherDataService weatherDataService;
    @GetMapping("/fee")
    public String calculateDeliveryFee(@RequestParam String city, @RequestParam String vehicleType) {
        vehicleType.strip();
        if (city.equals("Tartu")) linn = new Tartu(weatherDataService);
        if (city.equals("Tallinn")) linn = new Tallinn(weatherDataService);
        if (city.equals("Pärnu")) linn = new Pärnu(weatherDataService);

        try {
            fee = Calculator.calculateFee(linn, vehicleType);
        } catch (WeatherError e) {
            return e.getMessage();
        }

        return "Fee calculated for city: " + city + ", vehicle type: " + vehicleType + "\n" +
                "The total fee is: " + fee;
    }
}

