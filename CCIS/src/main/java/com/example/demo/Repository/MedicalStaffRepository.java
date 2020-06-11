package com.example.demo.Repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {

    MedicalStaff findByEmail(String email);

    @Query("SELECT medicalStaff FROM MedicalStaff medicalStaff JOIN FETCH medicalStaff.clinic WHERE medicalStaff.email = (:email)")
    public MedicalStaff findByEmailAndFetchClinicEagerly(@Param("email") String email);
}
