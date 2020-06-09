package com.example.demo.service;

import com.example.demo.Repository.AppointmentRequestRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.RegistrationRequestRepository;
import com.example.demo.model.Authority;
import com.example.demo.model.Nurse;
import com.example.demo.model.Patient;
import com.example.demo.model.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public List<UserRegisterRequest> getRequests() {
        return registrationRequestRepository.findAll();
    }

    public boolean handleAcceptingRequest(Integer id) {
        UserRegisterRequest req = registrationRequestRepository.findById(id).get();
        if(req != null){
            List<Authority> auth = authorityService.findByName("ROLE_PATIENT");
            Patient patient = new Patient(req.getEmail(), req.getEmail(), passwordEncoder.encode(req.getPassword()), req.getFirstName(), req.getLastName(), req.getAddress(), req.getCity(), req.getCountry(), req.getPhoneNumber(), req.getSocialSecurityNumber(), auth);
            registrationRequestRepository.deleteById(id);
            patientRepository.save(patient);
            return true;
        }
        return false;
    }

    public boolean handleDeleteRequest(Integer id, String message) {
        Optional<UserRegisterRequest> req = registrationRequestRepository.findById(id);
        if(req.isPresent()){
            emailService.alertDeniedUser(req.get().getEmail(), message);
            registrationRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
