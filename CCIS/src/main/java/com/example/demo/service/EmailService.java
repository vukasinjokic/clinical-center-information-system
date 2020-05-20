package com.example.demo.service;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.*;
import com.example.demo.useful_beans.MedicalStaffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void alertDoctorsOperation(List<DoctorDTO> doctors) throws MailException, InterruptedException{
        //TODO Appointment treba da se prosledi sa backa
        //TODO schedule appointment in doctors calendars
        for(DoctorDTO doctor : doctors){
            SimpleMailMessage mail = new SimpleMailMessage();
//            mail.setTo(doctor.getEmail());
            mail.setTo("isamrstim23@gmail.com");
            mail.setFrom("blabla");
            mail.setSubject("Nova operacija zakazana");
            mail.setText("Postovani/a " + doctor.getFirstName() + ", \n\n Zakazana je nova operacija ___________ u sali _______, dana ____________, sa pocetkom u ___________. Pozvani ste da joj prisustvujete. \n\n");
            javaMailSender.send(mail);
        }
    }

    @Async
    public void alertAdminForVacation(Doctor doctor, MedicalStaffRequest request){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("isamrstim23@gmail.com");
        mail.setFrom(doctor.getEmail());
        mail.setSubject("Zahtev za " + request.getType().toString());
        mail.setText("Postovani/a " + ", \n\n doktor " + doctor.getFirstName() + " " + doctor.getLastName()
                       + " je zatrazio " + request.getType() + " od: " + request.getFromDate().toString()+
                        " do: " +request.getToDate().toString() + " datuma.");
        javaMailSender.send(mail);
    }

    @Async
    public void alertClinicAdminForAppointmentPatientRequest(List<ClinicAdmin> admins, AppointmentRequest appointmentRequest) {
        for (ClinicAdmin clinicAdmin : admins) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("isamrstim23@gmail.com");
            mail.setFrom("Clinical-center-information-system@gmail.com");
            mail.setSubject("Zahtev za lekarski pregled pacijenta");
            mail.setText("Postovani/a administratore klinike " + clinicAdmin.getFirstName() + " " + clinicAdmin.getLastName()
                    + ", \n\n Pacijent " +
                    appointmentRequest.getPatient().getFirstName() + " " + appointmentRequest.getPatient().getLastName()
                    + " je zatrazio lekarski pregled kod doktora " +
                    appointmentRequest.getDoctor().getFirstName()+ " " + appointmentRequest.getDoctor().getLastName() +
                    " na datum " + appointmentRequest.getTime().toString());
            javaMailSender.send(mail);
        }
    }

    @Async
    public void alertClinicCenterAdminsForUserRegister(List<ClinicCenterAdmin> clinicCenterAdmins, UserRegisterRequest userRegisterRequest) {
        for (ClinicCenterAdmin clinicCenterAdmin : clinicCenterAdmins) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("isamrstim23@gmail.com");
            mail.setFrom("Clinical-center-information-system@gmail.com");
            mail.setSubject("Zahtev za registraciju korisnika");
            mail.setText("Postovani/a administratore kliničkog centra " + clinicCenterAdmin.getFirstName() + " " + clinicCenterAdmin.getLastName()
                    + ", \n\n Stigao je zahtev za registraciju neregistrovanog korisnika " +
                    userRegisterRequest.getFirstName() + " " + userRegisterRequest.getLastName()
                    + ".\nNeophodno je da taj zahtev odbijete ili potvrdite.");
            javaMailSender.send(mail);
        }
    }
}
