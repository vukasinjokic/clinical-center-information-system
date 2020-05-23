package com.example.demo.Repository;

import com.example.demo.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {

    Nurse findByEmail(String email);
}
