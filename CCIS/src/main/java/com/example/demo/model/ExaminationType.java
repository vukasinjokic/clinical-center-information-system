package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "examination_types")
public class ExaminationType {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true, nullable = false)
   private Integer id;

   @Column(name = "name", unique = false,nullable = false)
   private String name;

   @Column(name = "duration", unique = false, nullable = false)
   private float duration;

   public ExaminationType() {
   }

   public ExaminationType(String name, float duration) {
      this.name = name;
      this.duration = duration;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public float getDuration() {
      return duration;
   }

   public void setDuration(float duration) {
      this.duration = duration;
   }
}