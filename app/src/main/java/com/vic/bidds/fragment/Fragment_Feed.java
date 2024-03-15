package com.vic.bidds.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vic.bidds.AboutusActivity2;
import com.vic.bidds.R;
import com.vic.bidds.ViewDetailsActivity;
import com.vic.bidds.ViewDetailsActivity2;


public class Fragment_Feed extends Fragment   {
    View view;
    CardView one, two, three, four, five, six, livebet;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String value;
    TextView okay_text, cancel_text;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);


        
        one = view.findViewById(R.id.card_one);
        livebet = view.findViewById(R.id.livebetCard);
        two = view.findViewById(R.id.card_two);
        three = view.findViewById(R.id.card_three);
        four = view.findViewById(R.id.card_four);

        five = view.findViewById(R.id.card_contact);


        onClickListeners();

        return view;
    }


    private void onClickListeners() {
        one.setOnClickListener(view -> {
            intentToActivity("Free", "surebet");
        });
        livebet.setOnClickListener(view -> {
            intentToActivity2("Free", "allsports","Today");

        });

        two.setOnClickListener(view -> {
            intentToActivity("Free", "dailyodds");
        });

        three.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AboutusActivity2.class);
            startActivity(intent);
        });

        four.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vic.bidds"));
            startActivity(browserIntent);
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("whatsapp");
        Dialog dialog = new Dialog(getContext());
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setContentView(R.layout.custom_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                okay_text = dialog.findViewById(R.id.okay_button);
                cancel_text = dialog.findViewById(R.id.cancel_button);

                okay_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Agreed", Toast.LENGTH_SHORT).show();
                        String url = "https://api.whatsapp.com/send?phone=" +value;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                cancel_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

            }
        });
        getdata();
    }

    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getActivity(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });}

    private void intentToActivity(String sub, String page) {
        Intent intent = new Intent(getActivity(), ViewDetailsActivity.class);
        intent.putExtra("subject", sub);
        intent.putExtra("page", page);
        getActivity().startActivity(intent);


    }

    private void intentToActivity2(String sub, String page,String day) {
        Intent intent = new Intent(getActivity(), ViewDetailsActivity2.class);
        intent.putExtra("subject", sub);
        intent.putExtra("page", page);
        intent.putExtra("Old", day);
        getActivity().startActivity(intent);
    }



}
