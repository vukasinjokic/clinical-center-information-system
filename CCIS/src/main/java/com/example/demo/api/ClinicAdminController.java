package com.example.demo.api;

import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.service.ClinicAdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clinicAdmins")
@RestController
public class ClinicAdminController {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @GetMapping(path = "/getClinicDoctors/{email}")
    //@PreAuthorize
    public List<DoctorDTO> getClinicDoctors(@PathVariable("email") String email){
        return clinicAdminService.getClinicDoctors(email);
    }

}
