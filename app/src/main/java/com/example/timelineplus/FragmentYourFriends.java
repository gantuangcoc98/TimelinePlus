package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class FragmentYourFriends extends Fragment {

    public FragmentYourFriends() {
        // Required empty public constructor
    }

    private Context context;
    private RecyclerView recyclerViewYourFriends;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_friends, container, false);


        // Initialize the global variables
        context = getContext();


        // Initialize the recyclerview
        recyclerViewYourFriends = view.findViewById(R.id.recyclerViewYourFriends);
        recyclerViewYourFriends.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}