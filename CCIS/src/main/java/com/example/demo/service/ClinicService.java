package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.dto.ClinicDTO;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
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
    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;
    @Autowired
    private PriceListItemRepository priceListItemRepository;
    @Autowired
    private PriceListRepository priceListRepository;


    public Clinic findById(Integer id) {
        return clinicRepository.findById(id).orElse(null);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public boolean gradeClinic(Clinic clinic, float newRating) {
        clinic.setRating((clinic.getRating() + newRating) / 2);
        clinic = clinicRepository.save(clinic);
        return clinic != null;
    }

    public Clinic addClinic(ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = new Clinic(clinicDTO.getName(), clinicDTO.getDescription(), clinicDTO.getAddress());
        clinicRepository.save(clinic);
        return clinic;
    }

    public PriceList getPriceList(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //ClinicAdmin admin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
        Optional<ClinicAdmin> ad = clinicAdminRepository.findById(user.getId());
        ClinicAdmin admin = ad.get();
        if(admin != null){
            Clinic clinic = admin.getClinic();
            return clinic.getPriceList();
        }
        return null;
    }

    public PriceListItem addPriceListItem(PriceListItem priceListItem){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<ClinicAdmin> ad = clinicAdminRepository.findById(user.getId());

        if(this.DoesExTypeNameExist(priceListItem.getExaminationType().getName(),ad.get()))
            return null;

        ExaminationType examinationType = examinationTypeRepository.findByName(priceListItem.getExaminationType().getName());
        priceListItem.setExaminationType(examinationType);
        if(ad.isPresent()){
            ClinicAdmin admin = clinicAdminRepository.findByEmailAndFetchClinicEagerly(user.getEmail());
            Clinic clinic = admin.getClinic();
            PriceList priceList = clinic.getPriceList();
            if(priceList.getId() == null){
                priceList = new PriceList();
            }
            priceList.getItems().add(priceListItem);
            priceList.setClinic(admin.getClinic());
            priceListRepository.save(priceList);
            return priceListItemRepository.save(priceListItem);
        }
        return null;
    }

    public PriceListItem updatePriceListItem(PriceListItem priceListItem){
        Optional<PriceListItem> findItem = priceListItemRepository.findById(priceListItem.getId());
        if(findItem.isPresent()){
            PriceListItem updateItem = findItem.get();
            updateItem.setPrice(priceListItem.getPrice());
           // updateItem.setExaminationType(priceListItem.getExaminationType());
            return priceListItemRepository.save(updateItem);
        }
        return null;
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

    public boolean DoesExTypeNameExist(String name, ClinicAdmin admin){
        if(admin.getClinic().getPriceList() == null)
        {
            admin.getClinic().setPriceList(new PriceList());
            return false;
        }
        List<PriceListItem> priceListItems = (List<PriceListItem>) admin.getClinic().getPriceList().getItems();
        Optional<PriceListItem> it = priceListItems.stream()
                .filter(item -> item.getExaminationType().getName().equals(name))
                .findAny();
        if(it.isPresent())
            return true;
        return false;
    }
}