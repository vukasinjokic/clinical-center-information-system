package model;
import java.util.*;

public abstract class MedicalStaff extends User {
   private Calendar calendar;

   public MedicalStaff() {
   }

   public MedicalStaff(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
   }

   public MedicalStaff(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar) {
      super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
      this.calendar = calendar;
   }

   public Calendar getCalendar() {
      return calendar;
   }

   public void setCalendar(Calendar calendar) {
      this.calendar = calendar;
   }
}