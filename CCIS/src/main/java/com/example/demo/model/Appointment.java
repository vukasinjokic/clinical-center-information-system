package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "doctor_id")
   private Doctor doctor;

   @OneToOne(fetch = LAZY)
   @JoinColumn(name = "operation_room_id")
   private OperationRoom operationRoom;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "examination_type_id")
   private ExaminationType examinationType;

   @ManyToOne(fetch = EAGER)
   @JoinColumn(name = "patient_id")
   private Patient patient;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "medical_record_id")
   private MedicalRecord medicalRecord;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "clinic_id")
   private Clinic clinic;


   public Appointment() {
   }

   public Appointment(Integer id, Date time, float price, float discount, Doctor doctor, OperationRoom operationRoom, ExaminationType examinationType, Patient patient, MedicalRecord medicalRecord, Clinic clinic) {
      this.id = id;
      this.time = time;
      this.price = price;
      this.discount = discount;
      this.doctor = doctor;
      this.operationRoom = operationRoom;
      this.examinationType = examinationType;
      this.patient = patient;
      this.medicalRecord = medicalRecord;
      this.clinic = clinic;
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

   public MedicalRecord getMedicalRecord() {
      return medicalRecord;
   }

   public void setMedicalRecord(MedicalRecord medicalRecord) {
      this.medicalRecord = medicalRecord;
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