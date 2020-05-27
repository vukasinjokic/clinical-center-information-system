package com.example.demo.api;

import com.example.demo.Repository.DoctorRepository;
import com.example.demo.Repository.ExaminationTypeRepository;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.ExaminationType;
import com.example.demo.model.Room;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private final RoomService roomService;
    private ModelMapper modelMapper = new ModelMapper();

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
        List<Doctor> doctors = doctorRepository.findByExaminationTypeId(ex_type_id);

        return doctors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/addAppointment", consumes = "application/json")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
        //validacija ide u service
        Appointment appointment = appointmentService.saveAppointment(appointmentDTO);
        if(appointment == null)
            return ResponseEntity.badRequest().header("Soba je zauzeta").body(new AppointmentDTO());

        return new ResponseEntity<AppointmentDTO>(convertToDTO(appointment), HttpStatus.CREATED);
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
