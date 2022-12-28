package com.example.toyfirstmobile.activity.cart.model;

import android.graphics.Bitmap;

public class ToyData {
    private int toyID;
    private String name;
    private float toyPrice;
    private int quantity;
    private int category;
    private byte[] image;

    public ToyData(int toyID, String name, float toyPrice, int quantity, int category, byte[] image) {
        this.toyID = toyID;
        this.name = name;
        this.toyPrice = toyPrice;
        this.quantity = quantity;
        this.category = category;
        this.image = image;
    }

    public int getToyID() {
        return toyID;
    }

    public void setToyID(int toyID) {
        this.toyID = toyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getToyPrice() {
        return toyPrice;
    }

    public void setToyPrice(float toyPrice) {
        this.toyPrice = toyPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
