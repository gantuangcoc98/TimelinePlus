package com.example.timelineplus;

public class Profile {

    private String setFirstName;
    private String setLastName;
    private String setEmail;

    public Profile() {
    }

    public Profile(String setFirstName, String setLastName, String setEmail) {
        this.setFirstName = setFirstName;
        this.setLastName = setLastName;
        this.setEmail = setEmail;
    }

    public String getSetFirstName() {
        return setFirstName;
    }

    public void setSetFirstName(String setFirstName) {
        this.setFirstName = setFirstName;
    }

    public String getSetLastName() {
        return setLastName;
    }

    public void setSetLastName(String setLastName) {
        this.setLastName = setLastName;
    }

    public String getSetEmail() {
        return setEmail;
    }

    public void setSetEmail(String setEmail) {
        this.setEmail = setEmail;
    }
}
