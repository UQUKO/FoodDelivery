package com.example.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WeatherDataServiceTest {

    @Mock
    private WeatherDataRepository weatherDataRepository;

    private WeatherDataService weatherDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherDataService = new WeatherDataService();
        weatherDataService.setWeatherDataRepository(weatherDataRepository);
    }

    @Test
    public void testGetPhenomenonByWmocode() {
        // Given
        int wmocode = 123;
        String expectedPhenomenon = "Sunny";
        when(weatherDataRepository.findPhenomenonByWmocode(wmocode)).thenReturn(expectedPhenomenon);

        // When
        String actualPhenomenon = weatherDataService.getPhenomenonByWmocode(wmocode);

        // Then
        assertEquals(expectedPhenomenon, actualPhenomenon);
        verify(weatherDataRepository, times(1)).findPhenomenonByWmocode(wmocode);
    }

    @Test
    public void testGetAirtempByWmocode() {
        // Given
        int wmocode = 123;
        double expectedAirtemp = 25.5;
        when(weatherDataRepository.findAirtempByWmocode(wmocode)).thenReturn(expectedAirtemp);

        // When
        double actualAirtemp = weatherDataService.getAirtempByWmocode(wmocode);

        // Then
        assertEquals(expectedAirtemp, actualAirtemp);
        verify(weatherDataRepository, times(1)).findAirtempByWmocode(wmocode);
    }

    @Test
    public void testGetWindspeedByWmocode() {
        // Given
        int wmocode = 123;
        double expectedWindspeed = 10.2;
        when(weatherDataRepository.findWindspeedByWmocode(wmocode)).thenReturn(expectedWindspeed);

        // When
        double actualWindspeed = weatherDataService.getWindspeedByWmocode(wmocode);

        // Then
        assertEquals(expectedWindspeed, actualWindspeed);
        verify(weatherDataRepository, times(1)).findWindspeedByWmocode(wmocode);
    }
}
