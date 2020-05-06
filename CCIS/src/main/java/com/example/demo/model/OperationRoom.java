package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "operation_rooms")
public class OperationRoom {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
   private Integer id;

   @Column(name = "name", unique = true, nullable = false)
   private String name;

   @Column(name = "number", unique = true, nullable = false)
   private String number;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "calendar_id")
   private Calendar calendar;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "clinic_id")
   private Clinic clinic;

   @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
   @JoinColumn(name = "operation_room_id")
   private Collection<Appointment> appointments;

   public OperationRoom() {
   }
   public OperationRoom(String name, Calendar calendar, Clinic clinic, Collection<Appointment> appointments) {
      this.name = name;
      this.calendar = calendar;
      this.clinic = clinic;
      this.appointments = appointments;
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

   public Clinic getClinic() {
      return clinic;
   }

   public void setClinic(Clinic clinic) {
      this.clinic = clinic;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Calendar getCalendar() {
      return calendar;
   }

   public void setCalendar(Calendar calendar) {
      this.calendar = calendar;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

}
