package com.burgers.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Main GUI class - Giao diện chính của ứng dụng
 */
public class MenuGUI extends JFrame {
    private OrderPanel orderPanel;

    public MenuGUI() {
        // Sử dụng constants
        setTitle(UIConstants.APP_TITLE);
        setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(UIConstants.PADDING_MEDIUM, UIConstants.PADDING_MEDIUM));

        // Thêm Header
        add(new HeaderPanel(), BorderLayout.NORTH);

        // Tạo Order Panel (Cart)
        orderPanel = new OrderPanel(this);

        // Tạo Menu Panel
        MenuPanel menuPanel = new MenuPanel(this, item -> orderPanel.addItemToCart(item));
        add(menuPanel, BorderLayout.WEST);

        // Thêm Order Panel
        add(orderPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MenuGUI());
    }
}