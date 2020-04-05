package model;
import java.util.*;

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