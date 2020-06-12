package com.example.demo.api;

import com.example.demo.Repository.MedicalStaffRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.MedicalRecordDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.ForbiddenException;
import com.example.demo.model.*;
import com.example.demo.service.DoctorService;
import com.example.demo.service.MedicalStaffService;
import com.example.demo.useful_beans.Grade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

    private final DoctorService doctorService;
    private final PatientRepository patientRepository;
    private final MedicalStaffService medicalStaffService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public DoctorController(DoctorService doctorService, PatientRepository patientRepository, MedicalStaffService medicalStaffService) {
        this.doctorService = doctorService;
        this.patientRepository = patientRepository;
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public List<DoctorDTO> getDoctors(){
        List<Doctor> doctors = doctorService.findAllDoctors();

        return doctors.stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "getDoctorsFromClinic/{clinicId}")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public List<DoctorDTO> getDoctorsFromClinic(@PathVariable Integer clinicId){
        List<Doctor> doctors = doctorService.findDoctorsFromClinic(clinicId);
        doctors.removeIf(doctor -> !doctor.getActivity());
        return doctors.stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/saveDoctor")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctorDTO){
        Doctor saved = doctorService.saveDoctor(doctorDTO);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<DoctorDTO>(convertDTO(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDoctor{id}")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) throws ForbiddenException {
        doctorService.deleteDoctor(id);

        return new ResponseEntity<>("Usesno obrisan", HttpStatus.OK);

        //return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/sendVacationRequest")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
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

    @GetMapping("/getPatientProfile/{patient_email}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    public ResponseEntity getPatientProfile(@PathVariable String patient_email){
        Patient patient = doctorService.findPatientProfile(patient_email);
        UserDTO userDTO = modelMapper.map(patient, UserDTO.class);
        if(patient != null){
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

    @GetMapping("/checkIfCanViewRecord/{patient_email}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    public ResponseEntity canViewRecord(@PathVariable String patient_email){
        if(doctorService.canStaffViewRecord(patient_email))
            return ResponseEntity.ok().body(true);
        return ResponseEntity.badRequest().body(false);
    }

    @GetMapping(path = "/calendar/{email}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    public Calendar getDoctorsCalendar(@PathVariable("email") String email){
        return medicalStaffService.getStaffCalendar(email);
    }

    @PostMapping("/updateMedicalRecord")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity updateMedicalRecord(@RequestBody MedicalRecordDTO recordToUpdate){
        if(doctorService.updateMedicalRecord(recordToUpdate)){
            return ResponseEntity.ok("Successfully updated patient's medical record");
        }
        return ResponseEntity.badRequest().body("Something went wrong. Can't update patients medical record");
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
    public DoctorDTO convertDTO(Doctor doctor){
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setFields(doctor);
        return doctorDTO;
    }
}
