package com.example.demo.service;

import com.example.demo.Repository.ClinicAdminRepository;
import com.example.demo.Repository.ClinicRepository;
import com.example.demo.Repository.CodeBookRepository;
import com.example.demo.dto.ClinicDTO;
import com.example.demo.model.Clinic;
import com.example.demo.model.ClinicAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClinicService {
    private static Set<Clinic> clinics;

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private CodeBookRepository codeBookRepository;


    public Clinic findById(Integer id) {
        return clinicRepository.findById(id).orElse(null);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Clinic addClinic(ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = new Clinic(clinicDTO.getName(), clinicDTO.getDescription(), clinicDTO.getAddress());
        clinicRepository.save(clinic);
        return clinic;
    }

    public Clinic updateClinic(ClinicDTO clinicDTO){
        Optional<Clinic> find_clinic = clinicRepository.findById(Integer.parseInt(clinicDTO.getId()));
        //validiraj ime
        List<Clinic> clinics = getAllClinics();
        Optional<Clinic> clinicOptional = clinics.stream().filter(clinic -> clinic.getName().equals(clinicDTO.getName())).findFirst();
        if(find_clinic.isPresent()){
            Clinic clinic = find_clinic.get();
            if(!clinicDTO.equals(clinic.getName())){
                if(clinicOptional.isPresent())
                    return null;
            }

            clinic.setName(clinicDTO.getName());
            clinic.setAddress(clinicDTO.getAddress());
            clinic.setDescription(clinicDTO.getDescription());
            return clinicRepository.save(clinic);
        }
        return null;
    }

    public Clinic findByAdminEmail(String email){
        ClinicAdmin admin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(email);
        return this.findById(admin.getClinic().getId());
    }
}