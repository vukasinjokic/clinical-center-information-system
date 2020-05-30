package com.example.demo.model;

import org.hibernate.annotations.Type;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection(fetch = LAZY)
    @CollectionTable(name = "prescriptions_contents",
            joinColumns = {@JoinColumn(name = "prescription_id")})
    @MapKeyColumn(name = "medication")
    @Column(name = "description")
    private Map<String, String> content;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @Type(type = "true_false")
    private boolean isVerified;

    public Prescription() {
    }

    public Prescription(Integer id, Map<String, String> content, boolean isVerified, MedicalRecord medicalRecord) {
        this.id = id;
        this.content = content;
        this.isVerified = isVerified;
        this.medicalRecord = medicalRecord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
