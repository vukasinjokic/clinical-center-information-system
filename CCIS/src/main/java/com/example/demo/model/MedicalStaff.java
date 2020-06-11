package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicalStaff extends User {

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "calendar_id", nullable = true)
   private Calendar calendar;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "clinic_id")
   private Clinic clinic;

   public void addVacationDates(MedicalStaffRequest medicalStaffRequest){
      calendar.addVacationDates(medicalStaffRequest);
   }

   public MedicalStaff() {
   }

   public MedicalStaff(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, List<Authority> authorities) {
      super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, null, authorities);
   }

   public MedicalStaff(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar, Clinic clinic, List<Authority> authorities) {
      super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, null, authorities);
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