package com.example.demo.service;

import com.example.demo.Repository.DoctorRepository;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.useful_beans.MedicalStaffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private EmailService emailService;

    public Doctor findById(Integer id){
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor findByEmail(String email){
        return doctorRepository.findByEmail(email);
    }

    public boolean sendRequest(MedicalStaffRequest request){

        Doctor user = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        emailService.alertAdminForVacation(user,request);

        return true;
    }

    public boolean schedule(AppointmentDTO appointmentDTO){
        Doctor user = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

}
