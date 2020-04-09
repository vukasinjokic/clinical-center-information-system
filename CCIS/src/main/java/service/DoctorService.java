package service;

import model.Doctor;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;

public class DoctorService {

    HashSet<Doctor> doctors = new HashSet<Doctor>();

    public boolean addDoctor(Doctor doctor){
        doctors.add(doctor);
        return true;
    }

    public HashSet<Doctor> getDoctors() {
        return doctors;
    }

    public boolean deleteDoctor(String id){
        Optional<Doctor> doctor = selectDoctorById(id);
        if(doctor.isPresent())
        {
            doctors.remove(doctor);
            return true;
        }
        return false;
    }

    public Optional<Doctor> selectDoctorById(String id){
        return doctors.stream().filter(doctor -> doctor.getEmail().equals(id))
                .findFirst();
    }
}
