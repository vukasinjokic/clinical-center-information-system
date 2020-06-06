package com.example.demo.api;

import com.example.demo.model.Clinic;
import com.example.demo.model.Doctor;
import com.example.demo.service.ClinicService;
import com.example.demo.service.DoctorService;
import com.example.demo.useful_beans.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/grades")
@RestController
public class GradeController {
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    @Autowired
    public GradeController(DoctorService doctorService, ClinicService clinicService) {
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @PostMapping("/gradeDoctor")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public ResponseEntity<String> gradeDoctor(@RequestBody Grade grade) {
        Doctor doctor = doctorService.findById(grade.id);
        if (doctor == null)
            return new ResponseEntity<>("There is no doctor with given id.", HttpStatus.BAD_REQUEST);

        boolean success = doctorService.gradeDoctor(doctor, grade.newRating);
        if (success)
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else
            return new ResponseEntity<>("Doctor grade not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/gradeClinic")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public ResponseEntity<String> gradeClinic(@RequestBody Grade grade) {
        Clinic clinic = clinicService.findById(grade.id);
        if (clinic == null)
            return new ResponseEntity<>("There is no clinic with given id.", HttpStatus.BAD_REQUEST);

        boolean success = clinicService.gradeClinic(clinic, grade.newRating);

        if (success)
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else
            return new ResponseEntity<>("Clinic grade not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
