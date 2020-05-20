package com.example.demo.service;

import com.example.demo.Repository.UserRegisterRepository;
import com.example.demo.model.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    private final UserRegisterRepository userRegisterRepository;

    @Autowired
    public UserRegisterService(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }

    public boolean saveUserRegisterRequest(UserRegisterRequest userRegisterRequest) {
        UserRegisterRequest save = userRegisterRepository.save(userRegisterRequest);
        return save != null;
    }
}
