package com.example.timelineplus;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

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
