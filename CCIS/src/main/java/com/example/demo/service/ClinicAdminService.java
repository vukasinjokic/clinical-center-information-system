package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.*;
import com.example.demo.useful_beans.AppointmentToReserve;
import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.Repository.MedicalStaffRequestRepository;
import com.example.demo.useful_beans.DeclineVacRequest;
import com.example.demo.validation.DoctorValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private MedicalStaffRequestRepository medicalStaffRequestRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;


    private final ModelMapper modelMapper = new ModelMapper();


    public ClinicAdmin getClinicAdminByEmail(String email){
        return clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
    }

    public List<ClinicAdmin> getClinicAdminsByClinicId(Integer clinicId) {
        return clinicAdminRepository.findByClinicId(clinicId);
    }

    public boolean handleReservation(AppointmentToReserve appointmentToReserve) throws InterruptedException {
        AppointmentRequest appointmentRequest = appointmentRequestRepository.findById(appointmentToReserve.getRequestId()).get();

        Integer patient_id = appointmentRequest.getPatient().getId();
        Patient patient = patientRepository.findById(patient_id).get();

        List<Doctor> doctors = doctorRepository.findAllById(appointmentToReserve.getDoctorsIds());
        if(!checkIfDoctorsAreAvailable(doctors, appointmentToReserve, appointmentRequest)){
            return false;
        }
        Doctor doctor = doctors.get(doctors.size() - 1);

        Integer room_id = Integer.parseInt(appointmentToReserve.getRoom().getId());
        Room room = roomRepository.findById(room_id).get();

        Appointment appointment;
        if(appointmentRequest.getPredefAppointment() != null){
            Integer appointment_id = appointmentRequest.getPredefAppointment().getId();
            appointment = appointmentRepository.getOne(appointment_id);
            appointment.setTime(appointmentToReserve.getReservedTime());
        }
        else{
            appointment = new Appointment(appointmentToReserve.getReservedTime(), 0, 0, doctor, room, doctor.getExaminationType(), patient, doctor.getClinic());
        }
        patient.addAppointment(appointment);
        addAppointmentToDoctors(appointment, doctors);

        room.addAppointment(appointment);
        updateDataBase(appointment, doctors, appointmentRequest);
        emailService.alertDoctorsOperation(doctors, appointment);
        emailService.alertPatientOperation(appointment);
        return true;
    }

    private boolean checkIfDoctorsAreAvailable(List<Doctor> doctors, AppointmentToReserve appointmentToReserve, AppointmentRequest appointmentRequest){
        DoctorValidation doctorValidation = new DoctorValidation();
        if(doctors.isEmpty()) return false;
        float duration = doctors.get(0).getExaminationType().getDuration();
        for(Doctor doctor : doctors){
            if(!doctorValidation.validateDoctorBusy(appointmentToReserve.getReservedTime(), duration, doctor)){
                return false;
            }
        }
        return true;
    }

    private void updateDataBase(Appointment appointment, List<Doctor> doctors, AppointmentRequest appointmentRequest ){
        appointmentRepository.save(appointment);
        doctorRepository.saveAll(doctors);
        appointmentRequestRepository.delete(appointmentRequest);
    }

    private void addAppointmentToDoctors(Appointment appointment, List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            doctor.addAppointment(appointment);
        }
    }

    public List<MedicalStaffRequest> getRequests(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
        return (List<MedicalStaffRequest>)
                clinicAdmin.getClinic().getMedicalStaffRequests();
    }

    public float getProfit(String email, String dateFrom, String dateTo) throws ParseException {
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmail(email);
        Integer clinicId = clinicAdmin.getClinic().getId();
        Date startDate = parseFromStringToDate(dateFrom);
        Date endDate = parseFromStringToDate(dateTo);
        List<Appointment> appointments = appointmentRepository.findAllByClinicIdAndTimeBetweenAndFinished(clinicId,startDate,endDate, true);

        return this.CalculateProfit(appointments);
    }

    private float CalculateProfit(List<Appointment> appointments){
        float ret = 0;
        for(Appointment ap : appointments){
            float priceWithDiscount = ap.getPrice() - ap.getPrice()*ap.getDiscount()/100;
            ret = ret + priceWithDiscount;
        }
        return ret;
    }

    public boolean declineRequest(DeclineVacRequest declineVacRequest){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
        Optional<MedicalStaffRequest> check_request = medicalStaffRequestRepository.findById(declineVacRequest.id);

        if(check_request.isPresent()){
            emailService.alertStaffForVacation(user, check_request.get(),declineVacRequest.description);
            clinicAdmin.getClinic().getMedicalStaffRequests().remove(check_request.get());
            medicalStaffRequestRepository.deleteById(check_request.get().getId());
            return true;
        }
        return false;
    }

    public boolean AcceptRequest(Integer id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<MedicalStaffRequest> check_request = medicalStaffRequestRepository.findById(id);
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());

        if(check_request.isPresent()){

            MedicalStaff medicalStaff = (MedicalStaff) userRepository.findByEmail(check_request.get().getMedicalStaff_email());
            List<Appointment> appointments = appointmentRepository.findAllByDoctorIdAndTimeBetweenAndFinishedFalse(
                medicalStaff.getId(), check_request.get().getFromDate(), check_request.get().getToDate()
            );
            if(appointments.size() != 0)
                return false;

            medicalStaff.addVacationDates(check_request.get());

            emailService.alertStaffForVacation(user, check_request.get(),"");
            clinicAdmin.getClinic().getMedicalStaffRequests().remove(check_request.get());
            medicalStaffRequestRepository.deleteById(check_request.get().getId());
            return true;
        }

        return false;
    }

    public List<DoctorDTO> getClinicDoctors(String email){
        ClinicAdmin ca = clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
        return ca.getClinic().getDoctors().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private DoctorDTO convertToDTO(Doctor doctor){
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        return doctorDTO;
    }
    private Date parseFromStringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf. parse(date);
    }
}
