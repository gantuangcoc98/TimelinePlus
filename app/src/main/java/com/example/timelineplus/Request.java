package com.example.timelineplus;

public class Request {
    private String userID;
    private String name;

    public Request(String name) {
        this.name = name;
    }

    public Request() {}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
