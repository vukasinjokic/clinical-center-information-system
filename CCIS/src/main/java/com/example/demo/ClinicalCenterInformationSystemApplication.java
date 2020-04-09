package com.example.demo;

import com.example.demo.api.LoginController;
import com.example.demo.model.Doctor;
import com.example.demo.model.User;
import com.example.demo.service.LoginService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.print.Doc;
import java.util.HashSet;

@SpringBootApplication
public class ClinicalCenterInformationSystemApplication {

	public static LoginService loginService = new LoginService();
	public static LoginController loginController;

	public static void main(String[] args) {
		SpringApplication.run(ClinicalCenterInformationSystemApplication.class, args);

		User doctor = new Doctor("nikola@gmail.com","123","Nikola","Stojanovic","Puskinova 4",
				"Beograd","Srbija","0111111111","12");
		HashSet<User> users = new HashSet<User>();
		users.add(doctor);
		loginService.setUsers(users);

		loginController = new LoginController(loginService);

	}

}
