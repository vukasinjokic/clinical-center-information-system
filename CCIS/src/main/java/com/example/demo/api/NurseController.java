package com.example.demo.api;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.service.NurseService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/nurses")
@RestController
public class NurseController {


    private final NurseService nurseService;
    private final ModelMapper modelMapper = new ModelMapper();

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping(path="/getPatients/{nurse_email}")
    @PreAuthorize("hasAnyRole('NURSE')")
    public List<PatientDTO> getPatientsByClinicForNurse(@PathVariable("nurse_email") String nurse_email){
        List<Patient> patients = nurseService.getPatientsForNurseMail(nurse_email);


        return patients.stream()
                .map(this::convertPatientToDTO)
                .collect(Collectors.toList());
    }

    private PatientDTO convertPatientToDTO(Patient patient){
        PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
        return patientDTO;
    }
}
