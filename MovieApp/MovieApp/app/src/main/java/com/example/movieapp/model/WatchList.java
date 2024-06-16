package com.example.movieapp.model;

public class WatchList {
    private String _id;
    private String name;
    private String slug;
    private String thumbUrl;
    private int __v;
    private String accountid;

    public WatchList(String name, String slug, String thumbUrl, String accountid) {
        this.name = name;
        this.slug = slug;
        this.thumbUrl = thumbUrl;
        this.accountid = accountid;
    }

    public WatchList() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
}
