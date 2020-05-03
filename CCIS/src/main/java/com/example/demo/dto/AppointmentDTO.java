package com.example.demo.dto;


import com.example.demo.model.*;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentDTO {
    private String id;
    private String time;
    private float price;
    private float discount;
    private String doctor;
    private String patient;
    private String operationRoom;
    private String examinationType;
    private String medicalRecord; //vrv ne treba
    private String clinic;


    public void setFields(Appointment appointment){
        this.setDoctor(appointment.getDoctor());
        this.setPatient(appointment.getPatient());
        this.setOperationRoom(appointment.getOperationRoom());
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
        this.doctor = doctor.getLastName() + " " +  doctor.getFirstName();
    }

    public void setPatient(Patient patient){
        this.patient = patient.getLastName() + " " +  patient.getFirstName();
    }

    public void setOperationRoom(OperationRoom or){
        this.operationRoom = or.getName();
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

    public String getOperationRoom() {
        return operationRoom;
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
