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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private ArrayList<ScheduleItem> scheduleItems;
    private Context context;

    public ScheduleAdapter(Context context, ArrayList<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
        this.context = context;
    }

    @NonNull
    @Override
    // This will inflate the recycler view rows with the custom layout that we made (item_schedule.xml)
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    // This will assign the values from our Firebase data to the values of the views in our custom layout
    // that we made (item_schedule.xml)
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleItem scheduleItem = scheduleItems.get(position);

        holder.scheduleTitle.setText(scheduleItem.getScheduleTitle());
        holder.setScheduleTitle.setText(scheduleItem.getSetScheduleTitle());
        holder.dateTitle.setText(scheduleItem.getDateTitle());
        holder.setDate.setText(scheduleItem.getSetDate());
        holder.timeTitle.setText(scheduleItem.getTimeTitle());
        holder.setTime.setText(scheduleItem.getSetTime());

        holder.joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Perform the implementation when Join button is clicked

            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView scheduleTitle;
        private TextView setScheduleTitle;
        private TextView dateTitle;
        private TextView setDate;
        private TextView timeTitle;
        private TextView setTime;
        private Button joinButton;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            scheduleTitle = itemView.findViewById(R.id.tvTitle);
            setScheduleTitle = itemView.findViewById(R.id.tvCustomTitle);
            dateTitle = itemView.findViewById(R.id.tvDate);
            setDate = itemView.findViewById(R.id.tvCustomDate);
            timeTitle = itemView.findViewById(R.id.tvTime);
            setTime = itemView.findViewById(R.id.tvCustomTime);
            joinButton = itemView.findViewById(R.id.joinButton);
        }
    }
}
