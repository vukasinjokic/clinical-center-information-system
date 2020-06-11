package com.example.demo.service;

import com.example.demo.Repository.MedicalStaffRepository;
import com.example.demo.Repository.MedicalStaffRequestRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.model.Calendar;
import com.example.demo.model.MedicalStaff;
import com.example.demo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffService {

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatientsForStaffMail(String staff_email){
        MedicalStaff ms = medicalStaffRepository.findByEmail(staff_email);
        return patientRepository.findByClinicName(ms.getClinic().getName());
    }

    public Calendar getStaffCalendar(String email){
        MedicalStaff medicalStaff = medicalStaffRepository.findByEmail(email);
        return medicalStaff.getCalendar();
    }
}
