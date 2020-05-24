package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.*;
import com.example.demo.validation.DoctorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private BusinessHoursRepository businessHoursRepository;
    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    private DoctorValidation doctorValidation = new DoctorValidation();

    public Doctor findById(Integer id){
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> findAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor findByEmail(String email){
        return doctorRepository.findByEmail(email);
    }

    public String deleteDoctor(Integer id){
        Optional<Doctor> find_doc = doctorRepository.findById(id);
        List<Appointment> appointments = appointmentRepository.findByDoctorId(id);
        //ne pokupi doktora koji ima appointments null
        Date date = new Date();
        if(find_doc.isPresent()){
            Doctor doctor = find_doc.get();
            if(doctorValidation.checkAppointments(appointments,date)) {
                doctorRepository.delete(doctor);
                return "";
            }
            if(doctor.getCalendar() == null){
                doctorRepository.delete(doctor);
                return "";
            }
//            if(doctorValidation.validateDeleting(doctor,date)){
//                doctorRepository.delete(doctor);
//                return "";
//            }

            return "Ne mozete obrisati doktora koji ima zakazan pregled.";
        }
        return "Doktor sa zadatim id ne postoji.";
    }

    public Doctor saveDoctor(DoctorDTO doctorDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClinicAdmin admin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());

        List<Doctor> allDoctors = findAllDoctors(); //from clinic
        if(doctorValidation.isEmailExist(doctorDTO, allDoctors))
            return null;
        if(doctorValidation.isNumberExist(doctorDTO, allDoctors))
            return null;

        Doctor newDoctor = new Doctor();

        BusinessHours businessHours = new BusinessHours();
        LocalTime startTime = LocalTime.parse(doctorDTO.getBusinessHours().getStarted());
        businessHours.setStarted(Time.valueOf(startTime));
        LocalTime endTime = LocalTime.parse(doctorDTO.getBusinessHours().getEnded());
        businessHours.setEnded(Time.valueOf(endTime));
        Optional<ExaminationType> examinationType = examinationTypeRepository.findById(doctorDTO.getExaminationType().getId());

        businessHoursRepository.save(businessHours); //videcemo da pretrazimo postojece pa dodelimo
        newDoctor.setUsername(doctorDTO.getEmail());
        newDoctor.setEmail(doctorDTO.getEmail());
        newDoctor.setPassword(doctorDTO.getPassword());
        newDoctor.setFirstName(doctorDTO.getFirstName());
        newDoctor.setLastName(doctorDTO.getLastName());
        newDoctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        newDoctor.setSocialSecurityNumber(doctorDTO.getSocialSecurityNumber());
        newDoctor.setCity(doctorDTO.getCity());
        newDoctor.setAddress(doctorDTO.getAddress());
        newDoctor.setBusinessHours(businessHours);
        newDoctor.setClinic(admin.getClinic());
        newDoctor.setCountry(doctorDTO.getCountry());
        newDoctor.setExaminationType(examinationType.get());
        return doctorRepository.save(newDoctor);
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
