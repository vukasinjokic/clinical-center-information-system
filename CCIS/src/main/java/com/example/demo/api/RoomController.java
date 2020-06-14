package com.example.demo.api;

import com.example.demo.dto.RoomDTO;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/addRoom")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN')")
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO){
        Room saved_room = roomService.save(roomDTO);
        if(saved_room != null){
            RoomDTO retDTO =  convertToDTO(saved_room);
            retDTO.setDtoFields(saved_room);
            return new ResponseEntity<RoomDTO>(retDTO, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/updateRoom")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN')")
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO roomDTO){
        Room updated = roomService.update(roomDTO);

        if(updated != null) {
            RoomDTO retDTO = convertToDTO(updated);
            retDTO.setDtoFields(updated);
            return new ResponseEntity<RoomDTO>(retDTO,HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteRoom/{room_id}")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN')")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer room_id){
        if(roomService.deleteRoom(room_id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getRoom/{room_id}")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN')")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable Integer room_id){
        Room room = roomService.getRoom(room_id);
        if(room != null){
            return new ResponseEntity<RoomDTO>(convertToDTO(room),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getFiltered")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public Map<Integer, Date> getFiltered(
            @RequestParam(name = "duration") String duration,
            @RequestParam(name = "date") String date) throws ParseException {

        return roomService.getFiltered(duration, date);
    }

    private RoomDTO convertToDTO(Room room){
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        roomDTO.setDtoFields(room);
        return roomDTO;
    }
}
