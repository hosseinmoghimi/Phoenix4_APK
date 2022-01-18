package com.khafonline.phoenix4.model;

import com.khafonline.phoenix4.repository.CategoryRepository;

public class Category {
    private int id;
    private String title;
    private String thumbnail;
    private int priority;
    private int parent_id;


    public int save(){
        return CategoryRepository.insert(this);
    }
    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
