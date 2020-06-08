package com.example.demo.Repository;

import com.example.demo.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerscriptionRepository extends JpaRepository<Prescription, Integer> {

    @Query("select perscription from Prescription perscription where perscription.isVerified = false and perscription.clinic.id = (:clinic_id)")
    List<Prescription> getClinicsNotVerifiedPerscriptions(@Param("clinic_id") Integer clinic_id);

}
