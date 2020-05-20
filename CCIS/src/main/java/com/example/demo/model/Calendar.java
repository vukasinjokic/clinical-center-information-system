package com.example.demo.model;

import javax.persistence.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "calendars")
public class Calendar {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(name = "eventStartDates", joinColumns = @JoinColumn(name = "calendar_id"))
    @Column(name = "eventStartDates")
    private List<Date> eventStartDates;

    @ElementCollection
    @CollectionTable(name = "eventEndDates", joinColumns = @JoinColumn(name = "calendar_id"))
    @Column(name = "eventEndDates")
    private List<Date> eventEndDates;

    @ElementCollection
    @CollectionTable(name = "eventNames", joinColumns = @JoinColumn(name = "calendar_id"))
    @Column(name = "eventNames")
    private List<String> eventNames;

    public Calendar() {
    }

    public Calendar(Integer id, List<Date> eventStartDates, List<Date> eventEndDates, List<String> eventNames){
        this.id = id;
        this.eventStartDates = eventStartDates;
        this.eventEndDates = eventEndDates;
        this.eventNames = eventNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Date> getEventStartDates() {
        return eventStartDates;
    }

    public void setEventStartDates(List<Date> startDates) {
        this.eventStartDates = startDates;
    }

    public List<Date> getEventEndDates() {
        return eventEndDates;
    }

    public void setEventEndDates(List<Date> endDates) {
        this.eventEndDates = endDates;
    }

    public List<String> getEventNames() {
        return eventNames;
    }

    public void setEventNames(List<String> eventNames) {
        this.eventNames = eventNames;
    }

    public void addAppointment(Appointment appointment){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date startTime = appointment.getTime();
            ExaminationType exType = appointment.getExaminationType();
            eventStartDates.add(sdf.parse(sdf.format(startTime)));
            eventEndDates.add(sdf.parse(sdf.format(new Date(startTime.getTime() + (long)exType.getDuration()))));
            eventNames.add(exType.getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}