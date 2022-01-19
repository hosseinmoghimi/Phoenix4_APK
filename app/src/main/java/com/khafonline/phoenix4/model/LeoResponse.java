package com.khafonline.phoenix4.model;

import java.util.List;

public class LeoResponse {
    List<Category> categories;
    List<Product> products;
    Product product;
    String result;
    String token;
    Profile profile;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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
