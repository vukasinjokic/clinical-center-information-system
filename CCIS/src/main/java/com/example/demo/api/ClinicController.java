package com.example.demo.api;

import com.example.demo.model.Clinic;
import com.example.demo.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clinics")
@RestController
public class ClinicController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public Set<Clinic> getAllClinics() {
        return clinicService.getAllClinics();
    }

    @GetMapping(path = "{id}")
    public Clinic findById(@PathVariable("id") Integer id) {
        return clinicService.findById(id);
    }
}