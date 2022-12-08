package com.example.toyfirstmobile;

import java.util.Date;

public class Order {

    private int orderID;
    private String userName;

    public Order(){}

    public Order(int orderID, String userName, int toyID, String status, int quantity, Date orderDate) {
        this.orderID = orderID;
        this.userName = userName;
        this.toyID = toyID;
        this.status = status;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    private int toyID;
    private String status;//ordered , cancelled , delivered
    private int quantity;
    private Date orderDate;

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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
