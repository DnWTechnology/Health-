package com.example.nikhil.vihaan;

public class PatientSigns {

    private int systolic;
    private int diastolic;
    private int heartRate;
    private int oxygenSaturation;
    private int respirationRate;

    public PatientSigns() {}
    public PatientSigns(int systolic, int diastolic, int heartRate, int oxygenSaturation, int respirationRate) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heartRate;
        this.oxygenSaturation = oxygenSaturation;
        this.respirationRate = respirationRate;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getOxygenSaturation() {
        return oxygenSaturation;
    }

    public void setOxygenSaturation(int oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
    }

    public int getRespirationRate() {
        return respirationRate;
    }

    public void setRespirationRate(int respirationRate) {
        this.respirationRate = respirationRate;
    }
}
