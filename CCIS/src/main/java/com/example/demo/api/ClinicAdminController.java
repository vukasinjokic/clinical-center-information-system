package com.example.demo.api;

import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.service.ClinicAdminService;
import com.example.demo.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clinicAdmins")
@RestController
public class ClinicAdminController {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private EmailService emailService;

    @GetMapping(path = "/getClinicDoctors/{email}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public List<DoctorDTO> getClinicDoctors(@PathVariable("email") String email){
        return clinicAdminService.getClinicDoctors(email);
    }

    @PostMapping(path ="/alertDoctorsOperation", consumes = "application/json")
//    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public void alertDoctorsOperation(@RequestBody List<DoctorDTO> doctors){

        try {
            emailService.alertDoctorsOperation(doctors);
        }catch( Exception e ){
            ResponseEntity.status(404);
            System.out.println(e.getMessage());
        }

    }

}
