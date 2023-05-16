package com.example.timelineplus;

public class ScheduleItem {
    private String scheduleTitle;
    private String setScheduleTitle;
    private String dateTitle;
    private String setDate;
    private String timeTitle;
    private String setTime;

    public ScheduleItem(String scheduleTitle, String setScheduleTitle, String dateTitle, String setDate, String timeTitle, String setTime) {
        this.scheduleTitle = scheduleTitle;
        this.setScheduleTitle = setScheduleTitle;
        this.dateTitle = dateTitle;
        this.setDate = setDate;
        this.timeTitle = timeTitle;
        this.setTime = setTime;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getSetScheduleTitle() {
        return setScheduleTitle;
    }

    public void setSetScheduleTitle(String setScheduleTitle) {
        this.setScheduleTitle = setScheduleTitle;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public void setDateTitle(String dateTitle) {
        this.dateTitle = dateTitle;
    }

    public String getSetDate() {
        return setDate;
    }

    public void setSetDate(String setDate) {
        this.setDate = setDate;
    }

    public String getTimeTitle() {
        return timeTitle;
    }

    public void setTimeTitle(String timeTitle) {
        this.timeTitle = timeTitle;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

}
