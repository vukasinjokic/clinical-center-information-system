package com.example.demo.api;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.UserRegisterRequest;
import com.example.demo.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/registrationRequests")
@RestController
public class RegistrationRequestsController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @GetMapping("/getRegistrationRequests")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public List<UserRegisterRequest> getAppointmentRequests(){
        return registrationRequestService.getRequests();
    }

    @PostMapping("/handleAcceptingRequest/{id}")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity handleAcceptingRequest(@PathVariable Integer id){
        if(registrationRequestService.handleAcceptingRequest(id)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Accepted request doesn't exist");
    }

    @DeleteMapping("/deleteRequest/{id}")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity deleteRequest(@PathVariable Integer id){
        if(registrationRequestService.deleteRequest(id)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Cannot delete request because it doesn't exist");
    }
}
