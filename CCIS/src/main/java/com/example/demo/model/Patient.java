package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "patients")
public class Patient extends User {


   @OneToOne(fetch = LAZY)
   @JoinColumn(name = "medical_record_id")
   private MedicalRecord medicalRecord;

   @OneToMany(cascade = {CascadeType.ALL},fetch = LAZY)
   @JoinColumn(name = "appointment_id")
   private Collection<Appointment> appointment;

   public Patient() {
   }

   public Collection<Appointment> getAppointment() {
      return appointment;
   }

   public void setAppointment(Collection<Appointment> appointment) {
      this.appointment = appointment;
   }

   public Patient(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, MedicalRecord medicalRecord) {
      super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
      this.medicalRecord = medicalRecord;
   }

   public MedicalRecord getMedicalRecord() {
      return medicalRecord;
   }

   public void setMedicalRecord(MedicalRecord medicalRecord) {
      this.medicalRecord = medicalRecord;
   }
}