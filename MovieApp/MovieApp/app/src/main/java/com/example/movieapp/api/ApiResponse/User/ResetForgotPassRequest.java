package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class ResetForgotPassRequest {
    @SerializedName("email")
    private String email;
    @SerializedName("newPass")
    private String newPass;

    public ResetForgotPassRequest(String email, String newPass) {
        this.email = email;
        this.newPass = newPass;
    }

    public String getToken() {
        return email;
    }

    public void setToken(String token) {
        this.email = email;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
