package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "clinic_center_admins")
public class ClinicCenterAdmin extends User {
    public ClinicCenterAdmin() {
    }

    public ClinicCenterAdmin(Integer id, String username, String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, List<Authority> authorities) {
        super(id, username, email, password, name, lastName, address, city, country, phone, socialSecurityNumber, null, authorities);
    }
}