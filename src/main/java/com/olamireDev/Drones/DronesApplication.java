package com.olamireDev.Drones;

import com.olamireDev.Drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DronesApplication implements CommandLineRunner {

	@Autowired
	MedicationService medicationService;

	public static void main(String[] args) {
		SpringApplication.run(DronesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		medicationService.loadMedications();
	}
}
