package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ClinicalCenterInformationSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(ClinicalCenterInformationSystemApplication.class, args);
	}

}
