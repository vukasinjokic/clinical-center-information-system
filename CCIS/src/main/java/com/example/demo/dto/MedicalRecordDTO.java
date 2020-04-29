package com.example.demo.dto;

import java.util.Collection;

public class MedicalRecordDTO {
    private String id;
    private Collection<String> history;
    private Collection<AppointmentDTO> appointments;
}
