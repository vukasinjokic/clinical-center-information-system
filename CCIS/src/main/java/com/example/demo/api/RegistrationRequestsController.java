package com.example.demo.api;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.UserRegisterRequest;
import com.example.demo.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/registrationRequests")
@RestController
public class RegistrationRequestsController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @GetMapping("/getRegistrationRequests")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public List<UserRegisterRequest> getAppointmentRequests(){
        return registrationRequestService.getRequests();
    }
}
