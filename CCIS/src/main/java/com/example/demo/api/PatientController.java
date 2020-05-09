package com.example.demo.api;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.PatientDTO;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.service.ClinicAdminService;
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
    private final ClinicAdminService clinicAdminService;
    private ModelMapper modelMapper = new ModelMapper();

    public PatientController(PatientService patientService, ClinicAdminService clinicAdminService) {
        this.patientService = patientService;
        this.clinicAdminService = clinicAdminService;
    }

    @GetMapping(path="/getPatients/{admin_email}")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN', 'DOCTOR')")
    public List<PatientDTO> getPatients(@PathVariable("admin_email") String admin_email){
        ClinicAdmin clinicAdmin = clinicAdminService.getClinicAdminByEmail(admin_email);
        List<Patient> patients = patientService.getPatients(clinicAdmin.getClinic().getName());

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
