package com.burgers.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Panel hiển thị header/tiêu đề của ứng dụng
 */
public class HeaderPanel extends JPanel {

    public HeaderPanel() {
        // Sử dụng constants từ UIConstants
        setBackground(UIConstants.PRIMARY_ORANGE);
        setPreferredSize(new Dimension(UIConstants.WINDOW_WIDTH, UIConstants.HEADER_HEIGHT));

        JLabel titleLabel = new JLabel(UIConstants.APP_NAME);
        titleLabel.setFont(UIConstants.FONT_HEADER);
        titleLabel.setForeground(UIConstants.TEXT_WHITE);

        add(titleLabel);
    }
}