package com.example.demo.validation;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.Room;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

    public boolean valDoctorBusy(Date sd, Date ed, Doctor doctor){
        long start = sd.getTime();
        long end = ed.getTime();
        List<Date> startDates = doctor.getCalendar().getEventStartDates();
        List<Date> endDates = doctor.getCalendar().getEventEndDates();
        for(int i = 0; i<startDates.size(); i++){
            long getTimeStart = startDates.get(i).getTime();
            long getTimeEnd = endDates.get(i).getTime();
            if((getTimeStart <= start && getTimeEnd > start) ||
                    (getTimeStart <= end && getTimeEnd >= end) ||
                    (getTimeStart >= start && getTimeStart <= end) )
            {
                return false;
            }
        }return true;
    }

    public boolean validateDoctorBusy(Date startDate, float duration, Doctor doctor){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int d = (int) duration*3600*1000;
        Date endDate = new Date(startDate.getTime()+d);

        if(isDoctorOnVacation(doctor,startDate,endDate))
            return false;

        if(doctor.getCalendar().getEventStartDates() == null){
            return true;
        }
        if(!this.checkBusinessHours(doctor,startDate,endDate))
            return false;

        return this.valDoctorBusy(startDate, endDate, doctor);
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

    public boolean isDoctorOnVacation(Doctor doctor, Date start, Date end){
        if(doctor.getCalendar().getVacationDates().size() != 0){
            Date startVacation = doctor.getCalendar().getVacationDates().get(0);
            Date endVacation = doctor.getCalendar().getVacationDates().get(1);
            if(start.after(startVacation) && start.before(endVacation))
                return true;
        }

        return false;
    }

    public boolean checkBusinessHours(Doctor doctor, Date start, Date end){
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");

        String startTimeString = localDateFormat.format(start);
        String endTimeString = localDateFormat.format(end);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalTime endTime = LocalTime.parse(endTimeString);

        LocalTime startBusinessHours = LocalTime.parse(doctor.getBusinessHours().getStarted().toString());
        LocalTime endBusinessHours = LocalTime.parse(doctor.getBusinessHours().getEnded().toString());
        //mozda bi tu moglo i equals
        if(startTime.isAfter(startBusinessHours) && startTime.isBefore(endBusinessHours))
            if(endTime.isBefore(endBusinessHours))
                return true;
        return false;
    }
}
