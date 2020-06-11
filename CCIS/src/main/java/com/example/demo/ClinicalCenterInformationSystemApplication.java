package com.example.demo;

import com.example.demo.api.AuthenticationController;
import com.example.demo.service.LoginService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;
@SpringBootApplication
@EnableAsync
public class ClinicalCenterInformationSystemApplication {
//
//	public static LoginService loginService = new LoginService();
//	public static AuthenticationController authenticationController = new AuthenticationController(loginService);

	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) {
//		final Runnable beeper = new Runnable() {
//			public void run() { System.out.println("beep"); }
//		};
//		final ScheduledFuture<?> beeperHandle =	scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);

		SpringApplication.run(ClinicalCenterInformationSystemApplication.class, args);

	}

}
