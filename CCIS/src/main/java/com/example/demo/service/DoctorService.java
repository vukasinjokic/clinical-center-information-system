package com.example.demo.service;

import com.example.demo.Repository.ClinicRepository;
import com.example.demo.Repository.DoctorRepository;
import com.example.demo.Repository.MedicalStaffRequestRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.RatingRepository;
import com.example.demo.Repository.*;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.MedicalRecordDTO;
import com.example.demo.exceptions.ForbiddenException;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.MedicalStaffRequest;
import com.example.demo.model.Rating;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.*;
import com.example.demo.validation.DoctorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private MedicalStaffRequestRepository medicalStaffRequestRepository;
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private BusinessHoursRepository businessHoursRepository;
    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CalendarRepository calendarRepository;

    private DoctorValidation doctorValidation = new DoctorValidation();
    public Doctor findById(Integer id){
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> findAllDoctors(){
        //treba izmena za aktivnost
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<ClinicAdmin> clinicAdmin =  clinicAdminRepository.findById(user.getId());
        return doctorRepository.findAllByClinicIdAndActivity(
                clinicAdmin.get().getClinic().getId(),true);
    }

    public Patient findPatientProfile(String email){
        Patient patient = patientRepository.findByEmail(email);
        return patient;
    }

    public boolean canStaffViewRecord(String patientEmail){
        //DOCTOR ACTIVITY
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Appointment> appointments = appointmentRepository.findAllByDoctorIdAndPatientEmailAndFinished(
                user.getId(), patientEmail, true);
        if(appointments.size() == 0)
            return false;
        return true;
    }

    public List<Doctor> findDoctorsFromClinic(Integer clinicId) {
        //DOCTOR ACTIVITY
        Optional<Clinic> optionalClinic = clinicRepository.findById(clinicId);
        Clinic clinic = optionalClinic.get();
        return clinicRepository.findDoctorsFromClinic(clinic);
    }


    public Doctor findByEmail(String email){
        //DOCTOR ACTIVITY
        return doctorRepository.findByEmailAndActivity(email, true);
    }

    public boolean gradeDoctor(Doctor doctor, Integer patientId, float newGrade) {
        Rating doctorRating = doctor.getRating();
        doctorRating.setGrade(patientId, newGrade);
        doctorRating = ratingRepository.save(doctorRating);
        return doctorRating != null;
    }
    public void deleteDoctor(Integer id) throws ForbiddenException {
        Optional<Doctor> find_doc = doctorRepository.findById(id);
        Date date = new Date();
        List<Appointment> appointments = appointmentRepository.findAllByDoctorIdAndTimeAfter(id, date);
        //ne pokupi doktora koji ima appointments null
        if(find_doc.isPresent()){
            Doctor doctor = find_doc.get();

            if(appointments.size() == 0){
                doctor.setActivity(false);
                doctorRepository.save(doctor);
                return;
            }
//            if(doctorValidation.validateDeleting(doctor,date)){
//                doctorRepository.delete(doctor);
//                return "";
//            }
            throw new ForbiddenException("Doktor ima zakazane preglede.");
        }
        throw new ForbiddenException("Doktor ima zakazane preglede.");
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

        //businessHours u novu funkciju
        BusinessHours businessHours = new BusinessHours();
        LocalTime startTime = LocalTime.parse(doctorDTO.getBusinessHours().getStarted());
        businessHours.setStarted(Time.valueOf(startTime));
        LocalTime endTime = LocalTime.parse(doctorDTO.getBusinessHours().getEnded());
        businessHours.setEnded(Time.valueOf(endTime));
        Optional<ExaminationType> examinationType = examinationTypeRepository.findById(doctorDTO.getExaminationType().getId());

        setDoctorFields(newDoctor, doctorDTO);

        businessHoursRepository.save(businessHours); //videcemo da pretrazimo postojece pa dodelimo
        newDoctor.setBusinessHours(businessHours);
        newDoctor.setClinic(admin.getClinic());
        newDoctor.setExaminationType(examinationType.get());
        return doctorRepository.save(newDoctor);
    }

    private void setDoctorFields(Doctor newDoctor, DoctorDTO doctorDTO) {
        newDoctor.setEmail(doctorDTO.getEmail());
        newDoctor.setPassword(passwordEncoder.encode(doctorDTO.getPassword()));
        newDoctor.setFirstName(doctorDTO.getFirstName());
        newDoctor.setLastName(doctorDTO.getLastName());
        newDoctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        newDoctor.setSocialSecurityNumber(doctorDTO.getSocialSecurityNumber());
        newDoctor.setCity(doctorDTO.getCity());
        newDoctor.setAddress(doctorDTO.getAddress());
        newDoctor.setCountry(doctorDTO.getCountry());
        Rating rating = new Rating();
        rating.setAverageGrade(0.0f);
        newDoctor.setRating(rating);
        Calendar calendar = calendarRepository.save(new Calendar());
        newDoctor.setCalendar(calendar);
        newDoctor.setActivity(true);
    }

    public boolean sendRequest(MedicalStaffRequest request){

        MedicalStaff user = (MedicalStaff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MedicalStaff get_doctor_clinic = medicalStaffRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
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
        try {
            Doctor user = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Patient patient = patientRepository.findByEmail(appointmentDTO.getPatient());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");

            Date startDate = formatter.parse(appointmentDTO.getDate());
//          if(!doctorValidation.validateDoctorBusy(startDate,))

            if (patient == null)
                return false;

            AppointmentRequest request = new AppointmentRequest();
            user.setCounter(user.getCounter() + 1);
//            Thread.sleep(5000);           // for testing optimistic blocking
            doctorRepository.save(user);
            request.setDoctor(user);
            request.setPatient(patient);
            request.setTime(formatter.parse(appointmentDTO.getDate()));
            request.setType(AppointmentRequest.AppointmentReqType.DOCTOR);

            Doctor get_doctor_clinic = doctorRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
            get_doctor_clinic.getClinic().getAppointmentRequests().add(request);

            clinicRepository.save(get_doctor_clinic.getClinic());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMedicalRecord(MedicalRecordDTO recordToUpdate) {
        Optional<MedicalRecord> recordOptional = medicalRecordRepository.findById(recordToUpdate.getId());
        if(recordOptional.isPresent()){
            MedicalRecord record = recordOptional.get();
            record.setReports(recordToUpdate.getReports());
            record.setBloodType(recordToUpdate.getBloodType());
            record.setHeight(recordToUpdate.getHeight());
            record.setLeftEye(recordToUpdate.getLeftEye());
            record.setRightEye(recordToUpdate.getRightEye());
            record.setWeight(recordToUpdate.getWeight());
            medicalRecordRepository.save(record);
            return true;
        }
        return false;
    }

    public List<Doctor> gedDoctorsByExType(Integer ex_type_id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Integer> clinicId = clinicAdminRepository.findClinicIdByAdminId(user.getId());
        if(clinicId.isPresent()){
            return doctorRepository.findAllByClinicIdAndExaminationTypeIdAndActivityTrue(clinicId.get(), ex_type_id);
        }
        return null;
    }
}
