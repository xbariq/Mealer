package com.example.mealerapp;

import java.io.Serializable;

public class Account implements Serializable {
    private String role;
    private String username;
    private String uid;


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private boolean isActive;

    public Account(){}

    public Account (String role, String username){
        this.role=role;
        this.username=username;
        this.isActive= true;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
