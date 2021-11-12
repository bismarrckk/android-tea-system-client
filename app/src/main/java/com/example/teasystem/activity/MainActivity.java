package com.example.teasystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teasystem.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView textViewEmail,textViewAlias,textViewDate,textViewUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewEmail=findViewById(R.id.textViewEmail);
        textViewAlias=findViewById(R.id.textViewAlias);
        textViewUsers=findViewById(R.id.textViewUsers);
        textViewDate=findViewById(R.id.textViewDate);
        Date date=new Date();
        SimpleDateFormat formatter= new SimpleDateFormat("E, dd MMM yyyy");
        String strDate = formatter.format(date);

        Bundle extras = getIntent().getExtras();
        String username;
        if(extras != null){
            username = extras.getString("username");
            textViewEmail.setText(username);
        }
        textViewAlias.setText("DreamTeam Farm");
        textViewDate.setText(strDate);
        openUserActivity();
    }

    public void openUserActivity(){
        textViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}