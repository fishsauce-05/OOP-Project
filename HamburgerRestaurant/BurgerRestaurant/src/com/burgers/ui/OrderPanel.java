package com.burgers.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel hiển thị giỏ hàng (Shopping Cart)
 * Sử dụng UIConstants cho tất cả màu sắc, font, và kích thước
 *
 * @author fishsauce-05
 * @version 2.0
 * @since 2025-10-23
 */
public class OrderPanel extends JPanel {
    private JTextArea cartArea;
    private ArrayList<CartItem> cartItems;
    private JLabel totalLabel;
    private double cartTotal = 0;
    private JFrame parentFrame;

    /**
     * Constructor
     */
    public OrderPanel(JFrame parent) {
        this.parentFrame = parent;
        this.cartItems = new ArrayList<>();

        initializeUI();
    }

    /**
     * Khởi tạo giao diện
     */
    private void initializeUI() {
        setLayout(new BorderLayout(UIConstants.PADDING_MEDIUM, UIConstants.PADDING_MEDIUM));
        setBorder(UIConstants.createPaddedBorder(UIConstants.PADDING_MEDIUM));

        // Thêm các component
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createCartDisplayPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    /**
     * Tạo panel tiêu đề
     */
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();

        JLabel titleLabel = new JLabel(UIConstants.LABEL_SHOPPING_CART);
        titleLabel.setFont(UIConstants.FONT_SUBTITLE);
        titleLabel.setForeground(UIConstants.TEXT_PRIMARY);

        panel.add(titleLabel);
        return panel;
    }

    /**
     * Tạo panel hiển thị giỏ hàng
     */
    private JScrollPane createCartDisplayPanel() {
        cartArea = new JTextArea();
        cartArea.setEditable(false);
        cartArea.setFont(UIConstants.FONT_CART);
        cartArea.setBackground(UIConstants.LIGHT_GRAY);
        cartArea.setText(UIConstants.MSG_EMPTY_CART);

        return new JScrollPane(cartArea);
    }

    /**
     * Tạo panel phía dưới (Total + Buttons)
     */
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(
                UIConstants.PADDING_MEDIUM,
                UIConstants.PADDING_MEDIUM
        ));

        // Total label
        panel.add(createTotalPanel(), BorderLayout.NORTH);

        // Buttons
        panel.add(createButtonPanel(), BorderLayout.CENTER);

        return panel;
    }

    /**
     * Tạo panel hiển thị tổng tiền
     */
    private JPanel createTotalPanel() {
        JPanel panel = new JPanel();

        totalLabel = new JLabel(UIConstants.formatTotal(0));
        totalLabel.setFont(UIConstants.FONT_TITLE);
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setForeground(UIConstants.TEXT_SUCCESS);

        panel.add(totalLabel);
        return panel;
    }

    /**
     * Tạo panel chứa các nút bấm
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(
                FlowLayout.CENTER,
                UIConstants.PADDING_MEDIUM,
                UIConstants.PADDING_MEDIUM
        ));

        // Nút Remove Last
        JButton removeButton = createButton(
                UIConstants.BTN_REMOVE_LAST,
                UIConstants.SECONDARY_ORANGE,
                e -> removeLastItem()
        );

        // Nút Clear Cart
        JButton clearButton = createButton(
                UIConstants.BTN_CLEAR_CART,
                UIConstants.PRIMARY_RED,
                e -> clearCart()
        );

        // Nút Checkout
        JButton checkoutButton = createButton(
                UIConstants.BTN_CHECKOUT,
                UIConstants.PRIMARY_GREEN,
                e -> checkout()
        );

        panel.add(removeButton);
        panel.add(clearButton);
        panel.add(checkoutButton);

        return panel;
    }

    /**
     * Tạo nút bấm với style thống nhất
     */
    private JButton createButton(String text, Color backgroundColor, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(UIConstants.TEXT_BLACK);
        button.setFont(UIConstants.FONT_BUTTON);
        button.setPreferredSize(UIConstants.BUTTON_SIZE_LARGE);
        button.setFocusPainted(false);
        button.addActionListener(action);

        return button;
    }

    /**
     * Thêm món vào giỏ hàng
     */
    public void addItemToCart(CartItem item) {
        cartItems.add(item);
        updateCartDisplay();

        // Hiển thị thông báo
        showInfoMessage(
                item.getItemName() + UIConstants.MSG_ADDED_TO_CART,
                UIConstants.DIALOG_ADDED
        );
    }

    /**
     * Cập nhật hiển thị giỏ hàng
     */
    private void updateCartDisplay() {
        if (cartItems.isEmpty()) {
            displayEmptyCart();
            return;
        }

        displayCartItems();
    }

    /**
     * Hiển thị giỏ hàng rỗng
     */
    private void displayEmptyCart() {
        cartArea.setText(UIConstants.MSG_EMPTY_CART);
        totalLabel.setText(UIConstants.formatTotal(0));
    }

    /**
     * Hiển thị danh sách món trong giỏ
     */
    private void displayCartItems() {
        StringBuilder sb = new StringBuilder();

        // Header
        sb.append("========================================\n");
        sb.append("       YOUR ORDER\n");
        sb.append("========================================\n\n");

        // Danh sách món
        cartTotal = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            sb.append(formatCartItemDisplay(i + 1, item));
            cartTotal += item.getPrice();
        }

        cartArea.setText(sb.toString());
        totalLabel.setText(UIConstants.formatTotal(cartTotal));

        // Auto-scroll lên đầu
        cartArea.setCaretPosition(0);
    }

    /**
     * Format hiển thị 1 món trong giỏ hàng
     */
    private String formatCartItemDisplay(int index, CartItem item) {
        StringBuilder sb = new StringBuilder();

        sb.append(index).append(". ");
        sb.append(item.toString());
        sb.append("\n\n");

        return sb.toString();
    }

    /**
     * Xóa món cuối cùng
     */
    private void removeLastItem() {
        if (cartItems.isEmpty()) {
            showWarningMessage(
                    UIConstants.MSG_CART_EMPTY,
                    "Cannot Remove"
            );
            return;
        }

        CartItem removed = cartItems.remove(cartItems.size() - 1);
        updateCartDisplay();

        showInfoMessage(
                removed.getItemName() + UIConstants.MSG_REMOVED_FROM_CART,
                UIConstants.DIALOG_REMOVED
        );
    }

    /**
     * Xóa toàn bộ giỏ hàng
     */
    private void clearCart() {
        if (cartItems.isEmpty()) {
            showWarningMessage(
                    UIConstants.MSG_CART_EMPTY,
                    "Cannot Clear"
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                parentFrame,
                UIConstants.MSG_CONFIRM_CLEAR,
                "Confirm Clear",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            cartItems.clear();
            updateCartDisplay();
        }
    }

    /**
     * Thanh toán
     */
    private void checkout() {
        if (cartItems.isEmpty()) {
            showWarningMessage(
                    UIConstants.MSG_CANNOT_CHECKOUT,
                    "Cannot Checkout"
            );
            return;
        }

        String receipt = generateReceipt();
        showInfoMessage(receipt, UIConstants.DIALOG_CHECKOUT);

        // Xóa giỏ hàng sau khi thanh toán
        cartItems.clear();
        updateCartDisplay();
    }

    /**
     * Tạo hóa đơn
     */
    private String generateReceipt() {
        StringBuilder receipt = new StringBuilder();

        // Header
        receipt.append("========== RECEIPT ==========\n");
        receipt.append("Date: 2025-10-23 13:31:37 UTC\n");
        receipt.append("Customer: fishsauce-05\n");
        receipt.append("=============================\n\n");

        // Danh sách món
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            receipt.append(String.format("%d. %s - %s\n",
                    i + 1,
                    item.getItemName(),
                    UIConstants.formatPrice(item.getPrice())
            ));
        }

        // Footer
        receipt.append("\n-----------------------------\n");
        receipt.append(String.format("TOTAL: %s\n", UIConstants.formatPrice(cartTotal)));
        receipt.append("=============================\n\n");
        receipt.append(UIConstants.MSG_THANK_YOU);

        return receipt.toString();
    }

    /**
     * Hiển thị thông báo info
     */
    private void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(
                parentFrame,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Hiển thị thông báo warning
     */
    private void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(
                parentFrame,
                message,
                title,
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Lấy số lượng món trong giỏ
     */
    public int getCartSize() {
        return cartItems.size();
    }

    /**
     * Lấy tổng tiền
     */
    public double getCartTotal() {
        return cartTotal;
    }

    /**
     * Kiểm tra giỏ hàng có rỗng không
     */
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}