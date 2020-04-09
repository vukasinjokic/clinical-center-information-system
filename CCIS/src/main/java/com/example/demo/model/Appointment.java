package com.example.demo.model;

import java.util.*;

public class Appointment {
   private Date time;
   private float price;
   private float discount;
   
   private Doctor doctor;
   private OperationRoom operationRoom;
   private ExaminationType examinationType;
   private Patient patient;


   public Appointment() {
   }

   public Appointment(Date time, float price, float discount, Doctor doctor, OperationRoom operationRoom, ExaminationType examinationType, Patient patient) {
      this.time = time;
      this.price = price;
      this.discount = discount;
      this.doctor = doctor;
      this.operationRoom = operationRoom;
      this.examinationType = examinationType;
      this.patient = patient;
   }

   public Date getTime() {
      return time;
   }

   public void setTime(Date time) {
      this.time = time;
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

   public Doctor getDoctor() {
      return doctor;
   }

   public void setDoctor(Doctor doctor) {
      this.doctor = doctor;
   }

   public OperationRoom getOperationRoom() {
      return operationRoom;
   }

   public void setOperationRoom(OperationRoom operationRoom) {
      this.operationRoom = operationRoom;
   }

   public ExaminationType getExaminationType() {
      return examinationType;
   }

   public void setExaminationType(ExaminationType examinationType) {
      this.examinationType = examinationType;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }


}