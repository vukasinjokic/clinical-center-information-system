package com.example.demo.service;

import com.example.demo.dto.DoctorDTO;
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

}
