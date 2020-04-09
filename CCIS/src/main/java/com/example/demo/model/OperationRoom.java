package com.example.demo.model;

public class OperationRoom {
   private String name;

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