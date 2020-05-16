package com.example.demo.api;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Calendar;
import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;
import com.example.demo.useful_beans.MedicalStaffRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper = new ModelMapper();

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/sendVacationRequest")
    @PreAuthorize("hasAnyRole('DOCTOR')")
    public ResponseEntity<String> sendVacationRequest(@RequestBody MedicalStaffRequest request){
        if(doctorService.sendRequest(request)){
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/scheduleAppointment")
    @PreAuthorize("hasAnyRole('DOCTOR')")
    public ResponseEntity<String> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO){
        if(doctorService.schedule(appointmentDTO)){
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad", HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public DoctorDTO findById(@PathVariable("id") Integer id){
        Doctor doctor = doctorService.findById(id);
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setFields(doctor);
        return doctorDTO;
    }

    @GetMapping(path = "/calendar/{email}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    public Calendar getDoctorsCalendar(@PathVariable("email") String email){
        Doctor doctor = doctorService.findByEmail(email);
        DoctorDTO doctorDTO = modelMapper.map(doctor,DoctorDTO.class);
        doctorDTO.setFields(doctor);
        return doctorDTO.getCalendar();
    }
}
