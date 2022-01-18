package com.khafonline.phoenix4.model;

public class Product {
    private int id;
    private String title;
    private String thumbnail;
    private int category_id;
    private  int price;
    private  int priority;
    private String description;


    public Product(int id, String title, int priority, String thumbnail, int category_id) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.priority = priority;
        this.category_id = category_id;
    }


    public Product() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
