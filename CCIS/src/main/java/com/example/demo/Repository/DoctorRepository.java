package com.example.demo.Repository;

import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findByExaminationTypeName(String name);
    Doctor findByEmail(String email);

    @Query("SELECT doctor FROM Doctor doctor JOIN FETCH doctor.clinic WHERE doctor.email = (:email)")
    public Doctor findByEmailAndFetchClinicEagerly(@Param("email") String email);

    @Query("SELECT doctor FROM Doctor doctor JOIN fetch doctor.appointments where doctor.id = (:id)")
    public Doctor findByIdAndFetchAppointmentsEagerly(@Param("id") Integer id);
}
