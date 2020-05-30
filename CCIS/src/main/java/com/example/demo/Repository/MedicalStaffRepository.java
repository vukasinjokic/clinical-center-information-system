package com.example.demo.Repository;

import com.example.demo.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {

//    MedicalStaff findByEmail(String email);
}
