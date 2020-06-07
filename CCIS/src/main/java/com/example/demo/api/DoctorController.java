package com.example.demo.api;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Calendar;
import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;
import com.example.demo.model.MedicalStaffRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper = new ModelMapper();

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public List<DoctorDTO> getDoctors(){
        List<Doctor> doctors = doctorService.findAllDoctors();

        return doctors.stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/saveDoctor")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctorDTO){
        Doctor saved = doctorService.saveDoctor(doctorDTO);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<DoctorDTO>(convertDTO(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDoctor{id}")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id){
        String message = doctorService.deleteDoctor(id);
        if(message == ""){
            return new ResponseEntity<>("Usesno obrisan", HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/sendVacationRequest")
    @PreAuthorize("hasAnyRole('DOCTOR')")
    public ResponseEntity<String> sendVacationRequest(@RequestBody MedicalStaffRequest request){
        if(doctorService.sendRequest(request)){
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/scheduleAppointment")
    @PreAuthorize("hasAnyRole('DOCTOR')")
    public ResponseEntity<String> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
        if(doctorService.schedule(appointmentDTO)){
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad", HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE')")
    public DoctorDTO findById(@PathVariable("id") Integer id){
        Doctor doctor = doctorService.findById(id);
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setFields(doctor);
        return doctorDTO;
    }

    @GetMapping(path = "/calendar/{email}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    public Calendar getDoctorsCalendar(@PathVariable("email") String email){
        Doctor doctor = doctorService.findByEmail(email);
        DoctorDTO doctorDTO = modelMapper.map(doctor,DoctorDTO.class);
        doctorDTO.setFields(doctor);
        return doctorDTO.getCalendar();
    }
    public DoctorDTO convertDTO(Doctor doctor){
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setFields(doctor);
        return doctorDTO;
    }
}
