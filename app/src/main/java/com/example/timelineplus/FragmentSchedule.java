package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FragmentSchedule extends Fragment {

    public FragmentSchedule() {
        // Required empty constructor
    }

    private Button btnJoinSchedule;
    private Button btnYourSchedule;
    private DatabaseReference databaseReference;
    private Context context;
    private RecyclerView recyclerViewSchedule;
    JoinedScheduleAdapter joinedScheduleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);


        // Initialize the ids of the button
        btnJoinSchedule = view.findViewById(R.id.btnJoinedSchedule);
        btnYourSchedule = view.findViewById(R.id.btnYourSchedule);


        // Initialize the DatabaseReference of the Firebase and get the currents userID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("schedules");


        // Initialize the RecyclerView in Schedule
        context = getContext();
        recyclerViewSchedule = view.findViewById(R.id.recyclerViewSchedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(context));


        // Initialize to display all of the schedules
        DatabaseReference allSchedules = FirebaseDatabase.getInstance().getReference("schedules").child(currentUserID);
        allSchedules.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<JoinedSchedule> scheduleList = new ArrayList<>();

                for (DataSnapshot schedType : snapshot.getChildren()) {

                    if (schedType.getKey().equals("Joined Schedules")) {
                        for (DataSnapshot joinedSched : schedType.getChildren()) {
                            JoinedSchedule joinedSchedule = joinedSched.getValue(JoinedSchedule.class);
                            scheduleList.add(joinedSchedule);
                        }
                    } else if (schedType.getKey().equals("Own Schedule")) {
                        for (DataSnapshot ownSched : schedType.getChildren()) {
                            ScheduleItem scheduleItem = ownSched.getValue(ScheduleItem.class);
                            JoinedSchedule ownSchedule = new JoinedSchedule(scheduleItem);
                            scheduleList.add(ownSchedule);
                        }
                    }

                }

                joinedScheduleAdapter = new JoinedScheduleAdapter(context, scheduleList);
                recyclerViewSchedule.setAdapter(joinedScheduleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /* Implement the button with their ids */

        // Joined Schedule button implementation
        btnJoinSchedule.setOnClickListener(new View.OnClickListener() { // This will display only those schedules that are joined by the user
            @Override
            public void onClick(View view) {
                allSchedules.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<JoinedSchedule> scheduleList = new ArrayList<>();

                        for (DataSnapshot schedType : snapshot.getChildren()) {
                            if (schedType.getKey().equals("Joined Schedules")) {
                                for (DataSnapshot joinedSched : schedType.getChildren()) {
                                    JoinedSchedule joinedSchedule = joinedSched.getValue(JoinedSchedule.class);
                                    scheduleList.add(joinedSchedule);
                                }
                            }
                        }

                        joinedScheduleAdapter = new JoinedScheduleAdapter(context, scheduleList);
                        recyclerViewSchedule.setAdapter(joinedScheduleAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        // Your Schedule button implementation
        btnYourSchedule.setOnClickListener(new View.OnClickListener() { // This will display only those schedules that are owned by the user
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<JoinedSchedule> joinedSchedules = new ArrayList<>();

                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            if (userSnapshot.getKey().equals(currentUserID)) {
                                for (DataSnapshot allSchedule : userSnapshot.getChildren()) {
                                    if (allSchedule.getKey().equals("Own Schedule")) {
                                        for (DataSnapshot scheduleSnapshot : allSchedule.getChildren()) {
                                            ScheduleItem scheduleItem = scheduleSnapshot.getValue(ScheduleItem.class);
                                            JoinedSchedule joinedSchedule = new JoinedSchedule(scheduleItem);
                                            joinedSchedules.add(joinedSchedule);
                                        }
                                        break;
                                    }
                                }
                            }
                        }

                        joinedScheduleAdapter = new JoinedScheduleAdapter(context, joinedSchedules);
                        recyclerViewSchedule.setAdapter(joinedScheduleAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Failed to read schedules.", error.toException());
                    }
                });
            }
        });


        return view;
    }

}