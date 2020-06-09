package com.example.demo.Repository;

import com.example.demo.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
}
