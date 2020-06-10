package com.example.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "nurses")
public class Nurse extends MedicalStaff {

    public Nurse() {
    }

    public Nurse(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, List<Authority> authorities) {
        super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, authorities);
    }

    public Nurse(Integer id, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar, Clinic clinic, List<Authority> authorities) {
        super(id, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, calendar, clinic, authorities);
    }
}