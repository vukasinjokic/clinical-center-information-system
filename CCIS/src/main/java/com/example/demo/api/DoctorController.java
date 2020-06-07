package com.example.demo.api;

import com.example.demo.Repository.PatientRepository;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Calendar;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.service.DoctorService;
import com.example.demo.model.MedicalStaffRequest;
import com.example.demo.useful_beans.Grade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

    private final DoctorService doctorService;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public DoctorController(DoctorService doctorService, PatientRepository patientRepository) {
        this.doctorService = doctorService;
        this.patientRepository = patientRepository;
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
    public ResponseEntity<String> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
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

    @PostMapping("/gradeDoctor")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public ResponseEntity<String> gradeDoctor(@RequestBody Grade grade) {
        Doctor doctor = doctorService.findById(grade.itemId);
        if (doctor == null)
            return new ResponseEntity<>("There is no doctor with given id.", HttpStatus.BAD_REQUEST);

        Patient patient = patientRepository.findByEmail(grade.patientEmail);
        if (patient == null)
            return new ResponseEntity<>("There is no patient with given email.", HttpStatus.BAD_REQUEST);

        boolean success = doctorService.gradeDoctor(doctor, patient.getId(), grade.newGrade);
        if (success)
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else
            return new ResponseEntity<>("Doctor grade not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
