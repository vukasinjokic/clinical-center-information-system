package com.example.demo.dto;

import java.util.List;

public class ClinicDTO {
    private String id;
    private String name;
    private String address;
    private String description;
    private String priceList;
    private String rating;
    private List<String> doctors;
    private List<String> nurses;
    private List<AppointmentDTO> appointments;
    private List<String> operationRooms;
    private String codebook; // String ili da se napravi codebookDTO?
}
