package com.example.timelineplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewGroupAdapter extends RecyclerView.Adapter<ViewGroupAdapter.ViewGroupViewHolder> {

    private Context context;
    private ArrayList<Group> groupList;

    public ViewGroupAdapter(Context context, ArrayList<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public ViewGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_joined_group, parent, false);
        return new ViewGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewGroupViewHolder holder, int position) {
        Group g = groupList.get(position);

        holder.tvGroupName.setText(g.getGroupName());

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public static class ViewGroupViewHolder extends RecyclerView.ViewHolder {

        private TextView tvGroupName;
        private Button btnAdd;
        private Button btnSave;
        private RecyclerView recyclerViewGroupView;
        private Context context;

        public ViewGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnSave = itemView.findViewById(R.id.btnSave);
            recyclerViewGroupView = itemView.findViewById(R.id.recyclerViewGroupView);
            context = itemView.getContext();
            recyclerViewGroupView.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
