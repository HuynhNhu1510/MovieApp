package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class ChangeInfoRequest {
    @SerializedName("token")
    private String token;
    @SerializedName("newPhone")
    private String Phone;

    public ChangeInfoRequest(String token, String phone) {
        this.token = token;
        Phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
