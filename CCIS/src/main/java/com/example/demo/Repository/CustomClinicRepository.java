package com.example.demo.Repository;

import com.example.demo.dummy.DummyClinicClass;
import com.example.demo.model.Clinic;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("CustomClinicRepository")
public class CustomClinicRepository implements ClinicRepository {
    @Override
    public Clinic findById(Integer id) {
        return DummyClinicClass.getClinic(1);
    }

    @Override
    public Set<Clinic> getAllClinics() {
        return DummyClinicClass.getAllClinics();
    }
}
