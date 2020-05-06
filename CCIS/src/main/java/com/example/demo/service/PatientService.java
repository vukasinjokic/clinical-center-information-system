package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.useful_beans.PatientToAdd;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientService {

    Set<Patient> patients = new HashSet<Patient>();

    public boolean addPatient(PatientToAdd patient){
        Patient p = new Patient();
        p.setFirstName(patient.name);
        patients.add(p);
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