package com.example.demo.useful_beans;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.RoomDTO;

import java.util.Date;
import java.util.List;

public class AppointmentToReserve {
    public List<DoctorDTO> doctors;
    public AppointmentRequestDTO request;
    public Date reservedTime;
    public RoomDTO room;

    public AppointmentToReserve() {
    }

    public AppointmentToReserve(List<DoctorDTO> doctors, AppointmentRequestDTO request, Date reservedTime, RoomDTO room) {
        this.doctors = doctors;
        this.request = request;
        this.reservedTime = reservedTime;
        this.room = room;
    }

    public List<DoctorDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorDTO> doctors) {
        this.doctors = doctors;
    }

    public AppointmentRequestDTO getRequest() {
        return request;
    }

    public void setRequest(AppointmentRequestDTO request) {
        this.request = request;
    }

    public Date getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(Date reservedTime) {
        this.reservedTime = reservedTime;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }
}