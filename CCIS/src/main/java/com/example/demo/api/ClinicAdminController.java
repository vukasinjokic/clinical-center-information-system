package com.example.demo.api;

import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.Repository.ClinicRepository;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.model.MedicalStaffRequest;
import com.example.demo.service.ClinicAdminService;
import com.example.demo.service.EmailService;
import com.example.demo.useful_beans.AppointmentToReserve;
import com.example.demo.useful_beans.DeclineVacRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/getVacationRequests")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public List<MedicalStaffRequest> getVacationRequests(){
        return clinicAdminService.getRequests();
    }

    @PostMapping("/declineRequest")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Void> declineRequest(@RequestBody DeclineVacRequest declineVacRequest){
        if(clinicAdminService.declineRequest(declineVacRequest))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/acceptRequest/{id}")
//    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Void> acceptRequest(@PathVariable("id") Integer id){
        if(clinicAdminService.AcceptRequest(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/getProfit")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity getProfit(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "dateFrom") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo)
    {
        float ret = 0;
        try{
            ret = clinicAdminService.getProfit(email, dateFrom,dateTo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(ret);
    }

    @PostMapping(path ="/handleReservation", consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public void handleReservation(@RequestBody AppointmentToReserve appointmentToReserve){

        try {
            clinicAdminService.handleReservation(appointmentToReserve);
        }catch( Exception e ){
            ResponseEntity.status(404);
            e.printStackTrace();
        }

    }

}
