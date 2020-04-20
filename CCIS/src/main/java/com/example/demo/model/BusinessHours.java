package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "business_hours")
public class BusinessHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="started", unique = false, nullable = false)
    private Time started;

    @Column(name="ended", unique = false, nullable = false)
    private Time ended;

    public BusinessHours(Time started, Time ended) {
        this.started = started;
        this.ended = ended;
    }

    public Time getStarted() {
        return started;
    }

    public void setStarted(Time started) {
        this.started = started;
    }

    public Time getEnded() {
        return ended;
    }

    public void setEnded(Time ended) {
        this.ended = ended;
    }
}
