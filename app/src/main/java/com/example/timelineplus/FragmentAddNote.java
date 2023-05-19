package com.example.timelineplus;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentAddNote extends Fragment {

    public FragmentAddNote() {
        // Required empty public constructor
    }

    // Declare the components
    private Context context;
    private EditText etAddNote;
    private Button btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);


        // Initialize the declared components with their corresponding ids
        context = getContext();
        etAddNote = view.findViewById(R.id.etAddNote);
        btnSave = view.findViewById(R.id.btnSave);


        /* Implement the functionality of the buttons */

        // Save button implementation
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notes = etAddNote.getText().toString();

                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}