package com.example.demo.service;

import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.useful_beans.AppointmentToReserve;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    private EmailService emailService;

    private final ModelMapper modelMapper = new ModelMapper();


    public ClinicAdmin getClinicAdminByEmail(String email){
        return clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
    }

    public void handleReservation(AppointmentToReserve appointmentToReserve) throws InterruptedException {
        emailService.alertDoctorsOperation(appointmentToReserve.getDoctors());
        //add patient to predefined appointment if it exists
        //add appointment to doctors calendars
        //add appointment to room calendar
        //add room to appointment
        //main doctor is the last in the list
        Integer patient_id = Integer.parseInt(appointmentToReserve.getRequest().getPatient().getId());
        Patient patient = patientRepository.getOne(patient_id);
        if(appointmentToReserve.getRequest().getPredefAppointment() != null){
            Integer predef_app_id = Integer.parseInt(appointmentToReserve.getRequest().getPredefAppointment().getId());
            Appointment predef_app = appointmentRepository.getOne(predef_app_id);
            predef_app.setPatient(patient);

        }

    }

    public List<DoctorDTO> getClinicDoctors(String email){
        ClinicAdmin ca = clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
        return ca.getClinic().getDoctors().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DoctorDTO convertToDTO(Doctor doctor){
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        return doctorDTO;
    }
}
