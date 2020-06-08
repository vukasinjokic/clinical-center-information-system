package com.example.demo.Repository;

import com.example.demo.model.Appointment;
import com.example.demo.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    Optional<Clinic> findById(Integer id);
    List<Clinic> findAll();
    Optional<Clinic> findByName(String name);

}
