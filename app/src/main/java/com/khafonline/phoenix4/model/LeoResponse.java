package com.khafonline.phoenix4.model;

import java.util.List;

public class LeoResponse {
    List<Category> categories;
    List<Product> products;
    String result;
    String token;
    Profile profile;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getResult() {
        return result;
    }
}
