package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment_requests")
public class AppointmentRequest {
   public enum AppointmentReqType {DOCTOR, PATIENT}
   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "doctor_id")
   private Doctor doctor;

   @ManyToOne
   @JoinColumn(name = "patient_id")
   private Patient patient;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "time", nullable = false)
   private Date time;

   @Column(name = "price", nullable = false)
   private float price;

   @Column(name = "discount", nullable = false)
   private float discount;

   @Enumerated(EnumType.STRING)
   @Column(name = "app_req_type", length = 7)
   private AppointmentReqType type;

   @ManyToOne
   @JoinColumn(name = "predef_appointment_id")
   private Appointment predefAppointment;

   public AppointmentRequest() {
   }

   public AppointmentRequest(Doctor doctor, Patient patient, Date time, float price, float discount, AppointmentReqType type, Appointment predefAppointment){
      this.doctor = doctor;
      this.patient = patient;
      this.time = time;
      this.price = price;
      this.discount = discount;
      this.type = type;
      this.predefAppointment = predefAppointment;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Appointment getPredefAppointment() {
      return predefAppointment;
   }

   public void setPredefAppointment(Appointment predefAppointment) {
      this.predefAppointment = predefAppointment;
   }

   public AppointmentReqType getType() {
      return type;
   }

   public void setType(AppointmentReqType type) {
      this.type = type;
   }

   public Doctor getDoctor() {
      return doctor;
   }

   public void setDoctor(Doctor doctor) {
      this.doctor = doctor;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
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
}