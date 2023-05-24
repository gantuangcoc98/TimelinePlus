package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentAddGroup extends Fragment {
    public FragmentAddGroup() {
        // Required empty public constructor
    }

    private EditText etSetGroupName;
    private Context context;
    private Button btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_group, container, false);


        etSetGroupName = view.findViewById(R.id.etSetGroupName);
        context = getContext();


        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference("people").child(currentUserID);
                currentUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot user) {
                        Person p = user.getValue(Person.class);

                        String owner = p.getName();
                        String groupName = etSetGroupName.getText().toString();
                        ArrayList<Person> members = new ArrayList<>();
                        members.add(p);

                        Group group = new Group(groupName, owner, members);

                        DatabaseReference groups = FirebaseDatabase.getInstance().getReference("groups").child(groupName);
                        groups.setValue(group, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error == null) {
                                    System.out.println("Successfully added new data in groups column");
                                    Toast.makeText(context, "New group created", Toast.LENGTH_SHORT).show();

                                    // Replace the current fragment with FragmentGroups
                                    FragmentGroups fragmentGroups = new FragmentGroups();
                                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.homeFrameLayout, fragmentGroups)
                                            .addToBackStack(null) // Add the transaction to the back stack
                                            .commit();
                                } else {
                                    System.out.println("Failed to add new data in groups column");
                                    Toast.makeText(context, "An error occured, please try again later", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }
}