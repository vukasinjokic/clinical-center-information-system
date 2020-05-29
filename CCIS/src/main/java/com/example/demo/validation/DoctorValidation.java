package com.example.demo.validation;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public boolean validateDoctorBusy(Date startDate, float duration, Doctor doctor){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int d = (int) duration*3600*1000;
        Date endDate = new Date(startDate.getTime()+d);
        if(doctor.getCalendar().getEventStartDates() == null){
            doctor.getCalendar().setEventStartDates(new ArrayList<Date>());
            doctor.getCalendar().setEventEndDates(new ArrayList<Date>());
            return true;
        }
        List<Pair<Date,Date>> check_dates_list = doctor.getCalendar().formatDates().get(sdf.format(startDate).substring(0,10));
        if(check_dates_list == null)
            return true;
        for(int i = 0; i< check_dates_list.size(); i++){
            if(startDate.after(check_dates_list.get(i).getKey())){
                if(startDate.before(check_dates_list.get(i).getValue()))
                    return false;
                if(i < check_dates_list.size()-1){
                    if(startDate.before(check_dates_list.get(i+1).getKey()) && startDate.after(check_dates_list.get(i).getValue())){
                        if(endDate.before(check_dates_list.get(i+1).getKey())){
                            return true;
                        }
                    }else{
                        continue;
                    }
                }else{
                    if(startDate.after(check_dates_list.get(i).getKey())){
                        if(startDate.after(check_dates_list.get(i).getValue()))
                            return true;
                    }else{ //before
                        if(endDate.before(check_dates_list.get(i).getKey()))
                            return true;
                    }
                }
            }else if(startDate.before(check_dates_list.get(i).getKey())){
                if(endDate.before(check_dates_list.get(i).getKey()))
                    return true;
                else
                    return false;
            }
        }
        return false;
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
