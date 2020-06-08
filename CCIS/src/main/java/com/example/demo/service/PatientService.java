package com.example.demo.service;

import com.example.demo.Repository.PatientRepository;
import com.example.demo.model.Patient;
import com.example.demo.useful_beans.PatientToAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatients(String clinic_name){
        return patientRepository.findByClinicName(clinic_name);
    }

    public Patient findByEmail(String patientEmail){
        return patientRepository.findByEmail(patientEmail);
    }
}