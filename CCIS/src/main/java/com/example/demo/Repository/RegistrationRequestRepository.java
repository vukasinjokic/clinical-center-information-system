package com.example.demo.Repository;

import com.example.demo.model.UserRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequestRepository extends JpaRepository<UserRegisterRequest, Integer> {

}
