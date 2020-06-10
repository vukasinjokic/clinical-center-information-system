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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
}
