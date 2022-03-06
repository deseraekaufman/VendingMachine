package com.techelevator;

//the purpose of this abstract class is to act as a blueprint for each of our different snack types

public abstract class Snack {

    //instance variables
    private String name;
    private double price;
    private static final int INITIAL_QUANTITY = 5;
    private int quantity=INITIAL_QUANTITY;

    //getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //constructor that takes 2 parameters, name and price
    public Snack(String name, double price) {
        this.name = name;
        this.price = price;
        //this.quantity = quantity;
    }

    //abstract method responsible for the noise the vM makes upon item dispension
    public abstract String getNoise();

}
