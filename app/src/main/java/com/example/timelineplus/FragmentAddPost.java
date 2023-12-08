package com.example.timelineplus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentAddPost extends Fragment {
    public FragmentAddPost() {
        // Required empty public constructor
    }

    private DatabaseReference databaseReference;
    private EditText setTitle;
    private EditText setStartTime;
    private EditText setEndTime;
    private EditText setDate;
    private EditText setLocation;
    private EditText setEmail;
    private EditText setNotes;
    private Button btnPublish;
    private String currentUserID;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);
        context = getContext();


        // Initialize the values of the global variables
        databaseReference = FirebaseDatabase.getInstance().getReference("schedules");


        // Get the id of the current user that is logged in
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        currentUserID = user.getUid();


        // Get the ids of the EditText and the Buttons
        setTitle = view.findViewById(R.id.addPostTitle);
        setStartTime = view.findViewById(R.id.startTime);
        setEndTime = view.findViewById(R.id.endTime);
        setDate = view.findViewById(R.id.setDate);
        setLocation = view.findViewById(R.id.setLocation);
        setEmail = view.findViewById(R.id.setEmail);
        setNotes = view.findViewById(R.id.setNotes);
        btnPublish = view.findViewById(R.id.btnPublish);


        /* Implement the functionality of the buttons */

        // Publish button implementation
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference people = FirebaseDatabase.getInstance().getReference("people").child(currentUserID);
                people.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Person currentPerson = snapshot.getValue(Person.class);

                        // Convert the initialized ids to String variables
                        String scheduleTitle = setTitle.getText().toString();
                        String date = setDate.getText().toString();
                        String time = setStartTime.getText().toString() + " - " + setEndTime.getText().toString();
                        String location = setLocation.getText().toString();
                        String email = setEmail.getText().toString();
                        String notes = setNotes.getText().toString();

                        // Create a new object of ScheduleItem from the converted string variables
                        ScheduleItem scheduleItem = new ScheduleItem(scheduleTitle, date, location, email, time, notes);
                        String schedulesID = databaseReference.child(currentUserID).push().getKey(); // Create a key under the user's ID
                        scheduleItem.setScheduleID(schedulesID);


                        // Insert the created ScheduleItem data to the database
                        insertScheduleToDatabase(scheduleItem, currentPerson);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }

    // This method will insert a ScheduleItem data with the corresponding user that posted it
    private void insertScheduleToDatabase(ScheduleItem scheduleItem, Person owner) {
        databaseReference.child(currentUserID).child("Own Schedule").child(scheduleItem.getScheduleID()).setValue(scheduleItem, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) { // There is no error
                    System.out.println("Successfully added new data to the schedules column");

                    ArrayList<Person> members = new ArrayList<>();
                    members.add(owner);

                    Group newGroup = new Group(scheduleItem.getSetScheduleTitle(), owner.getName(), members);
                    DatabaseReference groups = FirebaseDatabase.getInstance().getReference("groups").child(scheduleItem.getSetScheduleTitle());
                    groups.setValue(newGroup, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if (error == null) {
                                System.out.println("Successfully added new data to the groups column");
                                Toast.makeText(getContext(), "Schedule published!", Toast.LENGTH_SHORT).show();
                            } else {
                                System.out.println("Failed to add new data to the groups column");
                                Toast.makeText(context, "An error occured, please try again later", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Intent home = new Intent(context, Home.class);
                    startActivity(home);
                } else { // There is error
                    System.out.println("Failed to add new data to the schedules column");
                    Toast.makeText(getContext(), "An error occured, please try again", Toast.LENGTH_SHORT);
                }
            }
        });
    }

}