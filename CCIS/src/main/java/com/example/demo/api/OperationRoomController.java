package com.example.demo.api;

import com.example.demo.dto.OperationRoomDTO;
import com.example.demo.model.OperationRoom;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.OperationRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/operationRooms")
@RestController
public class OperationRoomController {

    private final OperationRoomService roomService;
    private ModelMapper modelMapper = new ModelMapper();

    public OperationRoomController(OperationRoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getOperationRooms")
    public List<OperationRoomDTO> getAllOperationRooms(){
        List<OperationRoom> allRooms = roomService.getAllOperationRooms();

        return allRooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private OperationRoomDTO convertToDTO(OperationRoom operationRoom){
        OperationRoomDTO operationRoomDTO = modelMapper.map(operationRoom, OperationRoomDTO.class);
        operationRoomDTO.setDtoFields(operationRoom);
        return operationRoomDTO;
    }
}
