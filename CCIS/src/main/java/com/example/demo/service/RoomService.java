package com.example.demo.service;

import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.Repository.CalendarRepository   ;
import com.example.demo.Repository.ClinicAdminRepository;
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
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    private RoomValidation roomValidation = new RoomValidation();

    public List<Room> getAllRooms(){
        ClinicAdmin user = (ClinicAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return roomRepository.findByClinicIdAndActivity(user.getClinic().getId(), true);
    }

    public Room save(RoomDTO roomDTO){
        ClinicAdmin user = (ClinicAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Integer> clinicId = clinicAdminRepository.findClinicIdByAdminId(user.getId());
        Room doesExist = roomRepository.findByNumberAndClinicIdAndActivity(roomDTO.getNumber(), clinicId.get(), true);

        if(doesExist == null){
            Clinic clinic = user.getClinic();

            Room new_room = new Room(roomDTO.getName(),roomDTO.getNumber(),clinic, Room.RoomType.valueOf(roomDTO.getType().toUpperCase()));
            Calendar calendar = calendarRepository.save(new Calendar());
            new_room.setCalendar(calendar);
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

            if(!find_room.get().getNumber().equals(roomDTO.getNumber()))
                if(!roomValidation.validateNumber(roomDTO.getNumber(),roomRepository,find_room.get().getClinic().getId()))
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
                room.setNumber(null);
                roomRepository.save(room);
                return true;
            }
        }
        return false;
    }
}
