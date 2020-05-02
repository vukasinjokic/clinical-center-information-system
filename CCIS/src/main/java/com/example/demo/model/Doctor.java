package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doctors")
public class Doctor extends MedicalStaff {

   @Column(name="rating", unique = false, nullable = false)
   private float rating;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "bus_hours_id", nullable = false)
   private BusinessHours businessHours;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ex_type_id",nullable = false)
   private ExaminationType examinationType;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "doctor_id")
   private Collection<Appointment> appointments;

   public Doctor() {
   }

   public Doctor(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar, float rating, BusinessHours businessHours, ExaminationType examinationType, Clinic clinic) {
      super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, calendar, clinic );
      this.rating = rating;
      this.businessHours = businessHours;
      this.examinationType = examinationType;
   }

   public Doctor(Integer id,String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
      super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
   }

   public float getRating() {
      return rating;
   }

   public void setRating(float rating) {
      this.rating = rating;
   }

   public BusinessHours getBusinessHours() {
      return businessHours;
   }

   public void setBusinessHours(BusinessHours businessHours) {
      this.businessHours = businessHours;
   }

   public ExaminationType getExaminationType() {
      return examinationType;
   }

   public void setExaminationType(ExaminationType examinationType) {
      this.examinationType = examinationType;
   }

   public Collection<Appointment> getAppointments() {
      return appointments;
   }

   public void setAppointments(Collection<Appointment> appointments) {
      this.appointments = appointments;
   }
}