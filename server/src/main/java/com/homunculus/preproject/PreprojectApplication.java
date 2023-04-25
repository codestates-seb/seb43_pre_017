package com.homunculus.preproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PreprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreprojectApplication.class, args);
	}

}
