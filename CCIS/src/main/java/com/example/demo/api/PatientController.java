package com.example.demo.api;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.PatientDTO;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.service.ClinicAdminService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;
import com.example.demo.useful_beans.PatientToAdd;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/patients")
@RestController
public class PatientController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    private ModelMapper modelMapper = new ModelMapper();

    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping(path="/getPatients/{doctor_email}")
    @PreAuthorize("hasAnyRole('DOCTOR')")
    public List<PatientDTO> getPatientsByClinic(@PathVariable("doctor_email") String doctor_email){
        Doctor d = doctorService.findByEmail(doctor_email);
        List<Patient> patients = patientService.getPatients(d.getClinic().getName());

        return patients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PatientDTO convertToDTO(Patient patient){
        PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
        return patientDTO;
    }

//    @DeleteMapping(path = "{id}")
//    public void deletePatient(@PathVariable("id") String id){
//        patientService.deletePatient(id);
//    }
}
