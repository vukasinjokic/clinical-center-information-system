package com.example.demo.Repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByExaminationTypeId(Integer id);

    List<Appointment> findByRoomId(Integer id);

    List<Appointment> findByDoctorId(Integer id);
    List<Appointment> findByPatientId(Integer id);

    @Query("SELECT appointment FROM Appointment appointment JOIN FETCH appointment.clinic WHERE appointment.id = (:id)")
    Optional<Appointment> findByIdAndFetchClinicEagerly(@Param("id") Integer id);

}
