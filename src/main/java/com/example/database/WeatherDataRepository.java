package com.example.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    @Query(value = "SELECT * FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    String findDataByWmocode(@Param("wmocode") int wmocode);

    @Query(value = "SELECT phenomenon FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    String findPhenomenonByWmocode(@Param("wmocode") int wmocode);

    @Query(value = "SELECT airtemperature FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    double findAirtempByWmocode(@Param("wmocode") int wmocode);

    @Query(value = "SELECT windspeed FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    double findWindspeedByWmocode(@Param("wmocode") int wmocode);
}
