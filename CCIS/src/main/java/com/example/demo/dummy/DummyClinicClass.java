package com.example.demo.dummy;

import com.example.demo.model.Clinic;
import com.example.demo.model.Doctor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// TODO: Delete when database problems are resolved
public class DummyClinicClass {
    public static Clinic getClinic(int number) {
        Clinic clinic =  new Clinic();
        clinic.setName("Klinika " + number);
        clinic.setAddress("Adresa " + number);
        clinic.setDescription("Opis " + number);
        clinic.setPriceList("Cenovnik " + number);
        clinic.setRating(5.0f);

        Doctor doctor1 = new Doctor();
        doctor1.setEmail("mejl: klinika " + number);
        doctor1.setPassword("sifra: klinika " + number);
        doctor1.setFirstName("Ime: klinika " + number);
        doctor1.setLastName("Prezime: klinika " + number);
        doctor1.setAddress("Adresa: klinika " + number);
        doctor1.setCity("Grad: klinika " + number);
        doctor1.setCountry("Drzava: klinika " + number);
        doctor1.setPhoneNumber("Telefon: klinika " + number);
        doctor1.setSocialSecurityNumber("LBO: klinika " + number);
        doctor1.setRating(5.0f);

        Doctor doctor2 = new Doctor();
        doctor2.setEmail("mejl2: klinika " + number);
        doctor2.setPassword("sifra2: klinika " + number);
        doctor2.setFirstName("Ime2: klinika " + number);
        doctor2.setLastName("Prezime2: klinika " + number);
        doctor2.setAddress("Adresa2: klinika " + number);
        doctor2.setCity("Grad2: klinika " + number);
        doctor2.setCountry("Drzava2: klinika " + number);
        doctor2.setPhoneNumber("Telefon2: klinika " + number);
        doctor2.setSocialSecurityNumber("LBO2: klinika " + number);
        doctor2.setRating(5.0f);

        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);

        clinic.setDoctors(doctors);
        return clinic;
    }

    public static Set<Clinic> getAllClinics() {
        Set<Clinic> clinics = new HashSet<>();
        clinics.add(getClinic(1));
        clinics.add(getClinic(2));
        clinics.add(getClinic(3));
        return clinics;
    }
}
