package com.burgers.ui;

import com.burgers.item.Burger;
import com.burgers.item.DeluxeBurger;
import com.burgers.item.Drink;
import com.burgers.item.SideItem;
import com.burgers.order.MealOrder;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel hiển thị menu với 3 tabs: Burgers, Drinks, Meals
 * Sử dụng UIConstants cho tất cả màu sắc, font, và kích thước
 *
 * @author fishsauce-05
 * @version 2.0
 * @since 2025-10-23
 */
public class MenuPanel extends JPanel {
    private Consumer<CartItem> onItemAdded;
    private JFrame parentFrame;

    /**
     * Constructor
     */
    public MenuPanel(JFrame parent, Consumer<CartItem> onItemAdded) {
        this.parentFrame = parent;
        this.onItemAdded = onItemAdded;

        initializeUI();
    }

    /**
     * Khởi tạo giao diện
     */
    private void initializeUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(
                UIConstants.MENU_PANEL_WIDTH,
                UIConstants.MENU_PANEL_HEIGHT
        ));
        setBorder(UIConstants.createPaddedBorder(UIConstants.PADDING_MEDIUM));

        // Tạo tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Thêm các tabs
        tabbedPane.addTab(UIConstants.TAB_BURGERS, createBurgerPanel());
        tabbedPane.addTab(UIConstants.TAB_DRINKS, createDrinkPanel());
        tabbedPane.addTab(UIConstants.TAB_MEALS, createMealPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    // ==================== BURGER PANEL ====================

    /**
     * Tạo panel danh sách Burgers
     */
    private JScrollPane createBurgerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(UIConstants.createPaddedBorder(UIConstants.PADDING_MEDIUM));

        // Thêm các burger items
        addBurgerItem(panel, "Classic Burger", "Beef", 5.50);
        addBurgerItem(panel, "Chicken Burger", "Chicken", 6.00);
        addBurgerItem(panel, "Veggie Burger", "Veggie", 5.00);
        addBurgerItem(panel, "Fish Burger", "Fish", 6.50);
        addDeluxeBurgerItem(panel, "Deluxe Burger", 10.00);

        return new JScrollPane(panel);
    }

    /**
     * Thêm một burger item vào panel
     */
    private void addBurgerItem(JPanel panel, String displayName, String burgerType, double price) {
        // Tạo item panel
        JPanel itemPanel = new JPanel(new BorderLayout(
                UIConstants.PADDING_SMALL,
                UIConstants.PADDING_SMALL
        ));
        itemPanel.setBorder(UIConstants.createItemBorder(
                UIConstants.BORDER_LIGHT,
                UIConstants.BORDER_THIN,
                UIConstants.PADDING_MEDIUM
        ));
        itemPanel.setMaximumSize(UIConstants.ITEM_PANEL_BURGER);
        itemPanel.setBackground(UIConstants.TEXT_WHITE);

        // Label tên và giá
        JLabel nameLabel = new JLabel(String.format(
                "<html><b>%s</b><br/>%s</html>",
                displayName,
                UIConstants.formatPrice(price)
        ));
        nameLabel.setFont(UIConstants.FONT_ITEM_NAME);

        // Nút Add
        JButton addButton = createAddButton(UIConstants.PRIMARY_GREEN);

        // Xử lý sự kiện click
        addButton.addActionListener(e -> handleBurgerSelection(displayName, burgerType, price));

        // Thêm vào panel
        itemPanel.add(nameLabel, BorderLayout.WEST);
        itemPanel.add(addButton, BorderLayout.EAST);
        panel.add(itemPanel);
        panel.add(Box.createVerticalStrut(UIConstants.SPACING_SMALL));
    }

    /**
     * Xử lý chọn burger
     */
    private void handleBurgerSelection(String displayName, String burgerType, double price) {
        // Tạo danh sách toppings
        String[] toppings = {
                "Cheese (+" + UIConstants.formatPrice(UIConstants.PRICE_CHEESE) + ")",
                "Bacon (+" + UIConstants.formatPrice(UIConstants.PRICE_BACON) + ")",
                "Avocado (+" + UIConstants.formatPrice(UIConstants.PRICE_AVOCADO) + ")",
                "Lettuce (+" + UIConstants.formatPrice(UIConstants.PRICE_LETTUCE) + ")",
                "Tomato (+" + UIConstants.formatPrice(UIConstants.PRICE_TOMATO) + ")",
                "Pickles (+" + UIConstants.formatPrice(UIConstants.PRICE_PICKLES) + ")"
        };

        // Hiển thị dialog chọn toppings
        java.util.List<String> selectedToppings = showToppingDialog(
                toppings,
                UIConstants.MAX_TOPPINGS_REGULAR,
                UIConstants.DIALOG_SELECT_TOPPINGS
        );

        if (selectedToppings != null) {
            // Tính giá và tạo details
            ToppingResult result = processToppings(selectedToppings, price);

            // Tạo CartItem
            String details = "Type: " + burgerType + result.details;
            CartItem cartItem = new CartItem("BURGER", displayName, result.totalPrice, details);
            onItemAdded.accept(cartItem);
        }
    }

    /**
     * Thêm Deluxe Burger item
     */
    private void addDeluxeBurgerItem(JPanel panel, String displayName, double price) {
        // Tạo item panel với style đặc biệt
        JPanel itemPanel = new JPanel(new BorderLayout(
                UIConstants.PADDING_SMALL,
                UIConstants.PADDING_SMALL
        ));
        itemPanel.setBorder(UIConstants.createItemBorder(
                UIConstants.BORDER_ORANGE,
                UIConstants.BORDER_THICK,
                UIConstants.PADDING_MEDIUM
        ));
        itemPanel.setMaximumSize(UIConstants.ITEM_PANEL_DELUXE);
        itemPanel.setBackground(UIConstants.CREAM);

        // Label với description
        JLabel nameLabel = new JLabel(String.format(
                "<html><b>%s %s</b><br/>%s<br/><i>Up to %d toppings included!</i></html>",
                UIConstants.TAG_SPECIAL,
                displayName,
                UIConstants.formatPrice(price),
                UIConstants.MAX_TOPPINGS_DELUXE
        ));
        nameLabel.setFont(UIConstants.FONT_ITEM_NAME);

        // Nút Add với màu cam
        JButton addButton = createAddButton(UIConstants.PRIMARY_ORANGE);

        // Xử lý sự kiện click
        addButton.addActionListener(e -> handleDeluxeBurgerSelection(displayName, price));

        // Thêm vào panel
        itemPanel.add(nameLabel, BorderLayout.WEST);
        itemPanel.add(addButton, BorderLayout.EAST);
        panel.add(itemPanel);
        panel.add(Box.createVerticalStrut(UIConstants.SPACING_SMALL));
    }

    /**
     * Xử lý chọn deluxe burger
     */
    private void handleDeluxeBurgerSelection(String displayName, double price) {
        // Tạo danh sách toppings cho deluxe
        String[] toppings = {
                "Cheese (+" + UIConstants.formatPrice(UIConstants.PRICE_CHEESE) + ")",
                "Bacon (+" + UIConstants.formatPrice(UIConstants.PRICE_BACON) + ")",
                "Egg (+" + UIConstants.formatPrice(UIConstants.PRICE_EGG) + ")",
                "Pickles (+" + UIConstants.formatPrice(UIConstants.PRICE_PICKLES) + ")",
                "Onions (+" + UIConstants.formatPrice(UIConstants.PRICE_ONIONS) + ")"
        };

        // Hiển thị dialog chọn toppings
        java.util.List<String> selectedToppings = showToppingDialog(
                toppings,
                UIConstants.MAX_TOPPINGS_DELUXE,
                UIConstants.DIALOG_SELECT_TOPPINGS_DELUXE
        );

        if (selectedToppings != null) {
            // Tính giá và tạo details (bao gồm Egg)
            ToppingResult result = processToppingsDeluxe(selectedToppings, price);

            // Tạo CartItem
            String details = "Type: Deluxe" + result.details;
            CartItem cartItem = new CartItem("BURGER", displayName, result.totalPrice, details);
            onItemAdded.accept(cartItem);
        }
    }

    // ==================== DRINK PANEL ====================

    /**
     * Tạo panel danh sách Drinks
     */
    private JScrollPane createDrinkPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(UIConstants.createPaddedBorder(UIConstants.PADDING_MEDIUM));

        // SOFT DRINKS
        addCategoryLabel(panel, UIConstants.CATEGORY_SOFT_DRINKS, UIConstants.PRIMARY_BLUE);
        addDrinkItem(panel, "Coca Cola", 1.50);
        addDrinkItem(panel, "Pepsi", 1.50);
        addDrinkItem(panel, "Sprite", 1.50);
        addDrinkItem(panel, "Fanta Orange", 1.50);
        addDrinkItem(panel, "7Up", 1.50);

        panel.add(Box.createVerticalStrut(UIConstants.SPACING_MEDIUM));

        // FRESH JUICES
        addCategoryLabel(panel, UIConstants.CATEGORY_FRESH_JUICES, UIConstants.SECONDARY_ORANGE);
        addDrinkItem(panel, "Orange Juice", 2.50);
        addDrinkItem(panel, "Apple Juice", 2.50);
        addDrinkItem(panel, "Mango Juice", 2.75);
        addDrinkItem(panel, "Pineapple Juice", 2.75);
        addDrinkItem(panel, "Watermelon Juice", 2.50);

        panel.add(Box.createVerticalStrut(UIConstants.SPACING_MEDIUM));

        // OTHERS
        addCategoryLabel(panel, UIConstants.CATEGORY_OTHERS, UIConstants.DARK_GRAY);
        addDrinkItem(panel, "Iced Tea", 1.75);
        addDrinkItem(panel, "Lemonade", 2.00);
        addDrinkItem(panel, "Mineral Water", 1.00);
        addDrinkItem(panel, "Chocolate Milkshake", 3.50);
        addDrinkItem(panel, "Vanilla Milkshake", 3.50);
        addDrinkItem(panel, "Strawberry Milkshake", 3.50);

        return new JScrollPane(panel);
    }

    /**
     * Thêm category label
     */
    private void addCategoryLabel(JPanel panel, String categoryName, Color color) {
        JLabel label = new JLabel(categoryName);
        label.setFont(UIConstants.FONT_CATEGORY);
        label.setForeground(color);
        panel.add(label);
        panel.add(Box.createVerticalStrut(UIConstants.SPACING_SMALL));
    }

    /**
     * Thêm một drink item vào panel
     */
    private void addDrinkItem(JPanel panel, String drinkName, double price) {
        // Tạo item panel
        JPanel itemPanel = new JPanel(new BorderLayout(
                UIConstants.PADDING_SMALL,
                UIConstants.PADDING_SMALL
        ));
        itemPanel.setBorder(UIConstants.createItemBorder(
                UIConstants.LIGHT_BLUE,
                UIConstants.BORDER_THIN,
                UIConstants.PADDING_MEDIUM
        ));
        itemPanel.setMaximumSize(UIConstants.ITEM_PANEL_DRINK);
        itemPanel.setBackground(UIConstants.TEXT_WHITE);

        // Label tên và giá
        JLabel nameLabel = new JLabel(String.format(
                "<html><b>%s</b><br/>%s</html>",
                drinkName,
                UIConstants.formatPrice(price)
        ));
        nameLabel.setFont(UIConstants.FONT_ITEM_NAME);

        // Nút Add với màu xanh
        JButton addButton = createAddButton(UIConstants.PRIMARY_BLUE);

        // Xử lý sự kiện click
        addButton.addActionListener(e -> handleDrinkSelection(drinkName, price));

        // Thêm vào panel
        itemPanel.add(nameLabel, BorderLayout.WEST);
        itemPanel.add(addButton, BorderLayout.EAST);
        panel.add(itemPanel);
        panel.add(Box.createVerticalStrut(UIConstants.SPACING_SMALL));
    }

    /**
     * Xử lý chọn drink
     */
    private void handleDrinkSelection(String drinkName, double price) {
        // Tạo options cho size
        String[] sizes = {
                "SMALL",
                "MEDIUM (+" + UIConstants.formatPrice(UIConstants.PRICE_SIZE_MEDIUM) + ")",
                "LARGE (+" + UIConstants.formatPrice(UIConstants.PRICE_SIZE_LARGE) + ")"
        };

        // Hiển thị dialog chọn size
        String sizeChoice = (String) JOptionPane.showInputDialog(
                parentFrame,
                "Select Size for " + drinkName + ":",
                UIConstants.DIALOG_SELECT_SIZE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                sizes,
                sizes[1] // Default: MEDIUM
        );

        if (sizeChoice != null) {
            // Xác định size và giá
            DrinkSizeResult result = processDrinkSize(sizeChoice, price);

            // Tạo CartItem
            String details = "Size: " + result.sizeName;
            CartItem cartItem = new CartItem("DRINK", drinkName, result.totalPrice, details);
            onItemAdded.accept(cartItem);
        }
    }

    // ==================== MEAL PANEL ====================

    /**
     * Tạo panel danh sách Meals (Combo)
     */
    private JScrollPane createMealPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(UIConstants.createPaddedBorder(UIConstants.PADDING_MEDIUM));

        // Thêm các combo meals
        addMealItem(panel, "Default Meal", "Regular", 5.00, "Coke", 1.50, "Fries", 2.00);
        addMealItem(panel, "Chicken Meal", "Chicken", 6.00, "Sprite", 1.50, "Fries", 2.00);
        addMealItem(panel, "Deluxe Meal", "Deluxe", 10.00, "Juice", 0, "Salad", 0);

        return new JScrollPane(panel);
    }

    /**
     * Thêm Meal (Combo) item
     */
    private void addMealItem(JPanel panel, String mealName, String burgerType, double burgerPrice,
                             String drinkType, double drinkPrice, String sideType, double sidePrice) {
        // Tạo item panel
        JPanel itemPanel = new JPanel(new BorderLayout(
                UIConstants.PADDING_SMALL,
                UIConstants.PADDING_SMALL
        ));
        itemPanel.setBorder(UIConstants.createItemBorder(
                UIConstants.BORDER_BLUE,
                UIConstants.BORDER_THICK,
                UIConstants.PADDING_MEDIUM
        ));
        itemPanel.setMaximumSize(UIConstants.ITEM_PANEL_MEAL);
        itemPanel.setBackground(UIConstants.TEXT_WHITE);

        // Tính tổng giá combo
        double comboPrice = burgerPrice + drinkPrice + sidePrice;

        // Label với description
        JLabel nameLabel = new JLabel(String.format(
                "<html><b>%s %s</b><br/>%s + %s + %s<br/>%s</html>",
                UIConstants.TAG_COMBO,
                mealName,
                burgerType,
                drinkType,
                sideType,
                UIConstants.formatPrice(comboPrice)
        ));
        nameLabel.setFont(UIConstants.FONT_MEAL);

        // Nút Add với màu xanh
        JButton addButton = createAddButton(UIConstants.PRIMARY_BLUE);

        // Xử lý sự kiện click
        addButton.addActionListener(e -> handleMealSelection(
                mealName, burgerType, burgerPrice, drinkType, drinkPrice, sideType, sidePrice
        ));

        // Thêm vào panel
        itemPanel.add(nameLabel, BorderLayout.WEST);
        itemPanel.add(addButton, BorderLayout.EAST);
        panel.add(itemPanel);
        panel.add(Box.createVerticalStrut(UIConstants.SPACING_SMALL));
    }

    /**
     * Xử lý chọn meal
     */
    private void handleMealSelection(String mealName, String burgerType, double burgerPrice,
                                     String drinkType, double drinkPrice, String sideType, double sidePrice) {
        // Tạo options cho size
        String[] sizes = {
                "SMALL",
                "MEDIUM (+" + UIConstants.formatPrice(UIConstants.PRICE_SIZE_MEDIUM) + ")",
                "LARGE (+" + UIConstants.formatPrice(UIConstants.PRICE_SIZE_LARGE) + ")"
        };

        // Hiển thị dialog chọn size
        String sizeChoice = (String) JOptionPane.showInputDialog(
                parentFrame,
                "Select Drink Size:",
                UIConstants.DIALOG_SELECT_SIZE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                sizes,
                sizes[1] // Default: MEDIUM
        );

        if (sizeChoice != null) {
            // Tính tổng giá
            double comboPrice = burgerPrice + drinkPrice + sidePrice;
            DrinkSizeResult result = processDrinkSize(sizeChoice, comboPrice);

            // Tạo details
            String details = String.format("%s Burger + %s (%s) + %s",
                    burgerType, drinkType, result.sizeName, sideType);

            // Tạo CartItem
            CartItem cartItem = new CartItem("MEAL", mealName, result.totalPrice, details);
            onItemAdded.accept(cartItem);
        }
    }

    // ==================== HELPER METHODS ====================

    /**
     * Tạo nút Add với màu tùy chỉnh
     */
    private JButton createAddButton(Color backgroundColor) {
        JButton button = new JButton(UIConstants.BTN_ADD_TO_CART);
        button.setBackground(backgroundColor);
        button.setForeground(UIConstants.TEXT_BLACK);
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Hiển thị dialog chọn toppings với giới hạn
     */
    private java.util.List<String> showToppingDialog(String[] toppings, int maxToppings, String title) {
        // Tạo JList
        JList<String> toppingList = new JList<>(toppings);
        toppingList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Thêm listener để kiểm tra số lượng
        toppingList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedCount = toppingList.getSelectedIndices().length;
                if (selectedCount > maxToppings) {
                    // Bỏ chọn item thừa
                    int[] indices = toppingList.getSelectedIndices();
                    toppingList.setSelectedIndices(java.util.Arrays.copyOf(indices, maxToppings));

                    // Hiển thị warning
                    JOptionPane.showMessageDialog(
                            parentFrame,
                            String.format(UIConstants.LABEL_MAX_TOPPINGS_WARNING, maxToppings),
                            UIConstants.DIALOG_WARNING,
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        // Tạo panel với label hướng dẫn
        JPanel toppingPanel = new JPanel(new BorderLayout());
        toppingPanel.add(
                new JLabel(String.format(UIConstants.LABEL_SELECT_UP_TO, maxToppings)),
                BorderLayout.NORTH
        );
        toppingPanel.add(new JScrollPane(toppingList), BorderLayout.CENTER);

        // Hiển thị dialog
        int result = JOptionPane.showConfirmDialog(
                parentFrame,
                toppingPanel,
                title,
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            java.util.List<String> selectedToppings = toppingList.getSelectedValuesList();

            // Kiểm tra lại lần cuối
            if (selectedToppings.size() > maxToppings) {
                JOptionPane.showMessageDialog(
                        parentFrame,
                        "You can only select up to " + maxToppings + " toppings!\nPlease try again.",
                        UIConstants.DIALOG_ERROR,
                        JOptionPane.ERROR_MESSAGE
                );
                return null;
            }

            return selectedToppings;
        }

        return null;
    }

    /**
     * Xử lý toppings cho burger thường
     */
    private ToppingResult processToppings(java.util.List<String> selectedToppings, double basePrice) {
        double totalPrice = basePrice;
        StringBuilder details = new StringBuilder();

        if (!selectedToppings.isEmpty()) {
            details.append(" | Toppings: ");
            for (int i = 0; i < selectedToppings.size(); i++) {
                String topping = selectedToppings.get(i);
                if (i > 0) details.append(", ");

                if (topping.contains("Cheese")) {
                    details.append("Cheese");
                    totalPrice += UIConstants.PRICE_CHEESE;
                } else if (topping.contains("Bacon")) {
                    details.append("Bacon");
                    totalPrice += UIConstants.PRICE_BACON;
                } else if (topping.contains("Avocado")) {
                    details.append("Avocado");
                    totalPrice += UIConstants.PRICE_AVOCADO;
                } else if (topping.contains("Lettuce")) {
                    details.append("Lettuce");
                    totalPrice += UIConstants.PRICE_LETTUCE;
                } else if (topping.contains("Tomato")) {
                    details.append("Tomato");
                    totalPrice += UIConstants.PRICE_TOMATO;
                } else if (topping.contains("Pickles")) {
                    details.append("Pickles");
                    totalPrice += UIConstants.PRICE_PICKLES;
                }
            }
        } else {
            details.append(" | No toppings");
        }

        return new ToppingResult(totalPrice, details.toString());
    }

    /**
     * Xử lý toppings cho deluxe burger
     */
    private ToppingResult processToppingsDeluxe(java.util.List<String> selectedToppings, double basePrice) {
        double totalPrice = basePrice;
        StringBuilder details = new StringBuilder();

        if (!selectedToppings.isEmpty()) {
            details.append(" | Toppings: ");
            for (int i = 0; i < selectedToppings.size(); i++) {
                String topping = selectedToppings.get(i);
                if (i > 0) details.append(", ");

                if (topping.contains("Cheese")) {
                    details.append("Cheese");
                    totalPrice += UIConstants.PRICE_CHEESE;
                } else if (topping.contains("Bacon")) {
                    details.append("Bacon");
                    totalPrice += UIConstants.PRICE_BACON;
                } else if (topping.contains("Egg")) {
                    details.append("Egg");
                    totalPrice += UIConstants.PRICE_EGG;
                } else if (topping.contains("Pickles")) {
                    details.append("Pickles");
                    totalPrice += UIConstants.PRICE_PICKLES;
                } else if (topping.contains("Onions")) {
                    details.append("Onions");
                    totalPrice += UIConstants.PRICE_ONIONS;
                }
            }
        } else {
            details.append(" | No extra toppings");
        }

        return new ToppingResult(totalPrice, details.toString());
    }

    /**
     * Xử lý drink size
     */
    private DrinkSizeResult processDrinkSize(String sizeChoice, double basePrice) {
        double totalPrice = basePrice;
        String sizeName = "";

        if (sizeChoice.contains("SMALL")) {
            sizeName = "Small";
            totalPrice += UIConstants.PRICE_SIZE_SMALL;
        } else if (sizeChoice.contains("MEDIUM")) {
            sizeName = "Medium";
            totalPrice += UIConstants.PRICE_SIZE_MEDIUM;
        } else if (sizeChoice.contains("LARGE")) {
            sizeName = "Large";
            totalPrice += UIConstants.PRICE_SIZE_LARGE;
        }

        return new DrinkSizeResult(totalPrice, sizeName);
    }

    // ==================== INNER CLASSES ====================

    /**
     * Class lưu kết quả xử lý toppings
     */
    private static class ToppingResult {
        double totalPrice;
        String details;

        ToppingResult(double totalPrice, String details) {
            this.totalPrice = totalPrice;
            this.details = details;
        }
    }

    /**
     * Class lưu kết quả xử lý drink size
     */
    private static class DrinkSizeResult {
        double totalPrice;
        String sizeName;

        DrinkSizeResult(double totalPrice, String sizeName) {
            this.totalPrice = totalPrice;
            this.sizeName = sizeName;
        }
    }
}