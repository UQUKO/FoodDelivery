package com.example.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    @Query(value = "SELECT * FROM weatherdata WHERE wmocode = :wmocode ORDER BY observation_time DESC LIMIT 1", nativeQuery = true)
    String findDataByWmocode(@Param("wmocode") int wmocode);
}
