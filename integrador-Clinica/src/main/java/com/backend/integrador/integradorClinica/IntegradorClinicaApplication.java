package com.backend.integrador.integradorClinica;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class IntegradorClinicaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegradorClinicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IntegradorClinicaApplication.class, args);
		LOGGER.info("ClinicaOdontologica is now running...");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
