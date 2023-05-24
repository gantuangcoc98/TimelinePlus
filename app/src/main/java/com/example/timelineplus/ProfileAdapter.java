package com.example.timelineplus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private ArrayList<Profile> profileList;
    private Context context;

    public ProfileAdapter(Context context, ArrayList<Profile> profileList) {
        this.profileList = profileList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_profile, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile profile = profileList.get(position);

        holder.etSetFirstName.setHint(profile.getSetFirstName());
        holder.etSetLastName.setHint(profile.getSetLastName());
        holder.etSetEmail.setHint(profile.getSetEmail());

        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.emptyFields()) {
                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    String firstName = holder.etSetFirstName.getText().toString();
                    String lastName = holder.etSetLastName.getText().toString();
                    String email = holder.etSetEmail.getText().toString();
                    String password = holder.etSetPassword.getText().toString();

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String currentUserID = currentUser.getUid();

                    User updatedUser = new User(email, password, firstName, lastName);
                    updatedUser.setUserID(currentUserID);

                    DatabaseReference updateUser = FirebaseDatabase.getInstance().getReference("users").child(currentUserID);
                    updateUser.setValue(updatedUser, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if (error == null) { //There is no error
                                System.out.println("Successfully updated the data of user: " + currentUserID);

                                currentUser.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            System.out.println("Successfully updated the new email in Authenticated User");

                                            currentUser.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        System.out.println("Successfully updated the new password in Authenticated User");
                                                        Toast.makeText(context, "New Profile Saved", Toast.LENGTH_SHORT).show();

                                                        // Logout the user in the authenticated user in FireBase
                                                        FirebaseAuth.getInstance().signOut();
                                                        System.out.println("Authenticated user successfully logged out");

                                                        // Run the login page again
                                                        Intent login = new Intent(context, MainActivity.class);
                                                        context.startActivity(login);
                                                    } else {
                                                        System.out.println("Failed to update the new password in Authenticated User");
                                                        Toast.makeText(context, "An error occured, please try again", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        } else {
                                            System.out.println("Failed to update the new email in Authenticated User");
                                        }
                                    }
                                });
                            } else { // There is error
                                System.out.println("Failed to updated the data of user: " + currentUserID);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        private EditText etSetFirstName;
        private EditText etSetLastName;
        private EditText etSetEmail;
        private EditText etSetPassword;
        private Button btnSave;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            etSetFirstName = itemView.findViewById(R.id.etSetFirstName);
            etSetLastName = itemView.findViewById(R.id.etSetLastName);
            etSetEmail = itemView.findViewById(R.id.etSetEmail);
            etSetPassword = itemView.findViewById(R.id.etSetPassword);
            btnSave = itemView.findViewById(R.id.btnSave);
        }

        private boolean emptyFields() {
            return etSetFirstName.getText().toString().isEmpty() || etSetLastName.getText().toString().isEmpty() || etSetEmail.getText().toString().isEmpty() || etSetPassword.getText().toString().isEmpty();
        }
    }
}
