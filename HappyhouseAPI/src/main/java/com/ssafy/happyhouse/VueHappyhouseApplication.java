package com.ssafy.happyhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VueHappyhouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueHappyhouseApplication.class, args);
	}

}
