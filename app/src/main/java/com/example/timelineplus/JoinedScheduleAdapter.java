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

public class JoinedScheduleAdapter extends RecyclerView.Adapter<JoinedScheduleAdapter.JoinedScheduleViewHolder> {

    private Context context;
    private ArrayList<JoinedSchedule> scheduleList;

    public JoinedScheduleAdapter(Context context, ArrayList<JoinedSchedule> scheduleList) {
        this.context = context;
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public JoinedScheduleAdapter.JoinedScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_joined_schedule, parent, false);
        return new JoinedScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinedScheduleAdapter.JoinedScheduleViewHolder holder, int position) {
        JoinedSchedule joinedSchedule = scheduleList.get(position);

        holder.scheduleTitle.setText(joinedSchedule.getScheduleItem().getScheduleTitle());
        holder.setScheduleTitle.setText(joinedSchedule.getScheduleItem().getSetScheduleTitle());
        holder.dateTitle.setText(joinedSchedule.getScheduleItem().getDateTitle());
        holder.setDate.setText(joinedSchedule.getScheduleItem().getSetDate());
        holder.timeTitle.setText(joinedSchedule.getScheduleItem().getTimeTitle());
        holder.setTime.setText(joinedSchedule.getScheduleItem().getSetTime());

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class JoinedScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView scheduleTitle;
        private TextView setScheduleTitle;
        private TextView dateTitle;
        private TextView setDate;
        private TextView timeTitle;
        private TextView setTime;
        private Button btnView;

        public JoinedScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            scheduleTitle = itemView.findViewById(R.id.tvTitle);
            setScheduleTitle = itemView.findViewById(R.id.tvCustomTitle);
            dateTitle = itemView.findViewById(R.id.tvDate);
            setDate = itemView.findViewById(R.id.tvCustomDate);
            timeTitle = itemView.findViewById(R.id.tvTime);
            setTime = itemView.findViewById(R.id.tvCustomTime);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }
}
