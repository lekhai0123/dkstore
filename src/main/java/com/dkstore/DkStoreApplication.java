package com.dkstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dkstore.services.StorageService;

@SpringBootApplication
public class DkStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DkStoreApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) ->{
			storageService.init();
		};
	}
}
