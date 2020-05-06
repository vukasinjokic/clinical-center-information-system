package com.example.demo.dto;

import com.example.demo.model.Appointment;
import com.example.demo.model.Calendar;
import com.example.demo.model.ExaminationType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CalendarDTO {

    public static void setUpCalendar(Integer calendar_id, Calendar calendar, Collection<Appointment> appointments) throws ParseException {
        ArrayList<Date> eventStartDates = new ArrayList<Date>();
        ArrayList<Date> eventEndDates = new ArrayList<Date>();
        ArrayList<String> eventNames = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(Appointment a : appointments){
            Date startTime = a.getTime();
            ExaminationType exType = a.getExaminationType();
            eventStartDates.add(sdf.parse(sdf.format(startTime)));
            eventEndDates.add(sdf.parse(sdf.format(new Date(startTime.getTime() + (long)exType.getDuration()))));
            eventNames.add(exType.getName());
        }
        calendar.setEventEndDates(eventEndDates);
        calendar.setEventStartDates(eventStartDates);
        calendar.setEventNames(eventNames);
        calendar.setId(calendar_id);
    }
}
