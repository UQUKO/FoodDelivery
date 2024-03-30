package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for configuring application components.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.example.database",
        "com.example.service",
        "com.example.task",
        "com.example.controller"
})
public class AppConfig {

}
