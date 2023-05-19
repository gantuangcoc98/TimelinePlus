package com.example.timelineplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.FriendViewHolder> {
    private ArrayList<Person> people;
    private Context context;

    public PersonAdapter(Context context, ArrayList<Person> people) {
        this.people = people;
        this.context = context;
    }

    @NonNull
    @Override
    // This will inflate the recycler view rows with the custom layout that we made (item_friends.xml)
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_person, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    // This will assign the values from our Firebase data to the values of the views in our custom layout
    // that we made (item_friends.xml)
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Person person = people.get(position);

        holder.tvName.setText(person.getName());


        // Confirm button implementation
        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        // Delete button implementation
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private Button btnConfirm;
        private Button btnDelete;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
