package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.*;
import com.example.demo.useful_beans.AppointmentToAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;

    public boolean addAppointment(AppointmentToAdd appointment){
        return true;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointment(AppointmentDTO appointmentDTO) throws ParseException {
        System.out.println(appointmentDTO.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date date = formatter.parse(appointmentDTO.getDate());
        Doctor getDoctor = doctorRepository.findByEmail(appointmentDTO.getDoctor());
        int room_len = appointmentDTO.getRoom().length();
        String room_number = appointmentDTO.getRoom().substring(room_len - 3, room_len);
        Room getRoom = roomRepository.findByNumber(room_number);
        Clinic getClinic = clinicRepository.findByName("Poliklinika Sparta").get();
        ExaminationType getType = examinationTypeRepository.findByName(appointmentDTO.getExaminationType());

        Appointment appointment_to_add = new Appointment(date,appointmentDTO.getPrice(),0,getDoctor,getRoom,getType,getClinic);

        appointmentRepository.save(appointment_to_add);

        return appointment_to_add;
    }

}
