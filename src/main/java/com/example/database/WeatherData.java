package com.example.database;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "weatherdata")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "observation_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
    private LocalDateTime observationTime;

    @Column(name = "station")
    private String station;

    @Column(name = "wmocode")
    private int wmocode;

    @Column(name = "phenomenon")
    private String phenomenon;

    @Column(name = "airtemperature")
    private double airTemperature;

    @Column(name = "windspeed")
    private double windSpeed;


    public void setObservationTime(LocalDateTime observationTime) {
        this.observationTime = observationTime;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setWmocode(int wmocode) {
        this.wmocode = wmocode;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

}
