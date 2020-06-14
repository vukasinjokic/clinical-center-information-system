package com.example.demo.useful_beans;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.RoomDTO;

import java.util.Date;
import java.util.List;

public class AppointmentToReserve {
    public List<Integer> doctorsIds;
    public Integer requestId;
    public Date reservedTime;
    public RoomDTO room;

    public AppointmentToReserve() {
    }

    public AppointmentToReserve(List<Integer> doctorsIds, Integer request, Date reservedTime, RoomDTO room) {
        this.doctorsIds = doctorsIds;
        this.requestId = request;
        this.reservedTime = reservedTime;
        this.room = room;
    }

    public List<Integer> getDoctorsIds() {
        return doctorsIds;
    }

    public void setDoctorsIds(List<Integer> doctorsIds) {
        this.doctorsIds = doctorsIds;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer request) {
        this.requestId = request;
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
