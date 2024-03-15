package com.vic.bidds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;


import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;

public class ContactUSActivty extends AppCompatActivity implements MaxAdViewAdListener {
    EditText mMessOpenWhatEdit;
    AppCompatTextView email, tel;
    AppCompatImageView wh;
    private AppCompatButton back;
    private MaxAdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_usactivty);
        createMrecAd();
        email = findViewById(R.id.two);
        tel = findViewById(R.id.three);
        back = findViewById(R.id.backID);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "victorybettingtipz@gmai.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        tel.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/victorybettingtip"));
            startActivity(intent);
        });


    }

    private void createMrecAd() {
        adView = new MaxAdView( "fb4af88be5c4086e", MaxAdFormat.MREC, this );
        adView.setListener( this );
        // MREC width and height are 300 and 250 respectively, on phones and tablets
        int widthPx = AppLovinSdkUtils.dpToPx( this, 300 );
        int heightPx = AppLovinSdkUtils.dpToPx( this, 250 );
        adView.setGravity(Gravity.BOTTOM);
        adView.setLayoutParams( new FrameLayout.LayoutParams( widthPx, heightPx ) );
        ViewGroup rootView = findViewById( android.R.id.content );
        rootView.addView( adView );

        adView.loadAd();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

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