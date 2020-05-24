package com.example.demo.Repository;

import com.example.demo.model.BusinessHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Integer> {
}
