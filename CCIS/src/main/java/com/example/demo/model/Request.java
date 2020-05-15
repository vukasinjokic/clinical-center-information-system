package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Request {
   public enum RequestType{REGISTRATION, LEAVE};
   @Id
   @GeneratedValue
   private Integer id;

   @Enumerated(EnumType.STRING)
   @Column(name = "req_type", length = 20)
   private RequestType type;

   @Column(name = "description", nullable = false)
   private String description;

   public Request() {
   }

   public Request(RequestType type, String description) {
      this.type = type;
      this.description = description;
   }

   public RequestType getType() {
      return type;
   }

   public void setType(RequestType type) {
      this.type = type;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}