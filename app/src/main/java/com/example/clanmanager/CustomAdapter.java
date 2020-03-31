package com.example.clanmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Member> arrayList;
    private Context content; // needed when bring contents from selected activity

    public CustomAdapter(ArrayList<Member> arrayList, Context content) { // constructor
        this.arrayList = arrayList;
        this.content = content;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //After listview connected to adapter, here, it creates viewholder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rating,parent,false);//When make one column of recyclerview, declare the list items here.
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    //up until here, it creates view for list times.

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) { //practically, it matches each item

        holder.memberNameTv.setText(arrayList.get(position).getMemberName());
        holder.skillNameTv.setText(arrayList.get(position).getSkillName());
        holder.ratingBar.setRating(arrayList.get(position).getRatingBar());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0); //if not null, bring size, otherwise, bring 0
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView memberNameTv;
        TextView skillNameTv;
        RatingBar ratingBar;

        public CustomViewHolder(@NonNull View itemView) { //view holder
            super(itemView);
            this.memberNameTv = itemView.findViewById(R.id.memberNameTv);
            this.skillNameTv = itemView.findViewById(R.id.skillNameTv);
            this.ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
