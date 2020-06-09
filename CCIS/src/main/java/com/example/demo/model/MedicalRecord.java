package com.example.demo.model;
import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "weight", nullable = false)
   private String weight;

   @Column(name = "height", nullable = false)
   private String height;

   @Column(name = "left_eye", nullable = false)
   private String leftEye;

   @Column(name = "right_eye", nullable = false)
   private String rightEye;

   @Column(name = "blood_type", nullable = false)
   private String bloodType;

   @OneToOne
   private Patient patient;

   @ElementCollection
   @CollectionTable(name = "reports", joinColumns = @JoinColumn(name = "medical_record_id"))
   @MapKeyColumn(name = "report")
   @Column(name = "doctor_email")
   private Map<String, String> reports;

   @OneToMany(cascade = CascadeType.ALL, fetch = LAZY)
   @JoinColumn(name = "medical_record_id")
   private Collection<Appointment> appointments;

   @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = LAZY)
   private Collection<Prescription> prescriptions;



   public MedicalRecord() {
      this.reports = new HashMap<String, String>();
   }

   public MedicalRecord(Map<String,String> reports, Collection<Appointment> appointments, Collection<Prescription> prescriptions, Patient patient) {
      this.reports = reports;
      this.appointments = appointments;
      this.prescriptions = prescriptions;
      this.patient = patient;
   }

   public Collection<Prescription> getPrescriptions() {
      return prescriptions;
   }

   public void setPrescriptions(Collection<Prescription> prescriptions) {
      this.prescriptions = prescriptions;
   }

   public Map<String, String> getReports() {
      return reports;
   }

   public void setReports(Map<String, String> reports) {
      this.reports = reports;
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

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public void addPrescription(Prescription p){
      if(prescriptions == null) prescriptions = new ArrayList<Prescription>();
      prescriptions.add(p);
      p.setMedicalRecord(this);
   }


    public void addAppointment(Appointment appointment) {
       if(appointments == null) appointments = new ArrayList<>();
       appointments.add(appointment);
    }

   public String getWeight() {
      return weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public String getHeight() {
      return height;
   }

   public void setHeight(String height) {
      this.height = height;
   }

   public String getLeftEye() {
      return leftEye;
   }

   public void setLeftEye(String leftEye) {
      this.leftEye = leftEye;
   }

   public String getRightEye() {
      return rightEye;
   }

   public void setRightEye(String rightEye) {
      this.rightEye = rightEye;
   }

   public String getBloodType() {
      return bloodType;
   }

   public void setBloodType(String bloodType) {
      this.bloodType = bloodType;
   }
}