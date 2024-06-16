package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class ResetForgotPassResponse {
    @SerializedName("message")
    private String message;

    public ResetForgotPassResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
