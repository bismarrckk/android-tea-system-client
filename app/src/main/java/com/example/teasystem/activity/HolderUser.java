package com.example.teasystem.activity;

import android.view.View;
import android.widget.TextView;

import com.example.teasystem.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderUser extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvUsername,tvPhone,tvEmail,tvStatus;
    ItemClickListener itemClickListener;

    public HolderUser(@NonNull View itemView) {

        super(itemView);
        tvUsername=itemView.findViewById(R.id.textViewUsername);
        tvPhone=itemView.findViewById(R.id.textViewUserPhone);
        tvEmail=itemView.findViewById(R.id.textViewUserEmail);
        tvStatus=itemView.findViewById(R.id.textViewUserStatus);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener=ic;
    }

    @Override
    public void onClick(View v) {

    }
}
