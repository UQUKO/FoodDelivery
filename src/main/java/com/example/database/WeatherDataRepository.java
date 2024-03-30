package com.example.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing weather data in the database.
 * <p>
 * This repository provides methods for retrieving weather data based on various criteria,
 * such as World Meteorological Organization (WMO) code.
 * </p>
 */
@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    /**
     * Finds the phenomenon description for the latest weather data of the specified WMO code.
     *
     * @param wmocode The WMO code for which to find the phenomenon description.
     * @return The phenomenon description.
     */
    @Query(value = "SELECT phenomenon FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    String findPhenomenonByWmocode(@Param("wmocode") int wmocode);

    /**
     * Finds the air temperature for the latest weather data of the specified WMO code.
     *
     * @param wmocode The WMO code for which to find the air temperature.
     * @return The air temperature.
     */
    @Query(value = "SELECT airtemperature FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    double findAirtempByWmocode(@Param("wmocode") int wmocode);

    /**
     * Finds the wind speed for the latest weather data of the specified WMO code.
     *
     * @param wmocode The WMO code for which to find the wind speed.
     * @return The wind speed.
     */
    @Query(value = "SELECT windspeed FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    double findWindspeedByWmocode(@Param("wmocode") int wmocode);
}
