package com.example.nikhil.vihaan;

public class PatientAppointment {

    private String patientName, doctorName, patientID, doctorID;
    private long appointmentTime;
    private String gender;
    private int age, confirmed;
    private String description;


    public PatientAppointment(String patientName, String doctorName, String patientID, String doctorID,
                              long appointmentTime, String gender, int age, String description, int confirmed) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentTime = appointmentTime;
        this.gender = gender;
        this.age = age;
        this.description = description;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public long getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(long appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
}
