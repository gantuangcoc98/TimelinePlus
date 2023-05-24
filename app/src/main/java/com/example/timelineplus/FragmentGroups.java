package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentGroups extends Fragment {

    public FragmentGroups() {
        // Required empty public constructor
    }

    private Context context;
    private Button btnNew;
    private Button btnYourGroups;
    private RecyclerView recyclerViewGroups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        context = getContext();
        btnNew = view.findViewById(R.id.btnNew);
        btnYourGroups = view.findViewById(R.id.btnYourGroups);


        recyclerViewGroups = view.findViewById(R.id.recyclerViewGroups);
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(context));


        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference groups = FirebaseDatabase.getInstance().getReference("groups");
        groups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Group> groupList = new ArrayList<>();

                for (DataSnapshot group : snapshot.getChildren()) {
                    ArrayList<Person> members = new ArrayList<>();
                    Group currGroup = group.getValue(Group.class);

                    members = currGroup.getMembers();

                    for (Person p : members) {
                        if (p.getPersonID().equals(currentUserID))
                            groupList.add(currGroup);
                    }
                }

                GroupAdapter groupAdapter = new GroupAdapter(context, groupList);
                recyclerViewGroups.setAdapter(groupAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnYourGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentYourGroups
                FragmentYourGroups fragmentYourGroups = new FragmentYourGroups();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentYourGroups)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Replace the current fragment with FragmentAddGroup
                FragmentAddGroup fragmentAddGroup = new FragmentAddGroup();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.homeFrameLayout, fragmentAddGroup)
                        .addToBackStack(null) // Add the transaction to the back stack
                        .commit();
            }
        });

        return view;
    }
}