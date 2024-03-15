package com.vic.bidds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.vic.bidds.R;
import com.vic.bidds.SignInActivity;

public class Fragment_vip extends Fragment  {
    View view;
    CardView one;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vip, container, false);
        one = view.findViewById(R.id.one);


        onClickListeners();

        return view;
    }
    private void onClickListeners(){
        one.setOnClickListener(view -> {
        Intent intent=new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);
        });



    }
}
