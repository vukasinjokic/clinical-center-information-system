package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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

   public OperationRoom() {
   }
   public OperationRoom(String name, Calendar calendar, Clinic clinic) {
      this.name = name;
      this.calendar = calendar;
      this.clinic = clinic;
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