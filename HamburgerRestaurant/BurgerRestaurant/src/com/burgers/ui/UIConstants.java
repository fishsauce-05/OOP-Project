package com.burgers.ui;

import java.awt.*;

/**
 * Class chứa các constants cho UI
 * Bao gồm: Colors, Fonts, Sizes, Prices, Limits
 */
public class UIConstants {

    // ==================== COLORS ====================

    // Primary Colors
    public static final Color PRIMARY_ORANGE = new Color(255, 140, 0);      // Header background
    public static final Color PRIMARY_GREEN = new Color(76, 175, 80);       // Success/Add button
    public static final Color PRIMARY_BLUE = new Color(33, 150, 243);       // Drink/Info button
    public static final Color PRIMARY_RED = new Color(244, 67, 54);         // Delete/Clear button

    // Secondary Colors
    public static final Color SECONDARY_ORANGE = new Color(255, 152, 0);    // Warning/Remove button
    public static final Color LIGHT_BLUE = new Color(100, 181, 246);        // Drink border
    public static final Color LIGHT_GRAY = new Color(245, 245, 245);        // Background
    public static final Color DARK_GRAY = new Color(96, 125, 139);          // Text secondary
    public static final Color CREAM = new Color(255, 248, 220);             // Deluxe background

    // Text Colors
    public static final Color TEXT_PRIMARY = new Color(33, 33, 33);         // Main text
    public static final Color TEXT_SUCCESS = new Color(0, 128, 0);          // Total price
    public static final Color TEXT_WHITE = Color.WHITE;
    public static final Color TEXT_BLACK = Color.BLACK;

    // Border Colors
    public static final Color BORDER_LIGHT = Color.LIGHT_GRAY;
    public static final Color BORDER_ORANGE = PRIMARY_ORANGE;
    public static final Color BORDER_BLUE = PRIMARY_BLUE;


    // ==================== FONTS ====================

    // Font Families
    public static final String FONT_FAMILY_MAIN = "Montserrat";
    public static final String FONT_FAMILY_MONO = "Monospaced";

    // Font Styles
    public static final int FONT_BOLD = Font.BOLD;
    public static final int FONT_PLAIN = Font.PLAIN;

    // Font Sizes
    public static final int FONT_SIZE_HUGE = 32;        // Header title
    public static final int FONT_SIZE_LARGE = 28;       // Large title
    public static final int FONT_SIZE_TITLE = 20;       // Section title
    public static final int FONT_SIZE_SUBTITLE = 18;    // Subtitle
    public static final int FONT_SIZE_CATEGORY = 16;    // Category label
    public static final int FONT_SIZE_NORMAL = 14;      // Normal text
    public static final int FONT_SIZE_SMALL = 13;       // Small text
    public static final int FONT_SIZE_MONO = 12;        // Monospace text

    // Predefined Fonts
    public static final Font FONT_HEADER = new Font(FONT_FAMILY_MAIN, FONT_BOLD, FONT_SIZE_HUGE);
    public static final Font FONT_TITLE = new Font(FONT_FAMILY_MAIN, FONT_BOLD, FONT_SIZE_TITLE);
    public static final Font FONT_SUBTITLE = new Font(FONT_FAMILY_MAIN, FONT_BOLD, FONT_SIZE_SUBTITLE);
    public static final Font FONT_CATEGORY = new Font(FONT_FAMILY_MAIN, FONT_BOLD, FONT_SIZE_CATEGORY);
    public static final Font FONT_BUTTON = new Font(FONT_FAMILY_MAIN, FONT_BOLD, FONT_SIZE_NORMAL);
    public static final Font FONT_ITEM_NAME = new Font(FONT_FAMILY_MAIN, FONT_PLAIN, FONT_SIZE_NORMAL);
    public static final Font FONT_MEAL = new Font(FONT_FAMILY_MAIN, FONT_PLAIN, FONT_SIZE_SMALL);
    public static final Font FONT_CART = new Font(FONT_FAMILY_MONO, FONT_PLAIN, FONT_SIZE_MONO);


    // ==================== DIMENSIONS ====================

    // Window Sizes
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 700;

    // Panel Sizes
    public static final int HEADER_HEIGHT = 60;
    public static final int MENU_PANEL_WIDTH = 400;
    public static final int MENU_PANEL_HEIGHT = 600;

    // Component Sizes
    public static final Dimension BUTTON_SIZE_LARGE = new Dimension(130, 40);
    public static final Dimension BUTTON_SIZE_MEDIUM = new Dimension(120, 40);
    public static final Dimension ITEM_PANEL_BURGER = new Dimension(350, 80);
    public static final Dimension ITEM_PANEL_DELUXE = new Dimension(350, 100);
    public static final Dimension ITEM_PANEL_DRINK = new Dimension(350, 70);
    public static final Dimension ITEM_PANEL_MEAL = new Dimension(350, 100);

    // Spacing
    public static final int PADDING_SMALL = 5;
    public static final int PADDING_MEDIUM = 10;
    public static final int PADDING_LARGE = 20;
    public static final int SPACING_SMALL = 10;
    public static final int SPACING_MEDIUM = 20;

    // Border Widths
    public static final int BORDER_THIN = 1;
    public static final int BORDER_THICK = 2;


    // ==================== BUSINESS LOGIC ====================

    // Topping Limits
    public static final int MAX_TOPPINGS_REGULAR = 3;
    public static final int MAX_TOPPINGS_DELUXE = 5;

    // Drink Size Prices
    public static final double PRICE_SIZE_SMALL = 0.00;
    public static final double PRICE_SIZE_MEDIUM = 0.25;
    public static final double PRICE_SIZE_LARGE = 0.50;

    // Topping Prices
    public static final double PRICE_CHEESE = 0.50;
    public static final double PRICE_BACON = 1.00;
    public static final double PRICE_AVOCADO = 1.25;
    public static final double PRICE_LETTUCE = 0.25;
    public static final double PRICE_TOMATO = 0.25;
    public static final double PRICE_PICKLES = 0.25;
    public static final double PRICE_EGG = 1.50;
    public static final double PRICE_ONIONS = 0.25;


    // ==================== TEXT CONSTANTS ====================

    // App Info
    public static final String APP_TITLE = "Burger Restaurant - Order System";
    public static final String APP_NAME = "BURGER RESTAURANT";

    // Tab Names
    public static final String TAB_BURGERS = "BURGERS";
    public static final String TAB_DRINKS = "DRINKS";
    public static final String TAB_MEALS = "MEALS";

    // Category Names
    public static final String CATEGORY_SOFT_DRINKS = "SOFT DRINKS";
    public static final String CATEGORY_FRESH_JUICES = "FRESH JUICES";
    public static final String CATEGORY_OTHERS = "OTHERS";

    // Button Labels
    public static final String BTN_ADD_TO_CART = "Add to Cart";
    public static final String BTN_REMOVE_LAST = "Remove Last";
    public static final String BTN_CLEAR_CART = "Clear Cart";
    public static final String BTN_CHECKOUT = "Checkout";

    // Dialog Titles
    public static final String DIALOG_SELECT_TOPPINGS = "Customize Your Burger";
    public static final String DIALOG_SELECT_TOPPINGS_DELUXE = "Customize Your Deluxe Burger";
    public static final String DIALOG_SELECT_SIZE = "Drink Size";
    public static final String DIALOG_CHECKOUT = "Checkout Complete";
    public static final String DIALOG_ADDED = "Added";
    public static final String DIALOG_REMOVED = "Removed";
    public static final String DIALOG_WARNING = "Too Many Toppings";
    public static final String DIALOG_ERROR = "Invalid Selection";

    // Messages
    public static final String MSG_EMPTY_CART = "Your cart is empty.\nAdd items from the menu!";
    public static final String MSG_ADDED_TO_CART = " added to cart!";
    public static final String MSG_REMOVED_FROM_CART = " removed from cart!";
    public static final String MSG_CART_EMPTY = "Cart is already empty!";
    public static final String MSG_CANNOT_CHECKOUT = "Your cart is empty!\nPlease add items before checkout.";
    public static final String MSG_CONFIRM_CLEAR = "Are you sure you want to clear the entire cart?";
    public static final String MSG_THANK_YOU = "Thank you for your order!";

    // Labels
    public static final String LABEL_SHOPPING_CART = "SHOPPING CART";
    public static final String LABEL_CURRENT_ORDERS = "CURRENT ORDERS";
    public static final String LABEL_TOTAL = "Total: $%.2f";
    public static final String LABEL_SELECT_UP_TO = "Select up to %d toppings:";
    public static final String LABEL_MAX_TOPPINGS_WARNING = "Maximum %d toppings allowed!";

    // Item Type Tags
    public static final String TAG_SPECIAL = "[SPECIAL]";
    public static final String TAG_COMBO = "[COMBO]";


    // ==================== HELPER METHODS ====================

    /**
     * Format giá tiền
     */
    public static String formatPrice(double price) {
        return String.format("$%.2f", price);
    }

    /**
     * Format total label
     */
    public static String formatTotal(double total) {
        return String.format(LABEL_TOTAL, total);
    }

    /**
     * Tạo border có padding
     */
    public static javax.swing.border.Border createPaddedBorder(int padding) {
        return javax.swing.BorderFactory.createEmptyBorder(padding, padding, padding, padding);
    }

    /**
     * Tạo compound border (line + padding)
     */
    public static javax.swing.border.Border createItemBorder(Color lineColor, int lineWidth, int padding) {
        return javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(lineColor, lineWidth),
                createPaddedBorder(padding)
        );
    }

    /**
     * Disable constructor
     */
    private UIConstants() {
        throw new AssertionError("Cannot instantiate constants class");
    }
}