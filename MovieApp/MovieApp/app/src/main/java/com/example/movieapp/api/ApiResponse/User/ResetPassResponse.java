package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class ResetPassResponse {
    @SerializedName("message")
    private String message;

    public ResetPassResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
