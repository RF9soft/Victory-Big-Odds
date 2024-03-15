package com.vic.bidds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class SplshActivity extends AppCompatActivity {
    VideoView imgAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splsh);
        imgAnim = (VideoView) findViewById(R.id.animimage);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.vic;
        Uri uri = Uri.parse(uriPath);
        imgAnim.setVideoURI(uri);
        imgAnim.requestFocus();
        imgAnim.start();
        int SPLASH_DISPLAY_LENGTH = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}