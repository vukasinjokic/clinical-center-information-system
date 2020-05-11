package com.example.demo.model;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User {

   @OneToOne(fetch = LAZY)
   @JoinColumn(name = "medical_record_id")
   private MedicalRecord medicalRecord;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name="clinic_id")
   private Clinic clinic;

   @OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL},fetch = LAZY)
   private Collection<Appointment> appointment;

   public Patient() {
   }

   public Collection<Appointment> getAppointment() {
      return appointment;
   }

   public void setAppointment(Collection<Appointment> appointment) {
      this.appointment = appointment;
   }

   public Patient(Integer id, String username, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, MedicalRecord medicalRecord, List<Authority> authorities) {
      super(id, username, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, null, authorities);
      this.medicalRecord = medicalRecord;
   }

   public MedicalRecord getMedicalRecord() {
      return medicalRecord;
   }

   public void setMedicalRecord(MedicalRecord medicalRecord) {
      this.medicalRecord = medicalRecord;
   }

   public Clinic getClinic() {
      return clinic;
   }

   public void setClinic(Clinic clinic) {
      this.clinic = clinic;
   }
}