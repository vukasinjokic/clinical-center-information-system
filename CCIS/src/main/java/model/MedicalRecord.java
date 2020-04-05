package model;
import java.util.*;

public class MedicalRecord {
   private Collection<String> history;
   
   private Collection<Appointment> appointments;

   public MedicalRecord() {
      this.appointments = new HashSet<Appointment>();
   }

   public MedicalRecord(Collection<String> history, Collection<Appointment> appointments) {
      this.history = history;
      this.appointments = appointments;
   }

   public Collection<String> getHistory() {
      return history;
   }

   public void setHistory(Collection<String> history) {
      this.history = history;
   }


   /** @pdGenerated default getter */
   public Collection<Appointment> getAppointments() {
      if (appointments == null)
         appointments = new HashSet<Appointment>();
      return appointments;
   }
   
   /** @pdGenerated default iterator getter */
   public Iterator getIteratorAppointment() {
      if (appointments == null)
         appointments = new java.util.HashSet<Appointment>();
      return appointments.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppointment */
   public void setAppointments(Collection<Appointment> newAppointment) {
      removeAllAppointment();
      for (Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAppointment */
   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointments == null)
         this.appointments = new HashSet<Appointment>();
      if (!this.appointments.contains(newAppointment))
         this.appointments.add(newAppointment);
   }
   
   /** @pdGenerated default remove
     * @param oldAppointment */
   public void removeAppointment(Appointment oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointments != null)
         if (this.appointments.contains(oldAppointment))
            this.appointments.remove(oldAppointment);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAppointment() {
      if (appointments != null)
         appointments.clear();
   }

}