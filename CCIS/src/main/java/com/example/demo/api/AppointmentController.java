package com.example.demo.api;

import com.example.demo.model.Appointment;
import com.example.demo.model.Clinic;
import com.example.demo.service.AppointmentService;
import com.example.demo.useful_beans.AppointmentToAdd;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/appointments")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add")
    public boolean addAppointment(@RequestBody AppointmentToAdd appointment){
        appointmentService.addAppointment(appointment);
        return true;
    }

    @GetMapping("/getAppointments")
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

}
