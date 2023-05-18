package com.example.timelineplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentAddPost extends Fragment {

    public FragmentAddPost() {
        // Required empty public constructor
    }

    private EditText setLocation;
    private EditText setEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        setLocation = view.findViewById(R.id.setLocation);
        setEmail = view.findViewById(R.id.setEmail);
        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation.setText("");
            }
        });

        setEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEmail.setText("");
            }
        });

        return view;
    }
}