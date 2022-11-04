package com.example.mealerapp;

public class Account {
    private String role;
    private String username;
    private String password;

    public Account (String role, String username, String password){
        this.role=role;
        this.username=username;
        this.password= password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
