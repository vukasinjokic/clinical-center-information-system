package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "businessHours")
public class BusinessHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @Column(name="started", unique = false, nullable = false)
    private float started;

    @Column(name="ended", unique = false, nullable = false)
    private float ended;

    public BusinessHours(float started, float ended) {
        this.started = started;
        this.ended = ended;
    }

    public float getStarted() {
        return started;
    }

    public void setStarted(float started) {
        this.started = started;
    }

    public float getEnded() {
        return ended;
    }

    public void setEnded(float ended) {
        this.ended = ended;
    }
}
