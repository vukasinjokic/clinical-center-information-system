package com.example.demo.service;

import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.Repository.ExaminationTypeRepository;
import com.example.demo.model.Appointment;
import com.example.demo.model.ExaminationType;
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

    public List<ExaminationType> findAllTypes(){
        //validacija
        List<ExaminationType> types = examinationTypeRepository.findAll();
        types.forEach(type -> type.setDuration(type.getDuration()/3600000));
        return types;
    }
    public boolean removeType(String name){
        ExaminationType examinationType = examinationTypeRepository.findByName(name);

        if(examinationType != null){
            List<Appointment> appointments = appointmentRepository.findByExaminationTypeId(examinationType.getId());
            if(appointments.size() == 0){
                examinationTypeRepository.deleteById(examinationType.getId());
                return true;
            }
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
        if(doesExist.get(0).getId() == name.getId()){
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
