package com.example.timelineplus;

public class Request {
    private String requestID;
    private String name;

    public Request(String name) {
        this.name = name;
    }

    public Request() {}

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
