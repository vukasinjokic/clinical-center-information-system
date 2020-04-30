package com.example.demo.api;

import com.example.demo.dto.ClinicDTO;
import com.example.demo.model.Clinic;
import com.example.demo.service.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
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
    public List<ClinicDTO> getAllClinics() {
        List<Clinic> clinics = clinicService.getAllClinics();

        return clinics.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public ClinicDTO findById(@PathVariable("id") Integer id) {
        Clinic clinic = clinicService.findById(id);
        ClinicDTO clinicDTO = modelMapper.map(clinic, ClinicDTO.class);
        clinicDTO.setDTOFields(clinic);
        return clinicDTO;
    }

    private ClinicDTO convertToDTO(Clinic clinic){
        ClinicDTO clinicDTO = modelMapper.map(clinic, ClinicDTO.class);
        clinicDTO.setDTOFields(clinic);
        return clinicDTO;
    }
}