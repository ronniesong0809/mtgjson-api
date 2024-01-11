package com.ronsong.mtgjsonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
class MtgJsonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtgJsonApiApplication.class, args);
	}

}
