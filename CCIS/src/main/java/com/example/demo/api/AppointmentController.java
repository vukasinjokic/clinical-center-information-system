package com.example.demo.api;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Clinic;
import com.example.demo.service.AppointmentService;
import com.example.demo.useful_beans.AppointmentToAdd;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/appointments")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;
    private ModelMapper modelMapper = new ModelMapper();

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add")
    public boolean addAppointment(@RequestBody AppointmentToAdd appointment){
        appointmentService.addAppointment(appointment);
        return true;
    }

    @GetMapping("/getAppointments")
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> allAppointments = appointmentService.getAllAppointments();

        return allAppointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentDTO convertToDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        setDTOFields(appointmentDTO, appointment);
        return appointmentDTO;
    }

    private void setDTOFields(AppointmentDTO appointmentDTO, Appointment appointment){
        appointmentDTO.setDoctor(appointment.getDoctor());
        appointmentDTO.setPatient(appointment.getPatient());
        appointmentDTO.setOperationRoom(appointment.getOperationRoom());
        appointmentDTO.setExaminationType(appointment.getExaminationType());
        appointmentDTO.setClinic(appointment.getClinic());
        appointmentDTO.setTime(appointment.getTime());
    }
}
