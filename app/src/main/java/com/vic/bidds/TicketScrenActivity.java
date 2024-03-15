package com.vic.bidds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TicketScrenActivity extends AppCompatActivity {
    private ImageView ticketImageView;
    private DatabaseReference dailyTicketRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_scren);
        ticketImageView = findViewById(R.id.ticketImageView);
        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Initialize Firebase Realtime Database reference
        dailyTicketRef = FirebaseDatabase.getInstance().getReference().child("dailyticket");

        // Read data from the database
        dailyTicketRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String imageUrl = dataSnapshot.getValue(String.class);
                    if (imageUrl != null) {
                        // Use Glide library to load the image from URL into ImageView
                        Glide.with(getApplicationContext())
                                .load(imageUrl)
                                .into(ticketImageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}