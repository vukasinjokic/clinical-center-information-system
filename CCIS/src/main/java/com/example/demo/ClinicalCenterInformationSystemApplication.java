package com.example.demo;

import com.example.demo.api.LoginController;
import com.example.demo.model.Doctor;
import com.example.demo.model.User;
import com.example.demo.service.LoginService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.Doc;
import java.util.HashSet;

@SpringBootApplication
public class ClinicalCenterInformationSystemApplication {

	public static LoginService loginService = new LoginService();
	public static LoginController loginController = new LoginController(loginService);
	public static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("CCISPersistence");

	public static void main(String[] args) {

		SpringApplication.run(ClinicalCenterInformationSystemApplication.class, args);

	}

}
