package com.example.demo.dto;

import com.example.demo.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class OperationRoomDTO {
    private String id;
    private String name;
    private String number;
    private Calendar calendar;
//    private Collection<Appointment> appointments;
    private String clinic;


    public void setDtoFields(OperationRoom operationRoom){
        try{
            this.number = operationRoom.getNumber();
            this.clinic = operationRoom.getClinic().getName();
            CalendarDTO.setUpCalendar(operationRoom.getCalendar().getId(), calendar, operationRoom.getAppointments());
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
}
