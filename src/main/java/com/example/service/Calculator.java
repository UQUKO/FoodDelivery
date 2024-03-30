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


    /**
     * Calculates the delivery fee based on the given parameters.
     * <p>
     * The delivery fee calculation considers various factors such as weather conditions and vehicle type.
     * If the vehicle type is a bike and the wind speed exceeds 20 m/s, or if the weather phenomenon includes
     * glaze, hail, or thunder and the vehicle type is not a car, the method throws a CustomError indicating
     * that the usage of the selected vehicle type is forbidden.
     * </p>
     * <p>
     * The delivery fee is calculated based on four factors: Road Condition Factor (RBF), Air Temperature Effect Factor (ATEF),
     * Wind Speed Effect Factor (WSEF), and Weather Phenomenon Effect Factor (WPEF). These factors are calculated
     * using specific algorithms based on the given city and vehicle type.
     * </p>
     *
     * @param linn    The city object representing the delivery location.
     * @param vehicle The type of vehicle used for delivery.
     * @return The calculated delivery fee.
     * @throws CustomError If the usage of the selected vehicle type is forbidden based on weather conditions,
     *                     or if an error occurs during fee calculation.
     */
    public static double calculateFee(Linn linn, String vehicle) throws CustomError{
        // Check for all the cases where usage of a certain vehicle type is forbidden
        if(vehicle.equalsIgnoreCase("bike") && linn.getWindspeed() > 20 ||
                (linn.getPhenomenon().toLowerCase().contains("glaze") ||
                linn.getPhenomenon().toLowerCase().contains("hail") ||
                linn.getPhenomenon().toLowerCase().contains("thunder")) &&
                !vehicle.equalsIgnoreCase("Car")) {
            throw new CustomError("Usage of selected vehicle type is forbidden!");
        }
        // Calculate all the components of the fee
        double rbf = calculateRBF(linn, vehicle);
        double atef = calculateATEF(linn, vehicle);
        double wsef = calculateWSEF(linn, vehicle);
        double wpef = calculateWPEF(linn, vehicle);

        // Return the final fee
        return rbf + atef + wsef + wpef;
    }

    /**
     * Calculates weather phenomenon extra fee
     * @param linn      The city object representing the delivery location.
     * @param vehicle   The type of vehicle used for delivery.
     * @return          Weather phenomenon extra fee
     */
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
    /**
     * Calculates wind speed extra fee
     * @param linn      The city object representing the delivery location.
     * @param vehicle   The type of vehicle used for delivery.
     * @return          Wind speed extra fee
     */
    private static double calculateWSEF(Linn linn, String vehicle) {
        if(vehicle.equalsIgnoreCase("Bike")){
            if (linn.getWindspeed()>10 && linn.getWindspeed()< 20) return 0.5;
        }
        return 0;
    }

    /**
     * Calculates air temperature extra fee
     * @param linn      The city object representing the delivery location.
     * @param vehicle   The type of vehicle used for delivery.
     * @return          Air temperature extra fee
     */
    private static double calculateATEF(Linn linn, String vehicle) {
        double atef = 0.0;
        if(vehicle.equalsIgnoreCase("Scooter") || vehicle.equalsIgnoreCase("Bike")){
            if (linn.getAirtemp() < -10.0) atef += 1;
            else if (linn.getAirtemp() < 0.0) atef += 0.5;
        }
        return atef;
    }
    /**
     * Calculates regional base fee
     * @param linn      The city object representing the delivery location.
     * @param vehicle   The type of vehicle used for delivery.
     * @return          Regional base fee
     */
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
