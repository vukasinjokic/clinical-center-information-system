package com.example.demo.useful_beans;

public class AppointmentToReservePatient {
    private String doctorId;
    private String clinicId;
    private String appointmentTime;
    private String patientEmail;
    private Integer appointmentId;
    private Float price;

    public AppointmentToReservePatient() {

    }

    public AppointmentToReservePatient(String doctorId, String clinicId, String appointmentTime, String patientEmail, Integer appointmentId, Float price) {
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.appointmentTime = appointmentTime;
        this.patientEmail = patientEmail;
        this.appointmentId = appointmentId;
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }
}
