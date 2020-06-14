package com.example.demo.service;

import com.example.demo.Repository.ClinicRepository;
import com.example.demo.Repository.PerscriptionRepository;
import com.example.demo.model.Clinic;
import com.example.demo.model.Nurse;
import com.example.demo.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PerscriptionService {

    @Autowired
    private PerscriptionRepository perscriptionRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Transactional(readOnly = false)
    public boolean handleAcceptingPerscription(Integer id) {
        Prescription prescription = perscriptionRepository.findById(id).get();
        if(prescription != null){
            Nurse nurse = (Nurse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            prescription.setNurse(nurse);
            prescription.setVerified(true);
            perscriptionRepository.save(prescription);
            return true;
        }
        return false;
    }

    public boolean handleDeletePerscription(Integer id) {
        Optional<Prescription> prescriptionOptional = perscriptionRepository.findById(id);
        if(prescriptionOptional.isPresent()){
            Nurse nurse = (Nurse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Clinic clinic  = clinicRepository.findById(nurse.getClinic().getId()).get();
            clinic.removePersciptionById(id);
            clinicRepository.save(clinic);
            perscriptionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Prescription> getClinicsNotVerifiedPerscriptions() {
        Nurse nurse = (Nurse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return perscriptionRepository.getClinicsNotVerifiedPerscriptions(nurse.getClinic().getId());
    }
}
