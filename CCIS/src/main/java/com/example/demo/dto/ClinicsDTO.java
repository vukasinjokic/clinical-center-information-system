package com.example.demo.dto;

import com.example.demo.model.Clinic;
import com.example.demo.model.Doctor;
import com.example.demo.model.PriceList;
import com.example.demo.model.PriceListItem;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicsDTO {

    private static ModelMapper modelMapper = new ModelMapper();

    private String id;
    private String name;
    private String address;
    private String description;
    private Map<String, Float> priceList = new HashMap<>();
    private String rating;
    private List<DoctorDTO> doctors = new ArrayList<>();

    public ClinicsDTO() {

    }

    public void setDTOFields(Clinic clinic) {
        setPriceListMap(clinic.getPriceList());
        setDoctors(clinic.getDoctors());
        setRating(clinic.getRating().getAverageGrade().toString());
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

    public Map<String, Float> getPriceList() {
        return priceList;
    }

    public void setPriceListMap(PriceList priceList) {
        if (priceList == null)
            return;
        for (PriceListItem item : priceList.getItems())
            this.priceList.put(item.getExaminationType().getName(), item.getPrice());
    }

    public void setPriceList(Map<String, Float> priceList) {
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
