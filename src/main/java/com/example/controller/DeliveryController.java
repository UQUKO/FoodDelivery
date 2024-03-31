package com.example.controller;

import com.example.database.WeatherDataService;
import com.example.service.*;
import com.example.task.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for handling delivery-related operations.
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private Linn linn;

    @Value("#{'${listOfCities}'.split(',')}")
    List<String> cityList;

    @Value("#{'${listOfTransport}'.split(',')}")
    private List<String> transportList;

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * Calculates the delivery fee based on the specified city and vehicle type.
     *
     * @param city        The city for which to calculate the delivery fee.
     * @param vehicleType The type of vehicle used for delivery.
     * @return A string representing the calculated delivery fee.
     */
    @GetMapping("/fee")
    public String calculateDeliveryFee(@RequestParam String city, @RequestParam String vehicleType) {
        try {
            validateInput(city, vehicleType);
            linn = createLinn(city);
            double fee = Calculator.calculateFee(linn, vehicleType);
            return "Fee calculated for city: " + city + ", vehicle type: " + vehicleType + "\n" +
                    "The total fee is: " + fee;
        } catch (CustomError e) {
            return e.getMessage();
        }
    }

    /**
     * Validates the input city and vehicle type.
     *
     * @param city        The city to validate.
     * @param vehicleType The vehicle type to validate.
     * @throws CustomError If the city or vehicle type is invalid.
     */
    private void validateInput(String city, String vehicleType) throws CustomError {
        if (!cityList.contains(city)) {
            throw new CustomError(city + " doesn't currently have any weather information.");
        }
        if (!transportList.contains(vehicleType)) {
            throw new CustomError(vehicleType + " is not a supported vehicle type");
        }
    }

    /**
     * Creates a Linn object based on the specified city.
     *
     * @param city The city for which to create the Linn object.
     * @return The created Linn object.
     * @throws CustomError If an invalid city is specified.
     */
    private Linn createLinn(String city) throws CustomError {
        switch (city) {
            case "Tartu":
                return new Tartu(weatherDataService);
            case "Tallinn":
                return new Tallinn(weatherDataService);
            case "Pärnu":
                return new Pärnu(weatherDataService);
            default:
                throw new CustomError("Invalid city specified: " + city);
        }
    }

    public List<String> getCityList() {
        return cityList;
    }

    public List<String> getTransportList() {
        return transportList;
    }
}
