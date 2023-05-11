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

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

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
        incorrectInput.setVisibility(View.INVISIBLE);
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
                if (etEmail.getText().toString().equals("gantuangcoc98") && etPassword.getText().toString().equals("hello.world")) {
                    Intent login = new Intent(MainActivity.this, Home.class);
                    startActivity(login);
                } else if (emptyFields()){
                    Toast.makeText(MainActivity.this, "All fields should not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    incorrectInput.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private boolean emptyFields(){
        return etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty();
    }
}