package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@MappedSuperclass
public abstract class MedicalStaff extends User {

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "calendar_id", nullable = false)
   private Calendar calendar;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "clinic_id")
   private Clinic clinic;

   public MedicalStaff() {
   }

   public MedicalStaff(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
      super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
   }

   public MedicalStaff(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar,Clinic clinic) {
      super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
      this.calendar = calendar;
      this.clinic = clinic;
   }

   public Clinic getClinic() {
      return clinic;
   }

   public void setClinic(Clinic clinic) {
      this.clinic = clinic;
   }

   public Calendar getCalendar() {
      return calendar;
   }

   public void setCalendar(Calendar calendar) {
      this.calendar = calendar;
   }
}