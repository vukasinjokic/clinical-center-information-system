package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.*;
import com.example.demo.useful_beans.AppointmentToReserve;
import com.sun.javaws.security.AppPolicy;
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

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EmailService emailService;

    private final ModelMapper modelMapper = new ModelMapper();


    public ClinicAdmin getClinicAdminByEmail(String email){
        return clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
    }

    public void handleReservation(AppointmentToReserve appointmentToReserve) throws InterruptedException {
        Integer patient_id = Integer.parseInt(appointmentToReserve.getRequest().getPatient().getId());
        Patient patient = patientRepository.findById(patient_id).get();

        Integer doctor_id = Integer.parseInt(appointmentToReserve.getRequest().getDoctor().getId());
        Doctor doctor = doctorRepository.findById(doctor_id).get();

        Integer room_id = Integer.parseInt(appointmentToReserve.getRoom().getId());
        Room room = roomRepository.findById(room_id).get();

        Appointment appointment;
        if(appointmentToReserve.getRequest().getPredefAppointment() != null){
            Integer appointment_id = Integer.parseInt(appointmentToReserve.getRequest().getId());
            appointment = appointmentRepository.getOne(appointment_id);
        }
        else{
            appointment = new Appointment(appointmentToReserve.getReservedTime(), appointmentToReserve.getRequest().getPrice(), appointmentToReserve.getRequest().getDiscount(), doctor, room, doctor.getExaminationType(), patient, doctor.getClinic());
        }
        patient.addAppointment(appointment);
        List<Doctor> doctors = appointmentToReserve.getDoctors().stream().map(doctorDTO -> modelMapper.map(doctorDTO, Doctor.class)).collect(Collectors.toList());
        addAppointmentToDoctors(appointment, doctors);
        room.addAppointment(appointment);
        updateDataBase(appointment, patient, doctors, room);
        emailService.alertDoctorsOperation(appointmentToReserve.getDoctors(), appointment);
        emailService.alertPatientOperation(appointment);
    }

    private void updateDataBase(Appointment appointment, Patient patient, List<Doctor> doctors, Room room){
        appointmentRepository.save(appointment);
//        patientRepository.save(patient);
//        roomRepository.save(room);
//        doctorRepository.saveAll(doctors);
    }

    private void addAppointmentToDoctors(Appointment appointment, List<Doctor> doctors){
        for(Doctor doctor : doctors){
            doctor.addAppointment(appointment);
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
