package com.example.sanmarapp.model;

public class UserCredentials {
    private String userEmail;
    private String userPassword;

    public UserCredentials(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
