package com.vic.bidds.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vic.bidds.Model.DataModel;
import com.vic.bidds.R;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<DataModel> list;
    Activity activity;

    public RecyclerAdapter(ArrayList<DataModel> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (list.get(position).getDate().equals("empty")){
            holder.date.setVisibility(View.GONE);
        }

        else {
            holder.date.setText(list.get(position).getDate());
        }

        holder.league_name.setText(list.get(position).getLeague_name());
        holder.teamone.setText(list.get(position).getTeam_1());
        holder.teamtwo.setText(list.get(position).getTeam_2());
        holder.odds.setText(list.get(position).getOdds());
        holder.away.setText(list.get(position).getTips_name());


        if (list.get(position).getVs().equals("check")){
            Picasso.get().load(R.drawable.checked).into(holder.VS);
        }
        if (list.get(position).getVs().equals("cross")){
            Picasso.get().load(R.drawable.grup).into(holder.VS);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView teamone, teamtwo, away, league_name,odds, date;
        ImageView VS;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            teamone = itemView.findViewById(R.id.team_one);
            teamtwo = itemView.findViewById(R.id.team_two);
            VS = itemView.findViewById(R.id.vs);
            away = itemView.findViewById(R.id.pro_tips);
            league_name = itemView.findViewById(R.id.league_name);
            odds = itemView.findViewById(R.id.awod);
            date = itemView.findViewById(R.id.date);
        }
    }
}
