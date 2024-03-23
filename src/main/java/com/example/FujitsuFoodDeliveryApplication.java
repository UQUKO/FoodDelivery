package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FujitsuFoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FujitsuFoodDeliveryApplication.class, args);
	}

}
