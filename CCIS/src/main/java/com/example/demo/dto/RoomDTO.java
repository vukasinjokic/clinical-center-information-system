package com.example.demo.dto;

import com.example.demo.model.*;

public class RoomDTO {
    private String id;
    private String name;
    private String number;
    private Calendar calendar;
//    private Collection<Appointment> appointments;
    private String clinic;
    private String type;


    public void setDtoFields(Room room){
        try{
            this.number = room.getNumber();
            this.clinic = room.getClinic().getName();
            CalendarDTO.setUpCalendar(room.getCalendar().getId(), calendar, room.getAppointments());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public Calendar getCalendar() {
        return calendar;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClinic() {
        return clinic;
    }
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
    public void setClinic(Clinic clinic){
        this.clinic = clinic.getName();
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
