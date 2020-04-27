package com.example.demo.model;
import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.TABLE;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
   @Id
   @GeneratedValue(strategy = TABLE)
   @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
   private Integer id;

   @Column(name = "email", unique = true, nullable = false)
   private String email;

   @Column(name = "password", nullable = false)
   private String password;

   @Column(name = "first_name", nullable = false)
   private String firstName;

   @Column(name = "last_name", nullable = false)
   private String lastName;

   @Column(name = "address", nullable = false)
   private String address;

   @Column(name = "city", nullable = false)
   private String city;

   @Column(name = "country", nullable = false)
   private String country;

   @Column(name = "phone_number", nullable = false)
   private String phoneNumber;

   @Column(name = "social_security_number", unique = true, nullable = false)
   private String socialSecurityNumber;

   public User() {
   }

   public User(String email, String password, String firstName, String lastName, String address, String city, String country, String phoneNumber, String socialSecurityNumber) {
      this.email = email;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.city = city;
      this.country = country;
      this.phoneNumber = phoneNumber;
      this.socialSecurityNumber = socialSecurityNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phone) {
      this.phoneNumber = phone;
   }

   public String getSocialSecurityNumber() {
      return socialSecurityNumber;
   }

   public void setSocialSecurityNumber(String socialSecurityNumber) {
      this.socialSecurityNumber = socialSecurityNumber;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof User)) return false;
      User user = (User) o;
      return socialSecurityNumber.equals(user.socialSecurityNumber);
   }

   @Override
   public int hashCode() {
      return Objects.hash(socialSecurityNumber);
   }
}