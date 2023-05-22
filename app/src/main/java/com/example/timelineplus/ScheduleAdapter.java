package com.example.timelineplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


        // Join button implementation
        holder.joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get first the id of the current user that is logged in
                String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();


                // Get the key of the schedule that is joined in
                String scheduleID = scheduleItem.getScheduleID();


                // Create an instance of JoinedSchedule base from the given ScheduleItem
                JoinedSchedule joinedSchedule = new JoinedSchedule(scheduleItem);


                // Create or update a joined schedule data column in the following current user in database
                DatabaseReference joinedSchedules = FirebaseDatabase.getInstance().getReference("schedules").child(currentUserID);
                joinedSchedules.child("Joined Schedules").child(scheduleID).setValue(joinedSchedule, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) { // There is no error
                            System.out.println("Successfully added new data to the Joined Schedules column");
                            Toast.makeText(context, "Added " + scheduleItem.getSetScheduleTitle() + " to your schedule", Toast.LENGTH_SHORT).show();
                        } else { // There is error
                            System.out.println("Failed to add new data to the Joined Schedules column");
                            Toast.makeText(context, "An error occured, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
