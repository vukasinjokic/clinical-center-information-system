package com.example.demo.api;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.useful_beans.AppointmentToReservePatient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private final UserService userService;
    private final ClinicAdminService clinicAdminService;
    private final EmailService emailService;

    private ModelMapper modelMapper = new ModelMapper();
    private SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");

    @Autowired
    public AppointmentRequestController(AppointmentRequestService appointmentRequestService,
                                        DoctorService doctorService,
                                        ClinicService clinicService,
                                        UserService userService,
                                        ClinicAdminService clinicAdminService,
                                        EmailService emailService) {
        this.appointmentRequestService = appointmentRequestService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
        this.userService = userService;
        this.clinicAdminService = clinicAdminService;
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
            Doctor doctor = doctorService.findById(Integer.parseInt(appointmentToAdd.getDoctorId()));
            if (doctor == null)
                return new ResponseEntity<>("Invalid doctor id", HttpStatus.BAD_REQUEST);

            Clinic clinic = clinicService.findById(Integer.parseInt(appointmentToAdd.getClinicId()));
            if (clinic == null)
                return new ResponseEntity<>("Invalid clinic id", HttpStatus.BAD_REQUEST);

            User user = userService.findByUsername(appointmentToAdd.getPatientEmail());
            if (user == null)
                return new ResponseEntity<>("Invalid user email", HttpStatus.BAD_REQUEST);

            AppointmentRequest appointmentRequest = new AppointmentRequest();
            appointmentRequest.setDoctor(doctor);
            appointmentRequest.setPatient((Patient) user);
            appointmentRequest.setTime(chosenDate);
            appointmentRequest.setType(AppointmentRequest.AppointmentReqType.PATIENT);

            boolean success = appointmentRequestService.saveRequest(appointmentRequest);
            if (success) {
                List<ClinicAdmin> clinicAdmins = clinicAdminService.getClinicAdminsByClinicId(clinic.getId());
                // TODO: Videti zašto traje dugo
                emailService.alertClinicAdminForAppointmentPatientRequest(clinicAdmins, appointmentRequest);

                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Request not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid date time format", HttpStatus.BAD_REQUEST);
        }
    }

    private AppointmentRequestDTO convertToDTO(AppointmentRequest req){
        AppointmentRequestDTO reqDTO = modelMapper.map(req, AppointmentRequestDTO.class);
        return reqDTO;
    }

}
