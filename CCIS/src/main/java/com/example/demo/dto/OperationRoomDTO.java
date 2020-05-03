package com.example.demo.dto;

import com.example.demo.model.Calendar;
import com.example.demo.model.Clinic;
import com.example.demo.model.OperationRoom;

public class OperationRoomDTO {
    private String id;
    private String name;
    private String number;
    private Calendar calendar;

    private String clinic;
//    private CalendarDTO calendarDTO;

    public void setDtoFields(OperationRoom operationRoom){
        this.number = operationRoom.getNumber();
        this.clinic = operationRoom.getClinic().getName();
        this.calendar.setEventStartDates(operationRoom.getCalendar().getEventStartDates());
        this.calendar.setEventEndDates(operationRoom.getCalendar().getEventEndDates());
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
}
