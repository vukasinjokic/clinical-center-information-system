package com.example.demo.model;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(name = "patient_ids", joinColumns = @JoinColumn(name = "rating_id"))
    @Column(name = "patient_id")
    private List<Integer> patientIds;

    @ElementCollection
    @CollectionTable(name = "grades", joinColumns = @JoinColumn(name = "rating_id"))
    @Column(name = "grade")
    private List<Integer> grades;

    @Column(name = "average_grade", unique = false, nullable = false)
    private Float averageGrade;

    public Rating() {
    }

    public Rating(List<Integer> patientIds, List<Integer> grades, Float averageGrade) {
        this.patientIds = patientIds;
        this.grades = grades;
        this.averageGrade = averageGrade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<Integer> patientIds) {
        this.patientIds = patientIds;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public Float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Float averageGrade) {
        this.averageGrade = averageGrade;
    }
}
