package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientService {

    Set<Patient> patients = new HashSet<Patient>();

    public boolean addPatient(Patient patient){
        patients.add(patient);
        return true;
    }

    public Set<Patient> getPatients(){
        return patients;
    }

    public boolean deletePatient(String id) {
        Optional<Patient> patient = selectPatientById(id);
        if(patient.isPresent())
        {
            patients.remove(patient);
            return true;
        }
        return false;
    }

    public Optional<Patient> selectPatientById(String id){
        return patients.stream().filter(patient -> patient.getEmail().equals(id))
                .findFirst();
    }
}