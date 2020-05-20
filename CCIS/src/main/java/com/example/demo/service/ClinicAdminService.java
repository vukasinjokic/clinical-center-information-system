package com.example.demo.service;

import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.Repository.MedicalStaffRepository;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.model.*;
import com.example.demo.useful_beans.DeclineVacRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private EmailService emailService;

    private final ModelMapper modelMapper = new ModelMapper();


    public ClinicAdmin getClinicAdminByEmail(String email){
        return clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
    }

    public List<MedicalStaffRequest> getRequests(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
        return (List<MedicalStaffRequest>)
                clinicAdmin.getClinic().getMedicalStaffRequests();

    }

    public boolean declineRequest(DeclineVacRequest declineVacRequest){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
        Optional<MedicalStaffRequest> check_request = medicalStaffRepository.findById(declineVacRequest.id);

        if(check_request.isPresent()){
            emailService.alertStaffForVacation(user, check_request.get(),declineVacRequest.description);
            clinicAdmin.getClinic().getMedicalStaffRequests().remove(check_request.get());
            medicalStaffRepository.deleteById(check_request.get().getId());
            return true;
        }
        return false;
    }

    public boolean AcceptRequest(Integer id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<MedicalStaffRequest> check_request = medicalStaffRepository.findById(id);
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());

        if(check_request.isPresent()){
//            MedicalStaff medicalStaff = medicalStaffRepository.findByEmail(check_request.get().getMedicalStaff_email());
//            medicalStaff.getCalendar().getDates();
            emailService.alertStaffForVacation(user, check_request.get(),"");
            clinicAdmin.getClinic().getMedicalStaffRequests().remove(check_request.get());
            medicalStaffRepository.deleteById(check_request.get().getId());
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
}
