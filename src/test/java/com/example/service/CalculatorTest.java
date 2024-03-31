package com.example.service;

import com.example.database.WeatherDataRepository;
import com.example.database.WeatherDataService;
import com.example.task.CustomError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CalculatorTest {

    @Mock
    private WeatherDataService weatherDataService;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @InjectMocks
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void originalTest() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(4.7);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(-2.1);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Light snow shower");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(4.0, fee);
    }

    @Test
    void calculateFee_BikeForbidden_ThrowsCustomError() {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(25.0); // Exceeds threshold for bikes
        Linn linn = new Tartu(weatherDataService);
        // Test
        assertThrows(CustomError.class, () -> calculator.calculateFee(linn, "Bike"));
    }

    @Test
    void calculateFee_BikeForbiddenGlaze_ThrowsCustomError() {
        // Mock weather data
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Glaze");
        Linn linn = new Tartu(weatherDataService);
        // Test
        assertThrows(CustomError.class, () -> calculator.calculateFee(linn, "Bike"));
    }

    @Test
    void calculateFee_BikeForbiddenThunder_ThrowsCustomError() {
        // Mock weather data
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Thunder");
        Linn linn = new Tartu(weatherDataService);
        // Test
        assertThrows(CustomError.class, () -> calculator.calculateFee(linn, "Bike"));
    }

    @Test
    void calculateFee_BikeForbiddenHail_ThrowsCustomError() {
        // Mock weather data
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Hail");
        Linn linn = new Tartu(weatherDataService);
        // Test
        assertThrows(CustomError.class, () -> calculator.calculateFee(linn, "Bike"));
    }

    @Test
    void calculateFee_ScooterForbiddenHail_ThrowsCustomError() {
        // Mock weather data
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Hail");
        Linn linn = new Tartu(weatherDataService);
        // Test
        assertThrows(CustomError.class, () -> calculator.calculateFee(linn, "Scooter"));
    }

    @Test
    void calculateFee_CarBaseFee_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Hail");
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(15.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(25.0);
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Car");
        assertEquals(3.5, fee);    }


    @Test
    void calculateFee_ScooterBaseFee_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(5.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(25.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Scooter");
        assertEquals(3.0, fee);
    }

    @Test
    void calculateFee_BikeBaseFee_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(5.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(25.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(2.5, fee);
    }

    @Test
    void calculateFee_BikeWindSpeedAllowed_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(15.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(25.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(3.0, fee);
    }

    @Test
    void calculateFee_BikeAirTempBetweenMinus10and0_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(5.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(-5.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(3.0, fee);
    }

    @Test
    void calculateFee_BikeAirTempUnderMinus10_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(5.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(-15.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(3.5, fee);
    }

    @Test
    void calculateFee_BikePhenomenonSnow_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(5.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(1.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Snow");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(3.5, fee);
    }

    @Test
    void calculateFee_BikePhenomenonSleet_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(5.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(1.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Sleet");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(3.5, fee);
    }

    @Test
    void calculateFee_BikePhenomenonRain_ReturnsCorrectFee() throws CustomError {
        // Mock weather data
        when(weatherDataService.getWindspeedByWmocode(26242)).thenReturn(5.0);
        when(weatherDataService.getAirtempByWmocode(26242)).thenReturn(1.0);
        when(weatherDataService.getPhenomenonByWmocode(26242)).thenReturn("Light rain");
        Linn linn = new Tartu(weatherDataService);
        // Test
        double fee = calculator.calculateFee(linn, "Bike");
        assertEquals(3.0, fee);
    }

}
