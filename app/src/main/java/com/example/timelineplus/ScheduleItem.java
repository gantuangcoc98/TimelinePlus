package com.example.timelineplus;

import java.util.ArrayList;

public class ScheduleItem {
    private String scheduleTitle = "Schedule Title: ";
    private String setScheduleTitle;
    private String dateTitle = "Date: ";
    private String setDate;
    private String timeTitle = "Time: ";
    private String setTime;
    private String setNotes;

    public ScheduleItem() {}

    public ScheduleItem(String setScheduleTitle, String setDate, String setTime, String setNotes) {
        this.setScheduleTitle = setScheduleTitle;
        this.setDate = setDate;
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
