package com.example.demo.dto;

import com.example.demo.model.User;

import java.util.Collection;

public class PatientDTO {

    private User user;
    private MedicalRecordDTO medicalRecord;
    private Collection<AppointmentDTO> appointments;


}
