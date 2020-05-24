package com.example.demo.validation;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DoctorValidation {

    public boolean isEmailExist(DoctorDTO doctorDTO, List<Doctor> doctors ){

        Optional<Doctor> find_email =
                doctors
                .stream()
                .filter(d -> d.getEmail().equals(doctorDTO.getEmail()))
                .findAny();

        if(find_email.isPresent())
            return true;

        return false;
    }

    public boolean isNumberExist(DoctorDTO doctorDTO, List<Doctor> doctors){
        Optional<Doctor> find_number =
                doctors
                .stream()
                .filter(d -> d.getSocialSecurityNumber().equals(doctorDTO.getSocialSecurityNumber()))
                .findAny();
        if(find_number.isPresent()) {
            return true;
        }

        return false;
    }
    public boolean validateDeleting(Doctor doctor, Date date){
        for(Date d: doctor.getCalendar().getEventStartDates()){
            if(date.before(d))
                return false;
        }
        return true;
    }

    public boolean checkAppointments(List<Appointment> appointments,Date date){
        if(appointments == null)
            return true;
        for(Appointment appointment: appointments){
            if(appointment.getTime().after(date))
                return false;
        }

        return true;
    }

}
