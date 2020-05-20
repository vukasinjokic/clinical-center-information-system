package com.example.demo.service;

import com.example.demo.Repository.ClinicRepository;
import com.example.demo.Repository.DoctorRepository;
import com.example.demo.Repository.MedicalStaffRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.MedicalStaffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    public Doctor findById(Integer id){
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor findByEmail(String email){
        return doctorRepository.findByEmail(email);
    }

    public boolean sendRequest(MedicalStaffRequest request){

        Doctor user = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Doctor get_doctor_clinic = doctorRepository.findByEmailAndFetchClinicEagerly(user.getEmail());

//        get_doctor_clinic.getCalendar().getDates();

        request.setMedicalStaff_email(user.getEmail());
        request.setMedicalStaffName(user.getFirstName());
        request.setMedicalStaffLastName(user.getLastName());
        get_doctor_clinic.getClinic().getMedicalStaffRequests().add(request);
        clinicRepository.save(get_doctor_clinic.getClinic());

        emailService.alertAdminForVacation(user,request);

        return true;
    }

    public boolean schedule(AppointmentDTO appointmentDTO) throws ParseException {
        Doctor user = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByEmail(appointmentDTO.getPatient());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        if(patient == null)
            return false;

        AppointmentRequest request = new AppointmentRequest();
        request.setDoctor(user);
        request.setPatient(patient);
        request.setTime(formatter.parse(appointmentDTO.getDate()));
        request.setType(AppointmentRequest.AppointmentReqType.DOCTOR);

        Doctor get_doctor_clinic = doctorRepository.findByEmailAndFetchClinicEagerly(user.getEmail());

        get_doctor_clinic.getClinic().getAppointmentRequests().add(request);

        clinicRepository.save(get_doctor_clinic.getClinic());
        return true;
    }

}
