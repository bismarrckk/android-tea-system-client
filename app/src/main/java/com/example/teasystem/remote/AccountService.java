package com.example.teasystem.remote;

import com.example.teasystem.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountService {
    @GET("accounts/")
    Call<List<Account>> getAccounts();

    @GET("accounts/{id}")
    Call<Account> getAccountById(@Path("id") int id);

    @POST("accounts/save/")
    Call<Account> createAccount(@Body Account account);
    @PUT("accounts/update/{id}")
    Call<Account> updateAccount(@Path("id") int id,@Body Account account);
    @DELETE("accounts/delete/{id}")
    Call<Account> deleteAccount(@Path("id") int id);


}
