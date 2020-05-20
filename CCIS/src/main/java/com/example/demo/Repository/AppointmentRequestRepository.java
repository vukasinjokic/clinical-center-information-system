package com.example.demo.Repository;

import com.example.demo.model.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Integer> {
}
