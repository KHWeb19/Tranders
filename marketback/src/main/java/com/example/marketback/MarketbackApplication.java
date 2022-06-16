package com.example.marketback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MarketbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketbackApplication.class, args);
	}

}
