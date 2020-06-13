package com.example.demo.api;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.useful_beans.AppointmentToReservePatient;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/appointmentRequests")
@RestController
public class AppointmentRequestController {

    private final AppointmentRequestService appointmentRequestService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;
    private final PatientService patientService;
    private final ClinicAdminService clinicAdminService;
    private final AppointmentService appointmentService;
    private final EmailService emailService;

    private ModelMapper modelMapper = new ModelMapper();
    private SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");

    @Autowired
    public AppointmentRequestController(AppointmentRequestService appointmentRequestService,
                                        DoctorService doctorService,
                                        ClinicService clinicService,
                                        PatientService patientService,
                                        ClinicAdminService clinicAdminService,
                                        AppointmentService appointmentService, EmailService emailService) {
        this.appointmentRequestService = appointmentRequestService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
        this.patientService = patientService;
        this.clinicAdminService = clinicAdminService;
        this.appointmentService = appointmentService;
        this.emailService = emailService;
    }

    @GetMapping("/getAppointmentRequests")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public List<AppointmentRequestDTO> getAppointmentRequests(){
        List<AppointmentRequest> requests = appointmentRequestService.getRequests();
        return requests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/addAppointmentRequest")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> addAppointmentRequest(@RequestBody AppointmentToReservePatient appointmentToAdd) {
        try {
            Date chosenDate = formatter.parse(appointmentToAdd.getAppointmentTime());
            Patient patient = patientService.findByEmail(appointmentToAdd.getPatientEmail());
            if (patient == null)
                return new ResponseEntity<>("Invalid user email", HttpStatus.BAD_REQUEST);

            Doctor doctor = doctorService.findById(Integer.parseInt(appointmentToAdd.getDoctorId()));
            if (doctor == null)
                return new ResponseEntity<>("Invalid doctor id", HttpStatus.BAD_REQUEST);

            Clinic clinic = clinicService.findById(Integer.parseInt(appointmentToAdd.getClinicId()));
            if (clinic == null)
                return new ResponseEntity<>("Invalid clinic id", HttpStatus.BAD_REQUEST);


            int result = appointmentRequestService.saveRequest(patient, doctor, chosenDate, appointmentToAdd.getPrice(), clinic.getId());
            if (result == 0)
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else if (result == 1)
                return new ResponseEntity<>("Request not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
            else if (result == -1) {
                return new ResponseEntity<>("Appointments is already taken.", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("Unknown server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid date time format", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unknown server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private AppointmentRequestDTO convertToDTO(AppointmentRequest req){
        AppointmentRequestDTO reqDTO = modelMapper.map(req, AppointmentRequestDTO.class);
        return reqDTO;
    }

    @DeleteMapping("/deleteRequest/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer id){
        if(appointmentRequestService.deleteRequest(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
