package com.example.demo.model;

public class ClinicAdmin extends User {
   private Clinic clinic;

   public ClinicAdmin() {
   }

   public ClinicAdmin(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
   }

   public ClinicAdmin(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Clinic clinic) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
      this.clinic = clinic;
   }

   public Clinic getClinic() {
      return clinic;
   }

   public void setClinic(Clinic clinic) {
      this.clinic = clinic;
   }
}