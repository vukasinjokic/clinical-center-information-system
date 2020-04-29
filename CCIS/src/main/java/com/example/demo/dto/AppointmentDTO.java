package com.example.demo.dto;


import java.util.Date;

public class AppointmentDTO {
    private Date date;
    private float price;
    private float discount;
//    private DoctorDTO doctor;
//    private PatientDTO patient;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

//    public DoctorDTO getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(DoctorDTO doctor) {
//        this.doctor = doctor;
//    }
}
