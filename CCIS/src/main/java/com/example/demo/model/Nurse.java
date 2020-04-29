package com.example.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nurses")
public class Nurse extends MedicalStaff {

    public Nurse() {
    }

    public Nurse(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
        super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
    }

    public Nurse(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar, Clinic clinic) {
        super(id ,email, password, name, lastName, address, city, country, phone, socialSecurityNumber, calendar,clinic);
    }
}