package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "clinics")
public class Clinic {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
   private Integer id;

   @Column(name = "name", unique = false, nullable = false)
   private String name;

   @Column(name = "address", unique = false, nullable = false)
   private String address;

   @Column(name = "description", unique = false, nullable = false)
   private String description;

   @Column(name = "price_list", unique = false)
   private String priceList;

   @Column(name = "rating", unique = false)
   private float rating;

   @OneToMany(mappedBy = "clinic", cascade = {ALL}, fetch = LAZY)
   private Collection<Doctor> doctors;

   @OneToMany(mappedBy = "clinic", cascade = {ALL}, fetch = LAZY)
   private Collection<Nurse> nurses;

   @OneToMany(mappedBy = "clinic", cascade = {ALL}, fetch = LAZY)
   private Collection<Appointment> appointments;

   @OneToMany(mappedBy = "clinic", cascade = {ALL}, fetch = LAZY)
   private Collection<Room> rooms;

   @OneToMany(mappedBy = "clinic", cascade = {ALL}, fetch = LAZY)
   private Collection<Patient> patients;

   @ManyToOne( fetch = LAZY, cascade = {ALL})
   @JoinColumn(name = "code_book_id")
   private CodeBook codeBook;


   public Clinic() {
   }

   public Clinic(String name, String description, String address){
      this.name = name;
      this.description = description;
      this.address = address;
   }

   public Clinic(String name, String address, String description, String priceList, float rating, Collection<Nurse> nurses, Collection<Doctor> doctors, Collection<Appointment> appointments, Collection<Room> rooms, CodeBook codeBook) {
      this.name = name;
      this.address = address;
      this.description = description;
      this.priceList = priceList;
      this.rating = rating;
      this.doctors = doctors;
      this.appointments = appointments;
      this.rooms = rooms;
      this.codeBook = codeBook;
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getPriceList() {
      return priceList;
   }

   public void setPriceList(String priceList) {
      this.priceList = priceList;
   }

   public float getRating() {
      return rating;
   }

   public void setRating(float rating) {
      this.rating = rating;
   }

   public void setDoctors(Collection<Doctor> doctors) {
      this.doctors = doctors;
   }

   public CodeBook getCodeBook() {
      return codeBook;
   }

   public void setCodeBook(CodeBook codeBook) {
      this.codeBook = codeBook;
   }

   public java.util.Collection<Doctor> getDoctors() {
      if (doctors == null)
         doctors = new java.util.HashSet<Doctor>();
      return doctors;
   }

   public Collection<Patient> getPatients() {
      return patients;
   }

   public void setPatients(Collection<Patient> patients) {
      this.patients = patients;
   }

   public java.util.Iterator getIteratorDoctors() {
      if (doctors == null)
         doctors = new java.util.HashSet<Doctor>();
      return doctors.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDoctor */
   public void setDoctor(java.util.Collection<Doctor> newDoctor) {
      removeAllDoctors();
      for (java.util.Iterator iter = newDoctor.iterator(); iter.hasNext();)
         addDoctor((Doctor)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDoctor */
   public void addDoctor(Doctor newDoctor) {
      if (newDoctor == null)
         return;
      if (this.doctors == null)
         this.doctors = new HashSet<Doctor>();
      if (!this.doctors.contains(newDoctor))
         this.doctors.add(newDoctor);
   }
   
   /** @pdGenerated default remove
     * @param oldDoctor */
   public void removeDoctor(Doctor oldDoctor) {
      if (oldDoctor == null)
         return;
      if (this.doctors != null)
         if (this.doctors.contains(oldDoctor))
            this.doctors.remove(oldDoctor);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDoctors() {
      if (doctors != null)
         doctors.clear();
   }

   public Collection<Nurse> getNurses() {
      return nurses;
   }

   public void setNurses(Collection<Nurse> nurses) {
      this.nurses = nurses;
   }

   public void addNurse(Nurse newNurse) {
      if (newNurse == null)
         return;
      if (this.nurses == null)
         this.nurses = new HashSet<Nurse>();
      if (!this.nurses.contains(newNurse))
         this.nurses.add(newNurse);
   }

   /** @pdGenerated default remove
    * @param oldNurse */
   public void removeNurse(Nurse oldNurse) {
      if (oldNurse == null)
         return;
      if (this.nurses != null)
         if (this.nurses.contains(oldNurse))
            this.nurses.remove(oldNurse);
   }

   /** @pdGenerated default removeAll */
   public void removeAllNurses() {
      if (nurses != null)
         nurses.clear();
   }

   /** @pdGenerated default getter */
   public java.util.Collection<Appointment> getAppointments() {
      if (appointments == null)
         appointments = new java.util.HashSet<Appointment>();
      return appointments;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAppointment() {
      if (appointments == null)
         appointments = new java.util.HashSet<Appointment>();
      return appointments.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppointment */
   public void setAppointments(java.util.Collection<Appointment> newAppointment) {
      removeAllAppointments();
      for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAppointment */
   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointments == null)
         this.appointments = new java.util.HashSet<Appointment>();
      if (!this.appointments.contains(newAppointment))
         this.appointments.add(newAppointment);
   }
   
   /** @pdGenerated default remove
     * @param oldAppointment */
   public void removeAppointment(Appointment oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointments != null)
         if (this.appointments.contains(oldAppointment))
            this.appointments.remove(oldAppointment);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAppointments() {
      if (appointments != null)
         appointments.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Room> getRooms() {
      if (rooms == null)
         rooms = new java.util.HashSet<Room>();
      return rooms;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRoom() {
      if (rooms == null)
         rooms = new java.util.HashSet<Room>();
      return rooms.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRoom */
   public void setRooms(java.util.Collection<Room> newRoom) {
      removeAllRooms();
      for (java.util.Iterator iter = newRoom.iterator(); iter.hasNext();)
         addRoom((Room)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRoom */
   public void addRoom(Room newRoom) {
      if (newRoom == null)
         return;
      if (this.rooms == null)
         this.rooms = new java.util.HashSet<Room>();
      if (!this.rooms.contains(newRoom))
         this.rooms.add(newRoom);
   }
   
   /** @pdGenerated default remove
     * @param oldRoom */
   public void removeRoom(Room oldRoom) {
      if (oldRoom == null)
         return;
      if (this.rooms != null)
         if (this.rooms.contains(oldRoom))
            this.rooms.remove(oldRoom);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRooms() {
      if (rooms != null)
         rooms.clear();
   }

}