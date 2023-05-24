package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class FragmentYourGroups extends Fragment {
    public FragmentYourGroups() {
        // Required empty public constructor
    }

    private Context context;
    private RecyclerView recyclerViewYourGroups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_your_groups, container, false);

        context = getContext();
        recyclerViewYourGroups = view.findViewById(R.id.recyclerViewYourGroups);
        recyclerViewYourGroups.setLayoutManager(new LinearLayoutManager(context));

        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference currentPerson = FirebaseDatabase.getInstance().getReference("people").child(currentUserID);
        currentPerson.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Person currPerson = snapshot.getValue(Person.class);

                String currName = currPerson.getName();

                DatabaseReference groups = FirebaseDatabase.getInstance().getReference("groups");
                groups.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Group> groupList = new ArrayList<>();

                        for (DataSnapshot group : snapshot.getChildren()) {
                            Group g = group.getValue(Group.class);

                            if (g.getOwner().equals(currName))
                                groupList.add(g);
                        }

                        GroupAdapter groupAdapter = new GroupAdapter(context, groupList);
                        recyclerViewYourGroups.setAdapter(groupAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}