package com.example.timelineplus;

import java.util.ArrayList;

public class Person {
    private String personID;
    private String name;
    public Person(String name) {
        this.name = name;
    }

    public Person() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

}
