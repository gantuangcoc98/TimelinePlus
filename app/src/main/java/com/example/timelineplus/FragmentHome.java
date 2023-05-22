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

import com.google.firebase.auth.FirebaseAuth;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ScheduleAdapter(context, new ArrayList<>());
        recyclerView.setAdapter(adapter);


        // Initialize the DatabaseReference of Firebase Database from the "schedules" data
        databaseReference = FirebaseDatabase.getInstance().getReference().child("schedules");
        fetchScheduleItems();

        return view;
    }

    // This method will get all the schedule data that is in the Firebase Database created by all users
    private void fetchScheduleItems() {

        // Get first the id of the current user
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ScheduleItem> scheduleItems = new ArrayList<>();

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    if (!userSnapshot.getKey().equals(currentUserID)) {
                        for (DataSnapshot allSchedule : userSnapshot.getChildren()) {
                            for (DataSnapshot scheduleSnapshot : allSchedule.getChildren()) {
                                ScheduleItem scheduleItem = scheduleSnapshot.getValue(ScheduleItem.class);
                                scheduleItems.add(scheduleItem);
                            }
                        }
                    }
                }

                adapter = new ScheduleAdapter(context, scheduleItems);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to read schedules.", error.toException());
            }
        });
    }

}