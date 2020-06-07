package com.example.demo.model;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User {

   @OneToOne(mappedBy = "patient" ,fetch = LAZY)
   private MedicalRecord medicalRecord;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name="clinic_id")
   private Clinic clinic;

   @OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL},fetch = LAZY)
   private Collection<Appointment> appointments;

   public Patient() {
      this.appointments = new HashSet<Appointment>();
   }
   public Patient(Integer id, String username, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, MedicalRecord medicalRecord, List<Authority> authorities) {
      super(id, username, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, null, authorities);
      this.medicalRecord = medicalRecord;
      this.appointments = new HashSet<Appointment>();
   }

   public Collection<Appointment> getAppointments() {
      return appointments;
   }

   public void setAppointments(Collection<Appointment> appointments) {
      this.appointments = appointments;
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

   public void addAppointment(Appointment a){
      this.appointments.add(a);
   }

   public void removeAppointment(Appointment a){
      if(a == null) return;
      if(appointments.contains(a)){
         appointments.remove(a);
      }
   }
}