package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "appointment_requests")
public class AppointmentRequest {
   public enum AppointmentReqType {DOCTOR, PATIENT}
   @Id
   @GeneratedValue(strategy = IDENTITY)
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "doctor_id")
   private Doctor doctor;

   @ManyToOne
   @JoinColumn(name = "patient_id")
   private Patient patient;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "time")
   private Date time;

   @Column(name = "price")
   private Float price;

   @Column(name = "discount")
   private Float discount;

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
      this.doctor = predefAppointment.getDoctor();
      this.time = predefAppointment.getTime();
      this.price = predefAppointment.getPrice();
      this.discount = predefAppointment.getDiscount();
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
      if(doctor == null) return;
      this.doctor = doctor;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      if(patient == null) return;
      this.patient = patient;
   }

   public Date getTime() {
      return time;
   }

   public void setTime(Date time) {
      if(time == null) return;
      this.time = time;
   }

   public Float getPrice() {
      return price;
   }

   public void setPrice(Float price) {
      if(price == null) return;
      this.price = price;
   }

   public Float getDiscount() {
      return discount;
   }

   public void setDiscount(Float discount) {
      if(discount == null) return;
      this.discount = discount;
   }
}