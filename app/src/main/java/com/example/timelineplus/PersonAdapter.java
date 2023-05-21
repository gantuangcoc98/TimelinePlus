package com.example.timelineplus;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private ArrayList<Person> people;
    private Context context;

    public PersonAdapter(Context context, ArrayList<Person> people) {
        this.people = people;
        this.context = context;
    }

    @NonNull
    @Override
    // This will inflate the recycler view rows with the custom layout that we made (item_friends.xml)
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    // This will assign the values from our Firebase data to the values of the views in our custom layout
    // that we made (item_friends.xml)
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = people.get(position);
        holder.tvName.setText(person.getName());


        // Add Friend button implementation
        holder.btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the userID of the current user that is logged in
                String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();


                // Get first the name of the current user that is logged in
                DatabaseReference peopleRef = FirebaseDatabase.getInstance().getReference("people");
                peopleRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot personData : snapshot.getChildren()) {
                            if (personData.getKey().equals(currentUserID)) {
                                Person currentPerson = personData.getValue(Person.class);
                                String currentName = currentPerson.getName(); // Now we have finally get the name of the current person that is logged in


                                // Get the userID of the requested user
                                String personID = person.getPersonID();


                                // Create new request object that passes the currentName as a parameter for the name of the request
                                Request request = new Request(currentName);
                                request.setUserID(personID);


                                // Run the addFriend method which create a data to the requests table in Datbase
                                addRequest(currentUserID, request);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private Button btnAddFriend;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            btnAddFriend = itemView.findViewById(R.id.btnAddFriend);
        }
    }

    private void addRequest(String currentUser, Request request) {

        // Initialize a DatabaseReference to create or update the requests data
        DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference("requests");
        requestRef.child(request.getUserID()).child(currentUser).setValue(request, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    System.out.println("Successfully added new friend request data to the Firebase Realtime Database");
                    Toast.makeText(context, "Friend Request Sent", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Failed to add data to the request Firebase Realtime Database");
                    Toast.makeText(context, "An error occured, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
