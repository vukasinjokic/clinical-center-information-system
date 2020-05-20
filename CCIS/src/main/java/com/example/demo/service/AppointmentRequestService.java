package com.example.demo.service;

import com.example.demo.Repository.AppointmentRequestRepository;
import com.example.demo.model.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentRequestService {

    @Autowired
    private AppointmentRequestRepository appointmentRequestRepository;

    public List<AppointmentRequest> getRequests(){
        return appointmentRequestRepository.findAll();
    }

    public boolean saveRequest(AppointmentRequest request) {
        AppointmentRequest appointmentRequest = appointmentRequestRepository.save(request);
        return appointmentRequest != null;
    }
}
