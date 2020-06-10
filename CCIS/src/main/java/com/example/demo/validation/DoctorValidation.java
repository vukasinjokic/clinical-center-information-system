package com.example.demo.validation;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import java.text.SimpleDateFormat;
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

    public boolean validateDoctorBusy(Date startDate, float duration, Doctor doctor){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int d = (int) duration*3600*1000;
        Date endDate = new Date(startDate.getTime()+d);

        if(isDoctorOnVacation(doctor,startDate,endDate))
            return false;

        if(doctor.getCalendar().getEventStartDates() == null){
            doctor.getCalendar().setEventStartDates(new ArrayList<Date>());
            doctor.getCalendar().setEventEndDates(new ArrayList<Date>());
            return true;
        }
        List<HashMap<Date,Date>> check_dates_list = doctor.getCalendar().formatDates().get(sdf.format(startDate).substring(0,10));
        if(check_dates_list == null)
            return true;
        for(int i = 0; i< check_dates_list.size(); i++){

            for (Map.Entry<Date, Date> entry : check_dates_list.get(i).entrySet()) {

                if (startDate.after(entry.getKey())) {
                    if (startDate.before(entry.getValue()))
                        return false;
                    if (i < check_dates_list.size() - 1) {
                        for (Map.Entry<Date, Date> entry1 : check_dates_list.get(i+1).entrySet()) {
                            if (startDate.before(entry1.getKey()) && startDate.after(entry.getValue())) {
                                if (endDate.before(entry1.getKey())) {
                                    return true;
                                }
                            } else {
                                continue;
                            }
                        }
                    } else {
                        if (startDate.after(entry.getKey())) {
                            if (startDate.after(entry.getValue()))
                                return true;
                        } else { //before
                            if (endDate.before(entry.getKey()))
                                return true;
                        }
                    }
                } else if (startDate.before(entry.getKey())) {
                    if (endDate.before(entry.getKey()))
                        return true;
                    else
                        return false;
                }
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
        Date startBusinessHours = doctor.getBusinessHours().getStarted();
        Date endBusinessHours = doctor.getBusinessHours().getEnded();
        //mozda bi tu moglo i equals
        if(start.after(startBusinessHours) && start.before(endBusinessHours))
            if(end.before(endBusinessHours))
                return true;
        return false;
    }
}
