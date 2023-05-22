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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private ArrayList<Request> requests;

    private Context context;

    public RequestAdapter(Context context, ArrayList<Request> requests) {
        this.context = context;
        this.requests = requests;
    }

    @NonNull
    @Override
    public RequestAdapter.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.RequestViewHolder holder, int position) {
        Request request = requests.get(position);
        holder.tvRequestName.setText(request.getName());

        DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference("requests");


        /* Implement the functionality of the buttons */

        // Confirm button implementation
        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Get the name and the ID of the current user that is logged in
                String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference people = FirebaseDatabase.getInstance().getReference("people");
                people.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot personData : snapshot.getChildren()) {
                            if (personData.getKey().equals(currentUserID)) {
                                Person currentPerson = personData.getValue(Person.class);
                                String currentName = currentPerson.getName(); // Now we have finally get the name of the current person that is logged in


                                // Get the name and the ID of the person that sent the request
                                String friendName = request.getName();
                                String friendID = request.getRequestID();


                                // Create a new object of the item Friends
                                Friends friend = new Friends(friendName);
                                friend.setFriendID(friendID);
                                Friends currentFriend = new Friends(currentName);
                                currentFriend.setFriendID(currentUserID);


                                // Use the method addFriend()
                                addFriend(currentUserID, friendID, friend);
                                addFriend(friendID, currentUserID, currentFriend);
                                Toast.makeText(context, "Added " + friendName + " as a friend", Toast.LENGTH_SHORT).show();

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


        // Decline button implementation
        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRequestName;

        private Button btnConfirm;
        private Button btnDecline;

        public RequestViewHolder(@NonNull View view) {
            super(view);
            tvRequestName = view.findViewById(R.id.tvRequestName);
            btnConfirm = view.findViewById(R.id.btnConfirm);
            btnDecline = view.findViewById(R.id.btnDecline);
        }
    }

    private void addFriend(String currentUserID, String friendID, Friends friend) {
        DatabaseReference friends = FirebaseDatabase.getInstance().getReference("friends");
        friends.child(currentUserID).child(friendID).setValue(friend, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) { // There is no error
                    System.out.println("Added new data to the friends column for ID: " + currentUserID);

                    deleteOnRequest(currentUserID, friendID);
                } else {
                    System.out.println("Failed adding new data to the friends column for ID: " + currentUserID);
                }
            }
        });
    }

    private void deleteOnRequest(String currentUserID, String friendID) {
        DatabaseReference requests = FirebaseDatabase.getInstance().getReference("requests").child(currentUserID);
        DatabaseReference childToDelete = requests.child(friendID);

        childToDelete.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) { // If successfully removed
                System.out.println("Request data was deleted in userID: " + currentUserID + " from requestID: " + friendID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) { // If failed to remove
                System.out.println("Failed to remove data in userID: " + currentUserID + " from requestID: " + friendID);
            }
        });
    }

}
