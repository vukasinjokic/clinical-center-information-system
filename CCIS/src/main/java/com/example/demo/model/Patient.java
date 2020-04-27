package com.example.demo.model;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient extends User {

   @OneToOne(fetch = LAZY)
   @JoinColumn(name = "medical_record_id")
   private MedicalRecord medicalRecord;

   public Patient() {
   }

   public Patient(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, MedicalRecord medicalRecord) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
      this.medicalRecord = medicalRecord;
   }

   public MedicalRecord getMedicalRecord() {
      return medicalRecord;
   }

   public void setMedicalRecord(MedicalRecord medicalRecord) {
      this.medicalRecord = medicalRecord;
   }
}