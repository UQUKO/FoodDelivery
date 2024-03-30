package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for retrieving weather data from the database.
 * <p>
 * This service class provides methods for retrieving various weather data attributes
 * based on the World Meteorological Organization (WMO) code.
 * </p>
 */
@Service
public class WeatherDataService {


    @Autowired
    private WeatherDataRepository weatherDataRepository;

    /**
     * Retrieves the phenomenon description for the specified WMO code.
     *
     * @param wmocode The WMO code for which to retrieve the phenomenon description.
     * @return The phenomenon description associated with the given WMO code.
     */
    public String getPhenomenonByWmocode(int wmocode) {
        return weatherDataRepository.findPhenomenonByWmocode(wmocode);
    }

    /**
     * Retrieves the air temperature for the specified WMO code.
     *
     * @param wmocode The WMO code for which to retrieve the air temperature.
     * @return The air temperature associated with the given WMO code.
     */
    public double getAirtempByWmocode(int wmocode) {
        return weatherDataRepository.findAirtempByWmocode(wmocode);
    }

    /**
     * Retrieves the wind speed for the specified WMO code.
     *
     * @param wmocode The WMO code for which to retrieve the wind speed.
     * @return The wind speed associated with the given WMO code.
     */
    public double getWindspeedByWmocode(int wmocode) {
        return weatherDataRepository.findWindspeedByWmocode(wmocode);
    }

}
