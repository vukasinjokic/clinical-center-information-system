package com.example.demo.service;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.Doctor;
import com.example.demo.model.MedicalStaffRequest;
import com.example.demo.model.User;
import org.hibernate.query.criteria.internal.SelectionImplementor;
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
    public void alertStaffForVacation(User admin, MedicalStaffRequest req,String description){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("isamrstim23@gmail.com");
        mail.setFrom(admin.getEmail());
        mail.setSubject("Zahtev za " + req.getType().toString());
        String mail_text = "";

        if(!description.equals("")){
            mail_text = String.format("Postovani/a " + "\n\n " +req.getMedicalStaffName() + " " + req.getMedicalStaffLastName()
                        + " Vas zahtev nije prihvacen uz obrazlozenje admina klinike: " + description);
        }
        else
            mail_text = String.format("Postovani/a " + ",\n\n " + req.getMedicalStaffName() + " vas zahtev je prohvacen.");

        mail.setText(mail_text);
        javaMailSender.send(mail);
    }

    public void alertAdminForAppointment(AppointmentDTO AppointmentDTO, Doctor doctor){

    }

}
