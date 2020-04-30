package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.LAZY;

/*
      Ova klasa predstavlja
      Mapa:   {kod_bolesti, dijagnoza(opis, ) uglavnom neka bude string }
*/

@Entity
@Table(name = "code_book")
public class CodeBook {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id", unique = true, nullable = false, columnDefinition = "serial")
   private Integer id;

   @ElementCollection(fetch = LAZY)
   @CollectionTable(name = "diagnoses",
           joinColumns = {@JoinColumn(name = "code_book_id")})
   @MapKeyColumn(name = "code")
   @Column(name = "description")
   private Map<String, String> diagnoses;

   @ElementCollection(fetch = LAZY)
   @CollectionTable(name = "medications",
           joinColumns = {@JoinColumn(name = "code_book_id")})
   @MapKeyColumn(name = "code")
   @Column(name = "description")
   private Map<String, String> medications;

   public CodeBook() {
   }

   public CodeBook(Map<String, String> diagnoses, Map<String, String> medications) {
      this.diagnoses = diagnoses;
      this.medications = medications;
   }

   public Map<String, String> getDiagnoses() {
      return diagnoses;
   }

   public void setDiagnoses(Map<String, String> diagnoses) {
      this.diagnoses = diagnoses;
   }

   public Map<String, String> getMedications() {
      return medications;
   }

   public void setMedications(Map<String, String> medications) {
      this.medications = medications;
   }
}