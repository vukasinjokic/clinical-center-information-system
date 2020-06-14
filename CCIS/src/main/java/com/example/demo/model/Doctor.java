package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "doctors")
public class Doctor extends MedicalStaff {

   @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @JoinColumn(name="rating_id", nullable = true)
   private Rating rating;

    @Type(type = "true_false")
    private Boolean activity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bus_hours_id", nullable = false)
    private BusinessHours businessHours;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ex_type_id",nullable = false)
    private ExaminationType examinationType;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "doctor" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Collection<Appointment> appointments;

    public Doctor() {
    }

    public Doctor(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar, Rating rating, BusinessHours businessHours, ExaminationType examinationType, Clinic clinic, List<Authority> authorities) {
        super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, calendar, clinic, authorities);
        this.rating = rating;
        this.businessHours = businessHours;
        this.examinationType = examinationType;
    }

    public Doctor(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, List<Authority> authorities) {
        super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, authorities);
    }

    public Boolean getActivity() {
        return activity;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public BusinessHours getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(BusinessHours businessHours) {
        this.businessHours = businessHours;
    }

    public ExaminationType getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(ExaminationType examinationType) {
        this.examinationType = examinationType;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        getCalendar().addAppointment(appointment);
    }

    public List<Date> getAvailableTimesForDate(Date time, Calendar calendar) {
        List<Date> availableStartTimes = new ArrayList<Date>();
        List<Date> eventStartDates = calendar.getEventStartDates();
        List<Date> eventEndDates = calendar.getEventEndDates();
        if(isOnVacationForTime(time, calendar.getVacationDates())){
            return new ArrayList<Date>();
        }
        int counter = 0;
        if((time.getTime() + examinationType.getMillisecondsDuration()) <= eventStartDates.get(0).getTime()){
            availableStartTimes.add(time);
        }
        for(int i = 0; i != eventStartDates.size() - 1; i++){
            if(!calendar.areTheSameDay(time, eventStartDates.get(i))){
                counter++;
                continue;
            }
            Date end = eventEndDates.get(i);
            while(end.getTime() + examinationType.getMillisecondsDuration() <= eventStartDates.get(i+1).getTime() && calendar.areTheSameDay(time, end)){
                if(calendar.areTheSameDay(end, eventStartDates.get(i + 1))
                        || (end.getTime() + examinationType.getMillisecondsDuration() <= calendar.getDayStart(time).getTime() + businessHours.getEnded().getTime() + 1000 * 60 * 60))
                {
                    availableStartTimes.add(new Date(end.getTime()));
                }
                end.setTime(end.getTime() + examinationType.getMillisecondsDuration());
            }
        }
        //nema pregleda za trazeni dan i ako se pregled moze izvrsiti pre kraja radnog vremena
        if(eventStartDates.size() == counter + 1
                && time.getTime() + examinationType.getMillisecondsDuration() <= calendar.getDayStart(time).getTime() + businessHours.getEnded().getTime())
        {
            availableStartTimes.add(time);
        }
        return availableStartTimes;
    }

    private boolean isOnVacationForTime(Date time, List<Date> vacationDates){
        Date dayStart = getCalendar().getDayStart(time);
        if(vacationDates.size() == 0) return false;
        if(dayStart.after(vacationDates.get(0)) && dayStart.before(vacationDates.get(1))) return true;
        return false;
    }

    public boolean isAvailableForTimeAndDuration(Date time, long millisecondsDuration) {
        List<Date> eventStartDates = getCalendar().getEventStartDates();
        List<Date> eventEndDates = getCalendar().getEventEndDates();
        int counter = 0;
        if((time.getTime() + millisecondsDuration) <= eventStartDates.get(0).getTime()){
            return true;
        }

        for(int i = 0; i != eventStartDates.size() - 1; i++){
            if(!getCalendar().areTheSameDay(time, eventStartDates.get(i))){
                counter++;
                continue;
            }
            Date end = eventEndDates.get(i);
            if(end.getTime() + millisecondsDuration <= eventStartDates.get(i+1).getTime() && getCalendar().areTheSameDay(eventStartDates.get(i+1), end)){
                return true;
            }
        }
        if(eventStartDates.size() == counter + 1)
            return true;
        return false;

    }

}