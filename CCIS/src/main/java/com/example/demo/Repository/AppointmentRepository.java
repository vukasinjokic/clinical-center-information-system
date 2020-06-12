package com.example.demo.Repository;

import com.example.demo.model.Appointment;
import com.example.demo.model.Calendar;
import com.example.demo.model.Clinic;
import com.example.demo.model.CodeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByExaminationTypeId(Integer id);

    List<Appointment> findByRoomId(Integer id);

    List<Appointment> findByDoctorId(Integer id);
    List<Appointment> findByPatientIdAndFinished(Integer id, boolean finished);

    @Query("SELECT a FROM Appointment a WHERE a.patient IS NULL AND a.clinic = (:clinic) AND a.finished = false")
    List<Appointment> findPredefinedAppointments(@Param("clinic") Clinic clinic);

    @Query("SELECT appointment FROM Appointment appointment JOIN FETCH appointment.clinic WHERE appointment.id = (:id)")
    Optional<Appointment> findByIdAndFetchClinicEagerly(@Param("id") Integer id);

    @Query("SELECT appointment FROM Appointment appointment JOIN FETCH appointment.doctor WHERE appointment.id = (:id)")
    Appointment findByIdAndFetchDoctorEagerly(@Param("id") Integer id);

    @Query("select appointment.patient.email FROM Appointment appointment where appointment.id = (:id)")
    String findPatientEmailFromAppointment(@Param("id") Integer id);

    @Query("select appointment.doctor.calendar from Appointment appointment where appointment.id = (:id)")
    Calendar findDoctorsCalendarFromAppointment(@Param("id") Integer id);

    Optional<Appointment> findFirstByDoctorEmailAndPatientEmailAndFinishedFalseOrderByTimeAsc(@Param("doctorEmail") String doctorEmail, @Param("patientEmail") String patientEmail);

    List<Appointment> findAllByClinicIdAndTimeBetweenAndFinished(
            @Param("clinicId") Integer clinicId,
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo")Date dateTo,
            @Param("finished")Boolean finished);

    List<Appointment> findAllByRoomIdAndTimeAfter(
            @Param("roomId") Integer roomId,
            @Param("date") Date date);

    List<Appointment> findAllByDoctorIdAndTimeAfter(
            @Param("doctorId") Integer doctorId,
            @Param("time") Date time
    );

    List<Appointment> findAllByDoctorIdAndPatientEmailAndFinished(
            @Param("doctorId") Integer doctorId,
            @Param("patientEmail") String patientEmail,
            @Param("finished") Boolean finished);

}
