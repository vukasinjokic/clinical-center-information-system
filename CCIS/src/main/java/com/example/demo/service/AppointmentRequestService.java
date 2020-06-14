package com.example.demo.service;

import com.example.demo.Repository.AppointmentRequestRepository;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.ClinicAdmin;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentRequestService {

    @Autowired
    private AppointmentRequestRepository appointmentRequestRepository;
    @Autowired
    private ClinicAdminService clinicAdminService;
    @Autowired
    private EmailService emailService;

    public List<AppointmentRequest> getRequests(){
        return appointmentRequestRepository.findAll();
    }

    public boolean deleteRequest(Integer id){
        Optional<AppointmentRequest> req = appointmentRequestRepository.findById(id);
        if(req.isPresent()){
            appointmentRequestRepository.deleteById(id);
            return true;
        }
        return false;


    }

    public int saveRequest(Patient patient, Doctor doctor, Date chosenDate, Float price, Integer clinicId) {
        try {
            AppointmentRequest appointmentRequest = new AppointmentRequest();
            doctor.setCounter(doctor.getCounter() + 1);
//                Thread.sleep(5000);           // for testing optimistic blocking
            appointmentRequest.setDoctor(doctor);
            appointmentRequest.setPatient(patient);
            appointmentRequest.setTime(chosenDate);

            appointmentRequest.setPrice(price);
            appointmentRequest.setDiscount(0f);

            appointmentRequest.setType(AppointmentRequest.AppointmentReqType.PATIENT);

            appointmentRequest = appointmentRequestRepository.save(appointmentRequest);
            if (appointmentRequest != null) {
                List<ClinicAdmin> clinicAdmins = clinicAdminService.getClinicAdminsByClinicId(clinicId);
                emailService.alertClinicAdminForAppointmentPatientRequest(clinicAdmins, appointmentRequest);
                return 0;       // success
            } else
                return 1;       // not saved to database
        } catch (Exception e) {
            e.printStackTrace();
            return -1;      // OptimisticLockException
        }
    }
}
