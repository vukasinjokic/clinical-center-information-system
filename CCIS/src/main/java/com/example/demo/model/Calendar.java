package com.example.demo.model;

import javafx.util.Pair;

import javax.persistence.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public HashMap<String,List<Pair<Date,Date>>> getDates(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String,List<Pair<Date,Date>>> map = new HashMap<String, List<Pair<Date,Date>>>();

        for(int i = 0; i<eventStartDates.size(); i++){
            if(map.containsKey(sdf.format(eventStartDates.get(i)).substring(0,10))){
                Pair<Date,Date> pair = new Pair<Date,Date>(eventStartDates.get(i),eventEndDates.get(i));
                map.get(sdf.format(eventStartDates.get(i)).substring(0,10)).add(pair);

            }else{
                List<Pair<Date,Date>> ls = new ArrayList<Pair<Date,Date>>();
                Pair<Date,Date> pair = new Pair<Date,Date>(eventStartDates.get(i),eventEndDates.get(i));
                map.get(sdf.format(eventStartDates.get(i)).substring(0,10)).add(pair);
            }
        }
        return map;
    }

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
}