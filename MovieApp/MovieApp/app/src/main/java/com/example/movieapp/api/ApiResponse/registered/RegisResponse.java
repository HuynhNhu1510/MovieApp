package com.example.movieapp.api.ApiResponse.registered;

import com.google.gson.annotations.SerializedName;

public class RegisResponse {

    @SerializedName("success")
    private boolean success;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

//    @SerializedName("accessToken")
//
//    private String accessToken;

    public RegisResponse(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
//        this.accessToken = accessToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
}
