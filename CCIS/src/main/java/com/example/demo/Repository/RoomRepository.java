package com.example.demo.Repository;

import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    Room findByNumber(String number);
    Room findByName(String name);
}
