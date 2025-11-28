package com.burgers.item;

public class Drink extends Item {
    private Size size = Size.SMALL;

    public enum Size {
        SMALL(0.0), MEDIUM(0.5), LARGE(1.0);
        private double priceModifier;

        Size(double priceModifier) {
            this.priceModifier = priceModifier;
        }
    }

    public Drink(String name, double price) {
        super(name, "Drink", price);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public double getAdjustedPrice() {
        return getBasePrice() + size.priceModifier;
    }

    @Override
    public void printItem() {
        System.out.printf("%s (%s) %-12s $%5.2f%n", getName(), size, "", getAdjustedPrice());
    }
}