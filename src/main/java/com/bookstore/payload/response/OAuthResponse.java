package com.bookstore.payload.response;

public class OAuthResponse {
    private String name;
    private String picture;

    public OAuthResponse() {
    }

    public OAuthResponse(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public OAuthResponse(String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
