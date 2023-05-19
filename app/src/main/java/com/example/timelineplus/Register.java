package com.example.timelineplus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPass;
    private DatabaseReference usersRef;
    private String userID;
    private ArrayList<String> joinedSchedules;
    private ArrayList<String> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        // Initialize Firebase Realtime Database reference
        usersRef = FirebaseDatabase.getInstance().getReference("users");


        // Get reference for the input fields and button
        etFirstName = findViewById(R.id.firstname);
        etLastName = findViewById(R.id.lastname);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPass = findViewById(R.id.confirmPassword);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);


        // Implement the code if the user clicks the button: Create Account
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                joinedSchedules = new ArrayList<>();
                joinedSchedules.add("");
                friends = new ArrayList<>();
                friends.add("");

                if (passwordConfirmed()) { // If the user inputted exact same password for confirm password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Get the id of the current registered user
                                FirebaseUser userRegistered = task.getResult().getUser();
                                userID = userRegistered.getUid();


                                // Create new instance of user
                                User user = new User(email, password, firstName, lastName);


                                // Store the generated User data to the Firebase Realtime Database
                                usersRef.child(userID).setValue(user, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if (error == null) { // If there is no error, proceed to Login Page

                                            // Insert a new user to be added in the People data in the Firebase
                                            insertNewPeople(user);


                                            // Go to login page directly
                                            Intent login = new Intent(Register.this, MainActivity.class);
                                            startActivity(login);

                                            System.out.println("Successfully added new data to the users");
                                            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                        } else { // There is error
                                            System.out.println("Failed to add data to the Firebase Realtime Database");
                                            Toast.makeText(Register.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                System.out.println("Successfully created authenticated user in Firebase Database");
                            } else {
                                Toast.makeText(Register.this, "Email already exist!", Toast.LENGTH_SHORT).show();
                                System.out.println("Failed creating an authorized email and password in the Firebase");
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean passwordConfirmed() {
        if (etPassword.getText().toString().equals(etConfirmPass.getText().toString())) {
            System.out.println("Actual Password is equal to the Confirm Password");
            return true;
        }
        Toast.makeText(Register.this, "Confirm Password and actual Password is not the same!", Toast.LENGTH_SHORT);
        return false;
    }

    // This method will insert a new data to the People database with an objcect FriendItem
    private void insertNewPeople(User user) {
        DatabaseReference peopleRef = FirebaseDatabase.getInstance().getReference("people");

        Person person = new Person(user.getFirstName() + " " + user.getLastName(), joinedSchedules, friends);


        // This line of code will add a new data to the People data
        peopleRef.child(userID).setValue(person, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) { // If there is no error
                    System.out.println("Successfully added new data to the people");
                } else { // There is error
                    System.out.println("Failed to add data to the People in Firebase");
                }
            }
        });
    }
}