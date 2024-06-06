package com.example.swadeveloperprototype;

import com.example.swadeveloperprototype.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SwaDeveloperPrototypeApplication implements CommandLineRunner {

	@Autowired
	private DataService dataService;
	private static final Logger logger = LoggerFactory.getLogger(SwaDeveloperPrototypeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SwaDeveloperPrototypeApplication.class, args);
		logger.info("Application started successfully.");

	}

	@Override
	public void run(String... args) throws Exception {
		String jsonFilePath = "src/main/resources/data.json";  // JSON dosyanızın yolu
		dataService.loadSubscribersFromJsonFileAndSaveToDatabase(jsonFilePath);
	}
}
