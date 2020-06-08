package com.example.demo.dto;

import com.example.demo.model.Nurse;

import java.util.Map;

public class PrescriptionDTO {
    public Integer id;
    public Map<String, String> content;
    public boolean isVerified;
    public String nurse;

    public PrescriptionDTO(){}

    public PrescriptionDTO(Integer id, Map<String, String> content, boolean isVerified, String nurse) {
        this.id = id;
        this.content = content;
        this.isVerified = isVerified;
        this.nurse = nurse;
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
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
