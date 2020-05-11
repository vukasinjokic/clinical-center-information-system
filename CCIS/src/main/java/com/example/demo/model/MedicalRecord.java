package com.example.demo.model;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @ElementCollection
   @CollectionTable(name = "histories", joinColumns = @JoinColumn(name = "medical_record_id"))
   @Column(name = "history")
   private Collection<String> history;

   public MedicalRecord() {
      this.history = new HashSet<String>();
   }

   public MedicalRecord(Collection<String> history, Collection<Appointment> appointments) {
      this.history = history;
   }

   public Collection<String> getHistory() {
      return history;
   }

   public void setHistory(Collection<String> history) {
      this.history = history;
   }


}