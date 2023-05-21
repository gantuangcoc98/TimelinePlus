package com.example.timelineplus;

import java.util.ArrayList;

public class Person {
    private String personID;
    private String name;
    private ArrayList<String> joinedSchedules;
    private ArrayList<String> friends;
    public Person(String name, ArrayList<String> joinedSchedules, ArrayList<String> friends) {
        this.name = name;
        this.joinedSchedules = joinedSchedules;
        this.friends = friends;
    }

    public Person() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getJoinedSchedules() {
        return joinedSchedules;
    }

    public void setJoinedSchedules(ArrayList<String> joinedSchedules) {
        this.joinedSchedules = joinedSchedules;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

}
