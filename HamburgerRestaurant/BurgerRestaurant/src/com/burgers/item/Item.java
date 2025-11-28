package com.burgers.item;

public abstract class Item {
    protected String name;
    protected String type;
    protected double price;

    public Item(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return price;
    }

    // Phương thức này sẽ được các lớp con override để tính giá cuối cùng
    // thể hiện tính đa hình (Polymorphism)
    public double getAdjustedPrice() {
        return price;
    }

    // In thông tin chi tiết của sản phẩm
    public void printItem() {
        System.out.printf("%-20s $%5.2f%n", getName(), getAdjustedPrice());
    }
}