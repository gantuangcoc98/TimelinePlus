package com.example.timelineplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentSettings extends Fragment {

    public FragmentSettings() {
        // Required empty public constructor
    }

    private Button btnEditProfile;
    private Button btnThemes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        // Initialize all global variables
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnThemes = view.findViewById(R.id.btnThemes);


        /* Implement the functionality of the buttons */

        // Edit Profile button implementation
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentEditProfile
                FragmentEditProfile fragmentEditProfile = new FragmentEditProfile();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentEditProfile)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });


        // Themes button implementation
        btnThemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}