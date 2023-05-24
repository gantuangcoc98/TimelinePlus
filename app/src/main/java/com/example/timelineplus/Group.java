package com.example.timelineplus;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private String owner;
    private ArrayList<Person> members;

    public Group(String groupName, String owner, ArrayList<Person> members) {
        this.groupName = groupName;
        this.owner = owner;
        this.members = members;
    }

    public Group(){}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Person> members) {
        this.members = members;
    }
}
