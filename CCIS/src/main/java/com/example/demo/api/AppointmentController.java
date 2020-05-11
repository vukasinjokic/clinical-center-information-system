package com.example.demo.api;

import com.example.demo.Repository.DoctorRepository;
import com.example.demo.Repository.ExaminationTypeRepository;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.OperationRoomDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.ExaminationType;
import com.example.demo.model.OperationRoom;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.OperationRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.text.ParseException;
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
    private final AppointmentService appointmentService;
    private final OperationRoomService operationRoomService;
    private ModelMapper modelMapper = new ModelMapper();

    public AppointmentController(AppointmentService appointmentService, OperationRoomService operationRoomService){
        this.appointmentService = appointmentService;
        this.operationRoomService = operationRoomService;
    }
    @GetMapping("/getAppointments")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> allAppointments = appointmentService.getAllAppointments();

        return allAppointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    //operation rooms for free appointment
    @GetMapping("/getRooms")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<OperationRoomDTO> getOperationRooms(){
        List<OperationRoom> operationRooms = operationRoomService.getAllOperationRooms();

        return operationRooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/getTypes")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<ExaminationType> getExaminationTypes(){
        return examinationTypeRepository.findAll();
    }

    @GetMapping(path="/getDoctors/{ex_type_name}")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<DoctorDTO> getDoctorsByExType(@PathVariable("ex_type_name") String ex_type_name){
        List<Doctor> doctors = doctorRepository.findByExaminationTypeName(ex_type_name);

        return doctors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/addAppointment", consumes = "application/json")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
        //patient = null  clinic = trenutno je zakucana vrednost dok ne bude login gotov
        //validacija ide u service
        Appointment appointment = appointmentService.saveAppointment(appointmentDTO);

        return new ResponseEntity<AppointmentDTO>(convertToDTO(appointment), HttpStatus.CREATED);
    }

    public AppointmentDTO convertToDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        appointmentDTO.setFields(appointment);
        return appointmentDTO;
    }
    private OperationRoomDTO convertToDTO(OperationRoom operationRoom){
        OperationRoomDTO operationRoomDTO = modelMapper.map(operationRoom, OperationRoomDTO.class);
        operationRoomDTO.setDtoFields(operationRoom);
        return operationRoomDTO;
    }
    private DoctorDTO convertToDTO(Doctor doctor){
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        //doctorDTO.setFields(doctor);
        return doctorDTO;
    }
}
