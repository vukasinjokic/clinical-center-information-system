package com.example.demo.api;

import com.example.demo.dto.RoomDTO;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/rooms")
@RestController
public class RoomController {

    private final RoomService roomService;
    private ModelMapper modelMapper = new ModelMapper();

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getRooms")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public List<RoomDTO> getAllRooms(){
        List<Room> allRooms = roomService.getAllRooms();

        return allRooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private RoomDTO convertToDTO(Room room){
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        roomDTO.setDtoFields(room);
        return roomDTO;
    }
}
