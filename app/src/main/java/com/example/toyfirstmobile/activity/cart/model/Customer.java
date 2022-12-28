package com.example.toyfirstmobile.activity.cart.model;

public class Customer extends User{

    String name;
    String address;
    String contact;

    public Customer(String userName, String password,String name,String address,String contact) {
        super(userName, password);
        this.name=name;
        this.address=address;
        this.contact=contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
