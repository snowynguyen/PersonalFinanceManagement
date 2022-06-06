package src.UI;

import src.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public final class UiHelper {
    private UiHelper() {
    }

    public static void setAppIcon(JFrame frame, String iconResourceName) {
        try {
           frame.setIconImage(ImageIO.read(ClassLoader.getSystemResource(iconResourceName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GridLayout createGridLayout(int rows, int columns) {
        GridLayout gridLayout = new GridLayout(rows, columns);
        gridLayout.setVgap(Constants.GRIDLAYOUT_VERTICAL_GAP);
        gridLayout.setHgap(Constants.GRIDLAYOUT_HORIZONTAL_GAP);
        return gridLayout;
    }

    public static JPanel createPanel(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setBorder(new EmptyBorder(Constants.COMPONENT_PADDING, Constants.COMPONENT_PADDING, Constants.COMPONENT_PADDING, Constants.COMPONENT_PADDING));
        return panel;
    }

    public static JPanel createPanel(LayoutManager layout,
            int topPadding, int leftPadding, int bottomPadding, int rightPadding) {
        JPanel panel = new JPanel(layout);
        panel.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        return panel;
    }

    public static JButton createButton(String title, ActionListener listener) {
        JButton button = new JButton(title);
        button.addActionListener(listener);
        return button;
    }
}
