package com.example.demo.api;

import com.example.demo.model.Prescription;
import com.example.demo.service.PerscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/registrationRequests")
@RestController
public class PerscriptionController {

    @Autowired
    private PerscriptionService perscriptionService;


}
