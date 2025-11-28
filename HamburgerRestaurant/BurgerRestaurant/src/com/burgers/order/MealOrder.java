package com.burgers.order;

import com.burgers.item.*;

public class MealOrder {
    private Burger burger;
    private Drink drink;
    private SideItem sideItem;

    // Default constructor for a regular meal
    public MealOrder() {
        this(new Burger("Regular", 4.00), new Drink("Coke", 1.50), new SideItem("Fries", 2.00));
    }

    // Constructor to create a custom meal
    public MealOrder(Burger burger, Drink drink, SideItem sideItem) {
        this.burger = burger;
        this.drink = drink;
        this.sideItem = sideItem;
    }
    public MealOrder(Burger burger, Drink drink) {}

    public void addBurgerTopping(String name, double price) {
        burger.addTopping(name, price);
    }

    public void setDrinkSize(Drink.Size size) {
        drink.setSize(size);
    }

    public double getTotalPrice() {
        // If it's a Deluxe Burger, its price already includes everything
        if (burger instanceof DeluxeBurger) {
            return burger.getAdjustedPrice();
        }
        return burger.getAdjustedPrice() + drink.getAdjustedPrice() + sideItem.getAdjustedPrice();
    }

    public void printItemizedList() {
        System.out.println("-".repeat(30));
        burger.printItem();
        // Don't print drink and side item prices if it's a Deluxe Burger
        if (!(burger instanceof DeluxeBurger)) {
            drink.printItem();
            sideItem.printItem();
        }
        System.out.println("-".repeat(30));
        System.out.printf("%-20s $%5.2f%n", "TOTAL:", getTotalPrice());
        System.out.println("=".repeat(30));
    }
}