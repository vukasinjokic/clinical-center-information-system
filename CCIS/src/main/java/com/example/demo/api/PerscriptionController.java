package com.example.demo.api;

import com.example.demo.dto.PrescriptionDTO;
import com.example.demo.model.Prescription;
import com.example.demo.service.PerscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/perscriptions")
@RestController
public class PerscriptionController {

    @Autowired
    private PerscriptionService perscriptionService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/handleAcceptingPerscription/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity handleAcceptingPerscription(@PathVariable Integer id){
        if(perscriptionService.handleAcceptingPerscription(id)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Verified perscription doesn't exist");
    }

    @DeleteMapping("/deletePerscription/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity handleDeleteRequest(@PathVariable Integer id){
        if(perscriptionService.handleDeletePerscription(id)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Cannot delete perscription because it doesn't exist");
    }

    @GetMapping("/getPerscriptions")
    @PreAuthorize("hasRole('NURSE')")
    public List<PrescriptionDTO> getPerscriptions() {
        List<Prescription> prescriptions = perscriptionService.getClinicsNotVerifiedPerscriptions();
        return prescriptions.stream()
                .map(prescription -> {return modelMapper.map(prescription, PrescriptionDTO.class);})
                .collect(Collectors.toList());
    }


}
