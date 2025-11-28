package com.burgers.ui;

/**
 * Đại diện cho 1 món trong giỏ hàng
 */
public class CartItem {
    private String itemType; // "BURGER", "DRINK", "MEAL"
    private String itemName;
    private double price;
    private String details; // Chi tiết (toppings, size, etc.)

    public CartItem(String itemType, String itemName, double price, String details) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.price = price;
        this.details = details;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - $%.2f\n  %s", itemType, itemName, price, details);
    }
}