package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MedicalStaffRequest {

    public enum RequestType{Vacation, Leave};

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fromDate", nullable = false)
    private Date fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "toDate", nullable = false)
    private Date toDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_req_type", length = 8)
    private RequestType type;

    @Column(name = "medicalStaffName", nullable = false)
    private String medicalStaffName;

    @Column(name = "medicalStaffLastName", nullable = false)
    private String medicalStaffLastName;

    @Column(name = "medicalStaff", nullable = false)
    private String medicalStaff_email;


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getMedicalStaff_email() {
        return medicalStaff_email;
    }

    public void setMedicalStaff_email(String medicalStaff_email) {
        this.medicalStaff_email = medicalStaff_email;
    }

    public String getMedicalStaffName() {
        return medicalStaffName;
    }

    public void setMedicalStaffName(String medicalStaffName) {
        this.medicalStaffName = medicalStaffName;
    }

    public String getMedicalStaffLastName() {
        return medicalStaffLastName;
    }

    public void setMedicalStaffLastName(String medicalStaffLastName) {
        this.medicalStaffLastName = medicalStaffLastName;
    }

    public Integer getId() {
        return id;
    }
}
