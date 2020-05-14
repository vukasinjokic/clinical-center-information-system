package com.example.demo.validation;

import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.model.Appointment;
import com.example.demo.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

public class RoomValidation {



    public boolean validateName(String name, RoomRepository roomRepository){
        List<Room> roomList = roomRepository.findAll();

        Optional<Room> find = roomList.stream()
                                .filter(room -> room.getName().equals(name) )
                                .findAny();
        if(find.isPresent())
            return false;

        return true;
    }
    public boolean validateNumber(String number, RoomRepository roomRepository){
        List<Room> roomList = roomRepository.findAll();

        Optional<Room> find = roomList.stream()
                .filter(room -> room.getNumber().equals(number))
                .findAny();
        if(find.isPresent())
            return false;

        return true;
    }
    public boolean validateUsing(Room room, AppointmentRepository appointmentRepository){
        List<Appointment> appointments = appointmentRepository.findByRoomId(room.getId());
        if(appointments.size() == 0)
            return true;

        return false;
    }

}
