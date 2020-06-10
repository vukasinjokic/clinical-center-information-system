package com.example.demo.service;

import com.example.demo.Repository.AppointmentRequestRepository;
import com.example.demo.Repository.CodeBookRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.RegistrationRequestRepository;
import com.example.demo.model.*;
import com.example.demo.useful_beans.CodeBookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicCenterAdminService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private CodeBookRepository codeBookRepository;

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

    public CodeBook getCodeBook() {
        return codeBookRepository.findById(1).get();
    }

    public boolean addMedication(CodeBookEntity medication) {
        CodeBook codeBook = codeBookRepository.findById(1).get();
        if(codeBook.getMedications().containsKey(medication.code)){
            return false;
        }
        codeBook.getMedications().put(medication.code, medication.name);
        codeBookRepository.save(codeBook);
        return true;
    }

    public boolean addDiagnosis(CodeBookEntity diagnosis) {
        CodeBook codeBook = codeBookRepository.findById(1).get();
        if(codeBook.getDiagnoses().containsKey(diagnosis.code)){
            return false;
        }
        codeBook.getDiagnoses().put(diagnosis.code, diagnosis.name);
        codeBookRepository.save(codeBook);
        return true;
    }
}
