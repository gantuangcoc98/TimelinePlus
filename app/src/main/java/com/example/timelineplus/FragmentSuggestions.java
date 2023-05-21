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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentSuggestions extends Fragment {

    public FragmentSuggestions() {
        // Required empty public constructor
    }

    private RecyclerView recyclerViewSuggestions;
    private PersonAdapter adapter;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_suggestions, container, false);


        // Get the id of the current user that is logged in
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // Initialize the global variables
        recyclerViewSuggestions = view.findViewById(R.id.recyclerViewSuggestions);
        recyclerViewSuggestions.setLayoutManager(new LinearLayoutManager(context));
        context = getContext();


        // Initialize the DatabaseReference to display all the people
        DatabaseReference people = FirebaseDatabase.getInstance().getReference("people");
        people.addValueEventListener(new ValueEventListener() {
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
                recyclerViewSuggestions.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }
}