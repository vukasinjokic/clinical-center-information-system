package api;

import model.Doctor;
import org.springframework.web.bind.annotation.*;
import service.DoctorService;

import java.util.HashSet;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
        return "Uspesno dodat";
    }
    @GetMapping("/getDoctors")
    public HashSet<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }

    @DeleteMapping(path = "{id}")
    public void deleteDoctor(@PathVariable("id") String id){
        doctorService.deleteDoctor(id);
    }
}
