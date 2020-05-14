package com.example.demo.api;


import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.ExaminationType;
import com.example.demo.service.ExaminationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/ex_type")
@RestController
public class ExaminationTypeController {

    private final ExaminationTypeService examinationTypeService;

    public ExaminationTypeController(ExaminationTypeService examinationTypeService) {
        this.examinationTypeService = examinationTypeService;
    }

    @GetMapping("/getTypes")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public List<ExaminationType> getExaminationType(){
        return examinationTypeService.findAllTypes();
    }

    @DeleteMapping(path="/delete/{type_name}")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<Void> deleteExaminationType(@PathVariable("type_name") String name){
        if(examinationTypeService.removeType(name)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/updateType")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<ExaminationType> updateType(@RequestBody ExaminationType examinationType){
        ExaminationType ex_type = examinationTypeService.updateType(examinationType);
        if(ex_type == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(ex_type, HttpStatus.OK);
    }

    @PostMapping("/addType")
    @PreAuthorize("hasAnyRole('CLINIC_ADMIN')")
    public ResponseEntity<ExaminationType> addType(@RequestBody ExaminationType examinationType){
        ExaminationType savedType = examinationTypeService.saveType(examinationType);

        if(savedType != null)
            return new ResponseEntity<ExaminationType>(savedType, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
