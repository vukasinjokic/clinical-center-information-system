package com.example.demo.dto;


import com.example.demo.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentDTO {
    private String id;
    private String time;
    private float price;
    private float discount;
    private String doctor; //
    private String patient;
    private String room;
    private String examinationType;
    private String medicalRecord; //vrv ne treba
    private String clinic;


    public void setFields(Appointment appointment){

        this.setDoctor(appointment.getDoctor());
        this.setPatient(appointment.getPatient());
        this.setRoom(appointment.getRoom());
        this.setExaminationType(appointment.getExaminationType());
        this.setClinic(appointment.getClinic());
        this.setTime(appointment.getTime());
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor.getEmail();
    }
    public void setDoctor(String doctor){
        this.doctor = doctor;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }
    public void setClinic(String clinic){
        this.clinic = clinic;
    }

    public void setRoom(String room){
        this.room = room;
    }

    public void setPatient(Patient patient){
        this.patient = patient.getLastName() + " " +  patient.getFirstName();
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setRoom(Room r){
        this.room = r.getName() + " " + r.getNumber();
    }

    public void setExaminationType(ExaminationType et){
        this.examinationType = et.getName();
    }

    public void setClinic(Clinic clinic){
        this.clinic = clinic.getName();
    }

    public void setTime(Date time){
        this.time = new SimpleDateFormat("dd-MM-yyyy hh:mm").format(time);
    }

    public String getClinic() {
        return clinic;
    }

    public String getDate() {
        return time;
    }


    public float getPrice() {
        return price;
    }


    public float getDiscount() {
        return discount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public String getRoom() {
        return room;
    }


    public String getExaminationType() {
        return examinationType;
    }


    public String getMedicalRecord() {
        return medicalRecord;
    }

    public String getId() {
        return id;
    }

}
