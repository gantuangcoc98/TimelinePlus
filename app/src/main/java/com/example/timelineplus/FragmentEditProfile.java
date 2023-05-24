package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentEditProfile extends Fragment {

    public FragmentEditProfile() {
        // Required empty public constructor
    }

    private RecyclerView recyclerViewEditProfile;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);


        // Initialize the global variables
        recyclerViewEditProfile = view.findViewById(R.id.recyclerViewEditProfile);
        recyclerViewEditProfile.setLayoutManager(new LinearLayoutManager(context));
        context = getContext();


        // Get the id of the current user that is logged in
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // Get the data of the current user
        DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference("users").child(currentUserID);
        currentUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot user) {
                ArrayList<Profile> profileList = new ArrayList<>();

                User currUser = user.getValue(User.class);

                Profile currProfile = new Profile(currUser.getFirstName(), currUser.getLastName(), currUser.getEmail());
                profileList.add(currProfile);

                ProfileAdapter profileAdapter = new ProfileAdapter(context, profileList);
                recyclerViewEditProfile.setAdapter(profileAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}