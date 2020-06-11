package com.example.demo.api;

import com.example.demo.model.CodeBook;
import com.example.demo.model.UserRegisterRequest;
import com.example.demo.service.ClinicCenterAdminService;
import com.example.demo.useful_beans.CodeBookEntity;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clinicCenterAdmin")
@RestController
public class ClinicCenterAdminController {

    @Autowired
    private ClinicCenterAdminService clinicCenterAdminService;

    @GetMapping("/getRegistrationRequests")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public List<UserRegisterRequest> getAppointmentRequests(){
        return clinicCenterAdminService.getRequests();
    }

    @PostMapping("/handleAcceptingRequest/{id}")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity handleAcceptingRequest(@PathVariable Integer id){
        if(clinicCenterAdminService.handleAcceptingRequest(id)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Accepted request doesn't exist");
    }

    @PostMapping("/deleteRequest/{id}")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity handleDeleteRequest(@PathVariable Integer id, @RequestBody String message){
        if(clinicCenterAdminService.handleDeleteRequest(id, message)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Cannot delete request because it doesn't exist");
    }

    @PostMapping("/addMedication")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity addMedication(@RequestBody CodeBookEntity medication){
        if(clinicCenterAdminService.addMedication(medication)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Medication with that code already exists");
    }

    @PostMapping("/addDiagnosis")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity addDiagnosis(@RequestBody CodeBookEntity diagnosis){
        if(clinicCenterAdminService.addDiagnosis(diagnosis)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Medication with that code already exists");
    }

    @GetMapping("/getCodeBook")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity<CodeBook> getCodeBook(){
        CodeBook codeBook = clinicCenterAdminService.getCodeBook();
        if(codeBook == null){
            return new ResponseEntity<CodeBook>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CodeBook>(codeBook, HttpStatus.OK);
    }
}
