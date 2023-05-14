package com.example.timelineplus;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    private String email;
    private String pass;
    private String firstName;
    private String lastName;

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public User(String email, String pass, String firstName, String lastName) {
        this.email = email;
        this.pass = pass;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {}

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("users");

    public void registerUser(User user) {
        String userId = usersRef.push().getKey();
        usersRef.child(userId).setValue(user);
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}
