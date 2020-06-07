package com.example.demo.service;

import com.example.demo.Repository.ClinicRepository;
import com.example.demo.Repository.CodeBookRepository;
import com.example.demo.Repository.RatingRepository;
import com.example.demo.dto.ClinicDTO;
import com.example.demo.model.Clinic;
import com.example.demo.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RatingRepository ratingRepository;

    @Autowired
    private CodeBookRepository codeBookRepository;


    public Clinic findById(Integer id) {
        return clinicRepository.findById(id).orElse(null);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public boolean gradeClinic(Clinic clinic, Integer patientId, float newGrade) {
        Rating clinicRating = clinic.getRating();
        clinicRating.setGrade(patientId, newGrade);
        clinicRating = ratingRepository.save(clinicRating);
        return clinicRating != null;
    }

    public Clinic addClinic(ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = new Clinic(clinicDTO.getName(), clinicDTO.getDescription(), clinicDTO.getAddress());
        clinicRepository.save(clinic);
        return clinic;
    }
}