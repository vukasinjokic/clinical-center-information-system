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
    private List<Float> grades;

    @Column(name = "average_grade", unique = false, nullable = false)
    private Float averageGrade;

    public Rating() {
    }

    public Rating(List<Integer> patientIds, List<Float> grades, Float averageGrade) {
        this.patientIds = patientIds;
        this.grades = grades;
        this.averageGrade = averageGrade;
    }

    public void calculateAverageGrade() {
        Float sum = 0f;
        for (Float grade : grades)
            sum += grade;
        sum /= grades.size();
        sum = (float) Math.round(sum * 100);
        sum /= 100;
        averageGrade = sum;
    }

    public void setGrade(Integer patientId, Float newGrade) {
        int patientIndex = patientIds.indexOf(patientId);
        if (patientIndex == -1) {
            patientIds.add(patientId);
            grades.add(newGrade);
        } else {
            grades.set(patientIndex, newGrade);
        }
        calculateAverageGrade();
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

    public List<Float> getGrades() {
        return grades;
    }

    public void setGrades(List<Float> grades) {
        this.grades = grades;
    }

    public Float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Float averageGrade) {
        this.averageGrade = averageGrade;
    }
}
