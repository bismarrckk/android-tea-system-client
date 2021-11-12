package com.example.teasystem.remote;

public class APIUtils {
    public APIUtils() {
    }
    public static final String API_URL = "http://192.168.1.36:9090/";
    public static AccountService getAccountService(){
        return RetrofitClient.getClient(API_URL).create(AccountService.class);
    }
    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}

