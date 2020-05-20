package com.example.demo.dto;

import com.example.demo.model.Appointment;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

public class AppointmentRequestDTO {
    public enum AppointmentReqType {DOCTOR, PATIENT}
    private String id;
    private DoctorDTO doctor;
    private PatientDTO patient;
    private String time;
    private Float price;
    private Float discount;
    private String type;
    private AppointmentDTO predefAppointment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        if(doctor == null) return;
        this.doctor = doctor;
    }



    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        if(patient == null) return;
        this.patient = patient;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if(time == null) return;
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        if(price == null) return;
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        if(discount == null) return;
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AppointmentDTO getPredefAppointment() {
        return predefAppointment;
    }

    public void setPredefAppointment(Appointment predefAppointment) {
        if (predefAppointment == null) { return ;}
        ModelMapper mapper = new ModelMapper();
        AppointmentDTO dto = mapper.map(predefAppointment, AppointmentDTO.class);
        dto.setFields(predefAppointment);
        this.predefAppointment = dto;
        this.doctor = mapper.map(predefAppointment.getDoctor(), DoctorDTO.class);
        this.time = mapper.map(predefAppointment.getTime(), String.class);
        this.price = mapper.map(predefAppointment.getPrice(), Float.class);
        this.discount = mapper.map(predefAppointment.getDiscount(), Float.class);
    }
}
