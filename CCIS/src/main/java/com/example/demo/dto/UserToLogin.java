package com.example.demo.dto;

public class UserToLogin {
    public String username;
    public String password;

    public UserToLogin() {

    }

    public UserToLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
