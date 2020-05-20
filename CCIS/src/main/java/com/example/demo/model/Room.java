package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "rooms")
public class Room {
   public enum RoomType{OPERATION, APPOINTMENT};
   @Id
   @GeneratedValue(strategy = IDENTITY)
   private Integer id;

   @Column(name = "name", unique = true, nullable = false)
   private String name;

   @Column(name = "number", unique = true, nullable = false)
   private String number;

   @Enumerated(EnumType.STRING)
   @Column(name = "room_type", length = 15)
   private RoomType type;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "calendar_id")
   private Calendar calendar;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "clinic_id")
   private Clinic clinic;

   @OneToMany(mappedBy = "room" ,cascade = {CascadeType.ALL}, fetch = LAZY)
   private Collection<Appointment> appointments;

   public Room() {
   }
   public Room(String name, String number, Calendar calendar, Clinic clinic) {
      this.name = name;
      this.number = number;
      this.calendar = calendar;
      this.clinic = clinic;
   }
   public Room(String name,String number,  Clinic clinic,RoomType roomType) {
      this.name = name;
      this.number = number;
      this.clinic = clinic;
      this.type = roomType;
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

   public RoomType getType() {
      return type;
   }

   public void setType(RoomType type) {
      this.type = type;
   }
}
