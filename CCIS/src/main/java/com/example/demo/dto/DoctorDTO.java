package com.example.demo.dto;


import com.example.demo.model.BusinessHours;
import com.example.demo.model.Doctor;
import com.example.demo.model.ExaminationType;
import com.example.demo.model.User;

import java.util.Collection;

public class DoctorDTO {
    private String email;
    private String firstName;
    private String lastName;
//    private CalendarDTO calendar TODO
    private String clinic;
    private String rating;
    private BusinessHours businessHours;
    private ExaminationType examinationType;
    private Collection<AppointmentDTO> appointments;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClinic() {
        return clinic;
    }
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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

    public Collection<AppointmentDTO> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<AppointmentDTO> appointments) {
        this.appointments = appointments;
    }
}
