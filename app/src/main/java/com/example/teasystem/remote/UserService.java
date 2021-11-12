package com.example.teasystem.remote;

import com.example.teasystem.model.User;

import java.util.ArrayList;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/")
    Call<ArrayList<User>> getAllUsers();
    @GET("users/{id}")
    Call<User> getUserById(@Path("id") int id);
    @POST("users/save/{accountId}")
    Call<User> createUser(@Body User user,@Path("accountId") int accountId);
    @PUT("users/update/{id}")
    Call<User> updateUser(@Path("id") int id,@Body User user);
    @DELETE("Users/delete/{id}")
    Call<User> deleteUser(@Path("id") int id);
    @POST("login/")
    Call<ResponseBody> login(@Body RequestBody params);
}
