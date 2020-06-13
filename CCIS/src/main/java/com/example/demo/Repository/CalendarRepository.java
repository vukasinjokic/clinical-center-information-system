package com.example.demo.Repository;

import com.example.demo.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    List<Calendar> findAllByAppointmentIdsContaining(@Param("appointment_id") Integer appointment_id);
}
