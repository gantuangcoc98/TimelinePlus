package com.example.timelineplus;

public class Friends {
    private String friendID;
    private String name;

    public Friends(String name) {
        this.name = name;
    }

    public Friends() {}

    public String getFriendID() {
        return friendID;
    }

    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
