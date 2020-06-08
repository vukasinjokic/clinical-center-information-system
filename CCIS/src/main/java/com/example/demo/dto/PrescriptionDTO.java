package com.example.demo.dto;

import com.example.demo.model.Nurse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrescriptionDTO {
    public Integer id;
    public String nurse;
    private boolean isVerified;
    public Map<String, String> content;
    private Collection<String> medications;
    private Collection<String> times;

    public PrescriptionDTO() {
        content = new HashMap<>();
        medications = new ArrayList<>();
        times = new ArrayList<>();
    }

    public PrescriptionDTO(Collection<String> medications, Collection<String> times, boolean isVerified) {
        this.medications = medications;
        this.times = times;
        this.isVerified = isVerified;
    }

    public PrescriptionDTO(Integer id, Map<String, String> content, boolean isVerified, String nurse) {
        this.id = id;
        this.content = content;
        this.isVerified = isVerified;
        this.nurse = nurse;
    }

    public Collection<String> getMedications() {
        return medications;
    }

    public void setMedications(Collection<String> medications) {
        this.medications = medications;
    }

    public Collection<String> getTimes() {
        return times;
    }

    public void setTimes(Collection<String> times) {
        this.times = times;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public void setNurse(Nurse nurse){
        if(nurse == null) return;
        this.nurse = nurse.getFirstName() + " " + nurse.getLastName();
    }

}
