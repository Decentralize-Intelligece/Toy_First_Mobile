package com.example.toyfirstmobile.model;// This class contains data for an individual item in a
// shopping cart.

import java.net.URL;

public class ShoppingCartItem
{
    private int id;//holds the shopping cart item id
    private String name;
    private int toyID;
    private int quantity;
    private int shoppingCartID;
    private float cost;

    public ShoppingCartItem( int toyID, int quantity, float cost) {

        this.toyID = toyID;
        this.quantity = quantity;
        this.cost = cost;
    }


    public ShoppingCartItem(String name,int toyID, int quantity, float cost) {

        this.name = name;
        this.toyID = toyID;
        this.quantity = quantity;
        this.cost = cost;
    }

    public ShoppingCartItem(int id, int toyID, int quantity, int shoppingCartID, float cost) {
        this.id = id;
        this.toyID = toyID;
        this.quantity = quantity;
        this.shoppingCartID = shoppingCartID;
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }



    public int getToyID() {
        return toyID;
    }

    public void setToyID(int toyID) {
        this.toyID = toyID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(int shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }



// The add method is a quick method for combining two similar
// items. It doesn't perform any checks to insure that they are
// similar, however. You use this method when adding items to a
// cart, rather than storing two instances of the same item, you
// add the quantities together.

    public void add(ShoppingCartItem otherItem)
    {
        this.quantity = this.quantity + otherItem.quantity;
    }

// The subtract method is similar to the add method, but it
// removes a certain quantity of items.

    public void subtract(ShoppingCartItem otherItem)
    {
        this.quantity = this.quantity - otherItem.quantity;
    }

// You can store items in a hash table if you implement hashCode. It's
// always a good idea to do this.



// The equals method does something a little dirty here, it only
// compares the item names and item costs. Technically, this is
// not the way that equals was intended to work.

    public boolean equals(Object other)
    {
        if (this == other) return true;

        if (!(other instanceof ShoppingCartItem))
            return false;

        ShoppingCartItem otherItem =
                (ShoppingCartItem) other;

        return (toyID == (otherItem.toyID));
    }

// Create a copy of this object

//    public ShoppingCartItem copy()
//    {
//        return new ShoppingCartItem(toyID,
//                quantity,shoppingCartID);
//    }

// Create a printable version of this object

    public String toString()
    {
        return name + " added " + quantity;
    }
}