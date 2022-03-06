package com.techelevator;

public class Candy extends Snack {
    public Candy (String name, double price) {
        super(name, price);
    }

    @Override
    public String getNoise() {
        return "Munch Munch, Yum!";
    }
}
