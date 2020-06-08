package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrescriptionDTO {
    private Collection<String> medications;
    private Collection<String> times;
    private boolean isVerified;

    public PrescriptionDTO() {
        medications = new ArrayList<>();
        times = new ArrayList<>();
    }

    public PrescriptionDTO(Collection<String> medications, Collection<String> times, boolean isVerified) {
        this.medications = medications;
        this.times = times;
        this.isVerified = isVerified;
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
}
