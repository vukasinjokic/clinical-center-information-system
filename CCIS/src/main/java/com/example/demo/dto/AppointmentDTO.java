package com.example.demo.dto;


import com.example.demo.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentDTO {
    private String id;
    private String time;
    private Float price;
    private Float discount;
    public String doctorEmail;
    private DoctorDTO doctor; //trebalo bi doctorDTO
    private String patient;
    private String roomName;
    private String roomNumber;
    private String roomId;
    private String examinationType;
    private String clinic;
    private String report;
    private boolean finished;

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

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public void setDoctor(Doctor doctor){
        if(this.doctor == null)
            this.doctor = new DoctorDTO();
        this.doctor.setId(doctor.getId().toString());
        this.doctor.setFirstName(doctor.getFirstName());
        this.doctor.setLastName(doctor.getLastName());
        this.doctor.setSocialSecurityNumber(doctor.getSocialSecurityNumber());
        this.doctor.setEmail(doctor.getEmail());
        this.doctor.setClinic(doctor.getClinic().getName());
        this.doctor.setClinicId(doctor.getClinic().getId().toString());
    }

    public String getReport() {
        return report;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }
    public void setClinic(String clinic){
        this.clinic = clinic;
    }

    public void setRoom(Room r){
        this.setRoomName(r.getName());
        this.setRoomNumber(r.getNumber());
        this.setRoomId(r.getId().toString());
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPatient(Patient patient){
        if(patient == null) return;
        this.patient = patient.getLastName() + " " +  patient.getFirstName();
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setExaminationType(ExaminationType et){
        this.examinationType = et.getName();
    }

    public void setClinic(Clinic clinic){
        this.clinic = clinic.getName();
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setTime(Date time){
        this.time = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(time);
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


    public Float getDiscount() {
        return discount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public String getId() {
        return id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
