package com.example.countries_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CountriesDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountriesDataApplication.class, args);
	}

}
