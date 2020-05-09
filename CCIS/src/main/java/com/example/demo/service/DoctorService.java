package com.example.demo.service;

import com.example.demo.Repository.DoctorRepository;
import com.example.demo.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findById(Integer id){
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor findByEmail(String email){
        return doctorRepository.findByEmail(email);
    }

}
