package com.example.demo.Repository;

import com.example.demo.model.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Integer> {

    @Query("select ar from AppointmentRequest ar join fetch ar.patient where ar.id = (:id)")
    Optional<AppointmentRequest> findByIdAndFetchPatientEagerly(@Param("id") Integer id);
}
