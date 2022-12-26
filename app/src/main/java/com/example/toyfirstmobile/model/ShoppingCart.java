package com.example.toyfirstmobile.model;


import java.net.*;
import java.util.*;

// This class is a simple container of shopping cart items.
// It is observable, which means that it notifies any interested
// classes whenever it changes.

public class ShoppingCart
{

    public static int id; // ID of the shopping cart
    public static Vector<ShoppingCartItem> items =new Vector<ShoppingCartItem>();;	// the items in the cart
    public static float total;	// the total item cost so far

    public ShoppingCart()
    {
        //items = new Vector<ShoppingCartItem>();
        total = 0;
    }

// Add a new item and update the total

    public static void addItem(ShoppingCartItem newItem)
    {

// See if there's already an item like this in the cart
        int currIndex = items.indexOf(newItem);

        ShoppingCartEvent event = new ShoppingCartEvent();

        if (currIndex == -1) {
// If the item is new, add it to the cart
            items.addElement(newItem);
            event.item = newItem;
            event.eventType = ShoppingCartEvent.ADDED_ITEM;
        } else {

// If there is a similar item, just add the quantities
            ShoppingCartItem currItem =
                    (ShoppingCartItem)
                            items.elementAt(currIndex);

            currItem.add(newItem);
            event.item = currItem;
            event.eventType = ShoppingCartEvent.CHANGED_ITEM;
        }

        total += newItem.getCost() * newItem.getQuantity();

// Tell the observers what just happened

    }

// Remove item removes an item from the cart. Since it removes
// n items from the cart at a time, if there are more than n items
// in the cart, it just subtracts n from the quantity.

    public static void removeItem(ShoppingCartItem oldItem)
    {
// Find this object in the cart
        int currIndex = items.indexOf(oldItem);
        ShoppingCartEvent event = new ShoppingCartEvent();

        if (currIndex == -1) {
// If it wasn't there, just return, assume everything's okay
            return;
        } else {
            ShoppingCartItem currItem =
                    (ShoppingCartItem)
                            items.elementAt(currIndex);

// If you are trying to subtract more items than are in the cart,
// adjust the amount you want to subtract so it is equal to the
// number of items in the cart.

            if (oldItem.getQuantity() > currItem.getQuantity()) {
                oldItem.setQuantity(currItem.getQuantity());
            }

// Adjust the total
            total -= oldItem.getCost() * oldItem.getQuantity();

            currItem.subtract(oldItem);

            event.item = currItem;
            event.eventType = ShoppingCartEvent.CHANGED_ITEM;

// If the quantity drops to 0, remove the item entirely

            if (currItem.getQuantity() == 0) {
                items.removeElementAt(currIndex);
                event.eventType =
                        ShoppingCartEvent.REMOVED_ITEM;
            }

        }

// Tell everyone what happened


    }

// getItems returns a copy of all the items in the cart

    public ShoppingCartItem[] getItems()
    {
        ShoppingCartItem[] itemArray =
                new ShoppingCartItem[items.size()];

        items.copyInto(itemArray);

        return itemArray;
    }


}