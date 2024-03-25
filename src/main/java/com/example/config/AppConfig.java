package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.example.database",
        "com.example.service",
        "com.example.task",
        "com.example.dao",
        "com.example.controller"
        // Add more packages as needed
})
public class AppConfig {
    // Additional configuration if needed
}
