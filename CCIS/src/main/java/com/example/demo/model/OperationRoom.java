package com.example.demo.model;

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
   @Column(name = "id", unique = true, nullable = false)
   private Integer id;

   @Column(name = "name", unique = true, nullable = false)
   private String name;

   @OneToOne(fetch = EAGER)
   @JoinColumn(name = "calendar_id",referencedColumnName = "id")
   private Calendar calendar;

   public OperationRoom() {
   }

   public OperationRoom(String name, Calendar calendar) {
      this.name = name;
      this.calendar = calendar;
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
}