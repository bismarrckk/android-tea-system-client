package com.example.teasystem.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teasystem.R;
import com.example.teasystem.model.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterUser extends RecyclerView.Adapter<HolderUser> {
    Context c;
    ArrayList<User> users;

    public AdapterUser(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }

    @NonNull
    @Override
    public HolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_user,null);
        HolderUser holder=new HolderUser(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderUser holder, int position) {
        holder.tvUsername.setText(users.get(position).getFullname());
        holder.tvPhone.setText(users.get(position).getPhone());
        holder.tvEmail.setText(users.get(position).getEmail());
        holder.tvStatus.setText(users.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
