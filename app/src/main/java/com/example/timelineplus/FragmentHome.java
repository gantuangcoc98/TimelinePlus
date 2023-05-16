package com.example.timelineplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    public FragmentHome() {
        // Required empty public constructor
    }

    private ArrayList<ScheduleItem> scheduleItemList;
    private ScheduleItem scheduleItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        scheduleItemList = new ArrayList<>();
        scheduleItem = new ScheduleItem("Schedule Title: ", "Birthday Event", "Date Title: ", "5-16-2023", "Time: ", "4:30 PM - 8:30 PM");
        scheduleItemList.add(scheduleItem);

        ScheduleAdapter adapter = new ScheduleAdapter(getContext(), scheduleItemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}