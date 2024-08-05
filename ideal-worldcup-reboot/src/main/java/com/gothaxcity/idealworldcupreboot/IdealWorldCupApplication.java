package com.gothaxcity.idealworldcupreboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class IdealWorldCupApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdealWorldCupApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.ofNullable(Optional.of(UUID.randomUUID()).toString());
	}


}
