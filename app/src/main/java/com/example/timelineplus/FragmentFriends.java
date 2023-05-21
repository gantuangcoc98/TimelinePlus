package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentFriends extends Fragment {
    public FragmentFriends() {
        // Required empty public constructor
    }

    private Button btnSuggestions;
    private Button btnYourFriends;
    private Context context;
    private RecyclerView recyclerViewFriendRequest;
    private PersonAdapter adapter;
    private String userID; // This is the userID of the current user that is logged in
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);


        // Get the current user's ID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();


        // Initialize the ids of the buttons and the global variables
        btnSuggestions = view.findViewById(R.id.btnSuggestions);
        btnYourFriends = view.findViewById(R.id.btnYourFriends);
        context = getContext();


        // Initialize the recyclerview and the adapters
        recyclerViewFriendRequest = view.findViewById(R.id.recyclerViewFriendRequest);
        recyclerViewFriendRequest.setLayoutManager(new LinearLayoutManager(context));


        // Initialize to display all of the friend request of the current user that is logged in
        DatabaseReference requests = FirebaseDatabase.getInstance().getReference("requests");
        requests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Request> requests = new ArrayList<>();

                for (DataSnapshot personData : snapshot.getChildren()) {
                    if (personData.getKey().equals(userID)) {
                        for (DataSnapshot requestData : personData.getChildren()) {
                            Request request = requestData.getValue(Request.class);
                            requests.add(request);
                        }
                        break;
                    }
                }

                RequestAdapter requestAdapter = new RequestAdapter(context, requests);
                recyclerViewFriendRequest.setAdapter(requestAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /* Implement the functionality of the buttons */

        // Suggestions button implementation
        btnSuggestions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { // This will show all people in the database if the user clicks the Suggestions

                // Replace the current fragment with FragmentSuggestions
                FragmentSuggestions fragmentSuggestions = new FragmentSuggestions();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentSuggestions)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }

        });


        // Your Friends button implementation
        btnYourFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentYourFriends
                FragmentYourFriends fragmentYourFriends = new FragmentYourFriends();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentYourFriends)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });


        return view;
    }

    private boolean alreadyRequested(String userID) {
        final boolean[] flag = new boolean[1];

        DatabaseReference requests = FirebaseDatabase.getInstance().getReference("requests");
        requests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot personData : snapshot.getChildren()) {
                    for (DataSnapshot requestData : personData.getChildren()) {
                        if (requestData.getKey().equals(userID)) {
                            flag[0] = true;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return false;
    }

}