package com.example.nikhil.vihaan;

public class MyAppointments_data {

    String doc,fees;

    public MyAppointments_data(String doc, String fees, Time time) {
        this.doc = doc;
        this.fees = fees;
        this.time = time;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    Time time;
}
