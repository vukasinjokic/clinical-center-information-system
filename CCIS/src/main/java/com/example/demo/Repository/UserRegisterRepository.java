package com.example.demo.Repository;

import com.example.demo.model.UserRegisterRequest;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegisterRequest,Integer> {

}
