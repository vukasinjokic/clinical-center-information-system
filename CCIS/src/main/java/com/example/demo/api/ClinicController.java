package com.example.demo.api;

import com.example.demo.dto.ClinicDTO;
import com.example.demo.dto.ClinicsDTO;
import com.example.demo.dto.PrescriptionDTO;
import com.example.demo.model.Clinic;
import com.example.demo.model.Prescription;
import com.example.demo.service.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clinics")
@RestController
public class ClinicController {

    private final ClinicService clinicService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
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

    @GetMapping("/getPerscriptions")
    @PreAuthorize("hasRole('NURSE')")
    public List<PrescriptionDTO> getPerscriptions() {
        List<Prescription> prescriptions = clinicService.getClinicsPerscriptions();
        return prescriptions.stream()
                .map(prescription -> {return modelMapper.map(prescription, PrescriptionDTO.class);})
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/addClinic", consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity<ClinicDTO> addClinic(@RequestBody ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = clinicService.addClinic(clinicDTO);
        return new ResponseEntity<ClinicDTO>(convertToClinicDTO(clinic), HttpStatus.CREATED);
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