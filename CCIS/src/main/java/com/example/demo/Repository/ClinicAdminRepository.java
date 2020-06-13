package com.example.demo.Repository;

import com.example.demo.model.ClinicAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClinicAdminRepository extends JpaRepository<ClinicAdmin, Integer> {

    @Query("SELECT admin FROM ClinicAdmin admin JOIN FETCH admin.clinic WHERE admin.email = (:email)")
    public ClinicAdmin findByEmailAndFetchClinicEagerly(@Param("email") String email);
    public ClinicAdmin findByEmail(String email);
    public List<ClinicAdmin> findByClinicId(Integer clinicId);

    @Query("select admin.clinic.id from ClinicAdmin admin where admin.id = (:id)")
    Optional<Integer> findClinicIdByAdminId(@Param("id") Integer id);

}
