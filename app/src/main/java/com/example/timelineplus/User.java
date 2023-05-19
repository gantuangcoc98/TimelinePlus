package com.example.timelineplus;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {}

    @PropertyName("firstName")
    public String getFirstName() {
        return firstName;
    }

    @PropertyName("lastName")
    public String getLastName() {
        return lastName;
    }

    @PropertyName("email")
    public String getEmail() {
        return email;
    }

    @PropertyName("password")
    public String getPassword() {
        return password;
    }

}
