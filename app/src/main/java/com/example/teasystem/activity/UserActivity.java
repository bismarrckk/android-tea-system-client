package com.example.teasystem.activity;

import android.content.Context;
import android.os.Bundle;

import com.example.teasystem.model.User;
import com.example.teasystem.remote.APIUtils;
import com.example.teasystem.remote.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.teasystem.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    RecyclerView rv;
    AdapterUser adapter;
    ArrayList<User> users;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        rv=findViewById(R.id.recyclerUser);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_view_divider));
        rv.addItemDecoration(dividerItemDecoration);
        userService= APIUtils.getUserService();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        retrieveUsers();

    }
    public void retrieveUsers(){
        Call<ArrayList<User>> call=userService.getAllUsers();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(response.isSuccessful()) {
                    users = response.body();
                    if (!(users.size() < 1)) {
                        rv.setAdapter(new AdapterUser(getApplicationContext(),users));
                    }
                }else{
                    try {
                        Toast.makeText(UserActivity.this, "Server error!! "+ response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(UserActivity.this, "No internet connection!! ", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());

            }
        });
    }
}