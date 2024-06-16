package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class ForgetResponse {
    @SerializedName("random")
    private int randomcode;
    @SerializedName("code")
    private String code;

     @SerializedName("message")
    private String message;

    public ForgetResponse(int randomcode, String code, String message) {
        this.randomcode = randomcode;
        this.code = code;
        this.message = message;
    }

    public int getRandomcode() {
        return randomcode;
    }

    public void setRandomcode(int randomcode) {
        this.randomcode = randomcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
