package com.example.timelineplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import java.sql.*;

public class MainActivity extends AppCompatActivity {
    TextView signup;
    Button btnLogin;
    EditText etEmail, etPassword;
    TextView incorrectInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = (TextView) findViewById(R.id.signup);
        btnLogin = findViewById(R.id.login);
        incorrectInput = (TextView) findViewById(R.id.IncorrectInput);
        incorrectInput.setVisibility(View.GONE);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(MainActivity.this, Register.class);
                startActivity(signup);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Initialize to Sign in using the authenticated user in Firebase Authentication
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { // If the user successfully inputted correct email and password
                            Intent home = new Intent(MainActivity.this, Home.class);
                            startActivity(home);

                            System.out.println("Successfully login with authenticated user in Firebase Database");
                        } else { // If the user inputted wrong email or password
                            incorrectInput.setVisibility(View.VISIBLE);
                            System.out.println("Login failed due to incorrect username or password");
                        }
                    }
                });
            }
        });
    }

    private boolean emptyFields() {
        return etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty();
    }

}