package com.example.demo.api;

import com.example.demo.Repository.DoctorRepository;
import com.example.demo.Repository.ExaminationTypeRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.model.*;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.RoomService;
import com.example.demo.useful_beans.AppointmentToReservePatient;
import com.example.demo.useful_beans.UserData;
import com.example.demo.useful_beans.AppointmentToFinish;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/appointments")
@RestController
public class AppointmentController {

    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorService doctorService;

    private final AppointmentService appointmentService;
    private final RoomService roomService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AppointmentController(AppointmentService appointmentService, RoomService roomService){
        this.appointmentService = appointmentService;
        this.roomService = roomService;
    }
    @GetMapping("/getAppointments")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> allAppointments = appointmentService.getAllAppointments();

        return allAppointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/getPatientAppointments")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public List<AppointmentDTO> getPatientAppointments(@RequestBody UserData userData) {
        Patient patient = patientRepository.findByEmail(userData.mail);
        List<Appointment> allAppointments = appointmentService.findByPatientIdFinished(patient.getId());

        return allAppointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/getPredefinedAppointments/{clinicId}")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public List<AppointmentDTO> getPredefinedAppointments(@PathVariable Integer clinicId) {
        List<Appointment> predefinedAppointments = appointmentService.getPredefinedAppointments(clinicId);

        return predefinedAppointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/addPatientToPredefinedAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> addPatientToPredefinedAppointment(@RequestBody AppointmentToReservePatient appointmentToAdd) {
        Patient patient = patientRepository.findByEmail(appointmentToAdd.getPatientEmail());
        if (patient == null)
            return new ResponseEntity<>("Invalid user email", HttpStatus.BAD_REQUEST);

        Appointment predefinedAppointment = appointmentService.getAppointment(appointmentToAdd.getAppointmentId());

        int result = appointmentService.savePatientToPredefinedAppointments(patient, predefinedAppointment);
        if (result == 0)
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else if (result == 1)
            return new ResponseEntity<>("Request not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
        else if (result == -1) {
            return new ResponseEntity<>("Appointments is already taken.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Unknown server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //operation rooms for free appointment
    @GetMapping("/getRooms")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<RoomDTO> getRooms(){
        List<Room> rooms = roomService.getAllRooms();

        return rooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/getTypes")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<ExaminationType> getExaminationTypes(){
        return examinationTypeRepository.findAll();
    }

    @GetMapping(path="/getDoctors/{ex_type_id}")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<DoctorDTO> getDoctorsByExType(@PathVariable("ex_type_id") Integer ex_type_id){
        List<Doctor> doctors = doctorService.gedDoctorsByExType(ex_type_id);
        return doctors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/addAppointment", consumes = "application/json;charset=UTF-8")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
        //validacija ide u service
        Appointment appointment = appointmentService.saveAppointment(appointmentDTO);
        if(appointment == null)
            return ResponseEntity.badRequest().header("Soba je zauzeta").body(new AppointmentDTO());

        return new ResponseEntity<AppointmentDTO>(convertToDTO(appointment), HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAppointment/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable("id") Integer id){
        Appointment appointment = appointmentService.getAppointment(id);
        if(appointment == null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);};
        return new ResponseEntity<AppointmentDTO>(convertToDTO(appointment), HttpStatus.OK);

    }

    @GetMapping(path = "/getCodebook/{appointment_id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<CodeBook> getCodebookFromAppointmentClinic(@PathVariable("appointment_id") Integer appointment_id){
        CodeBook codebook = appointmentService.getCodebookFromAppointmentClinic(appointment_id);
        if(codebook == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<CodeBook>(codebook, HttpStatus.OK);
    }

    @GetMapping(path = "/getPatientEmail/{appointment_id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity getPatientEmail(@PathVariable("appointment_id") Integer appointment_id){
        String email = appointmentService.getPatinetEmail(appointment_id);
        if(email == null) return ResponseEntity.badRequest().body("No email found");
        return ResponseEntity.ok(email);
    }

    @GetMapping(path = "/getAppointmentForPatient/{patientEmail}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity getAppointmentForPatient(@PathVariable("patientEmail") String patientEmail){
        Appointment appointment = appointmentService.getAppointmentForPatient(patientEmail);
        if(appointment == null) return ResponseEntity.badRequest().body("You don't have a scheduled appointment with this patient");
        return new ResponseEntity<AppointmentDTO>(convertToDTO(appointment), HttpStatus.OK);

    }

    @PostMapping(path = "/handleAppointmentFinish", consumes = "application/json")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity handleAppointmentFinish(@RequestBody AppointmentToFinish appointmentToFinish){
        if(appointmentService.handleAppointmentFinish(appointmentToFinish)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    public AppointmentDTO convertToDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        appointmentDTO.setFields(appointment);
        return appointmentDTO;
    }
    private RoomDTO convertToDTO(Room room){
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        roomDTO.setDtoFields(room);
        return roomDTO;
    }
    private DoctorDTO convertToDTO(Doctor doctor){
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        //doctorDTO.setFields(doctor);
        return doctorDTO;
    }
}
