package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class ResetPassResquest {
    @SerializedName("token")
    private String token;
    @SerializedName("oldPass")
    private String oldPass;
    @SerializedName("newPass")
    private String newPass;

    public ResetPassResquest(String token, String oldPass, String newPass) {
        this.token = token;
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getEmail() {
        return token;
    }

    public void setEmail(String email) {
        this.token = token;
    }

}
