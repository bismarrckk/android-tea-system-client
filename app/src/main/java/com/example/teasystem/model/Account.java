package com.example.teasystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Account {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("contactPerson")
    @Expose
    private String contactPerson;

    @SerializedName("contactPhone")
    @Expose
    private String contactPhone;

    @SerializedName("status")
    @Expose
    private Boolean status;


    public Account() {
    }

    public Account(int id, String name, String contactPerson, String contactPhone, Boolean status) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.status = status;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
