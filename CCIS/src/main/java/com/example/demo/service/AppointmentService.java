package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.*;
import com.example.demo.useful_beans.AppointmentToAdd;
import com.example.demo.validation.AppointmentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    private AppointmentValidation appointmentValidation;

    public boolean addAppointment(AppointmentToAdd appointment){
        return true;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointment(AppointmentDTO appointmentDTO) throws ParseException {
        ClinicAdmin user = (ClinicAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date date = formatter.parse(appointmentDTO.getDate());
        Doctor getDoctor = doctorRepository.findByEmail(appointmentDTO.getDoctor());
        int room_len = appointmentDTO.getRoom().length();
        String room_number = appointmentDTO.getRoom().substring(room_len - 3, room_len);
        Room getRoom = roomRepository.findByNumber(room_number);
        Optional<Clinic> getClinic = clinicRepository.findById(user.getClinic().getId());
        ExaminationType getType = examinationTypeRepository.findByName(appointmentDTO.getExaminationType());

//        if(!appointmentValidation.validateDoctor(getDoctor.getId(),date,getType))
//            return null;

        Appointment appointment_to_add;
        if(getClinic.isPresent()) {
            appointment_to_add = new Appointment(date, appointmentDTO.getPrice(), 0, getDoctor, getRoom, getType, getClinic.get());
            appointmentRepository.save(appointment_to_add);
            return appointment_to_add;
        }

        return null;
    }

}
