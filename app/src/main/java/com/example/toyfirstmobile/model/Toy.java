package com.example.toyfirstmobile.model;

public class Toy {

    private int toyID;
    private String name;
    private int quantity;
    private String category;
    private String image;

    public Toy(){}

    public Toy(int toyID, String name, int quantity, String category, String image) {
        this.toyID = toyID;
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
