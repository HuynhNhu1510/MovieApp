package com.example.movieapp.model;

public class InfoUser {
  private String id;
  private String name;
  private String email;
  private String phone;
  private String img;
  private String accessToken;
    private static InfoUser instance;

    public static synchronized InfoUser getInstance() {
        if (instance == null) {
            instance = new InfoUser();
        }
        return instance;
    }

    public InfoUser(String id, String name, String email, String phone,String img,String accessToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img = img;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static void setInstance(InfoUser instance) {
        InfoUser.instance = instance;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public InfoUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
