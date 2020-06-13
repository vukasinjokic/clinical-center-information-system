package com.example.demo.Repository;

import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    Room findByNumberAndClinicIdAndActivity(String number, Integer clinicId, Boolean activity);
    Room findByNumberAndClinicId(String number, Integer id);
    Room findByIdAndActivity(Integer id, Boolean activity);
    List<Room> findByClinicIdAndActivity(@Param("clinicId") Integer clinicId, @Param("activity") Boolean activity);
}
