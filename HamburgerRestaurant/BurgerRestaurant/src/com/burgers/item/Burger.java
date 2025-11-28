package com.burgers.item;

import java.util.ArrayList;
import java.util.List;

public class Burger extends Item {
    private List<Item> toppings;
    private int maxToppings;

    public Burger(String name, double price) {
        super(name, "Burger", price);
        this.maxToppings = 3;
        this.toppings = new ArrayList<>();
    }


    protected Burger(String name, double price, int maxToppings) {
        super(name, "Burger", price);
        this.maxToppings = maxToppings;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(String name, double price) {
        if (toppings.size() < maxToppings) {
            toppings.add(new Item(name, "Topping", price) {});
        } else {
            System.out.println("Cannot add topping: Maximum number of toppings reached.");
        }
    }

    @Override
    public double getAdjustedPrice() {
        double adjustedPrice = super.getBasePrice();
        for (Item topping : toppings) {
            adjustedPrice += topping.getAdjustedPrice();
        }
        return adjustedPrice;
    }

    @Override
    public void printItem() {
        System.out.printf("%-20s $%5.2f%n", getName() + " Burger", getBasePrice());
        for (Item topping : toppings) {
            System.out.printf(" -> %-15s $%5.2f%n", topping.getName(), topping.getBasePrice());
        }
    }
}