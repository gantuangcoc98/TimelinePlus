package com.example.timelineplus;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class FragmentSettings extends Fragment {

    public FragmentSettings() {
        // Required empty public constructor
    }

    private Button btnEditProfile;
    private Switch switchDarkMode;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        // Initialize all global variables
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        switchDarkMode = view.findViewById(R.id.switchDarkMode);
        context = getContext();


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


        // Dark Mode switch implementation
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, "Switch is on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Switch is off", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}