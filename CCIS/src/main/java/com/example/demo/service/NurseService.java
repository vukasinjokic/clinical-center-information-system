package com.example.demo.service;

import com.example.demo.Repository.NurseRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.model.Nurse;
import com.example.demo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatientsForNurseMail(String nurse_email){
        Nurse nurse = nurseRepository.findByEmail(nurse_email);
        return patientRepository.findByClinicName(nurse.getClinic().getName());
    }


}
