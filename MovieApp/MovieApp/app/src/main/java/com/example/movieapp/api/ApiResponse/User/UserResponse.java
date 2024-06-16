package com.example.movieapp.api.ApiResponse.User;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("_id")
    private String id;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("imageUrl")
    private String image;


    public UserResponse(String id, String email, String phone, String image) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
