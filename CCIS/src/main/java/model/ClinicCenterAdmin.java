package model;

import java.util.*;

public class ClinicCenterAdmin extends User {
    public ClinicCenterAdmin() {
    }

    public ClinicCenterAdmin(String email, String password, String name, String lastName, String address, String city, String country, String phone, String socialSecurityNumber) {
        super(email, password, name, lastName, address, city, country, phone, socialSecurityNumber);
    }
}