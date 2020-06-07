package com.example.demo.Repository;

import com.example.demo.model.Appointment;
import com.example.demo.model.Calendar;
import com.example.demo.model.CodeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByExaminationTypeId(Integer id);

    List<Appointment> findByRoomId(Integer id);

    @Query("SELECT appointment FROM Appointment appointment JOIN FETCH appointment.clinic WHERE appointment.id = (:id)")
    Optional<Appointment> findByIdAndFetchClinicEagerly(@Param("id") Integer id);

    @Query("select appointment.patient.email FROM Appointment appointment where appointment.id = (:id)")
    String findPatientEmailFromAppointment(@Param("id") Integer id);

    @Query("select appointment.doctor.calendar from Appointment appointment where appointment.id = (:id)")
    Calendar findDoctorsCalendarFromAppointment(@Param("id") Integer id);
}
