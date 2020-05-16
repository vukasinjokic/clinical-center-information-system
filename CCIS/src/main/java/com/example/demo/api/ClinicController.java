package com.example.demo.api;

import com.example.demo.dto.ClinicDTO;
import com.example.demo.dto.ClinicsDTO;
import com.example.demo.model.Clinic;
import com.example.demo.service.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('PATIENT')")
    public List<ClinicsDTO> getAllClinics() {
        List<Clinic> clinics = clinicService.getAllClinics();
//        List<ClinicDTO> clinicDTOs = new ArrayList<>(clinics.size());
//
//        for (int i = 0; i < clinics.size(); i++) {
//            clinicDTOs.add(convertToDTO(clinics.get(i)));
//        }
//
//        return clinicDTOs;

        return clinics.stream()
                .map(this::convertToClinicsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ClinicDTO findById(@PathVariable("id") Integer id) {
        Clinic clinic = clinicService.findById(id);
        ClinicDTO clinicDTO = modelMapper.map(clinic, ClinicDTO.class);
        clinicDTO.setDTOFields(clinic);
        return clinicDTO;
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