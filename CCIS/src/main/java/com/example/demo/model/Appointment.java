package com.example.demo.model;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "appointments")
public class Appointment {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
   private Integer id;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "time", unique = false, nullable = false)
   private Date time;

   @Column(name = "price", unique = false, nullable = false)
   private float price;

   @Column(name = "discount", unique = false, nullable = false)
   private float discount;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "doctor_id", referencedColumnName = "id")
   private Doctor doctor;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "operation_room_id", referencedColumnName = "id")
   private OperationRoom operationRoom;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "examination_type_id", referencedColumnName = "id")
   private ExaminationType examinationType;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "patient_id", referencedColumnName = "id")
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