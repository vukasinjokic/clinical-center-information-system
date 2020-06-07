package com.example.demo.api;

import com.example.demo.Repository.PatientRepository;
import com.example.demo.dto.ClinicDTO;
import com.example.demo.dto.ClinicsDTO;
import com.example.demo.model.Clinic;
import com.example.demo.model.Patient;
import com.example.demo.service.ClinicService;
import com.example.demo.useful_beans.Grade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clinics")
@RestController
public class ClinicController {

    private final ClinicService clinicService;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ClinicController(ClinicService clinicService, PatientRepository patientRepository) {
        this.clinicService = clinicService;
        this.patientRepository = patientRepository;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PATIENT','CLINIC_CENTER_ADMIN')")
    public List<ClinicsDTO> getAllClinics() {
        List<Clinic> clinics = clinicService.getAllClinics();
        return clinics.stream()
                .map(this::convertToClinicsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('PATIENT', 'CLINIC_CENTER_ADMIN')")
    public ClinicDTO findById(@PathVariable("id") Integer id) {
        Clinic clinic = clinicService.findById(id);
        ClinicDTO clinicDTO = modelMapper.map(clinic, ClinicDTO.class);
        clinicDTO.setDTOFields(clinic);
        return clinicDTO;
    }

    @PostMapping(path = "/addClinic", consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity<ClinicDTO> addClinic(@RequestBody ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = clinicService.addClinic(clinicDTO);
        return new ResponseEntity<ClinicDTO>(convertToClinicDTO(clinic), HttpStatus.CREATED);
    }

    @PostMapping("/gradeClinic")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public ResponseEntity<String> gradeClinic(@RequestBody Grade grade) {
        Clinic clinic = clinicService.findById(grade.itemId);
        if (clinic == null)
            return new ResponseEntity<>("There is no clinic with given id.", HttpStatus.BAD_REQUEST);

        Patient patient = patientRepository.findByEmail(grade.patientEmail);
        if (patient == null)
            return new ResponseEntity<>("There is no patient with given email.", HttpStatus.BAD_REQUEST);

        boolean success = clinicService.gradeClinic(clinic, patient.getId(), grade.newGrade);
        if (success)
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else
            return new ResponseEntity<>("Clinic grade not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClinicDTO convertToClinicDTO(Clinic clinic){
        ClinicDTO clinicDTO = modelMapper.map(clinic, ClinicDTO.class);
        clinicDTO.setDTOFields(clinic);
        return clinicDTO;
    }

    private ClinicsDTO convertToClinicsDTO(Clinic clinic){
        ClinicsDTO clinicsDTO = modelMapper.map(clinic, ClinicsDTO.class);
        clinicsDTO.setDTOFields(clinic);
        return clinicsDTO;
    }
}