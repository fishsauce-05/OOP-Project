package com.burgers;

import com.burgers.item.Burger;
import com.burgers.item.DeluxeBurger;
import com.burgers.item.Drink;
import com.burgers.item.SideItem;
import com.burgers.order.MealOrder;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Default Meal ---");
        MealOrder defaultMeal = new MealOrder();
        defaultMeal.addBurgerTopping("Cheese", 0.50);
        defaultMeal.setDrinkSize(Drink.Size.LARGE);
        defaultMeal.printItemizedList();

        System.out.println("\n--- Custom Meal ---");
        Burger customBurger = new Burger("Beef", 5.50);
        Drink customDrink = new Drink("Pepsi", 1.50);
        SideItem customSide = new SideItem("Onion Rings", 2.50);

        MealOrder customMeal = new MealOrder(customBurger, customDrink, customSide);
        customMeal.addBurgerTopping("Bacon", 1.00);
        customMeal.addBurgerTopping("Avocado", 1.25);
        customMeal.addBurgerTopping("Lettuce", 0.25);
        // Try to add 4th topping
        customMeal.addBurgerTopping("Tomato", 0.25);
        customMeal.setDrinkSize(Drink.Size.MEDIUM);
        customMeal.printItemizedList();

        System.out.println("\n--- Deluxe Meal ---");
        DeluxeBurger deluxeBurger = new DeluxeBurger("Deluxe", 10.00);
        // Drink and SideItem are still created but their prices are already included
        MealOrder deluxeMeal = new MealOrder(deluxeBurger, new Drink("Juice", 0), new SideItem("Salad", 0));
        deluxeMeal.addBurgerTopping("Cheese", 0.50);
        deluxeMeal.addBurgerTopping("Bacon", 1.00);
        deluxeMeal.addBurgerTopping("Egg", 1.50);
        deluxeMeal.addBurgerTopping("Pickles", 0.25);
        deluxeMeal.addBurgerTopping("Onions", 0.25);
        deluxeMeal.printItemizedList();
    }
}