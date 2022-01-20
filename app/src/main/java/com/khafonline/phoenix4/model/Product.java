package com.khafonline.phoenix4.model;

public class Product extends BasicPage{

    private int category_id;
    private  int price;


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



    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

}
