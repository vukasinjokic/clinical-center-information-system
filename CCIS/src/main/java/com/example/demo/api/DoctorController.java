package com.example.demo.api;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Calendar;
import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper = new ModelMapper();

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
        return "Uspesno dodat";
    }
    @GetMapping("/getDoctors")
    public HashSet<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }

    @DeleteMapping(path = "{id}")
    public void deleteDoctor(@PathVariable("id") String id){
        doctorService.deleteDoctor(id);
    }

    @GetMapping(path = "{id}")
    public DoctorDTO findById(@PathVariable("id") Integer id){
        Doctor doctor = doctorService.findById(id);
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setFields(doctor);
        return doctorDTO;

    }

    @GetMapping(path = "/{id}/calendar")
    public Calendar getDoctorsCalendar(@PathVariable("id") Integer id){
        DoctorDTO doctor = findById(id);
        return doctor.getCalendar();
    }
}
