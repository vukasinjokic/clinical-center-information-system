package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.useful_beans.AppointmentToAdd;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppointmentService {

    Set<Appointment> appointments = new HashSet<Appointment>();

    public boolean addAppointment(AppointmentToAdd appointment){
        appointments.add(new Appointment(null, appointment.price, 0.0f, null, null, null, null));
        return true;
    }

    public Set<Appointment> getAllAppointments(){
        return appointments;
    }
}
