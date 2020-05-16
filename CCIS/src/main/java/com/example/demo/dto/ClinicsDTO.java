package com.example.demo.dto;

import com.example.demo.model.Clinic;
import com.example.demo.model.Doctor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClinicsDTO {

    private static ModelMapper modelMapper = new ModelMapper();

    private String id;
    private String name;
    private String address;
    private String description;
    private String priceList;
    private String rating;
    private List<DoctorDTO> doctors = new ArrayList<>();

    public ClinicsDTO() {

    }

    public void setDTOFields(Clinic clinic) {
        setDoctors(clinic.getDoctors());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<DoctorDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(Collection<Doctor> doctors) {
        this.doctors.clear();
        for (Doctor doctor : doctors) {
            DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
            doctorDTO.setFields(doctor);
            this.doctors.add(doctorDTO);
        }
    }
//
//    public void setDoctors(List<DoctorDTO> doctors) {
//        this.doctors = doctors;
//    }
}
