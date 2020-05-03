package com.example.demo.service;

import com.example.demo.Repository.DoctorRepository;
import com.example.demo.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
@Service
public class DoctorService {

    HashSet<Doctor> doctors = new HashSet<Doctor>();

    @Autowired
    private DoctorRepository doctorRepository;

    public boolean addDoctor(Doctor doctor){
        doctors.add(doctor);
        return true;
    }

    public HashSet<Doctor> getDoctors() {
        return doctors;
    }

    public boolean deleteDoctor(String id){
        Optional<Doctor> doctor = selectDoctorById(id);
        if(doctor.isPresent())
        {
            doctors.remove(doctor);
            return true;
        }
        return false;
    }

    public Optional<Doctor> selectDoctorById(String id){
        return doctors.stream().filter(doctor -> doctor.getEmail().equals(id))
                .findFirst();
    }

    public Doctor findById(Integer id){
        return doctorRepository.findById(id).orElse(null);
    }
}
