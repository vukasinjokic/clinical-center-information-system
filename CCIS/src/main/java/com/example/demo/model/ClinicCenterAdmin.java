package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clinic_center_admins")
public class ClinicCenterAdmin extends User {
    public ClinicCenterAdmin() {
    }

    public ClinicCenterAdmin(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
        super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
    }
}