package com.backend.fitta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FitTaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitTaApplication.class, args);
	}

}
