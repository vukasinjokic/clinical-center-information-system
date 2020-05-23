package com.example.demo.dto;


import com.example.demo.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DoctorDTO {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String address;
    private String country;
    private String socialSecurityNumber;
    private String phoneNumber;
    private Calendar calendar;
    private String clinic;
    private String clinicId;
    private String username;
    private String rating;
    private BusinessHours businessHours;
    private ExaminationType examinationType;
    private Collection<AppointmentDTO> appointments;

    public void setFields(Doctor doctor){
        try {
//            CalendarDTO.setUpCalendar(doctor.getCalendar().getId(), calendar, doctor.getAppointments());
            setClinic(doctor.getClinic());
            setClinicId(doctor.getClinic());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String id){
        this.clinicId = id;
    }

    public void setClinicId(Clinic clinic) {
        this.clinicId = clinic.getId().toString();
    }

    public void setClinic(Clinic clinic){
        this.clinic = clinic.getName();
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getClinic() {
        return clinic;
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
