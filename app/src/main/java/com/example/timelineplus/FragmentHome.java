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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    public FragmentHome() {
        // Required empty public constructor
    }

    private Context context;
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // Initialize the global variables
        context = getContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ScheduleAdapter(context, new ArrayList<>());
        recyclerView.setAdapter(adapter);


        // Initialize the DatabaseReference of Firebase Database from the "schedules" data
        databaseReference = FirebaseDatabase.getInstance().getReference("schedules");


        // Initialize a ValueEventListener method to listen for changes in the data at that location in the Firebase Realtime Database.
        databaseReference.addValueEventListener(new ValueEventListener() {

            /* NOTE WHEN USING DATASNAPSHOT, CUSTOM CLASS SHOULD HAVE AN EMPTY DEFAULT CONSTRUCTOR */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ScheduleItem> scheduleItems = new ArrayList<>(); // Crate an arraylist of your specified custom object

                // Traverse to all the resulted data taken from the DataSnapshot
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ScheduleItem scheduleItem = dataSnapshot.getValue(ScheduleItem.class); // Retrieve the value of the result data and convert it into an instance of your specified custom class
                    scheduleItems.add(scheduleItem); // Add the instance of your class to the ArrayList scheduleItems
                }

                // Initialize a new schedule adapter with the following ArrayList scheduleItems
                adapter = new ScheduleAdapter(context, scheduleItems);

                // Set the new initialized adapter to be the adapter of the RecyclerView
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to read schedules.", error.toException());
            }
        });

        return view;
    }

}