package com.example.teasystem.util;

public class GetAccountIdSingleTone {
    int accountId;
    private static final GetAccountIdSingleTone accountInstance=new GetAccountIdSingleTone();
    public static GetAccountIdSingleTone getInstance(){
        return accountInstance;
    }
    private GetAccountIdSingleTone(){
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
