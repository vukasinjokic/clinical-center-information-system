package com.example.demo.service;

import com.example.demo.Repository.*;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExaminationTypeService {

    @Autowired
    private ExaminationTypeRepository examinationTypeRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PriceListItemRepository priceListItemRepository;
    @Autowired
    private PriceListRepository priceListRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<ExaminationType> findAllTypes(){
        //validacija
        List<ExaminationType> types = examinationTypeRepository.findAll();
        //types.forEach(type -> type.setDuration(type.getDuration()));
        return types;
    }
    public boolean removeType(String name){
        ExaminationType examinationType = examinationTypeRepository.findByName(name);
        List<Doctor> doctors = doctorRepository.findByExaminationTypeId(examinationType.getId());
        if(doctors.size() != 0)
            return false;

        List<PriceList> priceLists = priceListRepository.findAll();
        if(examinationType != null){
            Optional<PriceListItem> item = priceListItemRepository.findByExaminationTypeId(examinationType.getId());
            if(item.isPresent()){
                PriceListItem listItem = item.get();
                for(PriceList priceList : priceLists){
                    priceList.setItems(priceList.getItems().stream().filter(priceListItem -> !priceListItem.getId().equals(listItem.getId())).collect(Collectors.toList()));
                    priceListRepository.save(priceList);
                    priceListItemRepository.delete(listItem);
                }
//                priceListItemRepository.delete(listItem);
            }
            examinationTypeRepository.deleteById(examinationType.getId());
            return true;

        }
        return false;
    }
    public ExaminationType updateType(ExaminationType forUpdate){
        Optional<ExaminationType> findType = examinationTypeRepository.findById(forUpdate.getId());

        if(findType.isPresent()){
            ExaminationType examinationType = findType.get();
            List<Appointment> appointments = appointmentRepository.findByExaminationTypeId(examinationType.getId());
            if(appointments.size() == 0){
                if(!this.validateUsing(forUpdate)){
                    examinationType.setName(forUpdate.getName());
                    examinationType.setDuration(forUpdate.getDuration());
                    examinationType = examinationTypeRepository.save(examinationType);
                    return  examinationType;
                }
                return null;
            }
        }
        return null;
    }

    public ExaminationType saveType(ExaminationType examinationType){
        ExaminationType doesExist = examinationTypeRepository.findByName(examinationType.getName());
        if(doesExist == null){
            if(!validateName(examinationType.getName()))
                return null;

            ExaminationType ex_type = new ExaminationType(examinationType.getName(),examinationType.getDuration());
            examinationTypeRepository.save(ex_type);
            return ex_type;
        }
        return null;
    }

    public boolean validateUsing(ExaminationType name){
        List<ExaminationType> examinationTypes = examinationTypeRepository.findAll();
        List<ExaminationType> doesExist = examinationTypes.stream()
                .filter(t -> t.getName().equals(name.getName()))
                .collect(Collectors.toList());
        if(doesExist.size() == 0)
            return false;
        if(doesExist.get(0).getId().equals(name.getId())){
            return false;
        }
        return true;
    }

    public boolean validateName(String name){
        ExaminationType examinationType = examinationTypeRepository.findByName(name);
        if(examinationType == null)
            return true;
        return false;
    }

}
