package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findByEmailOrSocialSecurityNumber(String email, String socialSecurityNumber) {
        return userRepository.findByEmailOrSocialSecurityNumber(email, socialSecurityNumber);
    }

    public List<ClinicCenterAdmin> findAllClinicCenterAdmins() {
        return userRepository.findAllClinicCenterAdmins();
    }

    public Patient registerPatient(UserDTO userToRegister) {
        Patient patient = new Patient();
        patient.setUsername(userToRegister.getUsername());
        patient.setEmail(userToRegister.getEmail());
        // Ne hesira se jer se ne jos uve ne koristi BCrypt
        patient.setPassword(passwordEncoder.encode(userToRegister.getPassword()));

        patient.setFirstName(userToRegister.getFirstName());
        patient.setLastName(userToRegister.getLastName());
        patient.setAddress(userToRegister.getAddress());

        patient.setCity(userToRegister.getCity());
        patient.setCountry(userToRegister.getCountry());
        patient.setPhoneNumber(userToRegister.getPhoneNumber());

        patient.setSocialSecurityNumber(userToRegister.getSocialSecurityNumber());
        patient.setLastPasswordResetDate(null);

        List<Authority> auth = authorityService.findByName("ROLE_PATIENT");
        patient.setAuthorities(auth);

        patient.setMedicalRecord(null);
        patient.setAppointments(null);

        patient = this.userRepository.save(patient);
        return patient;
    }
}
