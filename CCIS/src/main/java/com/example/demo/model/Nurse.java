package com.example.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nurses")
@AttributeOverride(name = "id", column = @Column(name = "n_id", columnDefinition = "serial"))
public class Nurse extends MedicalStaff {

    public Nurse() {
    }

    public Nurse(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
        super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
    }

    public Nurse(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber, Calendar calendar) {
        super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber, calendar);
    }
}