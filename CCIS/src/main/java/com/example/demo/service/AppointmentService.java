package com.example.demo.service;

import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.model.Appointment;
import com.example.demo.useful_beans.AppointmentToAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public boolean addAppointment(AppointmentToAdd appointment){
        return true;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

}
