package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.*;
import com.example.demo.model.Calendar;
import com.example.demo.useful_beans.AppointmentToFinish;
import com.example.demo.useful_beans.MedicineForPrescription;
import com.example.demo.validation.AppointmentValidation;
import com.example.demo.validation.DoctorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;
    @Autowired
    private CalendarRepository calendarRepository;

    private DoctorValidation doctorValidation = new DoctorValidation();

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> findByPatientIdFinished(Integer patientId) {
        return appointmentRepository.findByPatientIdAndFinished(patientId, true);
    }

    public List<Appointment> getPredefinedAppointments(Integer clinicId) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        if (optionalClinic.isPresent())
            return appointmentRepository.findPredefinedAppointments(optionalClinic.get());
        else
            return new ArrayList<>();
    }

    public Appointment getAppointment(Integer id){
        return appointmentRepository.findById(id).get();
    }

    public Appointment saveAppointment(AppointmentDTO appointmentDTO) throws ParseException {
        ClinicAdmin user = (ClinicAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse(appointmentDTO.getDate());
        //apointmentDTO sada ima doctorDTO i tu treba izmena
        Doctor getDoctor = doctorRepository.findByEmail(appointmentDTO.doctorEmail);
        int room_len = appointmentDTO.getRoom().length();
        String room_number = appointmentDTO.getRoom().substring(room_len - 3, room_len);
        Room getRoom = roomRepository.findByNumber(room_number);
        Optional<Clinic> getClinic = clinicRepository.findById(user.getClinic().getId());
        ExaminationType getType = examinationTypeRepository.findById(Integer.parseInt(appointmentDTO.getExaminationType())).get();

        if(!doctorValidation.validateDoctorBusy(date, getType.getDuration(),getDoctor))
            return null;
        if(!validateRoom(date, getType.getDuration(), getRoom))
            return null;

        Appointment appointment_to_add;
        if(getClinic.isPresent()) {
            //Sta ako klinika nema cenovnik (vratimo bad request)
            float price = getClinic.get().getPriceList().getItems().stream()
                    .filter(item -> item.getExaminationType().getId() == getType.getId())
                    .findAny().get().getPrice();

            appointment_to_add = new Appointment(date, price, 0, getDoctor, getRoom, getType, getClinic.get());
            getRoom.addAppointment(appointment_to_add);
            getDoctor.addAppointment(appointment_to_add);
            appointmentRepository.save(appointment_to_add);
            return appointment_to_add;
        }
        return null;
    }

    public boolean validateRoom(Date startDate, float duration, Room room){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        int d = (int) duration*3600*1000;
        Date endDate = new Date(startDate.getTime()+d);
        if(room.getCalendar().getEventStartDates() == null){
            room.getCalendar().setEventStartDates(new ArrayList<Date>());
            room.getCalendar().setEventEndDates(new ArrayList<Date>());
            return true;
        }
        List<HashMap<Date,Date>> check_dates_list = room.getCalendar().formatDates().get(sdf.format(startDate).substring(0,10));
        if(check_dates_list == null)
            return true;
        for(int i = 0; i< check_dates_list.size(); i++){

            for (Map.Entry<Date, Date> entry : check_dates_list.get(i).entrySet()) {

                if (startDate.after(entry.getKey())) {
                    if (startDate.before(entry.getValue()))
                        return false;
                    if (i < check_dates_list.size() - 1) {
                        for (Map.Entry<Date, Date> entry1 : check_dates_list.get(i+1).entrySet()) {
                            if (startDate.before(entry1.getKey()) && startDate.after(entry.getValue())) {
                                if (endDate.before(entry1.getKey())) {
                                    return true;
                                }
                            } else {
                                continue;
                            }
                        }
                    } else {
                        if (startDate.after(entry.getKey())) {
                            if (startDate.after(entry.getValue()))
                                return true;
                        } else { //before
                            if (endDate.before(entry.getKey()))
                                return true;
                        }
                    }
                } else if (startDate.before(entry.getKey())) {
                    if (endDate.before(entry.getKey()))
                        return true;
                    else
                        return false;
                }
            }
        }
        return false;
    }

    public CodeBook getCodebookFromAppointmentClinic(Integer appointment_id) {
        //TODO make custom query
        Appointment appointment = appointmentRepository.findByIdAndFetchClinicEagerly(appointment_id).get();
        return appointment.getClinic().getCodeBook();
    }

    public boolean handleAppointmentFinish(AppointmentToFinish appointmentToFinish) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentToFinish.appointmentId);
        if(appointmentOptional.isPresent()){
            Appointment appointment = appointmentOptional.get();
            appointment.setReport(appointmentToFinish.report);
            Prescription prescription = new Prescription(appointmentToFinish.prescriptionToAdd);
            MedicalRecord mr = appointment.getPatient().getMedicalRecord();
            mr.addAppointment(appointment);
            mr.addPrescription(prescription);

            List<Calendar> calendars = calendarRepository.findAllByAppointmentIdsContaining(appointmentToFinish.appointmentId);
            for(Calendar calendar : calendars){
                calendar.removeEventByAppointmentId(appointmentToFinish.appointmentId);
            }

            appointment.getClinic().addPrescription(prescription);
            appointment.setFinished(true);

            appointmentRepository.save(appointment);
            return true;
        }
        return false;

    }

    public String getPatinetEmail(Integer appointment_id) {
        return appointmentRepository.findPatientEmailFromAppointment(appointment_id);
    }

    public Appointment getAppointmentForPatient(String patientEmail) {
        Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Appointment> appointmentOptional =  appointmentRepository.findFirstByDoctorEmailAndPatientEmailAndFinishedFalseOrderByTimeAsc(doctor.getEmail(), patientEmail);
        if(appointmentOptional.isPresent()) return appointmentOptional.get();
        return null;
    }
}
