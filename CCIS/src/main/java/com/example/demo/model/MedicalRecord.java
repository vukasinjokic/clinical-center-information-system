package com.example.demo.model;
import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @OneToOne
   private Patient patient;

   @ElementCollection
   @CollectionTable(name = "histories", joinColumns = @JoinColumn(name = "medical_record_id"))
   @Column(name = "history")
   private Collection<String> history;

   @OneToMany(cascade = CascadeType.ALL, fetch = LAZY)
   @JoinColumn(name = "medical_record_id")
   private Collection<Appointment> appointments;

   @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = LAZY)
   private Collection<Prescription> prescriptions;



   public MedicalRecord() {
      this.history = new HashSet<String>();
   }

   public MedicalRecord(Collection<String> history, Collection<Appointment> appointments, Collection<Prescription> prescriptions, Patient patient) {
      this.history = history;
      this.appointments = appointments;
      this.prescriptions = prescriptions;
      this.patient = patient;
   }

   public Collection<Prescription> getPrescriptions() {
      return prescriptions;
   }

   public void setPrescriptions(Collection<Prescription> prescriptions) {
      this.prescriptions = prescriptions;
   }

   public Collection<String> getHistory() {
      return history;
   }

   public void setHistory(Collection<String> history) {
      this.history = history;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Collection<Appointment> getAppointments() {
      return appointments;
   }

   public void setAppointments(Collection<Appointment> appointments) {
      this.appointments = appointments;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public void addPrescription(Prescription p){
      if(prescriptions == null) prescriptions = new ArrayList<Prescription>();
      prescriptions.add(p);
      p.setMedicalRecord(this);
   }


    public void addAppointment(Appointment appointment) {
       if(appointments == null) appointments = new ArrayList<>();
       appointments.add(appointment);
    }
}