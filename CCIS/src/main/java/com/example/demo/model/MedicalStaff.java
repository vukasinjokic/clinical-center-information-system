package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@MappedSuperclass
public abstract class MedicalStaff extends User {

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "calendar_id", nullable = false)
   private Calendar calendar;

   public MedicalStaff() {
   }

   public MedicalStaff(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
   }

   public MedicalStaff(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
      this.calendar = calendar;
   }

   public Calendar getCalendar() {
      return calendar;
   }

   public void setCalendar(Calendar calendar) {
      this.calendar = calendar;
   }
}