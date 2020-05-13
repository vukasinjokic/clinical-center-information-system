package com.example.demo.validation;

import com.example.demo.Repository.DoctorRepository;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.ExaminationType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class AppointmentValidation {

    @Autowired
    private DoctorRepository doctorRepository;

    public boolean validateDoctor(Integer doctor_id, Date date, ExaminationType examinationType){
        Optional<Doctor> doctor = doctorRepository.findById(doctor_id);
        if(doctor.isPresent()){
            doctor.get().getAppointments()
                    .forEach(app -> {
                        boolean a = true;
                    });
        }

        return true;
    }

}
