package com.example.demo.Repository;

import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findByExaminationTypeId(Integer id);
    Doctor findByEmail(String email);

    Doctor findByEmailAndActivity(
            @Param("email") String email,
            @Param("activity") Boolean activity
    );

    List<Doctor> findAllByClinicIdAndActivity(
            @Param("clinicId") Integer clinicId,
            @Param("activity") Boolean activity
    );

    @Query("SELECT doctor FROM Doctor doctor JOIN FETCH doctor.clinic WHERE doctor.email = (:email)")
    public Doctor findByEmailAndFetchClinicEagerly(@Param("email") String email);

    @Query("SELECT doctor FROM Doctor doctor JOIN fetch doctor.appointments where doctor.id = (:id)")
    public Doctor findByIdAndFetchAppointmentsEagerly(@Param("id") Integer id);
}
