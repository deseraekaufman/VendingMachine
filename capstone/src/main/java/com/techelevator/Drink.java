package com.techelevator;

public class Drink extends Snack {


    public Drink(String name, double price) {
        super(name, price);
    }
    @Override
    public String getNoise() {
        return "Glug Glug, Yum!";
    }
}
