package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
    private RecyclerView recyclerViewFriends;
    private PersonAdapter adapter;
    private DatabaseReference databaseReference;
    private String userID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);


        // Initialize the ids of the buttons
        btnSuggestions = view.findViewById(R.id.btnSuggestions);
        btnYourFriends = view.findViewById(R.id.btnYourFriends);
        context = getContext();


        // Initialize the recyclerview and the adapters
        recyclerViewFriends = view.findViewById(R.id.recyclerViewFriends);
        recyclerViewFriends.setLayoutManager(new LinearLayoutManager(context));
        adapter = new PersonAdapter(context, new ArrayList<>());
        recyclerViewFriends.setAdapter(adapter);


        // Initialize the DatabaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("people");


        // Get the current user's ID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();


        /* Implement the functionality of the buttons */


        // Suggestions button implementation
        btnSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // This will show all people in the database if the user clicks the Suggestions
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Person> people = new ArrayList<>();

                        for (DataSnapshot peopleSnapshot : snapshot.getChildren()) {
                            if (!peopleSnapshot.getKey().equals(userID)) {
                                Person person = peopleSnapshot.getValue(Person.class);
                                people.add(person);
                            }
                        }

                        adapter = new PersonAdapter(context, people);
                        recyclerViewFriends.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Failed to read schedules.", error.toException());
                    }
                });
            }
        });


        // Your Friends button implementation
        btnYourFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new PersonAdapter(context, new ArrayList<>());
                recyclerViewFriends.setAdapter(adapter);
            }
        });


        return view;
    }
}