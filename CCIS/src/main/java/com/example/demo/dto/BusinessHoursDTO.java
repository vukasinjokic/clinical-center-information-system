package com.example.demo.dto;

public class BusinessHoursDTO {

    private Integer id;
    private String started;
    private String ended;

    public BusinessHoursDTO(){

    }

    public BusinessHoursDTO(String started, String ended) {
        this.started = started;
        this.ended = ended;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }
}
