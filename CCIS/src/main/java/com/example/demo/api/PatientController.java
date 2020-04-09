package com.example.demo.api;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/patients")
@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("add")
    public String addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
        return "Pacijent uspesno dodat.";
    }

    @GetMapping("/getPatients")
    public Set<Patient> getDoctors(){
        return patientService.getPatients();
    }

    @DeleteMapping(path = "{id}")
    public void deletePatient(@PathVariable("id") String id){
        patientService.deletePatient(id);
    }
}
