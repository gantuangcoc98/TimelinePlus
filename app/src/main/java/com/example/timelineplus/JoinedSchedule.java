package com.example.timelineplus;

public class JoinedSchedule {
    ScheduleItem scheduleItem;

    public JoinedSchedule(ScheduleItem scheduleItem) {
        this.scheduleItem = scheduleItem;
    }

    public JoinedSchedule(){}

    public ScheduleItem getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(ScheduleItem scheduleItem) {
        this.scheduleItem = scheduleItem;
    }
}
