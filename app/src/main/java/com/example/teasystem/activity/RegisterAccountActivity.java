package com.example.teasystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.teasystem.R;
import com.example.teasystem.model.Account;
import com.example.teasystem.model.User;
import com.example.teasystem.remote.APIUtils;
import com.example.teasystem.remote.AccountService;
import com.example.teasystem.remote.UserService;
import com.example.teasystem.util.GetAccountIdSingleTone;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterAccountActivity extends AppCompatActivity {
    EditText accountName,accountContactPerson,accountPhone,editTextFullName,editTextPhone,editTextEmailAddress,editTextPassword,editTextPassword2;
    Button  btnProceed,btnCreateAdmin;
    AccountService accountService;
    UserService userService;
    GetAccountIdSingleTone singleTone=GetAccountIdSingleTone.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        accountName=findViewById(R.id.editTextAccountName);
        accountContactPerson=findViewById(R.id.editTextTextContactPerson);
        accountPhone=findViewById(R.id.editTextAccountPhone);
        btnProceed=findViewById(R.id.btnProceed);
        accountService= APIUtils.getAccountService();
        userService=APIUtils.getUserService();
        btnCreateAdmin=findViewById(R.id.btnCreateAdmin);
        editTextEmailAddress=findViewById(R.id.editTextEmailAddress);
        editTextFullName=findViewById(R.id.editTextFullName);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextPassword2=findViewById(R.id.editTextPassword2);
        editTextPhone=findViewById(R.id.editTextPhone);


        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((accountName.getText().toString().isEmpty())||(accountContactPerson.getText().toString().isEmpty())||
                        (accountPhone.getText().toString().isEmpty())){
                    Toast.makeText(RegisterAccountActivity.this, "All fields must be filled!!", Toast.LENGTH_SHORT).show();
                }
                Account account=new Account();
                account.setName(accountName.getText().toString());
                account.setStatus(false);
                account.setContactPerson(accountContactPerson.getText().toString());
                account.setContactPhone(accountPhone.getText().toString());
                addAccount(account);


            }
        });

        btnCreateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(editTextPassword.getText().toString().equals(editTextPassword2.getText().toString()))){
                    Toast.makeText(RegisterAccountActivity.this, "Passwords do not match!!", Toast.LENGTH_SHORT).show();
                }
                User user=new User();
                user.setFullname(editTextFullName.getText().toString());
                user.setEmail(editTextEmailAddress.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                user.setStatus("true");
                int accId=singleTone.getAccountId();
                if(accId==0){
                    Toast.makeText(RegisterAccountActivity.this, "Please complete step 1 first", Toast.LENGTH_SHORT).show();
                }
                addUser(user,accId);

            }
        });
    }

    public void addAccount(Account acc){
        Call<Account> call=accountService.createAccount(acc);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    accountContactPerson.setText(null);
                    accountName.setText(null);
                    accountPhone.setText(null);
                    int id=response.body().getId();
                    singleTone.setAccountId(id);
                    Toast.makeText(RegisterAccountActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        Toast.makeText(RegisterAccountActivity.this, "Server error!! "+ response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(RegisterAccountActivity.this, "No internet connection!! ", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }
    public void addUser(User user,int id){
        Call<User> call=userService.createUser(user,id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    editTextEmailAddress.setText(null);
                    editTextFullName.setText(null);
                    editTextPhone.setText(null);
                    editTextPassword.setText(null);
                    editTextPassword2.setText(null);
                    Toast.makeText(RegisterAccountActivity.this, "Admin profile created successfully!", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Toast.makeText(RegisterAccountActivity.this, "Server error!! "+response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(RegisterAccountActivity.this, "No internet connection ", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}