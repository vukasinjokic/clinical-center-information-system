package model;
import java.util.*;

public abstract class User {
   private String email;
   private String password;
   private String name;
   private String lastName;
   private String address;
   private String city;
   private String country;
   private String phone;
   private String socialSecurityNumber;

   public User() {
   }

   public User(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
      this.email = email;
      this.password = password;
      this.name = name;
      this.lastName = lastName;
      this.address = address;
      this.city = city;
      this.country = country;
      this.phone = phone;
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

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
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