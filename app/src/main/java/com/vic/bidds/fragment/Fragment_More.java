package com.vic.bidds.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.firebase.database.DatabaseReference;
import com.vic.bidds.Model.DataModel;
import com.vic.bidds.SignInActivity;
import com.vic.bidds.TicketScrenActivity;
import com.vic.bidds.ViewDetailsActivity;
import com.vic.bidds.databinding.FragmentMoreBinding;

import java.util.ArrayList;
import java.util.List;

public class Fragment_More extends Fragment implements MaxAdViewAdListener {
    Activity activity;
    List<String> skuList = new ArrayList<>();
    boolean enableAll = false, enablesingleVIP = false, enableFixedVIP = false, enableHtFT = false, enableCorrectScore = false, enableBigWin = false, enableBODVIP = false;
    private TextView message, today, old, heading;
    private String Page="", Subject="";
    private DatabaseReference ref;
    FragmentMoreBinding binding;
    private ArrayList<DataModel> list;
    private ProgressBar progressBar;
    private int count = 0;
    private MaxAdView adView;
    Button mDialogButton;
    TextView okay_text, cancel_text;
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(inflater, container, false);

        activity = getActivity();
        list = new ArrayList<>();

      //  createMrecAd();
        onClickListeners();

        return binding.getRoot();
    }

//    private void createMrecAd() {
//
//        adView = new MaxAdView( "267b9b9fdc9cbc4a", MaxAdFormat.MREC, getActivity() );
//        adView.setListener( this );
//
//        // MREC width and height are 300 and 250 respectively, on phones and tablets
//        int widthPx = AppLovinSdkUtils.dpToPx( getActivity(), 300 );
//        int heightPx = AppLovinSdkUtils.dpToPx( getActivity(), 60 );
//        adView.setGravity(Gravity.BOTTOM);
//        adView.setLayoutParams( new FrameLayout.LayoutParams( widthPx, heightPx ) );
//
//
//        adView.loadAd();
//    }

    private void onClickListeners() {
        binding.ticket.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TicketScrenActivity.class);
            startActivity(intent);
        });

        binding.cvSingleVIP.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
        });


        binding.cvhtftVIP.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
        });
        binding.oldVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = getActivity().getIntent();
//                Page = i.getStringExtra("page");
//                Subject = i.getStringExtra("VIP");
                 intentToActivity("VIP", "correct score","Old");


            }
        });
        binding.cvCorrectScoreVIP.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
        });

        binding.cvBigWinVIP.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
        });


        binding.llAllVIP.setOnClickListener(view -> {
            intentToActivity("VIP", "Draws","Old");
        });

    }






    private void intentToActivity(String sub, String page,String day) {
        Intent intent = new Intent(getActivity(), ViewDetailsActivity.class);
        intent.putExtra("subject", sub);
        intent.putExtra("page", page);
        intent.putExtra("Old", day);
        getActivity().startActivity(intent);
    }


    @Override
    public void onAdExpanded(MaxAd maxAd) {

    }

    @Override
    public void onAdCollapsed(MaxAd maxAd) {

    }

    @Override
    public void onAdLoaded(MaxAd maxAd) {

    }

    @Override
    public void onAdDisplayed(MaxAd maxAd) {

    }

    @Override
    public void onAdHidden(MaxAd maxAd) {

    }

    @Override
    public void onAdClicked(MaxAd maxAd) {

    }

    @Override
    public void onAdLoadFailed(String s, MaxError maxError) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {

    }
}
