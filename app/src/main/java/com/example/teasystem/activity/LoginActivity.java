package com.example.teasystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teasystem.R;
import com.example.teasystem.model.Account;
import com.example.teasystem.model.User;
import com.example.teasystem.remote.APIUtils;
import com.example.teasystem.remote.AccountService;
import com.example.teasystem.remote.UserService;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{
    TextView textViewButton;
    EditText loginEmail,loginPassword;
    Button loginBtn;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewButton=findViewById(R.id.textViewRegister);
        loginEmail=findViewById(R.id.LoginEmailAddress);
        loginPassword=findViewById(R.id.LoginPassword);
        loginBtn=findViewById(R.id.LoginBtn);
        userService=APIUtils.getUserService();

        viewRegistrationForm();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                //validate form
                if(validateLogin(username, password)){
                    //do login
                    doLogin(username, password);
                }
               /* Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                */

            }
        });
    }

    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void doLogin(final String username,final String password){
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("username",username);
        jsonParams.put("password",password);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
        Call<ResponseBody> response=userService.login(body);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                if(rawResponse.code()==200){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else {
                    try {
                        Toast.makeText(LoginActivity.this, "Server Error!"+rawResponse.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "No internet connection!! ", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void viewRegistrationForm() {
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterAccountActivity.class));
            }
        });
    }

}