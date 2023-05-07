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
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.signin);
        Button btnLogin = findViewById(R.id.login);

        /*SpannableString span = new SpannableString("Sign In");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(MainActivity.this, Register.class);
                startActivity(signin);
            }
        };

        span.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = findViewById(R.id.signin);
        textView.setText(span);
        textView.setMovementMethod(LinkMovementMethod.getInstance());*/
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(MainActivity.this, Register.class);
                startActivity(signin);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, Home.class);
                startActivity(login);
            }
        });
    }
}