package com.example.demo.Repository;

import com.example.demo.model.ClinicCenterAdmin;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByEmailOrSocialSecurityNumber(String email, String socialSecurityNumber);

    @Query("SELECT admin FROM ClinicCenterAdmin admin")
    List<ClinicCenterAdmin> findAllClinicCenterAdmins();
}
