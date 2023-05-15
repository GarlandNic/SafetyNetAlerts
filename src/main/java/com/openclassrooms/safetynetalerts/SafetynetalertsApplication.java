package com.openclassrooms.safetynetalerts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
@SpringBootApplication
public class SafetynetalertsApplication implements CommandLineRunner {
//public class SafetynetalertsApplication {
	
	private final static Logger logger = LogManager.getLogger("FirestationController");

	@Generated
	public static void main(String[] args) {
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Démarrage de l'application");
	}

}
