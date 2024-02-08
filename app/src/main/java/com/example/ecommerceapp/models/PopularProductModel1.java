package com.example.ecommerceapp.models;

import java.io.Serializable;

public class PopularProductModel1 implements Serializable {

    String name;
    String img_url;
    String rating;
    String price;

    public PopularProductModel1() {
    }

    public PopularProductModel1(String name, String img_url, String rating, String price) {
        this.name = name;
        this.img_url = img_url;
        this.rating = rating;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

