package com.khafonline.phoenix4.model;

import java.util.List;

public class LeoResponse {
    List<Category> categories;
    List<Product> products;
    String result;

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
