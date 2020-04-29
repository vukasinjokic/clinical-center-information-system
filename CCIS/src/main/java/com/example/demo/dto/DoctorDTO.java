package com.example.demo.dto;


import com.example.demo.model.BusinessHours;
import com.example.demo.model.ExaminationType;
import com.example.demo.model.User;

import java.util.Collection;

public class DoctorDTO {
    private User user;
//    private CalendarDTO calendar TODO
    private String clinic;
    private String rating;
    private BusinessHours businessHours;
    private ExaminationType examinationType;
    private Collection<AppointmentDTO> appointments;

}
