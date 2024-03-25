package com.example.controller;

import com.example.service.*;
import com.example.task.WeatherError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
//@CrossOrigin(origins = "http://localhost:63342")
public class DeliveryController {

    private static double fee;
    private Linn linn;
    @GetMapping("/fee")
    public String calculateDeliveryFee(@RequestParam String city, @RequestParam String vehicleType) {
        vehicleType.strip();
        if (city.equals("Tartu")) linn = new Tartu();
        if (city.equals("Tallinn")) linn = new Tallinn();
        if (city.equals("Pärnu")) linn = new Pärnu();

        try {
            fee = Calculator.calculateFee(linn, vehicleType);
        } catch (WeatherError e) {
            return "Custom exception occurred: " + e.getMessage();
        }

        return "Fee calculated for city: " + city + ", vehicle type: " + vehicleType + "\n" +
                "The total fee is: " + fee;
    }
}

