package com.example.demo.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.servlet.annotation.HttpMethodConstraint;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "appointments")
public class Appointment {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   private Integer id;

   @Version
   @Column(name = "version", unique = false, nullable = false)
   private int version;

   @Column(name = "counter", unique = false, nullable = false)
   private Integer counter = 0;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "time", unique = false, nullable = false)
   private Date time;

   @Column(name = "price", unique = false, nullable = false)
   private float price;

   @Column(name = "discount", unique = false, nullable = false)
   private float discount;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "doctor_id")
   private Doctor doctor;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "room_id")
   private Room room;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "examination_type_id")
   private ExaminationType examinationType;

   @ManyToOne(fetch = EAGER)
   @JoinColumn(name = "patient_id")
   private Patient patient;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "clinic_id")
   private Clinic clinic;

   @Column(name = "report")
   private String report;

   @Type(type = "true_false")
   private boolean finished;


   public Appointment() {
   }

   public Appointment(Integer id, Date time, float price, float discount, Doctor doctor, Room room, ExaminationType examinationType, Patient patient , Clinic clinic, String report, boolean finished) {
      this.id = id;
      this.time = time;
      this.price = price;
      this.discount = discount;
      this.doctor = doctor;
      this.room = room;
      this.examinationType = examinationType;
      this.patient = patient;
      this.clinic = clinic;
      this.report = report;
      this.finished = finished;
   }
   public Appointment( Date time, float price, float discount, Doctor doctor, Room room, ExaminationType examinationType, Patient patient , Clinic clinic) {
      this.time = time;
      this.price = price;
      this.discount = discount;
      this.doctor = doctor;
      this.room = room;
      this.examinationType = examinationType;
      this.patient = patient;
      this.clinic = clinic;
   }
   public Appointment(Date time, float price, float discount, Doctor doctor, Room room, ExaminationType examinationType, Clinic clinic) {
      this.time = time;
      this.price = price;
      this.discount = discount;
      this.doctor = doctor;
      this.room = room;
      this.examinationType = examinationType;
      this.clinic = clinic;
   }

   public Integer getCounter() {
      return counter;
   }

   public void setCounter(Integer counter) {
      this.counter = counter;
   }

   public String getReport() {
      return report;
   }

   public void setReport(String report) {
      this.report = report;
   }

   public Clinic getClinic() {
      return clinic;
   }

   public void setClinic(Clinic clinic) {
      this.clinic = clinic;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
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

   public Room getRoom() {
      return room;
   }

   public void setRoom(Room room) {
      this.room = room;
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

   public boolean isFinished() {
      return finished;
   }

   public void setFinished(boolean finished) {
      this.finished = finished;
   }
}