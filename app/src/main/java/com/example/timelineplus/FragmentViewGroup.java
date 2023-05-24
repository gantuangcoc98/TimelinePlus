package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class FragmentViewGroup extends Fragment {

    public FragmentViewGroup() {
        // Required empty public constructor
    }

    private RecyclerView recyclerViewGroupView;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_group, container, false);

        recyclerViewGroupView = view.findViewById(R.id.recyclerViewGroupView);

        return view;
    }
}