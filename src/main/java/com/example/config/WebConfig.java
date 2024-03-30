package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for configuring web resources.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds resource handlers for serving static resources.
     *
     * @param registry The registry for resource handlers.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}

