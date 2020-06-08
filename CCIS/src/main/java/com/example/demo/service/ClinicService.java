package com.example.demo.service;

import com.example.demo.Repository.ClinicRepository;
import com.example.demo.Repository.CodeBookRepository;
import com.example.demo.dto.ClinicDTO;
import com.example.demo.model.Clinic;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Nurse;
import com.example.demo.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Service
public class ClinicService {
    private static Set<Clinic> clinics;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private CodeBookRepository codeBookRepository;


    public Clinic findById(Integer id) {
        return clinicRepository.findById(id).orElse(null);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public boolean gradeClinic(Clinic clinic, float newRating) {
        clinic.setRating((clinic.getRating() + newRating) / 2);
        clinic = clinicRepository.save(clinic);
        return clinic != null;
    }

    public Clinic addClinic(ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = new Clinic(clinicDTO.getName(), clinicDTO.getDescription(), clinicDTO.getAddress());
        clinicRepository.save(clinic);
        return clinic;
    }

    public List<Prescription> getClinicsPerscriptions() {
        Nurse nurse = (Nurse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clinicRepository.getClinicsPerscriptions(nurse.getClinic().getId());
    }
}