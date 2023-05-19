package com.example.timelineplus;

import java.util.ArrayList;

public class ScheduleItem {
    private String scheduleTitle = "Schedule Title: ";
    private String setScheduleTitle;
    private String dateTitle = "Date: ";
    private String setDate;
    private String locationTitle = "Location: ";
    private String setLocation;
    private String emailTitle = "Posted by: ";
    private String setEmail;
    private String timeTitle = "Time: ";
    private String setTime;
    private String setNotes;

    public ScheduleItem() {}

    public ScheduleItem(String setScheduleTitle, String setDate, String setLocation, String setEmail, String setTime, String setNotes) {
        this.setScheduleTitle = setScheduleTitle;
        this.setDate = setDate;
        this.setLocation = setLocation;
        this.setEmail = setEmail;
        this.setTime = setTime;
        this.setNotes = setNotes;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public String getSetScheduleTitle() {
        return setScheduleTitle;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public String getSetDate() {
        return setDate;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getSetLocation() {
        return setLocation;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public String getSetEmail() {
        return setEmail;
    }

    public String getTimeTitle() {
        return timeTitle;
    }

    public String getSetTime() {
        return setTime;
    }

    public String getSetNotes() {
        return setNotes;
    }
}
