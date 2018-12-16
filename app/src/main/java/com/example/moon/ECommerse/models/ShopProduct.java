package com.example.moon.ECommerse.models;

import android.graphics.drawable.Drawable;

public class ShopProduct {

    public int id;
    public int image;
    public String title;
    public String price;
    public String details;
    public int count;


    public ShopProduct() {
    }



    public ShopProduct(int id,int image, String title, String price, String details,int count) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.details = details;
        this.id = id;
        this.count = count;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
