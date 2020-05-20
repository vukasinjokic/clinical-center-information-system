package com.example.demo.api;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.service.AppointmentRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/appointmentRequests")
@RestController
public class AppointmentRequestController {

    private final AppointmentRequestService appointmentRequestService;
    private ModelMapper modelMapper = new ModelMapper();

    public AppointmentRequestController(AppointmentRequestService appointmentRequestService) {
        this.appointmentRequestService = appointmentRequestService;
    }

    @GetMapping("/getAppointmentRequests")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public List<AppointmentRequestDTO> getAppointmentRequests(){
        List<AppointmentRequest> requests = appointmentRequestService.getRequests();
        return requests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentRequestDTO convertToDTO(AppointmentRequest req){
        AppointmentRequestDTO reqDTO = modelMapper.map(req, AppointmentRequestDTO.class);
        return reqDTO;
    }

    @DeleteMapping("/deleteRequest/{id}")
    @PreAuthorize("hasRole( 'CLINIC_ADMIN')")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer id){
        if(appointmentRequestService.deleteRequest(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
