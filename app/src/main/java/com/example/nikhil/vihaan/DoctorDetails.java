package com.example.nikhil.vihaan;

public class DoctorDetails {
    String name,qualification,address;
    int messageCharge, appointmentCharge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMessageCharge() {
        return messageCharge;
    }

    public void setMessageCharge(int messageCharge) {
        this.messageCharge = messageCharge;
    }

    public int getAppointmentCharge() {
        return appointmentCharge;
    }

    public void setAppointmentCharge(int appointmentCharge) {
        this.appointmentCharge = appointmentCharge;
    }
}
