package com.example.demo.Repository;

import com.example.demo.model.ExaminationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationTypeRepository extends JpaRepository<ExaminationType,Integer> {
}
