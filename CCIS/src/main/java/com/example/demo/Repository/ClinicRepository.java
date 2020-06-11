package com.example.demo.Repository;

import com.example.demo.model.Appointment;
import com.example.demo.model.Clinic;
import com.example.demo.model.Doctor;
import com.example.demo.model.Prescription;
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

    @Query("SELECT c.doctors FROM Clinic c WHERE c = (:clinic)")
    List<Doctor> findDoctorsFromClinic(@Param("clinic") Clinic clinic);

    @Query("select clinic.prescriptions from Clinic clinic where clinic.id = (:id)")
    List<Prescription> getClinicsPerscriptions(@Param("id") Integer id);
}
