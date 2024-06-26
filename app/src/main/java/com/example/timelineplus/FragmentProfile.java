package com.example.timelineplus;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class FragmentProfile extends Fragment {

    public FragmentProfile() {
        // Required empty public constructor
    }

    // Declare the object FirebaseAuth for the Authentication and the buttons
    private FirebaseAuth firebaseAuth;
    private Button btnSettings;
    private Button btnLogout;
    private Button btnSchedule;
    private Button btnFriends;
    private Button btnGroups;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        // Initialize the Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();


        /* Implement the button with their ids */

        // Settings button implementation
        btnSettings = view.findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentSettings
                FragmentSettings fragmentSettings = new FragmentSettings();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentSettings)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });


        // Schedule button implementation
        btnSchedule = view.findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentSchedule
                FragmentSchedule fragmentSchedule = new FragmentSchedule();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentSchedule)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });


        // Friends button implementation
        btnFriends = view.findViewById(R.id.btnFriends);
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentFriends
                FragmentFriends fragmentFriends = new FragmentFriends();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentFriends)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });


        // Groups button implementation
        btnGroups = view.findViewById(R.id.btnGroups);
        btnGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentGroups
                FragmentGroups fragmentGroups = new FragmentGroups();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentGroups)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });

        // Logout button implementation
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() { // If the user clicks the logout button
            @Override
            public void onClick(View view) {
                // Logout the user in the authenticated user in FireBase
                firebaseAuth.signOut();
                System.out.println("Authenticated user successfully logged out");

                // Run the login page again
                Intent login = new Intent(getActivity(), MainActivity.class);
                startActivity(login);
                getActivity().finish();
            }
        });


        return view;
    }
}