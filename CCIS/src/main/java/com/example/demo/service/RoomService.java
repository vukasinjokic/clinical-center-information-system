package com.example.demo.service;

import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.Repository.CalendarRepository   ;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.dto.RoomDTO;
import com.example.demo.model.*;
import com.example.demo.validation.RoomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CalendarRepository calendarRepository;

    private RoomValidation roomValidation = new RoomValidation();

    public List<Room> getAllRooms(){
        ClinicAdmin user = (ClinicAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return roomRepository.findByClinicIdAndActivity(user.getClinic().getId(), true);
    }

    public Room save(RoomDTO roomDTO){
        ClinicAdmin user = (ClinicAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Room doesExist = roomRepository.findByNumber(roomDTO.getNumber());
        Room doesExistName = roomRepository.findByName(roomDTO.getName());

        if(doesExist == null && doesExistName == null){
            Clinic clinic = user.getClinic();

            Room new_room = new Room(roomDTO.getName(),roomDTO.getNumber(),clinic, Room.RoomType.valueOf(roomDTO.getType().toUpperCase()));
            new_room.setActivity(true);
            return roomRepository.save(new_room);
        }

        return null;
    }

    public Room update(RoomDTO roomDTO){
        Optional<Room> find_room = roomRepository.findById(Integer.parseInt( roomDTO.getId() ));

        if(find_room.isPresent()){
            if(!roomValidation.validateUsing(find_room.get(),appointmentRepository))
                return null;

            if(!find_room.get().getName().equals(roomDTO.getName()))
                if(!roomValidation.validateName(roomDTO.getName(),roomRepository))
                    return null;
            if(!find_room.get().getNumber().equals(roomDTO.getNumber()))
                if(!roomValidation.validateNumber(roomDTO.getNumber(),roomRepository))
                    return null;

            Room update_room = find_room.get();
            update_room.setName(roomDTO.getName());
            update_room.setNumber(roomDTO.getNumber());
            update_room.setType(Room.RoomType.valueOf(roomDTO.getType()));
            return roomRepository.save(update_room);
        }
        return null;
    }

    public Room getRoom(Integer id){
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            return room.get();
        }
        return null;
    }

    public boolean deleteRoom(Integer id){
        Optional<Room> try_find = roomRepository.findById(id);
        if(try_find.isPresent()){
            Date date_now = new Date();
            Room room = try_find.get();
            List<Appointment> appointments = appointmentRepository.findAllByRoomIdAndTimeAfter(room.getId(),date_now);
            //logicko brisanje
            if(appointments.size() == 0){
                room.setActivity(false);
                roomRepository.save(room);
                return true;
            }
        }
        return false;
    }
}
