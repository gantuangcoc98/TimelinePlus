package com.example.timelineplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private ArrayList<Request> requests;

    private Context context;

    public RequestAdapter(Context context, ArrayList<Request> requests) {
        this.context = context;
        this.requests = requests;
    }

    @NonNull
    @Override
    public RequestAdapter.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.RequestViewHolder holder, int position) {
        Request request = requests.get(position);
        holder.tvRequestName.setText(request.getName());

        DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference("requests");


        /* Implement the functionality of the buttons */

        // Confirm button implementation
        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the userID of the current user that is logged in
                String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // Get the name of the person that sent the request
                String friendName = request.getName();


                // Create or update the friends data in the database
                DatabaseReference friends = FirebaseDatabase.getInstance().getReference("friends");
                friends.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        // Decline button implementation
        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRequestName;

        private Button btnConfirm;
        private Button btnDecline;

        public RequestViewHolder(@NonNull View view) {
            super(view);
            tvRequestName = view.findViewById(R.id.tvRequestName);
            btnConfirm = view.findViewById(R.id.btnConfirm);
            btnDecline = view.findViewById(R.id.btnDecline);
        }
    }
}
