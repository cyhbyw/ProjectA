package com.packtpub.java7.concurrency.chapter5.recipe01.util;

/**
 * This class stores the data of a Product. It's name and it's price
 */
public class Product {

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
