package com.example.nikhil.vihaan;

public class Time {

    private int hour, minutes, day, month, year;

    public Time(int hour, int minutes, int day, int month, int year) {
        this.hour = hour;
        this.minutes = minutes;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

//    public long getTimeStamp(){
//        return Long.parseLong(java.sql.Timestamp.valueOf(String.valueOf(year)+ "-" + String.valueOf(month) + "-" + String.valueOf(day)
//                + " " + String.valueOf(hour) + ":" + String.valueOf(minutes) + ":" + String.valueOf(0)).toString());
//    }
}
