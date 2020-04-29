package com.example.demo.service;

import com.example.demo.Repository.ClinicRepository;
import com.example.demo.model.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClinicService {
    private static Set<Clinic> clinics;
    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public Clinic findById(Integer id) {
        return clinicRepository.findById(id);
    }

    public Set<Clinic> getAllClinics() {
        return clinicRepository.getAllClinics();
    }
}