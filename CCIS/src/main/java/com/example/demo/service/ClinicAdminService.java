package com.example.demo.service;

import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    private final ModelMapper modelMapper = new ModelMapper();


    public ClinicAdmin getClinicAdminByEmail(String email){
        return clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
    }

    public List<DoctorDTO> getClinicDoctors(String email){
        ClinicAdmin ca = clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
        return ca.getClinic().getDoctors().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DoctorDTO convertToDTO(Doctor doctor){
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        return doctorDTO;
    }
}
