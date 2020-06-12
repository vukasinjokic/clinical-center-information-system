package com.example.demo.api;

import com.example.demo.Repository.*;
import com.example.demo.model.*;
import com.example.demo.service.AppointmentRequestService;
import com.example.demo.service.EmailService;
import com.example.demo.service.RoomService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentScheduleController {

    @Autowired
    AppointmentRequestService appointmentRequestService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "${schedule.cron}")
    public void scheduleAppointments(){
        try{
            List<AppointmentRequest> requests = appointmentRequestService.getRequests();
            for(AppointmentRequest request : requests){
                Doctor doctor = request.getDoctor();
                Date time = request.getTime();
                while(!reserveRoomForRequestAtTime(request, time)){
                    time.setTime(time.getTime() + 1000*60*60*24);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private boolean reserveRoomForRequestAtTime(AppointmentRequest request, Date time) throws InterruptedException {
        time = time == null ? request.getPredefAppointment().getTime() : time;
        Optional<Doctor> doctorOptional = doctorRepository.findById(request.getDoctor() == null ? request.getPredefAppointment().getDoctor().getId() : request.getDoctor().getId());
        if(!doctorOptional.isPresent()){
            return false;
        }
        Doctor doctor = doctorOptional.get();

        for(Date availableTime : doctor.getAvailableTimesForDate(time)){
            for(Room room : roomService.getRoomsForClinicId(doctor.getClinic().getId())){
                if(room.isAvailableForTimeAndDuration(availableTime, doctor.getExaminationType().getMillisecondsDuration())){
                    scheduleAppointmentForRequest(request, availableTime, room);
                    return true;
                }
            }
        }
        return false;
    }

    private void scheduleAppointmentForRequest(AppointmentRequest request, Date time, Room room) throws InterruptedException {
        Appointment appointment;
        Doctor doctor;
        Patient patient;
        Optional<AppointmentRequest> appointmentRequestOptional = appointmentRequestRepository.findByIdAndFetchPatientEagerly(request.getId());

        if(request.getPredefAppointment() != null){
            Integer appointment_id = request.getPredefAppointment().getId();
            appointment = appointmentRepository.findByIdAndFetchDoctorEagerly(appointment_id);
            patient = request.getPredefAppointment().getPatient();
            doctor = appointment.getDoctor();
        }
        else{
            if(!appointmentRequestOptional.isPresent()){
                return;
            }
            patient = appointmentRequestOptional.get().getPatient();
            doctor = request.getDoctor();
            Clinic clinic = doctorRepository.findClinicByDoctorId(doctor.getId());
            appointment = new Appointment(time, 0, 0, doctor, room, doctor.getExaminationType(), patient, clinic);
        }
        patient.addAppointment(appointment);
        doctor.addAppointment(appointment);
        room.addAppointment(appointment);
        updateDatabase(appointment, doctor, request, room);
        emailService.alertDoctorOperation(doctor, appointment);
        emailService.alertPatientOperation(appointment);
    }

    private void updateDatabase(Appointment appointment, Doctor doctor, AppointmentRequest request, Room room ){
        appointmentRepository.save(appointment);
        doctorRepository.save(doctor);
        calendarRepository.save(doctor.getCalendar());
        appointmentRequestRepository.deleteById(request.getId());
        roomRepository.save(room);
        calendarRepository.save(room.getCalendar());
    }
}
