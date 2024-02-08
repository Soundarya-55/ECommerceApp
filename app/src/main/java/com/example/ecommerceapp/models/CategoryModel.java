package com.example.ecommerceapp.models;

public class CategoryModel {

    String img_url;
    String name;
    String type;
    String description;

    public CategoryModel() {

    }



    public CategoryModel(String img_url, String name, String type, String description) {
        this.img_url = img_url;
        this.name=name;
        this.type = type;
        this.description = description;


    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }
}
