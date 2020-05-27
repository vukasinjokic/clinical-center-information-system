package com.example.demo;

import com.example.demo.api.AuthenticationController;
import com.example.demo.service.LoginService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
@EnableAsync
public class ClinicalCenterInformationSystemApplication {
//
//	public static LoginService loginService = new LoginService();
//	public static AuthenticationController authenticationController = new AuthenticationController(loginService);

	public static void main(String[] args) {

		SpringApplication.run(ClinicalCenterInformationSystemApplication.class, args);

	}

}
