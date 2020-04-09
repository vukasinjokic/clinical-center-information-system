package com.example.demo.model;

public class Patient extends User {
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