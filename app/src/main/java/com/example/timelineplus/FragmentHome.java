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

import java.lang.reflect.Array;
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


        // Get first the id of the current user that is logged in
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // Initialize the global variables
        context = getContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ScheduleAdapter(context, new ArrayList<>());
        recyclerView.setAdapter(adapter);


        // Get first the id of the joined schedule of the current user and store in a list
        ArrayList<String> joinedScheduleList = new ArrayList<>();
        DatabaseReference joinedSchedules = FirebaseDatabase.getInstance().getReference("schedules").child(currentUserID).child("Joined Schedules");
        joinedSchedules.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot joinedSched : snapshot.getChildren()) {
                    String joinedSchedKey = joinedSched.getKey();
                    joinedScheduleList.add(joinedSchedKey);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Initialize the DatabaseReference of Firebase Database from the "schedules" data
        databaseReference = FirebaseDatabase.getInstance().getReference().child("schedules");

        fetchScheduleItems(joinedScheduleList); // This method will display only those that are not owned by the current user and is also not joined yet

        return view;
    }


    // This method will get all the schedule data that is in the Firebase Database created by all users
    private void fetchScheduleItems(ArrayList<String> joinedScheduleList) {


        // Get first the id of the current user
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // Implement to only display the schedule that is not owned by the current user and is also not joined yet
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ScheduleItem> scheduleItems = new ArrayList<>();

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    if (!userSnapshot.getKey().equals(currentUserID)) {
                        for (DataSnapshot schedType : userSnapshot.getChildren()) {
                            if (schedType.getKey().equals("Own Schedule")) {
                                for (DataSnapshot scheduleSnapshot : schedType.getChildren()) {
                                    if (!joinedScheduleList.contains(scheduleSnapshot.getKey())) {
                                        ScheduleItem scheduleItem = scheduleSnapshot.getValue(ScheduleItem.class);
                                        scheduleItems.add(scheduleItem);
                                    }
                                }
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