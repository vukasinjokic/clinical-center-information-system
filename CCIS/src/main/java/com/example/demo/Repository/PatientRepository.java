package com.example.demo.Repository;

import com.example.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByClinicName(String name);
}
