package com.example.toyfirstmobile.model;

import java.util.Date;

public class Order {

    private int orderID;
    private String userName;
    private int toyID;
    private String status;//ordered , cancelled , delivered
    private String orderDate;

    public String getToyName() {
        return toyName;
    }

    public int getToyPrice() {
        return toyPrice;
    }

    private String toyName;
    private int toyPrice;
    private int quantity;
    public Order(){}

    public Order(int orderID, String userName, int toyID, String status, int quantity, String orderDate) {
        this.orderID = orderID;
        this.userName = userName;
        this.toyID = toyID;
        this.status = status;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public Order(int orderID, String userName, String status, String orderDate) {
        this.orderID = orderID;
        this.userName = userName;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Order(String toyName, int toyPrice, int quantity) {
        this.toyName = toyName;
        this.toyPrice = toyPrice;
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getToyID() {
        return toyID;
    }

    public void setToyID(int toyID) {
        this.toyID = toyID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
