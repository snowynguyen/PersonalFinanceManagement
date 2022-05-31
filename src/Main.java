package src;
import javax.swing.*;

import src.Controller.Controller;
import src.UI.MainFrame;
public class Main {
    

    public static void main(String[] args) {
        Controller vm = new Controller();
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainFrame().setVisible(true);
        });
    }
}
