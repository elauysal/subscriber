package com.example.swadeveloperprototype;

import com.example.swadeveloperprototype.service.DataService;
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

	public static void main(String[] args) {
		SpringApplication.run(SwaDeveloperPrototypeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String jsonFilePath = "src/main/resources/data.json";  // JSON dosyanızın yolu
		dataService.loadSubscribersFromJsonFileAndSaveToDatabase(jsonFilePath);
	}
}
