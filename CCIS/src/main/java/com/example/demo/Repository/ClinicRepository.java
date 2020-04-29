package com.example.demo.Repository;

import com.example.demo.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface ClinicRepository /* extends JpaRepository<Clinic, Integer>*/ {
    Clinic findById(Integer id);
    Set<Clinic> getAllClinics();
}
