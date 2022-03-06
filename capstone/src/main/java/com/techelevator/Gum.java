package com.techelevator;

public class Gum extends Snack {

    public Gum(String name, double price) {
        super(name, price);
    }
    @Override
    public String getNoise() {
        return "Chew Chew, Yum!";
    }
}
