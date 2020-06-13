package com.example.demo.Repository;

import com.example.demo.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

//    @Query("select count(c) from Calendar c.eventStartDates where " +
//            "((c.eventStartDates <= (:sd) and c.eventEndDates >= (:sd)) or " +
//            "(c.eventStartDates <= (:ed) and c.eventEndDates >= (:ed)) or " +
//            "(c.eventStartDates >= (:sd) and c.eventStartDates <= (:ed))) and c.id = (:id) ")
//    Long findIfIsTermFree(@Param("sd") Date sd, @Param("ed") Date ed, @Param("id") Integer id);

}
