package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Time;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "rooms", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"number", "clinic_id"})
})
public class Room {


   public enum RoomType{OPERATION, APPOINTMENT};
   @Id
   @GeneratedValue(strategy = IDENTITY)
   private Integer id;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "number", nullable = true)
   private String number;

   @Type(type = "true_false")
   private Boolean activity;

   @Enumerated(EnumType.STRING)
   @Column(name = "room_type", length = 15)
   private RoomType type;

   @OneToOne(cascade = CascadeType.ALL,fetch = EAGER)
   @JoinColumn(name = "calendar_id")
   private Calendar calendar;

   @ManyToOne(fetch = LAZY)
   @JoinColumn(name = "clinic_id")
   private Clinic clinic;

   @OneToMany(mappedBy = "room" ,cascade = {CascadeType.ALL}, fetch = LAZY)
   private Collection<Appointment> appointments;

   public Room() {
   }
   public Room(String name, String number, Calendar calendar, Clinic clinic) {
      this.name = name;
      this.number = number;
      this.calendar = calendar;
      this.clinic = clinic;
   }
   public Room(String name,String number,  Clinic clinic,RoomType roomType) {
      this.name = name;
      this.number = number;
      this.clinic = clinic;
      this.type = roomType;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Boolean getActivity() {
      return activity;
   }

   public void setActivity(Boolean activity) {
      this.activity = activity;
   }

   public Collection<Appointment> getAppointments() {
      return appointments;
   }

   public void setAppointments(Collection<Appointment> appointments) {
      this.appointments = appointments;
   }

   public Clinic getClinic() {
      return clinic;
   }

   public void setClinic(Clinic clinic) {
      this.clinic = clinic;
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

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public RoomType getType() {
      return type;
   }

   public void setType(RoomType type) {
      this.type = type;
   }

   public void addAppointment(Appointment appointment){
      getCalendar().addAppointment(appointment);
   }

   public boolean isAvailableForTimeAndDuration(Date time, long millisecondsDuration) {
      List<Date> eventStartDates = getCalendar().getEventStartDates();
      List<Date> eventEndDates = getCalendar().getEventEndDates();
      int counter = 0;
      if((time.getTime() + millisecondsDuration) <= eventStartDates.get(0).getTime()){
         return true;
      }

      for(int i = 0; i != eventStartDates.size() - 1; i++){
         if(!calendar.areTheSameDay(time, eventStartDates.get(i))){
            counter++;
            continue;
         }
         Date end = eventEndDates.get(i);
         if(end.getTime() + millisecondsDuration <= eventStartDates.get(i+1).getTime() && calendar.areTheSameDay(eventStartDates.get(i+1), end)){
            return true;
         }
      }
      if(eventStartDates.size() == counter + 1)
         return true;
      return false;

   }

   public Date getFirstAvailableTimeForDate(Date time, long duration, Calendar calendar) {
      List<Date> eventStartDates = calendar.getEventStartDates();
      List<Date> eventEndDates = calendar.getEventEndDates();
      int counter = 0;
      if(eventStartDates.size() == 0 || (time.getTime() + duration) <= eventStartDates.get(0).getTime()){
         return time;
      }
      for(int i = 0; i != eventStartDates.size() - 1; i++){
         if(!calendar.areTheSameDay(time, eventStartDates.get(i))){
            counter++;
            continue;
         }
         Date end = eventEndDates.get(i);
         while(end.getTime() + duration <= eventStartDates.get(i+1).getTime() && calendar.areTheSameDay(time, end)){
            if(calendar.areTheSameDay(end, eventStartDates.get(i + 1))
                    || (end.getTime() + duration <= calendar.getDayStart(time).getTime() + 14 * 1000 * 60 * 60))
            {
               return (new Date(end.getTime()));
            }
            end.setTime(end.getTime() + duration);
         }
      }
      //nema pregleda za trazeni dan i ako se pregled moze izvrsiti pre kraja radnog vremena
      if(eventStartDates.size() == counter + 1
              && time.getTime() + duration <= calendar.getDayStart(time).getTime() + 14 * 1000 * 60 * 60)
      {
         return time;
      }
      return null;
   }


}
