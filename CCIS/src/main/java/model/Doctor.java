package model;
import java.util.*;

public class Doctor extends MedicalStaff {
   private float rating;
   private int businessHours;

   private ExaminationType examinationType;

   public Doctor() {
   }

   public Doctor(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar, float rating, int businessHours, ExaminationType examinationType) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber, calendar);
      this.rating = rating;
      this.businessHours = businessHours;
      this.examinationType = examinationType;
   }

   public Doctor(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
   }

   public float getRating() {
      return rating;
   }

   public void setRating(float rating) {
      this.rating = rating;
   }

   public int getBusinessHours() {
      return businessHours;
   }

   public void setBusinessHours(int businessHours) {
      this.businessHours = businessHours;
   }

   public ExaminationType getExaminationType() {
      return examinationType;
   }

   public void setExaminationType(ExaminationType examinationType) {
      this.examinationType = examinationType;
   }
}