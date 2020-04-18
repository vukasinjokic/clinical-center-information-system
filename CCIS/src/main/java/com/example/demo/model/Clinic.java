package com.example.demo.model;

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
   @Column(name = "id", unique = true, nullable = false)
   private Integer id;

   @Column(name = "name", unique = false, nullable = false)
   private String name;

   @Column(name = "address", unique = false, nullable = false)
   private String address;

   @Column(name = "description", unique = false, nullable = false)
   private String description;

   @Column(name = "priceList", unique = false, nullable = false)
   private String priceList;

   @Column(name = "rating", unique = false, nullable = false)
   private float rating;

   @OneToMany(cascade = {ALL}, fetch = LAZY)
   private Collection<Doctor> doctors;

   @OneToMany(cascade = {ALL}, fetch = LAZY)
   private Collection<Appointment> appointments;

   @OneToMany(cascade = {ALL}, fetch = LAZY)
   private Collection<OperationRoom> operationRooms;

   @OneToOne(fetch = LAZY)
   private CodeBook codeBook;


   public Clinic() {
   }

   public Clinic(String name, String address, String description, String priceList, float rating, Collection<Doctor> doctors, Collection<Appointment> appointments, Collection<OperationRoom> operationRooms, CodeBook codeBook) {
      this.name = name;
      this.address = address;
      this.description = description;
      this.priceList = priceList;
      this.rating = rating;
      this.doctors = doctors;
      this.appointments = appointments;
      this.operationRooms = operationRooms;
      this.codeBook = codeBook;
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
   public java.util.Collection<OperationRoom> getOperationRooms() {
      if (operationRooms == null)
         operationRooms = new java.util.HashSet<OperationRoom>();
      return operationRooms;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOperationRoom() {
      if (operationRooms == null)
         operationRooms = new java.util.HashSet<OperationRoom>();
      return operationRooms.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOperationRoom */
   public void setOperationRooms(java.util.Collection<OperationRoom> newOperationRoom) {
      removeAllOperationRooms();
      for (java.util.Iterator iter = newOperationRoom.iterator(); iter.hasNext();)
         addOperationRoom((OperationRoom)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newOperationRoom */
   public void addOperationRoom(OperationRoom newOperationRoom) {
      if (newOperationRoom == null)
         return;
      if (this.operationRooms == null)
         this.operationRooms = new java.util.HashSet<OperationRoom>();
      if (!this.operationRooms.contains(newOperationRoom))
         this.operationRooms.add(newOperationRoom);
   }
   
   /** @pdGenerated default remove
     * @param oldOperationRoom */
   public void removeOperationRoom(OperationRoom oldOperationRoom) {
      if (oldOperationRoom == null)
         return;
      if (this.operationRooms != null)
         if (this.operationRooms.contains(oldOperationRoom))
            this.operationRooms.remove(oldOperationRoom);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOperationRooms() {
      if (operationRooms != null)
         operationRooms.clear();
   }

}