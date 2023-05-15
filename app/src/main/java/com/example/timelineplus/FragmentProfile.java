package com.example.timelineplus;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class FragmentProfile extends Fragment {

    public FragmentProfile() {
        // Required empty public constructor
    }

    // Declare the object FirebaseAuth for the Authentication
    private FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize the Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Implement the button functionality
        Button btnLogout = view.findViewById(R.id.btnLogout);
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