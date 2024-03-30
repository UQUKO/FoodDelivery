package com.example.config;

import com.example.service.Calculator;
import com.example.service.Tartu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.example.database",
        "com.example.service",
        "com.example.task",
        "com.example.controller"
        // Add more packages as needed
})
public class AppConfig {

}
