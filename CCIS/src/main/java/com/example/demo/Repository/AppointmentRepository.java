package com.example.demo.Repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByExaminationTypeId(Integer id);

    List<Appointment> findByRoomId(Integer id);

}
