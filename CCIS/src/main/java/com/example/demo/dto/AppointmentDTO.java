package com.example.demo.dto;


import com.example.demo.model.*;

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

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getDate() {
        return time;
    }

    public void setDate(String date) {
        this.time = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }



    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getOperationRoom() {
        return operationRoom;
    }

    public void setOperationRoom(String operationRoom) {
        this.operationRoom = operationRoom;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
