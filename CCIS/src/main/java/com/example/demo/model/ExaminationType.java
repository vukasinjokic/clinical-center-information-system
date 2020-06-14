package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "examination_types")
public class ExaminationType {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
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
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
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

    public long getMillisecondsDuration() {
      return (long) (duration * 1000 * 60 * 60);
    }
}