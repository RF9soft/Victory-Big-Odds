package com.vic.bidds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vic.bidds.Model.DataModel;
import com.vic.bidds.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ViewDetailsActivity2 extends AppCompatActivity {
    public View view;
    private RecyclerView recyclerView;
    private TextView message, today, old, heading;
    private String Page = "", Subject = "", day = "";
    private DatabaseReference ref;
    private ArrayList<DataModel> list;
    private ProgressBar progressBar;
    private int count = 0;
    private ImageView back;
    private MaxRewardedAd rewardedAd;
    private int retryAttempt;

    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details2);

        message = findViewById(R.id.message_txt);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ref = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        progressBar = findViewById(R.id.progress_bar);
        back = findViewById(R.id.btn_back);
        heading = findViewById(R.id.heading);

        back.setOnClickListener(view -> {
            if (rewardedAd.isReady()) {
                rewardedAd.showAd();
            }
            finish();
        });


        Intent i = getIntent();
        Page = i.getStringExtra("page");
        Subject = i.getStringExtra("subject");
        //  day = i.getStringExtra("Old");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getValues(Subject, Page, "Today");
                heading.setText(Page);
                count++;
            }
        }, 700);


    }

//    private void createNativeAd() {
//      //  FrameLayout nativeAdContainer = findViewById( R.id.native_ad_layout );
//
//        nativeAdLoader = new MaxNativeAdLoader( "YOUR_AD_UNIT_ID", this );
//        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
//        {
//            @Override
//            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
//            {
//                // Clean up any pre-existing native ad to prevent memory leaks.
//                if ( nativeAd != null )
//                {
//                    nativeAdLoader.destroy( nativeAd );
//                }
//
//                // Save ad for cleanup.
//                nativeAd = ad;
//
//                // Add ad view to view.
//                nativeAdContainer.removeAllViews();
//                nativeAdContainer.addView( nativeAdView );
//            }
//
//            @Override
//            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
//            {
//                // We recommend retrying with exponentially higher delays up to a maximum delay
//            }
//
//            @Override
//            public void onNativeAdClicked(final MaxAd ad)
//            {
//                // Optional click callback
//            }
//        } );
//
//        nativeAdLoader.loadAd();
//    }
//    }


    private void getValues(String sub, String page, String day) {
        ref.child(sub).child(page).child(day).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    list.add(ds.getValue(DataModel.class));
                }

                RecyclerAdapter adapter = new RecyclerAdapter(list, ViewDetailsActivity2.this);

                if (adapter.getItemCount() != 0) {
                    progressBar.setVisibility(View.GONE);
                }

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child(sub).child(page).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String mes = snapshot.child("message").getValue(String.class);
                message.setText(mes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}