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

public class MemberAdpater extends RecyclerView.Adapter<MemberAdpater.MemberViewHolder> {

    private Context context;
    private ArrayList<Person> personList;

    public MemberAdpater(Context context, ArrayList<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_members, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Person person = personList.get(position);

        holder.tvName.setText(person.getName());

        holder.btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Add Person clicked");
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private Button btnAddPerson;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            btnAddPerson = itemView.findViewById(R.id.btnAdd);
        }
    }
}
