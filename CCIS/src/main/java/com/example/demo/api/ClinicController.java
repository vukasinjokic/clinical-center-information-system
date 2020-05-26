package com.example.demo.api;

import com.example.demo.dto.ClinicDTO;
import com.example.demo.dto.ClinicsDTO;
import com.example.demo.dto.PriceListDTO;
import com.example.demo.model.Clinic;
import com.example.demo.model.PriceList;
import com.example.demo.model.PriceListItem;
import com.example.demo.model.User;
import com.example.demo.service.ClinicService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clinics")
@RestController
public class ClinicController {

    private final ClinicService clinicService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PATIENT','CLINIC_CENTER_ADMIN')")
    public List<ClinicsDTO> getAllClinics() {
        List<Clinic> clinics = clinicService.getAllClinics();
//        List<ClinicDTO> clinicDTOs = new ArrayList<>(clinics.size());
//
//        for (int i = 0; i < clinics.size(); i++) {
//            clinicDTOs.add(convertToDTO(clinics.get(i)));
//        }
//
//        return clinicDTOs;

        return clinics.stream()
                .map(this::convertToClinicsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/getClinic")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<ClinicDTO> getClinic(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Clinic clinic = clinicService.findByAdminEmail(user.getEmail());
        if(clinic != null)
            return new ResponseEntity<>(modelMapper.map(clinic,ClinicDTO.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getPriceList")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<PriceListDTO> getPriceList(){
        PriceList priceList = clinicService.getPriceList();
        if(priceList != null)
            return new ResponseEntity<>(new PriceListDTO(priceList), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addPriceListItem")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<PriceListItem> addPriceListItem(@RequestBody PriceListItem priceListItem){
        PriceListItem item = clinicService.addPriceListItem(priceListItem);
        if(item!= null){
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updatePriceListItem")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<PriceListItem> updatePriceListItem(@RequestBody PriceListItem priceListItem){
        PriceListItem item = clinicService.updatePriceListItem(priceListItem);
        if(item != null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path="/updateClinic", consumes = "application/json")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<ClinicDTO> updateClinic(@RequestBody ClinicDTO clinicDTO){
        Clinic clinic = clinicService.updateClinic(clinicDTO);
        if(clinic != null){
            return new ResponseEntity<>(modelMapper.map(clinic,ClinicDTO.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('PATIENT', 'CLINIC_CENTER_ADMIN')")
    public ClinicDTO findById(@PathVariable("id") Integer id) {
        Clinic clinic = clinicService.findById(id);
        ClinicDTO clinicDTO = modelMapper.map(clinic, ClinicDTO.class);
        clinicDTO.setDTOFields(clinic);
        return clinicDTO;
    }

    @PostMapping(path = "/addClinic", consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity<ClinicDTO> addClinic(@RequestBody ClinicDTO clinicDTO) throws ParseException{
        Clinic clinic = clinicService.addClinic(clinicDTO);
        return new ResponseEntity<ClinicDTO>(convertToClinicDTO(clinic), HttpStatus.CREATED);
    }

    private ClinicDTO convertToClinicDTO(Clinic clinic){
        ClinicDTO clinicDTO = modelMapper.map(clinic, ClinicDTO.class);
        clinicDTO.setDTOFields(clinic);
        return clinicDTO;
    }

    private ClinicsDTO convertToClinicsDTO(Clinic clinic){
        ClinicsDTO clinicsDTO = modelMapper.map(clinic, ClinicsDTO.class);
        clinicsDTO.setDTOFields(clinic);
        return clinicsDTO;
    }
}