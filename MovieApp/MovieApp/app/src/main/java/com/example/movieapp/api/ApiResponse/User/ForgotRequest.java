package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class ForgotRequest {
    @SerializedName("email")
    private String email;

    public ForgotRequest (String email) {
        this.email = email;
    }
}
