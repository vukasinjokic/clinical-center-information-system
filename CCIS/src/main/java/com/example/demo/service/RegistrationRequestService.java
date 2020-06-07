package com.example.demo.service;

import com.example.demo.Repository.AppointmentRequestRepository;
import com.example.demo.Repository.RegistrationRequestRepository;
import com.example.demo.model.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    public List<UserRegisterRequest> getRequests() {
        return registrationRequestRepository.findAll();
    }
}
