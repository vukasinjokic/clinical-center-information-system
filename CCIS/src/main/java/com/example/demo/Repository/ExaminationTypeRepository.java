package com.example.demo.Repository;

import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.ExaminationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExaminationTypeRepository extends JpaRepository<ExaminationType,Integer> {

    ExaminationType findByName(String name);

}
