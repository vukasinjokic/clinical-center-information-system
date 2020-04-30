package com.example.demo.service;

import com.example.demo.Repository.OperationRoomRepository;
import com.example.demo.model.OperationRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationRoomService {

    @Autowired
    private OperationRoomRepository operationRoomRepository;

    public List<OperationRoom> getAllOperationRooms(){
        return operationRoomRepository.findAll();
    }
}
